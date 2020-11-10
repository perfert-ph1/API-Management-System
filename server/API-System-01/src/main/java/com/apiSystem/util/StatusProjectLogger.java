package com.apiSystem.util;

import com.apiSystem.entity.po.ProjectLog;
import com.apiSystem.entity.po.Status;
import com.apiSystem.entity.po.StatusGrp;
import com.apiSystem.entity.po.UserPO;
import com.apiSystem.entity.vo.StatusGrpVo;
import com.apiSystem.entity.vo.StatusVO;
import com.apiSystem.service.api.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 用于记录项目动态的切面（只针对状态码和状态码分组）
 * 对2个service中的add、delete、update方法分别设置切入点表达式
 * 3种方法的命名中必须带有Status或StatusGrp，且每个方法必须带一个String类型的token（不能有其他String类型参数）
 * 要切入方法对应的类需要添加@ProjectAlterLog
 *
 * 一条项目动态记录需要插入：用户名，时间，操作信息，项目id
 * 其中
 *      用户名根据方法中的token查询，时间是当前时间。对于增删改，获取的方式相同
 *      操作信息分为：操作（新增，修改，删除） + 操作对象 + : +  操作数据 （如：新增状态码分组： test）
 *          操作根据方法的增删改获取；操作对象从切入方法的方法名获取；操作数据从切入方法第一个参数获取
 *      项目id
 *      对于Status的add，获取一个Status的gid，根据该gid查询对应项目id
 *      对于StatusGrp的add，直接根据获取其项目id属性
 *
 *      对于Status的update，获取gid，查询分组对应的pid
 *      对于StatusGrpupdate，获取其id值，查询对应的pid
 *
 *      对于delete，
 * 	        Status与其update处理同
 * 	        StatusGrp根据id获取pid
 */
@Component
@Aspect
public class StatusProjectLogger {

    private static final int LOG_ADD = 0;
    private static final int LOG_DELETE = 1;
    private static final int LOG_UPDATE = 2;

    @Autowired
    private LoginTokenService tokenService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private StatusGrpService grpService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectLogService projectLogService;

    @Pointcut("@target(ProjectAlterLog)")
    public void withTargetAnno(){}

    @Pointcut("execution(* com.apiSystem.service.api.*.add*(..))")
    public void addPointcut(){}

    @Pointcut("execution(* com.apiSystem.service.api.*.delete*(..))")
    public void deletePointcut(){}

    @Pointcut("execution(* com.apiSystem.service.api.*.update*(..))")
    public void updatePointcut(){}

    @Around("withTargetAnno() && addPointcut()")
    public Object logAdd(ProceedingJoinPoint pjp){
        return doLog(LOG_ADD, pjp);
    }

    @Around("withTargetAnno() && updatePointcut()")
    public Object logUpdate(ProceedingJoinPoint pjp){
        return doLog(LOG_UPDATE, pjp);
    }

    @Around("withTargetAnno() && deletePointcut()")
    public Object logDelete(ProceedingJoinPoint pjp){
        return doLog(LOG_DELETE, pjp);
    }

    private Object doLog(int type, ProceedingJoinPoint pjp){
        Object returnValue = null;//切入点方法返回值
        String methodName = pjp.getSignature().getName();//切入点方法名
        Object[] args = pjp.getArgs();//方法参数

        //操作信息的3部分
        String op = getOp(type);//操作
        String opTarget = getOpTarget(methodName);//操作对象
        StringBuffer opData = new StringBuffer("");//操作数据

        //插入项目日志需要的3个字段
        String opInfo = null;//操作信息（即op + opTarget + : +  opData）
        Integer projectId = null;//项目id
        String username = null;

        //项目id和操作数据根据操作类型的不同而不同
        //若执行的是删除操作，则应先查询即将被删除的相关信息
        if(type == LOG_DELETE){
            projectId = getOpDataAndProjectId(opData, LOG_DELETE, args);
        }

        //执行切入点方法
        try {
            returnValue = pjp.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //若执行成功
        if ((boolean) returnValue) {
            username = getUserNameInArgs(args);
            //批量修改，不用指定操作数据
            if(type==LOG_UPDATE && methodName.contains("Batch")){
                op = "批量修改";
                projectId = getOpDataAndProjectId(opData, type, args);
                opInfo = op + opTarget;
            }
            else if(type != LOG_DELETE ) {
                projectId = getOpDataAndProjectId(opData, type, args);
                opInfo = op + opTarget + ": " + opData.toString();
            }
            else {
                opInfo = op + opTarget + ": " + opData.toString();
            }

            //增加项目动态记录
            ProjectLog log = new ProjectLog();
            log.setOperationInfo(opInfo);
            log.setPid(projectId);
            log.setUsername(username);
            log.setTime(new Date());
            projectLogService.insert(log);
        }
        return returnValue;
    }

    /**
     * 获取操作数据以及项目id
     */
    private Integer getOpDataAndProjectId(StringBuffer opData, int type, Object[] args){
        Integer projectId = null;
        switch (type){
            case LOG_ADD:
                //第一个参数是List的实现类，说明该方法是批量增加状态码
                if (args[0] instanceof List) {
                    List<StatusVO> statusVOS = (List<StatusVO>) args[0];
                    for (StatusVO vo : statusVOS) {
                        opData.append(vo.getStatusCode() + ",");
                    }
                    opData.deleteCharAt(opData.length()-1);

                    //获取项目id
                    for (Object arg : args) {
                        if(arg instanceof Integer){
                            int gid = (Integer) arg;
                            projectId = grpService.queryProjectIdById(gid);
                        }
                    }
                    return projectId;
                }

                //第一个参数是状态码分组，则该方法是增加状态码
                //项目id直接获取
                if (args[0] instanceof StatusGrp) {
                    opData.append(((StatusGrp) args[0]).getGrpName());
                    projectId = ((StatusGrp)args[0]).getPid();
                    return projectId;
                }
                break;
            case LOG_UPDATE:
                //若是修改状态码
                if (args[0] instanceof Status) {
                    Status status = (Status)args[0];
                    //获取修改后的状态码的字符串
                    opData.append(status.getStatusCode());

                    //获取项目id
                    projectId = grpService.queryProjectIdById(status.getGid());
                    return projectId;
                }

                //若是修改状态码分组
                if (args[0] instanceof StatusGrpVo){
                    StatusGrpVo vo = (StatusGrpVo) args[0];
                    opData.append(vo.getGrpName());
                    projectId = grpService.queryProjectIdById(vo.getId());

                    return projectId;
                }

                //针对批量修改（移动状态码）的情况
                if(args[0] instanceof List){
                    Integer gid = null;
                    for (Object arg : args) {
                        if(arg instanceof Integer){
                            gid = (Integer)arg;
                        }
                    }
                    StatusGrp grp = grpService.queryById(gid);
                    projectId = grp.getPid();
                    return projectId;
                }
                break;
            case LOG_DELETE:
                //状态码分组只有单个删除，状态码只有批量删除
                //若第一个参数是Integer类型，则是删除分组
                if(args[0] instanceof Integer){
                    Integer groupId = (Integer) args[0];
                    StatusGrp grp = grpService.queryById(groupId);
                    opData.append(grp.getGrpName());
                    projectId = grp.getPid();

                    return projectId;
                }

                //若第一个参数是List类型，则是批量删除状态码
                if(args[0] instanceof List){
                    List<Integer> ids = (List<Integer>) args[0];
                    List<Status> statusList = statusService.queryByIds(ids);
                    for (Status status : statusList) {
                        opData.append(status.getStatusCode() + ",");
                    }
                    opData.deleteCharAt(opData.length()-1);
                    projectId = grpService.queryProjectIdById(statusList.get(0).getGid());

                    return projectId;
                }
        }
        return null;
    }

    /**
     * 获取参数中的token，根据改token获取用户名
     */
    private String getUserNameInArgs(Object[] args){
        for (Object arg : args) {
            if(arg instanceof String){
                String token = (String) arg;
                int uid = tokenService.queryUserIdByToken(token);
                UserPO userPO = userService.queryById(uid);
                if(userPO!=null){
                    return userPO.getUsername();
                }
            }
        }
        return null;
    }

    /**
     * 根据方法名获取操作对象
     */
    private String getOpTarget(String methodName){
        if(methodName.contains("StatusGrp")){
            return "状态码分组";
        }

        if(methodName.contains("Status") && !(methodName.contains("StatusGrp"))){
            return "状态码";
        }

        return null;
    }

    /**
     * 获取操作字符串
     */
    private String getOp(int type){
        switch (type){
            case LOG_ADD:
                return "新增";
            case LOG_DELETE:
                return "删除";
            case LOG_UPDATE:
                return "修改";
            default:
                return "fuck";
        }
    }
}
