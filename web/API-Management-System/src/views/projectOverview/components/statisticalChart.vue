<template>
  <div class="statisticalChart">
    <template v-for="item in statusList">
      <el-tooltip
        :content="item.status + '：' + item.number"
        placement="top"
        :key="item.status"
      >
        <div
          class="column"
          :style="{ background: item.color, height: item.height }"
        ></div>
      </el-tooltip>
    </template>
  </div>
</template>

<script>
import { statusList } from "@/common/constant.js";

export default {
  name: "statisticalChart",
  props: {
    statusInfo: Object,
  },
  data() {
    return {
      statusList: [],
    };
  },
  mounted() {
    this.getStatusList();
  },
  methods: {
    /**
     * 获得 api 状态统计表柱状图信息
     */
    getStatusList() {
      let numArr = []; // 用来保存每个状态的数量，之后用来计算柱状图的高度
      statusList.forEach((statusInfo) => {
        statusInfo = JSON.parse(JSON.stringify(statusInfo));
        let _status = {
          status: statusInfo.status,
          color: statusInfo.color,
          number: this.statusInfo[statusInfo.status],
        };
        numArr.push(_status.number);
        this.statusList.push(_status);
      });
      let maxNum = Math.max(...numArr);
      this.statusList.forEach((statusInfo) => {
        let height = ((statusInfo.number / maxNum) * 100).toFixed(2) + "%";
        height = height == "0.00%" ? "4px" : height;
        statusInfo.height = height;
      });
    },
  },
};
</script>

<style scoped>
.statisticalChart {
  width: 400px;
  height: 200px;
  border: 1px solid #d9d9d9;
  padding: 10px;
  box-sizing: border-box;
  display: flex;
  justify-content: space-around;
  align-items: flex-end;
}
.column {
  width: 20px;
  border-radius: 8px;
  transform-origin: 50% 100%;
  transform: scaleY(0);
  animation: extend ease-out 1.5s forwards;
  animation-delay: 0.5s;
}
@keyframes extend {
  from {
    transform: scaleY(0);
  }
  to {
    transform: scaleY(1);
  }
}
.statisticalChart /deep/ .el-tooltip {
  outline: none;
}
</style>