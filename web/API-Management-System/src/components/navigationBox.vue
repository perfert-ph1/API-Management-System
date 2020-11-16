<template>
  <div
    class="navigationBox"
    :class="{ navigationBox_fold: foldNavigation }"
    :style="{ width: (foldNavigation ? width_fold : width) + 'px' }"
  >
    <x-handle @widthChange="widthChange"></x-handle>
    <div class="header">
      <el-button
        type="primary"
        @click="showNewGroupCard('新建分组')"
        v-show="!foldNavigation"
      >
        <i class="el-icon-plus"></i><slot name="buttonName">新建分组</slot>
      </el-button>
      <div
        class="foldButton"
        :class="{ foldButton_fold: foldNavigation }"
        @click="controlNavigation"
      >
        <i class="el-icon-s-fold" v-show="!foldNavigation"></i>
        <i class="el-icon-s-unfold" v-show="foldNavigation"></i>
      </div>
    </div>
    <!-- 搜索框 -->
    <div class="searchBox" v-show="!foldNavigation">
      <el-input
        placeholder="搜索分组"
        v-model="keyWord"
        size="large"
        clearable
        @change="searchGroup"
      >
        <i
          slot="prefix"
          class="el-input__icon el-icon-search"
          @click="searchGroup"
        ></i>
      </el-input>
    </div>
    <!-- 分组列表 -->
    <div class="groupBox" v-show="!foldNavigation">
      <el-menu
        default-active="all"
        @select="selectGroup"
        class="el-menu-vertical-demo"
      >
        <el-menu-item index="all" class="groupHeader">
          <i class="el-icon-menu"></i>
          <span slot="title"><slot name="title">所有</slot></span>
        </el-menu-item>
        <el-menu-item
          v-for="item in groupList_show"
          :key="item.name"
          :index="item.name"
        >
          <i class="el-icon-caret-right"></i>
          <span slot="title">{{ item.name }}</span>
          <!-- 更多操作下拉框 -->
          <el-dropdown class="more" @command="handleGroup">
            <span class="el-dropdown-link">
              <i class="el-icon-more"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item
                icon="el-icon-setting"
                :command="{
                  operation: 'edit',
                  name: item.name,
                }"
                >编辑</el-dropdown-item
              >
              <el-dropdown-item
                icon="el-icon-delete"
                :command="{
                  operation: 'delete',
                  name: item.name,
                }"
                >删除</el-dropdown-item
              >
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item>
      </el-menu>
    </div>
    <new-group-card ref="newGroupCard" @newGroup="newGroup"></new-group-card>
  </div>
</template>

<script>
import newGroupCard from "./newGroupCard.vue";
import xHandle from "./xHandle.vue";

export default {
  name: "navigationBox",
  components: {
    xHandle,
    newGroupCard,
  },
  props: {
    groupList: Array,
  },
  data() {
    return {
      width: 240,
      width_fold: 50,
      foldNavigation: false,
      groupList_show: [], // 显示的分组列表
      keyWord: "", // 搜索的关键字
      operatedGroupName: null, // 正在进行操作的分组的索引值
    };
  },
  mounted() {
    this.groupList.forEach((group) => {
      this.groupList_show.push(group);
    });
  },
  methods: {
    /**
     * 拖动时改变宽度
     */
    widthChange(movement) {
      this.width -= movement;
    },
    /**
     * 控制折叠展开导航栏的图标的切换
     */
    controlNavigation() {
      this.foldNavigation = !this.foldNavigation;
    },
    /**
     * 显示新建分组的弹窗
     * @param {String} title 弹窗的名字
     * @param {String} groupName 分组名
     */
    showNewGroupCard(title = "新建分组", groupName = "") {
      this.$refs.newGroupCard.showNewGroupCard(title, groupName);
    },
    /**
     * 新建或编辑一个分组
     * @param {String} groupInfo 分组相关信息（包括分组名和要进行的操作）
     */
    newGroup(groupInfo) {
      if (groupInfo.operation == "新建分组") {
        // 判断分组是否已存在
        let exist = false;
        this.groupList.forEach((group) => {
          if (groupInfo.groupName == group.name) {
            this.$refs.newGroupCard.showNoticeDialog("该分组已存在");
            exist = true;
          }
        });
        if (exist) {
          return;
        }
        this.$emit("addGroup", groupInfo.groupName);
      } else if (groupInfo.operation == "编辑分组") {
        this.$emit("editGroup", this.operatedGroupName, groupInfo.groupName);
        this.operatedGroupName = null;
      }
      this.$refs.newGroupCard.dialogFormVisible = false;
    },
    /**
     * 选择一个分组
     * @param {String} groupName 分组名
     */
    selectGroup(groupName) {
      this.$emit("selectGroup", groupName);
    },
    /**
     * 对分组进行操作
     * @param {Object} command 要进行的操作（编辑或删除）和对应分组的索引值 {operation:"edit",name:"xxx"}
     */
    handleGroup(command) {
      if (command.operation == "edit") {
        this.showNewGroupCard("编辑分组", command.name);
        this.operatedGroupName = command.name;
      } else if (command.operation == "delete") {
        this.$emit("deleteGroup", command.name);
      }
    },
    /**
     * 根据关键词搜索分组
     * @param {String} keyWord 关键词
     */
    searchGroup(keyWord) {
      let filteredGroupList = [];
      this.groupList.forEach((group) => {
        if (group.name.includes(keyWord)) {
          filteredGroupList.push(group);
        }
      });
      this.groupList_show = filteredGroupList;
    },
  },
};
</script>

<style scoped>
.navigationBox {
  height: 100%;
  min-width: 240px;
  max-width: 50%;
  position: relative;
  flex-shrink: 0;
}
.navigationBox_fold {
  min-width: auto;
}
.header {
  height: 50px;
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
}
.el-button {
  padding: 8px 10px;
  margin-left: 10px;
}
.foldButton {
  height: 30px;
  width: 30px;
  line-height: 30px;
  font-size: 22px;
  color: #333333;
  cursor: pointer;
  text-align: center;
  position: absolute;
  right: 10px;
}
.foldButton_fold {
  margin: 0 auto;
}
.searchBox {
  height: 62px;
  width: 100%;
  box-sizing: border-box;
  padding: 5px 10px;
  background-color: #f7f7f7;
  display: flex;
  align-items: center;
  border-bottom: 1px solid #d9d9d9;
  border-top: 1px solid #d9d9d9;
  user-select: none;
}
.searchBox i {
  cursor: pointer;
}
.groupBox {
  width: 100%;
  height: calc(100% - 100px);
  overflow-y: auto;
  user-select: none;
}
.groupHeader {
  border-bottom: 1px dashed #d9d9d9;
}
.el-menu-item {
  height: 45px;
  display: flex;
  align-items: center;
  position: relative;
}
.el-menu-item span {
  width: calc(100% - 60px);
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.more {
  position: absolute;
  right: 10px;
  margin: 0;
}
.el-dropdown-link {
  outline: none;
}
.el-dropdown-menu {
  margin-top: -15px;
  margin-left: 20px;
  padding: 0;
}
.el-dropdown-menu__item {
  padding: 0 10px;
}
.el-popper {
  margin-left: -8px;
}
.el-menu-item.is-active i {
  color: #909399;
}
</style>