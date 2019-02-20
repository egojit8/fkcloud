<template>
  <div id="app" class="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  @import "./assets/css/main.css";
  @import "./assets/css/color-dark.css";     /*深色主题*/
  * {
    padding: 0px;
    margin: 0px;
  }
  body {
    background: #efefef;
    color: rgba(0,0,0,.65);
  }
  .el-table td, .el-table th {
    padding: 6px 0px;
  }
  .el-button {
    padding: 8px 18px;
  }
  .easyweb-list-buttom-icon {
    padding: 5px;
    cursor:pointer
  }
</style>
