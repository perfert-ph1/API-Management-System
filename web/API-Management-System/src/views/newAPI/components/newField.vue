<template>
  <el-dialog
    :visible.sync="dialogVisible"
    :width="dialogWidth"
    @close="initData"
  >
    <span slot="title" class="title">
      <slot name="title">详情</slot>
    </span>
    <el-form
      :model="info"
      :rules="rules"
      :hide-required-asterisk="false"
      label-position="right"
      label-width="80px"
      ref="form"
    >
      <el-form-item label="参数名" prop="name">
        <el-input v-model="info.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="必填">
        <el-switch v-model="info.required" active-text="是" inactive-text="否">
        </el-switch>
      </el-form-item>
      <el-form-item label="长度限制">
        <el-input
          v-model="info.lengthLimit"
          clearable
          style="width: 80px"
        ></el-input>
      </el-form-item>
      <el-form-item label="其他限制">
        <el-input v-model="info.otherLimit" clearable></el-input>
      </el-form-item>
      <el-form-item label="说明">
        <el-input v-model="info.explain" clearable></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="info.type">
          <el-option
            v-for="item in typeList"
            :key="item"
            :label="'[' + item + ']'"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="示例">
        <el-input
          v-model="info.example"
          type="textarea"
          :autosize="{ minRows: 4 }"
        ></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="newField">确 定</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "newField",
  props: {
    dialogWidth: {
      type: String,
      default: "40%",
    },
    typeList: Array,
  },
  data() {
    return {
      dialogVisible: false,
      rules: {
        name: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
      info: {
        dataId: "",
        name: "",
        required: false,
        lengthLimit: "",
        otherLimit: "",
        explain: "",
        type: "string",
        subfield: [],
        example: "",
      },
    };
  },
  methods: {
    /**
     * 关闭弹窗时清空数据
     */
    initData() {
      this.info = {
        dataId: "",
        name: "",
        required: false,
        lengthLimit: "",
        otherLimit: "",
        explain: "",
        type: "string",
        subfield: [],
        example: "",
      };
    },
    /**
     * 格式化 是否必填 的显示
     */
    requiredFormatter(row, column, cellValue, index) {
      return cellValue ? "是" : "";
    },
    /**
     * 新增或修改相应的字段
     */
    newField() {
      if (
        this.info.name.replace(
          /[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?]/g,
          ""
        ) != ""
      ) {
        this.$emit("newField", this.info);
        this.dialogVisible = false;
      }
    },
  },
  watch: {
    dialogVisible(val) {
      if (!val) {
        this.$refs.form.clearValidate();
      }
    },
  },
};
</script>

<style scoped>
.title {
  color: #333333;
}
/* .el-input{
  width: auto !important;
} */
</style>