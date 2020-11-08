/**
 * @description 保存常量
 * @author Lee
 */

const statusList = [
  {
    status: "已发布",
    color: "#58a942"
  },
  {
    status: "设计",
    color: "#9c27b0"
  },
  {
    status: "待定",
    color: "#ffcc00"
  },
  {
    status: "开发",
    color: "#1890ff"
  },
  {
    status: "对接",
    color: "#1890ff"
  },
  {
    status: "测试",
    color: "#1890ff"
  },
  {
    status: "已完成",
    color: "#8bc34a"
  },
  {
    status: "异常",
    color: "#ea0707"
  },
  {
    status: "维护",
    color: "#f18f00"
  },
  {
    status: "废弃",
    color: "#999999"
  },
]

const requestMethodList = [
  {
    method: "POST",
    abbreviation: "POST",
    color: "#58a942"
  }, {
    method: "GET",
    abbreviation: "GET",
    color: "#1890ff"
  }, {
    method: "PUT",
    abbreviation: "PUT",
    color: "#6d4c41"
  }, {
    method: "DELETE",
    abbreviation: "DEL",
    color: "#ea0707"
  }, {
    method: "HEAD",
    abbreviation: "HEAD",
    color: "#ff8f00"
  }, {
    method: "OPTIONS",
    abbreviation: "OPTS",
    color: "#546e7a"
  }, {
    method: "PATCH",
    abbreviation: "PATCH",
    color: "#f18f00"
  }
]

module.exports = {
  statusList,
  requestMethodList
}