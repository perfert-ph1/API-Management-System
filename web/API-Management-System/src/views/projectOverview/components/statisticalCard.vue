<template>
  <el-dialog
    class="dialog"
    title="API 统计"
    :visible.sync="showStatisticalCard"
    width="700px"
  >
    <!-- 状态选择按钮 -->
    <div class="statusBox">
      <el-button
        class="statusButton"
        v-for="item in statusList"
        :key="item.status"
        @click="getAPIStatus(item.status)"
      >
        <div class="statusPoint" :style="{ background: item.color }"></div>
        {{ item.status }}({{ item.number }})
      </el-button>
    </div>
    <!-- 表格 -->
    <el-table
      :data="
        apiList.slice(
          (tableCurrentPage - 1) * tablePageSize,
          tableCurrentPage * tablePageSize
        )
      "
      style="width: 100%"
      stripe
    >
      <el-table-column prop="name" label="名称" width="180"></el-table-column>
      <el-table-column prop="url" label="URL" width="180"></el-table-column>
      <el-table-column label="操作">操作</el-table-column>
    </el-table>
    <!-- 换页按钮 -->
    <el-pagination
      class="pagination"
      background
      layout="prev, pager, next"
      :total="apiList.length"
      @current-change="changeTablePage"
    >
    </el-pagination>
  </el-dialog>
</template>

<script>
import { statusList } from "@/common/constant.js";

export default {
  name: "statisticalCard",
  props: {
    statusInfo: Object,
  },
  data() {
    return {
      showStatisticalCard: false,
      statusList: [],
      apiList: [
        {
          name: "获取登录状态",
          url: "/user/check_login.php",
        },
        {
          name: "获取登录状态",
          url: "/user/check_login.php",
        },
        {
          name: "获取登录",
          url: "/user/cheogin.php",
        },
        {
          name: "获取登录状态",
          url: "/user/check_login.php",
        },
        {
          name: "获取登态",
          url: "/user/ceck_login.p",
        },
        {
          name: "获取登录状态",
          url: "/user/check_login.php",
        },
        {
          name: "获取登状态",
          url: "/userck_login.php",
        },
        {
          name: "获取登录状态",
          url: "/user/check_.php",
        },
        {
          name: "获取登状态",
          url: "/user/check_login.php",
        },
        {
          name: "获取态",
          url: "/user/check_login.php",
        },
        {
          name: "获登录状态",
          url: "/user/check_login.php",
        },
        {
          name: "获取登录状态",
          url: "/user/chlogin.php",
        },
        {
          name: "获取登录",
          url: "/user/check_login.php",
        },
      ],
      tablePageSize: 5, // api 表格每页显示的条数
      tableCurrentPage: 1, // api 表格当前显示的页数
    };
  },
  mounted() {
    this.getStatusList();
  },
  methods: {
    /**
     * 显示弹窗
     */
    showPopup() {
      this.showStatisticalCard = true;
    },
    /**
     * 获得 api 状态数量
     */
    getStatusList() {
      statusList.forEach((statusInfo) => {
        statusInfo = JSON.parse(JSON.stringify(statusInfo));
        let _status = {
          status: statusInfo.status,
          color: statusInfo.color,
          number: this.statusInfo[statusInfo.status],
        };
        this.statusList.push(_status);
      });
    },
    /**
     * 获取某一种状态的 api
     * @param {String} status 状态名称
     */
    getAPIStatus(status) {
      // todo
    },
    /**
     * 表格换页
     * @param {Number} pageNum 第几页
     */
    changeTablePage(pageNum) {
      this.tableCurrentPage = pageNum;
    },
  },
};
</script>

<style scoped>
.dialog /deep/ .el-dialog__body {
  /* padding-top: 0; */
}
.statusBox {
  width: 100%;
  font-size: 0;
}
.statusButton {
  width: 20%;
  margin: 0;
  border: 1px solid white;
  margin-bottom: 5px;
}
.statusButton:hover,
.statusButton:focus {
  border: 1px solid #c6e2ff;
}
.statusPoint {
  height: 8px;
  width: 8px;
  display: inline-block;
  border-radius: 50%;
}
.pagination {
  margin-top: 10px;
}
</style>