package com.apiSystem.util;

import com.apiSystem.entity.po.ProjectLog;
import com.apiSystem.entity.po.UserPO;
import com.apiSystem.entity.po.api.Api;
import com.apiSystem.entity.po.api.ApiGrp;
import com.apiSystem.entity.vo.ApiToEdit;
import com.apiSystem.entity.vo.ApiVo;
import com.apiSystem.mapper.ProjectPOMapper;
import com.apiSystem.service.api.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class ApiProjectLogger {

    @Autowired
    private LoginTokenService tokenService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ApiGrpService grpService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectLogService projectLogService;

    @Autowired
    private ProjectService prjService;

    @Pointcut("execution(* com.apiSystem.service.api.ApiService.add*(..)) || execution(* com.apiSystem.service.api.ApiGrpService.add*(..))")
    public void addPointcut(){}

    @Pointcut("execution(* com.apiSystem.service.api.ApiService.delete*(..)) || execution(* com.apiSystem.service.api.ApiGrpService.delete*(..))")
    public void deletePointcut(){}

    @Pointcut("execution(* com.apiSystem.service.api.ApiService.update*(..)) || execution(* com.apiSystem.service.api.ApiGrpService.update*(..)) " +
            "|| execution(* com.apiSystem.service.api.ApiService.move*(..))")
    public void updatePointcut(){}

    @Pointcut("execution(* com.apiSystem.service.api.ApiService.mark*(..))")
    public void markPointcut(){}

    @Around("addPointcut()")
    public Object logAdd(ProceedingJoinPoint pjp){
        return doLog("新增", pjp);
    }

    @Around("updatePointcut()")
    public Object logUpdate(ProceedingJoinPoint pjp){
        return doLog("修改", pjp);
    }

    @Around("deletePointcut()")
    public Object logDelete(ProceedingJoinPoint pjp){
        return doLog("删除", pjp);
    }

    @Around("markPointcut()")
    public Object logMark(ProceedingJoinPoint pjp){
        return doLog("修改API标记", pjp);
    }

    private Object doLog(String type, ProceedingJoinPoint pjp){
        Object rtnValue = null;
        Object[] args;
        String username;
        String methodName;
        String prefix;

        Map<String, Object> pidAndOpData;
        String opData;
        Integer projectId;

        args = pjp.getArgs();
        methodName = pjp.getSignature().getName();
        prefix = type=="修改API标记"? (type + ":") : (type + getOpTarget(methodName) + ":");
        pidAndOpData = getPidAndOpData(type, args, methodName);
        opData = (String) pidAndOpData.get("data");
        projectId = (Integer) pidAndOpData.get("pid");

        try {
            rtnValue = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        username = getUserNameInArgs(args);
        insertLog(prefix + opData, username, projectId);
        prjService.updateTime(projectId);

        return rtnValue;
    }

    private Map<String, Object> getPidAndOpData(String type, Object[] args, String methodName){
        Map<String, Object> map = new HashMap<>();
        String opData = "";
        Integer projectId = null;
        switch (type) {
            case "新增":
                if (args[0] instanceof ApiGrp) {
                    ApiGrp apiGrp = (ApiGrp) args[0];
                    opData = apiGrp.getGrpName();
                    projectId = apiGrp.getPid();
                } else {
                    ApiVo api = (ApiVo) args[0];
                    opData = api.getApiName();
                    projectId = grpService.queryById(api.getGid()).getPid();
                }
                break;
            case "修改":
                //修改api
                if(args[0] instanceof ApiToEdit){
                    ApiToEdit api = (ApiToEdit) args[0];
                    opData = api.getApiName()==null?apiService.queryById(api.getId()).getApiName():api.getApiName();
                    //从api的id查出gid，根据gid查出pid（不能直接从api获取gid，因为修改时不一定有指定gid的值）
                    projectId = grpService.queryById(apiService.queryById(api.getId()).getGid()).getPid();
                }
                //修改api分组
                else if(args[0] instanceof ApiGrp){
                    ApiGrp grp = (ApiGrp) args[0];
                    opData = grp.getGrpName();
                    projectId = grpService.queryById(grp.getId()).getPid();
                }
                //批量移动api
                else{
                    List<Integer> apiIds = (List<Integer>) args[0];
                    projectId = grpService.queryById(apiService.queryById(apiIds.get(0)).getGid()).getPid();
                    for (Integer id : apiIds) {
                        Api api = apiService.queryById(id);
                        opData += (api.getApiName() + ",");
                    }
                    opData = opData.substring(0, opData.length()-1);
                }
                break;
            case "删除":
                if(methodName.contains("Grp")){
                    ApiGrp grp = grpService.queryById((Integer)args[0]);
                    opData = grp.getGrpName();
                    projectId = grp.getPid();
                }
                else if(args[0] instanceof List){
                    List<Integer> ids = (List<Integer>) args[0];
                    projectId = grpService.queryById(apiService.queryById(ids.get(0)).getGid()).getPid();
                    opData = getOpDataInApiIds(ids);
                }
                else{
                    Api api = apiService.queryById((Integer)args[0]);
                    opData = api.getApiName();
                    projectId = grpService.queryById(api.getGid()).getPid();
                }
                break;
            case "修改API标记":
                if(args[0] instanceof List){
                    List<Integer> ids = (List<Integer>) args[0];
                    projectId = grpService.queryById(apiService.queryById(ids.get(0)).getGid()).getPid();
                    opData = getOpDataInApiIds(ids);
                }
                else{
                    Api api = apiService.queryById((Integer)args[0]);
                    opData = api.getApiName();
                    projectId = grpService.queryById(api.getGid()).getPid();
                }
            default:
                System.out.println("shit");
        }

        map.put("pid", projectId);
        map.put("data", opData);
        return map;
    }

    private String getOpDataInApiIds(List<Integer> ids){
        String opData = "";
        for (Integer id : ids) {
            Api api = apiService.queryById(id);
            opData += (api.getApiName() + ",");
        }
        opData = opData.substring(0, opData.length()-1);
        return opData;
    }

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

    private String getOpTarget(String methodName){
        if(methodName.contains("Api") && !methodName.contains("Grp")){
            return  "Api";
        }
        else {
            return  "Api分组";
        }
    }

    private void insertLog(String opInfo, String username, Integer projectId ){
        ProjectLog log = new ProjectLog();
        log.setOperationInfo(opInfo);
        log.setPid(projectId);
        log.setUsername(username);
        log.setTime(new Date());
        projectLogService.insert(log);
    }
}
