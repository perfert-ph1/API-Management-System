<template>
  <div class="projectOverview" v-loading="loading">
    <!-- 基本信息 -->
    <div class="projectInfoBox">
      <div class="projectName">
        <span>{{ projectInfo.name }}</span
        ><i class="el-icon-edit" @click="editProjectInfo"></i>
      </div>
      <div class="infoBox">
        <ul>
          <li>项目类型：{{ projectInfo.type }}</li>
          <li>更新时间：{{ projectInfo.updateTime }}</li>
          <li>项目组人数：{{ projectInfo.peopleNum }}人</li>
        </ul>
        <ul>
          <li>API 数量：{{ projectInfo.APINum }}</li>
          <!-- <li>测试用例数量：{{ projectInfo.testNum }}</li> -->
          <li>状态码数量：{{ projectInfo.statusNum }}</li>
          <li>版本：{{ projectInfo.version }}</li>
        </ul>
        <ul>
          <!-- <li>项目文档数量：{{ projectInfo.documentNum }}</li> -->
        </ul>
      </div>
      <!-- api 状态统计图 -->
      <div class="statisticalBox">
        <statistical-chart :statusInfo="statusInfo"></statistical-chart>
        <div class="description" @click="showStatistical">查看 API 统计</div>
      </div>
    </div>
    <!-- 项目动态（操作日志） -->
    <div class="operationLog">
      <div class="title">
        <span>项目动态（最近）</span>
        <el-button type="text"><strong>. . .</strong> 查看更多</el-button>
      </div>
      <el-table :data="operationLogList" style="width: 100%">
        <el-table-column prop="time" label="时间"></el-table-column>
        <el-table-column prop="operation" label="操作"></el-table-column>
        <el-table-column prop="person" label="成员"></el-table-column>
      </el-table>
    </div>
    <!-- 查看 api 统计的弹窗 -->
    <statistical-card ref="popup" :statusInfo="statusInfo"></statistical-card>
    <!-- 编辑项目信息的弹出框 -->
    <new-project-card
      ref="newProjectCard"
      :newProject="false"
      :projectInfo="projectInfo"
      operation="编辑项目"
      @refreshPage="refreshPage"
      ><span slot="title">编辑项目</span></new-project-card
    >
  </div>
</template>

<script>
import statisticalCard from "./components/statisticalCard.vue";
import statisticalChart from "./components/statisticalChart.vue";
import newProjectCard from "@/components/newProjectCard.vue";
import { getTime } from "@/utils/getTime.js";
import { projectTypeList } from "@/common/constant.js";

export default {
  name: "project",
  components: {
    statisticalChart,
    statisticalCard,
    newProjectCard,
  },
  data() {
    return {
      // statusInfo: {
      // published: 6, // 已发布
      // designing: 2, // 设计
      // pending: 1, // 待定
      // developing: 0, // 开发
      // docking: 0, // 对接
      // testing: 0, // 测试
      // completed: 3, // 已完成
      // anomalous: 0, // 异常的
      // maintain: 0, // 维护
      // abandoned: 1, // 废弃
      // },
      loading: true,
      projectInfo: {
        id: this.$route.query.projectId,
        name: this.$route.query.projectName,
        type: "Web",
        version: "1.2.1",
        remarks: "",
        // members: [],
        updateTime: "2020-10-30 08:57:21",
        peopleNum: "7",
        APINum: "10",
        // testNum: "20",
        statusNum: "35",
        // documentNum: "3",
      },
      statusInfo: {
        已发布: 5,
        设计: 2,
        待定: 1,
        开发: 0,
        对接: 3,
        测试: 0,
        已完成: 3,
        异常: 0,
        维护: 0,
        废弃: 1,
      },
      operationLogList: [
        {
          time: "2020-10-30 08:57:21",
          operation: "新增API: 获取登录状态",
          person: "李林飞",
        },
        {
          time: "2020-10-30 08:57:21",
          operation: "修改项目分享设置: 🎁示例项目",
          person: "李俊贤",
        },
        {
          time: "2020-10-30 08:57:21",
          operation: "新增API: 获取登录状态",
          person: "李林飞",
        },
        {
          time: "2020-10-30 08:57:21",
          operation: "新增API: 获取登录状态",
          person: "李林飞",
        },
        {
          time: "2020-10-30 08:57:21",
          operation: "新增API: 获取登录状态",
          person: "李林飞",
        },
      ],
    };
  },
  mounted() {
    this.getProjectInfo();
    this.getAPIStatistic();
  },
  methods: {
    /**
     * 获取项目基本信息
     */
    getProjectInfo() {
      this.loading = true;
      this.$axios
        .get({
          url: "/api_management/project/getProjectSituation",
          params: {
            pid: this.$route.query.projectId,
          },
        })
        .then((res) => {
          console.log(res);
          const data = res.data;
          this.projectInfo = {
            id: data.id,
            name: data.projectName,
            type: projectTypeList[data.projType - 1],
            version: data.version,
            remarks: data.remarks,
            // members: [],
            updateTime: getTime(data.updateTime),
            peopleNum: data.memberNum,
            APINum: data.apiNum,
            // testNum: "20",
            statusNum: data.statusCodeNum,
            // documentNum: "3",
          };
          this.loading = false;
        });
    },
    /**
     * 获取 api 统计
     */
    getAPIStatistic() {
      // todo
    },
    /**
     * 修改项目基本信息（名称、类型等）
     */
    editProjectInfo() {
      this.$refs.newProjectCard.showNewProjectCard();
      this.$refs.newProjectInfo = {
        id: this.projectInfo.id,
        name: this.projectInfo.name,
        type: this.projectInfo.type,
        version: this.projectInfo.version,
        remarks: this.projectInfo.remarks,
      };
    },
    /**
     * 显示 API 统计信息
     */
    showStatistical() {
      this.$refs.popup.showPopup();
    },
    /**
     * 重新获取数据
     */
    refreshPage() {
      this.getProjectInfo();
    },
  },
};
</script>

<style scoped>
.projectInfoBox {
  padding: 40px;
  background-color: white;
  box-shadow: 2px 3px 12px #dbdbdb;
}
.projectName {
  font-size: 20px;
  font-weight: 700;
  color: #333333;
}
.projectName > i {
  margin-left: 10px;
  cursor: pointer;
}
.infoBox {
  height: 90px;
  padding: 30px 0;
}
.infoBox > ul {
  display: inline-block;
  margin: 0 140px 0 0;
  padding: 0;
  vertical-align: top;
  font-size: 13px;
  color: #333333;
}
.infoBox > ul > li {
  height: 30px;
  line-height: 30px;
  list-style-type: none;
}
.statisticalBox {
  display: inline-block;
}
.description {
  height: 50px;
  font-size: 14px;
  color: #333333;
  text-align: center;
  line-height: 50px;
  background-color: #f9f9f9;
  border: 1px solid #d9d9d9;
  border-top: none;
  cursor: pointer;
  user-select: none;
  transition: all 0.2s;
}
.description:hover {
  color: #53a8ff;
}
.operationLog {
  box-sizing: border-box;
  background-color: #ffffff;
  border: 1px solid #d9d9d9;
  padding: 30px;
  margin: 20px;
  box-shadow: 2px 3px 12px #dbdbdb;
}
.title {
  font-size: 18px;
  font-weight: 700;
  color: #333333;
  margin: 0 0 15px 10px;
  display: flex;
  justify-content: space-between;
}
</style>