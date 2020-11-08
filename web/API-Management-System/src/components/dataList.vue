<template>
  <div class="dataList">
    <div class="header">
      <el-button type="primary" @click="newSth">
        <i class="el-icon-plus"></i>
        <slot name="buttonName">新建</slot>
      </el-button>
      <div class="rightBox">
        <el-input
          placeholder="搜索 API"
          v-model="apiKeyWord"
          class="apiSearchBox"
          size="small "
          clearable
          @change="search"
        >
          <i
            slot="prefix"
            class="el-input__icon el-icon-search"
            @click="search"
          ></i>
        </el-input>
        <slot name="filterButton"></slot>
      </div>
    </div>
    <div class="table">
      <slot name="table"></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "dataList",
  components: {},
  props: {},
  data() {
    return {
      apiKeyWord: "", // 搜索 api 的关键词
    };
  },
  methods: {
    /**
     * 点击新建按钮
     */
    newSth() {
      this.$emit("newSth");
    },
    /**
     * 根据关键词筛选 api
     * @param {String} keyWord 要进行搜索的关键词
     */
    search(keyWord) {
      // todo
      this.$emit("search", keyWord);
    },
  },
};
</script>

<style scoped>
.dataList {
  height: 100%;
  flex-grow: 1;
  background-color: #ffffff;
  overflow-x: auto;
}
.header {
  height: 50px;
  width: 100%;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.el-button {
  padding: 8px 10px;
  margin-left: 10px;
}
.rightBox {
  height: 100%;
  margin-right: 20px;
  display: flex;
  align-items: center;
}
.apiSearchBox {
  width: 200px;
  margin-right: 15px;
}
.apiSearchBox i {
  cursor: pointer;
}
.table {
  height: calc(100% - 90px);
  border-top: 1px solid #d9d9d9;
  box-sizing: border-box;
}
.tableHeader {
  height: 50px;
  min-width: 100%;
  box-sizing: border-box;
  padding: 5px 10px;
  background-color: #f7f7f7;
  border-bottom: 1px solid #d9d9d9;
  border-top: 1px solid #d9d9d9;
}
</style>