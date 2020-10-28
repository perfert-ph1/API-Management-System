<template>
  <div class="projectList">
    <div class="header">
      <div class="name">名称</div>
      <div class="moreInfo">
        <div class="version">版本号</div>
        <div class="remarks">备注</div>
        <div class="type">类型</div>
        <div class="lastUpdateTime">最后更新时间</div>
        <div class="operationBox">操作</div>
      </div>
    </div>
    <div
      class="item"
      v-for="projectInfo in projectInfoList"
      :key="projectInfo.id"
    >
      <div class="name" @click="intoProjectDetail(projectInfo.id)">
        {{ projectInfo.name }}
      </div>
      <div class="moreInfo">
        <div class="version">{{ projectInfo.version }}</div>
        <div class="remarks">{{ projectInfo.remarks }}</div>
        <div class="type">{{ projectInfo.type }}</div>
        <div class="lastUpdateTime">{{ projectInfo.lastUpdateTime }}</div>
        <div class="operationBox">
          <div class="operation">编辑</div>
          <el-dropdown
            class="dropdown"
            @command="operateProject"
            trigger="click"
          >
            <span class="el-dropdown-link">
              更多<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item
                :command="{ operation: 'copy', id: projectInfo.id }"
                icon="el-icon-document-copy"
              >
                复制
              </el-dropdown-item>
              <el-dropdown-item
                :command="{ operation: 'file', id: projectInfo.id }"
                icon="el-icon-document-checked"
              >
                归档
              </el-dropdown-item>
              <el-dropdown-item
                :command="{ operation: 'delete', id: projectInfo.id }"
                icon="el-icon-document-delete"
              >
                删除
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <div class="footer">共 {{ projectInfoList.length }} 条记录</div>
  </div>
</template>

<script>
export default {
  name: "projectList",
  props: {
    projectInfoList: {
      type: Array,
      default: () => {
        return [
          {
            name: "",
            version: "",
            remarks: "",
            type: "",
            lastUpdateTime: "",
          },
        ];
      },
    },
  },
  data() {
    return {};
  },
  methods: {
    /**
     * 进入项目详情页面
     * @param {Number} projectId 项目的 id
     */
    intoProjectDetail(projectId) {
      console.log(projectId);
      // todo
    },
    /**
     * 对项目进行操作
     * @param {Object} command 选中的选项名和此项目的id { operation: 'copy', id: 0 }
     */
    operateProject(command) {
      console.log(command);
      switch (command.operation) {
        case "copy":
          {
          }
          break;

        case "file":
          {
          }
          break;

        case "delete":
          {
          }
          break;

        default:
          break;
      }
    },
  },
};
</script>

<style scoped>
.projectList {
  margin: 15px;
  background: white;
  border: 1px solid #e5e5e5;
}
.header,
.item {
  width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: #333333;
  border-bottom: 1px solid #e5e5e5;
  padding: 10px 1%;
  box-sizing: border-box;
}
.header {
  font-size: 14px;
  font-weight: bold;
  background-color: #f8f8f8;
  color: #48555c;
  border: none;
}
.name {
  width: 25%;
  cursor: pointer;
}
.moreInfo {
  width: 75%;
  display: flex;
  justify-content: space-between;
}
.version {
  width: 15%;
}
.remarks {
  width: 35%;
  margin-right: 20px;
  box-sizing: border-box;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.type {
  width: 15%;
}
.lastUpdateTime {
  width: 23%;
}
.operationBox {
  min-width: 22%;
  max-width: 50%;
  user-select: none;
}
.operation {
  color: #409eff;
  cursor: pointer;
  border-right: 1px solid #d9d9d9;
  display: inline-block;
  font-size: 14px;
  padding: 0 5px 0 0;
}
.operation:hover {
  text-decoration: underline;
}
.dropdown {
  display: inline-block;
}
.el-dropdown-link {
  outline: none;
  color: #409eff;
  cursor: pointer;
}
.el-dropdown-link:hover {
  text-decoration: underline;
}
.footer {
  font-size: 14px;
  padding: 10px 1.5%;
  background-color: #f8f8f8;
}
</style>