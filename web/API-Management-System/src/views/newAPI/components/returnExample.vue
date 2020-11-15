<template>
  <el-dialog
    class="dialog"
    title="返回示例"
    @close="init"
    :visible.sync="dialogFormVisible"
  >
    <el-form class="form" :model="exampleInfo" label-position="top">
      <el-form-item label="示例名称">
        <el-input
          v-model="exampleInfo.name"
          :disabled="!ableEditName"
          size="small"
        ></el-input>
      </el-form-item>
      <el-form-item label="HTTP Status Code">
        <el-select
          v-model="exampleInfo.statusCode"
          filterable
          allow-create
          default-first-option
          placeholder="请选择"
          style="width: 100%"
          size="small"
        >
          <el-option
            v-for="item in statusCodeList"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Content-Type">
        <el-select
          v-model="exampleInfo.ContentType"
          filterable
          allow-create
          default-first-option
          placeholder="请选择"
          style="width: 100%"
          size="small"
        >
          <el-option
            v-for="item in ContentTypeList"
            :key="item"
            :label="item"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="示例内容">
        <codemirror
          class="codemirror"
          v-model="exampleInfo.value"
          :options="cmOptions"
        />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="dialogFormVisible = false"
        >确 定</el-button
      >
    </div>
  </el-dialog>
</template>

<script>
import "codemirror/mode/javascript/javascript.js";
import "codemirror/theme/dracula.css";

export default {
  name: "returnExample",
  data() {
    return {
      dialogFormVisible: false,
      ableEditName: true,
      statusCodeList: ["200", "302", "404", "500", "502"],
      ContentTypeList: [
        "application/json",
        "application/x-www-form-urlencoded",
      ],
      exampleInfo: {
        name: "",
        statusCode: "",
        ContentType: "",
        value: "",
      },
      cmOptions: {
        // 代码编辑器的配置
        tabSize: 4,
        indentUnit: 4,
        smartIndent: true,
        mode: "application/json",
        theme: "dracula",
        lineNumbers: true,
        line: true,
        // more CodeMirror options...
      },
    };
  },
  mounted() {
    console.log("the current CodeMirror instance object:", this.codemirror);
    // you can use this.codemirror to do something...
  },
  methods: {
    /**
     * 窗口关闭时初始化数据
     */
    init() {
      this.exampleInfo = {
        name: "",
        statusCode: "",
        ContentType: "",
        value: "",
      };
    },
  },
};
</script>

<style scoped>
.codemirror {
  font-size: 18px;
  line-height: 22px;
  border-radius: 20px;
}
.dialog /deep/ .el-dialog__body {
  padding: 20px 20px;
}
.form /deep/ .el-form-item__label {
  line-height: 16px;
  padding-bottom: 8px;
  font-weight: bold;
}
</style>