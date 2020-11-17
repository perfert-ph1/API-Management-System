/**
 * @description 处理时间戳的函数
 */

/**
 * 给一位数前面补 "0"
 * @param {String} timeStr 待处理的字符串
 */
const _format = (timeStr) => {
  return timeStr < 10 ? "0" + timeStr : timeStr;
}

const getTime = (timestamp) => {
  const now = new Date(timestamp),
    y = now.getFullYear(timestamp),
    m = now.getMonth(timestamp) + 1,
    d = now.getDate(timestamp),
    h = now.getHours(timestamp),
    mm = now.getMinutes(timestamp),
    s = now.getSeconds(timestamp);
  return y + "." + _format(m) + "." + _format(d) + " " + _format(h) + ":" + _format(mm) + ":" + _format(s);
}

module.exports = {
  getTime
}