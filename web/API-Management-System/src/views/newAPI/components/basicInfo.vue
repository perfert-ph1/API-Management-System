<template>
  <div class="basicInfo">
    <div class="item">
      <div class="itemTitle">API 状态</div>
      <div class="itemValue">
        <el-radio-group v-model="status">
          <template v-for="item in statusList">
            <el-radio :label="item.status" :key="item.status">{{
              item.status
            }}</el-radio>
          </template>
        </el-radio-group>
      </div>
    </div>
    <div class="item">
      <div class="itemTitle">API Path</div>
      <div class="itemValue">
        <el-input v-model="path" size="small">
          <template slot="prepend">
            <el-select
              v-model="transferProtocol"
              class="protocol"
              style="margin-right: 20px"
            >
              <el-option :key="'HTTP'" label="HTTP" value="HTTP"></el-option>
              <el-option :key="'HTTPS'" label="HTTPS" value="HTTPS">
              </el-option>
            </el-select>
            <el-select v-model="requestMethod" class="method">
              <el-option
                v-for="item in requestMethodList"
                :key="item.method"
                :label="item.method"
                :value="item.method"
              ></el-option>
            </el-select>
          </template>
        </el-input>
      </div>
    </div>
    <div class="item">
      <div class="itemTitle">分组 / API 名称</div>
      <div class="itemValue itemGroup">
        <el-input v-model="path" size="small">
          <template slot="prepend">
            <el-select v-model="group" class="group">
              <el-option
                v-for="item in groupList"
                :key="item.name"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </template>
        </el-input>
      </div>
    </div>
    <div class="item">
      <div class="itemTitle">
        标签 [Tags]
        <el-tooltip
          effect="dark"
          content="标签可以用于搜索、筛选"
          placement="top-start"
        >
          <i class="el-icon-info"></i>
        </el-tooltip>
        <span>( 选填 )</span>
      </div>
      <div class="itemValue">
        <el-select
          v-model="tags"
          multiple
          filterable
          allow-create
          default-first-option
          placeholder="请选择 api 标签"
          style="width: 100%"
          size="small"
        >
          <el-option
            v-for="item in tagList"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </div>
    </div>
    <div class="item">
      <div class="itemTitle">
        负责人
        <el-tooltip
          effect="dark"
          content="负责人默认是创建 API 的成员"
          placement="top-start"
        >
          <i class="el-icon-info"></i>
        </el-tooltip>
        <span>( 选填 )</span>
      </div>
      <div class="itemValue">
        <el-select
          v-model="leader"
          filterable
          default-first-option
          placeholder="请选择负责人"
          size="small"
        >
          <el-option
            v-for="item in projectMemberList"
            :key="item.userName"
            :label="item.userName"
            :value="item.userName"
          >
          </el-option>
        </el-select>
      </div>
    </div>
  </div>
</template>

<script>
import { statusList, requestMethodList } from "@/common/constant.js";

export default {
  name: "basicInfo",
  props: {},
  data() {
    return {
      statusList: statusList,
      requestMethodList: requestMethodList,
      status: "已发布", // api 的状态
      transferProtocol: "HTTP", // url 的传输协议
      requestMethod: "POST", // 请求方法
      path: "", // api path
      group: "", // 分组
      groupList: [
        // 可选择的分组
        {
          name: "分组一反反复复烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦",
        },
        {
          name: "分组二",
        },
      ],
      tags: [], // 标签
      tagList: ["懂的都懂", "月薪过万", "不方便多说", "利益牵扯太深"], // 可选择的标签
      leader: "", // 负责人
      projectMemberList: [
        {
          userName: "李林飞",
          profilePhoto: "",
        },
        {
          userName: "李俊贤",
          profilePhoto: "",
        },
        {
          userName: "陈金海",
          profilePhoto: "",
        },
      ], // 项目成员列表
    };
  },
  mounted() {
    this.leader = this.$store.getters.getUserName;
  },
};
</script>

<style scoped>
.item {
  margin: 10px 0;
}
.itemTitle {
  font-size: 13px;
  font-weight: bold;
  color: #333333;
}
.itemValue {
  padding: 10px 0;
}
.protocol {
  width: 100px;
  box-sizing: border-box;
}
.method {
  width: 120px;
  box-sizing: border-box;
}
.group {
  min-width: 300px;
  box-sizing: border-box;
}
.group /deep/ .el-input__inner {
  text-overflow: ellipsis !important;
}
.itemTitle span {
  margin-left: 5px;
  font-size: 12px;
  color: #999999;
}
</style>