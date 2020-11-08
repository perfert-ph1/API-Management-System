<template>
  <div class="newGroupCard">
    <el-dialog :title="title" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="分组名称">
          <el-input v-model="groupName" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="newGroup">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 不能为空提示 -->
    <el-dialog title="提示" :visible.sync="noticeDialogVisible" width="30%">
      <span>{{ noticeValue }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="noticeDialogVisible = false"
          >确 定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "newGroupCard",
  data() {
    return {
      dialogFormVisible: false,
      title: "新建分组",
      groupName: "",
      noticeDialogVisible: false,
      noticeValue: "",
    };
  },
  methods: {
    /**
     * 显示此弹窗
     * @param {String} title 弹窗的名字
     * @param {String} groupName 分组名
     */
    showNewGroupCard(title, groupName) {
      this.groupName = groupName;
      this.title = title;
      this.dialogFormVisible = true;
    },
    /**
     * 显示提示弹窗
     * @param {String} noticeValue 提示的文字
     */
    showNoticeDialog(noticeValue) {
      this.noticeDialogVisible = true;
      this.noticeValue = noticeValue;
    },
    /**
     * 通知父组件新建分组
     */
    newGroup() {
      if (this.groupName.length == 0) {
        this.noticeDialogVisible = true;
        this.showNoticeDialog("分组名称不能为空哦~");
        return;
      }
      this.$emit("newGroup", {
        groupName: this.groupName,
        operation: this.title,
      });
    },
  },
};
</script>

<style scoped>
</style>