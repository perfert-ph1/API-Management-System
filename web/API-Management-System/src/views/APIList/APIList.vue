<template>
  <div class="apiList">
    <navigation-box
      @selectGroup="selectGroup"
      @addGroup="addGroup"
      @editGroup="editGroup"
      @deleteGroup="deleteGroup"
      :groupList="groupList"
      ref="navigationBox"
    >
      <span slot="title">所有 API</span>
    </navigation-box>
    <data-list
      @search="search"
      @newSth="toNewAPIPage"
      :totalNum="APIInfoList.length"
    >
      <template v-slot:buttonName>新建 HTTP API</template>
      <template v-slot:filterButton>
        <el-popover trigger="click">
          <el-checkbox
            :indeterminate="isIndeterminate"
            v-model="checkAll"
            @change="showAllColumn"
            style="margin-bottom: 10px"
            >全选</el-checkbox
          >
          <el-checkbox-group
            v-model="checkedVisible"
            @change="changeColVisible"
          >
            <el-checkbox
              v-for="col in columnVisible"
              :label="col.colName"
              :key="col.colName"
              >{{ col.name_ch }}</el-checkbox
            >
          </el-checkbox-group>
          <el-button slot="reference" type="text" style="color: #333333"
            ><i class="el-icon-s-help"></i> 列表项</el-button
          >
        </el-popover>
      </template>
      <!-- 表格 -->
      <template v-slot:table>
        <el-table :data="APIInfoList" border height="100%" max-height="100%">
          <!-- 标记 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="flag"
            label=""
            width="40"
            column-key="flag"
            :filters="[
              { text: '无', value: '无' },
              { text: '标记', value: '标记' },
            ]"
            :filter-method="filterFlag"
            :key="0"
            v-if="columnVisible[0].visible"
          >
            <template slot-scope="scope">
              <i
                class="el-icon-s-flag"
                style="cursor: pointer"
                :style="{ color: scope.row.flag ? '#f48932' : '#cccccc' }"
                @click="setFlag(scope.$index)"
              ></i>
            </template>
          </el-table-column>
          <!-- ID -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="ID"
            label="ID"
            width="100"
            :key="1"
            v-if="columnVisible[1].visible"
          >
          </el-table-column>
          <!-- api -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="API"
            label="APIs"
            width="200"
            column-key="API"
            :filters="filters.api"
            :filter-method="filterStatus"
            :key="2"
          >
            <template slot-scope="scope">
              <el-tag
                size="small"
                effect="plain"
                :style="{
                  width: '56px',
                  textAlign: 'center',
                  borderColor: statusColor[scope.row.status],
                  color: statusColor[scope.row.status],
                }"
                >{{ scope.row.status }}</el-tag
              >
              <span>{{ scope.row.API }}</span>
            </template>
          </el-table-column>
          <!-- 请求方式 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="requestMethod"
            label="请求方式"
            width="120"
            column-key="requestMethod"
            :filters="[
              { text: 'HTTP[S]:POST', value: 'HTTP[S]:POST' },
              { text: 'HTTP[S]:GET', value: 'HTTP[S]:GET' },
              { text: 'HTTP[S]:PUT', value: 'HTTP[S]:PUT' },
              { text: 'HTTP[S]:DELETE', value: 'HTTP[S]:DELETE' },
              { text: 'HTTP[S]:HEAD', value: 'HTTP[S]:HEAD' },
              { text: 'HTTP[S]:OPTIONS', value: 'HTTP[S]:OPTIONS' },
              { text: 'HTTP[S]:PATCH', value: 'HTTP[S]:PATCH' },
            ]"
            :filter-method="filterRequestMethod"
            :key="3"
            v-if="columnVisible[2].visible"
          >
            <template slot-scope="scope">
              <el-tag
                size="small"
                effect="plain"
                :style="{
                  width: '56px',
                  textAlign: 'center',
                  borderColor:
                    requestMethodColor[scope.row.requestMethod].color,
                  color: requestMethodColor[scope.row.requestMethod].color,
                }"
                >{{
                  requestMethodColor[scope.row.requestMethod].abbreviation
                }}</el-tag
              >
            </template>
          </el-table-column>
          <!-- URL -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="URL"
            label="URL"
            width="180"
            :key="4"
            v-if="columnVisible[3].visible"
          >
          </el-table-column>
          <!-- 分组 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="group"
            label="分组"
            width="150"
            :key="5"
            v-if="columnVisible[4].visible"
          >
          </el-table-column>
          <!-- 版本号 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="version"
            label="版本号"
            width="120"
            column-key="version"
            :filters="filters.version"
            :filter-method="filterVersion"
            :key="6"
            v-if="columnVisible[5].visible"
          >
          </el-table-column>
          <!-- 标签 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="tag"
            label="标签"
            width="250"
            column-key="tag"
            :filters="filters.tag"
            :filter-method="filterTag"
            :key="7"
            v-if="columnVisible[6].visible"
          >
            <template slot-scope="scope">
              <el-tag size="mini" v-for="item in scope.row.tag" :key="item">{{
                item
              }}</el-tag>
            </template>
          </el-table-column>
          <!-- 负责人 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="leader"
            label="负责人"
            width="100"
            column-key="leader"
            :filters="filters.leader"
            :filter-method="filterLeader"
            :key="8"
            v-if="columnVisible[7].visible"
          >
          </el-table-column>
          <!-- 创建者 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="creator"
            label="创建者"
            width="100"
            column-key="creator"
            :filters="filters.creator"
            :filter-method="filterCreator"
            :key="9"
            v-if="columnVisible[8].visible"
          >
          </el-table-column>
          <!-- 最近更新者 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="lastUpdatedUser"
            label="最近更新者"
            width="120"
            column-key="lastUpdatedUser"
            :filters="filters.lastUpdatedUser"
            :filter-method="filterLastUpdatedUser"
            :key="10"
            v-if="columnVisible[9].visible"
          >
          </el-table-column>
          <!-- 创建时间 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="createdTime"
            sortable
            :sort-method="sortByCreatedTime"
            label="创建时间"
            width="180"
            :key="11"
            v-if="columnVisible[10].visible"
          >
          </el-table-column>
          <!-- 更新时间 -->
          <el-table-column
            :show-overflow-tooltip="true"
            prop="lastUpdatedTime"
            sortable
            :sort-method="sortByUpdatedTime"
            label="更新时间"
            width="180"
            :key="12"
            v-if="columnVisible[11].visible"
          >
          </el-table-column>
          <!-- 操作 -->
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button @click="editAPI(scope.row)" type="text" size="small"
                >编辑</el-button
              >
              <el-button @click="copyAPI(scope.$index)" type="text" size="small"
                >复制</el-button
              >
              <el-button
                @click="deleteDialogVisible = true"
                type="text"
                size="small"
                >删除</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </template>
    </data-list>
    <!-- 删除 api 的弹窗 -->
    <el-dialog title="删除 API" :visible.sync="deleteDialogVisible" width="30%">
      <span>此操作不可撤销，确认删除此 API 吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="deleteDialogVisible = false">取 消</el-button>
        <el-button type="danger" @click="deleteAPI">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 复制 api 的弹窗 -->
    <el-dialog title="复制 API" :visible.sync="copyDialogVisible" width="30%">
      <el-form :model="newAPIInfo">
        <el-form-item label="目标项目">
          <el-select v-model="newAPIInfo.projectName" style="width: 100%">
            <el-option
              v-for="project in projectList"
              :key="project.name"
              :value="project.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="目标分组">
          <el-select v-model="newAPIInfo.group" style="width: 100%">
            <el-option
              v-for="group in targetGroupList"
              :key="group.name"
              :value="group.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="API 名称">
          <el-input v-model="newAPIInfo.API"></el-input>
        </el-form-item>
        <el-form-item label="API 请求方式">
          <el-select v-model="newAPIInfo.requestMethod" style="width: 100%">
            <el-option
              v-for="requestMethod in requestMethodList"
              :key="requestMethod.method"
              :value="requestMethod.method"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="API URL">
          <el-input v-model="newAPIInfo.URL"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="copyDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="createAPI">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import navigationBox from "@/components/navigationBox.vue";
import dataList from "@/components/dataList.vue";
import { statusList, requestMethodList } from "@/common/constant";

export default {
  name: "APIList",
  components: {
    navigationBox,
    dataList,
  },
  mounted() {
    this.columnVisible.forEach((i) => {
      if (i.visible) {
        this.checkedVisible.push(i.colName);
      }
    });
  },
  data() {
    return {
      requestMethodList: requestMethodList,
      groupList: [
        // 分组
        {
          name:
            "用户相关用户相关用户相关用户相关用户相关用户相关用户相关用户相关用户相关用户相关",
        },
        {
          name: "积分相关",
        },
      ],
      nowGroup: "", // 当前分组
      APIInfoList: [
        {
          flag: true,
          ID: "00001",
          status: "已发布",
          API: "获取利益相关者",
          requestMethod: "GET",
          URL: "/user/login",
          group: "利益相关",
          version: "1.0.0",
          tag: ["懂得都懂", "不方便多说"],
          leader: "李俊贤",
          creator: "李林飞",
          lastUpdatedUser: "李林飞",
          createdTime: "2020-11-25 20:11:33",
          lastUpdatedTime: "2020-11-25 20:11:33",
        },
        {
          flag: false,
          ID: "00001",
          status: "测试",
          API: "获取利益相关者",
          requestMethod: "OPTIONS",
          URL: "/user/login",
          group: "利益相关",
          version: "1.2.1",
          tag: ["懂得都懂", "牵扯太深"],
          leader: "李俊贤",
          creator: "李林飞",
          lastUpdatedUser: "李林飞",
          createdTime: "2019-10-23 23:23:46",
          lastUpdatedTime: "2019-10-23 23:23:46",
        },
        {
          flag: true,
          ID: "00001",
          status: "已发布",
          API: "人均百万月薪",
          requestMethod: "GET",
          URL: "/user/login",
          group: "利益相关",
          version: "2.1.9",
          tag: ["懂得都懂", "不方便多说"],
          leader: "李俊贤",
          creator: "李林飞",
          lastUpdatedUser: "李林飞",
          createdTime: "2020-01-22 02:00:02",
          lastUpdatedTime: "2020-01-22 02:00:02",
        },
      ],
      APIInfoList_backup: [], // 进行 api 搜索之前备份的 api 信息，取消搜索之后直接赋给 APIInfoList 并清空
      columnVisible: [
        // 控制列的动态显示与隐藏
        {
          colName: "flag",
          visible: true,
          name_ch: "标记",
        },
        {
          colName: "ID",
          visible: false,
          name_ch: "ID",
        },
        {
          colName: "requestMethod",
          visible: true,
          name_ch: "请求方式",
        },
        {
          colName: "URL",
          visible: true,
          name_ch: "URL",
        },
        {
          colName: "group",
          visible: true,
          name_ch: "分组",
        },
        {
          colName: "version",
          visible: true,
          name_ch: "版本号",
        },
        {
          colName: "tag",
          visible: false,
          name_ch: "标签",
        },
        {
          colName: "leader",
          visible: false,
          name_ch: "负责人",
        },
        {
          colName: "creator",
          visible: false,
          name_ch: "创建者",
        },
        {
          colName: "lastUpdatedUser",
          visible: false,
          name_ch: "最近更新者",
        },
        {
          colName: "createdTime",
          visible: false,
          name_ch: "创建时间",
        },
        {
          colName: "lastUpdatedTime",
          visible: false,
          name_ch: "最近更新时间",
        },
      ],
      checkedVisible: [], // 选中要显示的列
      checkAll: false, // 筛选列全选
      isIndeterminate: true, // 筛选列模糊
      filters: {
        // 每列筛选项
        api: [],
        version: [],
        tag: [],
        leader: [],
        creator: [],
        lastUpdatedUser: [],
      },
      statusColor: {}, // api 状态对应的颜色
      requestMethodColor: {}, // 请求方式对应的颜色
      deleteDialogVisible: false,
      copyDialogVisible: false,
      newAPIInfo: {
        // 复制的 api 信息
        projectId: "",
        projectName: "",
        group: "",
        API: "",
        requestMethod: "",
        URL: "",
      },
      projectList: [
        // 复制时可选的项目列表
        {
          name: "wishWall",
        },
        {
          name: "API-management-system",
        },
      ],
      targetGroupList: [
        // 复制时可选的分组列表
        {
          name: "积分相关",
        },
        {
          name: "登陆注册相关",
        },
      ],
    };
  },
  mounted() {
    this.setFilters();
    this.getAPIInfo();
    // 将默认显示的列的钩显示出来
    this.columnVisible.forEach((i) => {
      if (i.visible) {
        this.checkedVisible.push(i.colName);
      }
    });
  },
  methods: {
    /**
     * 根据数据初始化相关的筛选项
     */
    setFilters() {
      statusList.forEach((status) => {
        this.filters.api.push({
          text: status.status,
          value: status.status,
        });
        this.$set(this.statusColor, status.status, status.color);
      });
      this.requestMethodList.forEach((method) => {
        this.$set(this.requestMethodColor, method.method, {
          color: method.color,
          abbreviation: method.abbreviation,
        });
      });

      let versionList = new Set();
      let tagList = new Set();
      let leaderList = new Set();
      let creatorList = new Set();
      let lastUpdatedUserList = new Set();
      this.APIInfoList.forEach((api) => {
        versionList.add(api.version);
        leaderList.add(api.leader);
        creatorList.add(api.creator);
        lastUpdatedUserList.add(api.lastUpdatedUser);
        api.tag.forEach((tag) => {
          tagList.add(tag);
        });
      });
      versionList.forEach((version) => {
        this.filters.version.push({
          text: version,
          value: version,
        });
      });
      tagList.forEach((tag) => {
        this.filters.tag.push({
          text: tag,
          value: tag,
        });
      });
      leaderList.forEach((leader) => {
        this.filters.leader.push({
          text: leader,
          value: leader,
        });
      });
      creatorList.forEach((creator) => {
        this.filters.creator.push({
          text: creator,
          value: creator,
        });
      });
      lastUpdatedUserList.forEach((lastUpdatedUser) => {
        this.filters.lastUpdatedUser.push({
          text: lastUpdatedUser,
          value: lastUpdatedUser,
        });
      });

      this.newAPIInfo.projectId = this.$route.query.projectId;
      this.newAPIInfo.projectName = this.$route.query.projectName;
    },
    /**
     * 选择一个分组
     * @param {String} groupName 分组名
     */
    selectGroup(groupName) {
      this.nowGroup = groupName;
    },
    /**
     * 新增一个分组
     * @param {String} groupName 分组名称
     */
    addGroup(groupName) {
      // todo：发起请求，增加分组
      this.groupList.push({
        name: groupName,
      });
      this.$refs.navigationBox.groupList_show.push({
        name: groupName,
      });
    },
    /**
     * 编辑分组
     * @param {String} oldName 此分组原来的名字
     * @param {String} groupName 分组名称
     */
    editGroup(oldName, groupName) {
      // todo：发起请求，更改分组名
      // 成功后：
      this.groupList.forEach((group) => {
        if (group.name == oldName) {
          group.name = groupName;
        }
      });
      this.$refs.navigationBox.groupList_show = JSON.parse(
        JSON.stringify(this.groupList)
      );
    },
    /**
     * 删除分组
     * @param {String} groupName 分组名称
     */
    deleteGroup(groupName) {
      // todo：发起请求，删除分组
      let index = null;
      this.groupList.forEach((group, i) => {
        if (group.name == groupName) {
          index = i;
        }
      });
      this.groupList.splice(index, 1);

      this.$refs.navigationBox.groupList_show.forEach((group, i) => {
        if (group.name == groupName) {
          index = i;
        }
      });
      this.$refs.navigationBox.groupList_show.splice(index, 1);
    },
    /**
     * 跳转到新建 api 的界面
     */
    toNewAPIPage() {
      this.$router.push({
        path: "newAPI",
        query: this.$route.query,
      });
    },
    /**
     * 获取 api 数据
     */
    getAPIInfo() {
      // todo 发起网络请求
      // 获得数据后同时进行进行备份
      this.APIInfoList_backup = JSON.parse(JSON.stringify(this.APIInfoList));
    },
    /**
     * 根据关键词筛选 api
     * @param {String} keyWord 要进行搜索的关键词
     */
    search(keyWord) {
      // 保留含有关键词的 api
      let filteredList = [];
      this.APIInfoList_backup.forEach((apiInfo) => {
        // console.log(apiInfo.API.includes(keyWord),keyWord);
        if (apiInfo.API.includes(keyWord)) {
          filteredList.push(apiInfo);
        }
      });
      this.APIInfoList = filteredList;
    },
    /**
     * 点击选择列全选的回调函数
     * @param {Boolean} val 是否全选
     */
    showAllColumn(val) {
      // 根据全选框的状态改变所有列的是否显示的值
      this.columnVisible.forEach((col) => {
        col.visible = val;
      });
      this.isIndeterminate = false;

      // 同时将显示的列前面的钩显示出来
      this.checkedVisible = [];
      this.columnVisible.forEach((i) => {
        if (i.visible) {
          this.checkedVisible.push(i.colName);
        }
      });
    },
    /**
     * 点击显示列选择框的回调函数
     * @param {Array} checkedArr 选中的值组成的数组
     */
    changeColVisible(checkedArr) {
      this.columnVisible.forEach((item) => {
        item.visible = checkedArr.indexOf(item.colName) != -1;
      });
      let checkedCount = checkedArr.length;
      this.checkAll = checkedCount === this.columnVisible.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.columnVisible.length;
    },
    /**
     * 标记或取消标记
     * @param {Number} colIndex 列的索引
     */
    setFlag(colIndex) {
      // todo：发起网络请求，标记或取消标记某一个 api
      // 请求成功后再改变前端显示
      this.APIInfoList[colIndex].flag = !this.APIInfoList[colIndex].flag;
    },
    /**
     * 根据创建时间排序的函数
     */
    sortByCreatedTime(a, b) {
      const aTime = this.getTimestamp(a.createdTime),
        bTime = this.getTimestamp(b.createdTime);
      if (aTime < bTime) {
        return -1;
      }
    },
    /**
     * 根据最后更新时间排序的函数
     */
    sortByUpdatedTime(a, b) {
      const aTime = this.getTimestamp(a.lastUpdatedTime),
        bTime = this.getTimestamp(b.lastUpdatedTime);
      if (aTime < bTime) {
        return -1;
      }
    },
    /**
     * 将表示时间的字符串转换为时间戳
     * @param {String} timeStr 时间字符串
     * @return {String} 时间戳字符串
     */
    getTimestamp(timeStr) {
      let timestamp = new Date(timeStr).getTime();
      return timestamp;
    },
    /**
     * 跳转到编辑 api 的页面
     */
    editAPI() {
      // todo
      console.log("跳转到编辑 api 的页面");
    },
    /**
     * 复制 api
     * @param {Number} rowIndex 行的索引
     */
    copyAPI(rowIndex) {
      // 将当前 api 的信息设为新 api 的默认信息
      this.newAPIInfo.group = this.APIInfoList[rowIndex].group;
      this.newAPIInfo.API = this.APIInfoList[rowIndex].API;
      this.newAPIInfo.requestMethod = this.APIInfoList[rowIndex].requestMethod;
      this.newAPIInfo.URL = this.APIInfoList[rowIndex].URL;

      this.copyDialogVisible = true;
    },
    /**
     * 删除 api
     */
    deleteAPI() {
      // todo
      // 网络请求完成后关闭弹窗
      this.deleteDialogVisible = false;
    },
    /**
     * 点击复制弹出框的确定之后，创建对应的 api
     */
    createAPI() {
      // todo
      // 网络请求完成后关闭弹窗
      this.copyDialogVisible = false;
    },
    /**
     * 筛选标记的函数
     */
    filterFlag(value, row, column) {
      value = value == "标记" ? true : false;
      return row.flag == value;
    },
    /**
     * 筛选 api 状态的函数
     */
    filterStatus(value, row, column) {
      return row.status == value;
    },
    /**
     * 筛选请求方式的函数
     */
    filterRequestMethod(value, row, column) {
      value = value.split(":")[1];
      return row.requestMethod == value;
    },
    /**
     * 筛选版本号的函数
     */
    filterVersion(value, row, column) {
      return row.version == value;
    },
    /**
     * 筛选标签的函数
     */
    filterTag(value, row, column) {
      return row.tag.indexOf(value) != -1;
    },
    /**
     * 筛选负责人的函数
     */
    filterLeader(value, row, column) {
      return row.leader == value;
    },
    /**
     * 筛选创建者的函数
     */
    filterCreator(value, row, column) {
      return row.creator == value;
    },
    /**
     * 筛选最近更新者的函数
     */
    filterLastUpdatedUser(value, row, column) {
      return row.lastUpdatedUser == value;
    },
  },
  // beforeUpdate() {
  //   this.$nextTick(() => {
  //     //在数据加载完，重新渲染表格
  //     this.$refs["table"].doLayout();
  //   });
  // },
};
</script>

<style scoped>
* {
  transition: none;
}
.apiList {
  height: calc(100vh - 120px);
  width: calc(100% - 30px);
  display: flex;
  margin: 17px 15px;
  box-sizing: border-box;
  border: 1px solid #d9d9d9;
  background-color: #ffffff;
}
.el-tag {
  margin: 0 5px;
}
.el-form-item {
  margin-bottom: 5px;
}
.el-table /deep/ .cell {
  height: 34px !important;
  line-height: 34px !important;
}
.el-checkbox {
  display: block;
}
.el-checkbox__input is-checked {
  display: inline-block;
}
</style>