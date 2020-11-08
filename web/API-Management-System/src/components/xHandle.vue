<template>
  <div class="x-handle" @mousedown="mouseDown"></div>
</template>

<script>
export default {
  name: "xHandle",
  data() {
    return {
      lastX: "",
    };
  },

  created() {
    document.addEventListener("mouseup", this.mouseUp);
  },

  destroyed() {
    document.removeEventListener("mouseup", this.mouseUp);
  },

  methods: {
    mouseDown(event) {
      document.addEventListener("mousemove", this.mouseMove);
      this.lastX = event.screenX;
    },
    mouseMove(event) {
      this.$emit("widthChange", this.lastX - event.screenX);
      this.lastX = event.screenX;
    },
    mouseUp() {
      this.lastX = "";
      document.removeEventListener("mousemove", this.mouseMove);
    },
  },
};
</script>
<style scoped>
.x-handle {
  width: 4px;
  height: 100%;
  cursor: col-resize;
  z-index: 10;
  position: absolute;
  right: 0;
  background: #ccc;
}
</style>