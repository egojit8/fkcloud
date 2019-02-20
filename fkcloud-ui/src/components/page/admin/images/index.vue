<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-card class="box-card" style="text-align: center" shadow="never">
          <el-input placeholder="请输入应用名称" v-model="search.key" class="input-with-select" style="width: 30%; margin: 20px auto">
            <el-button slot="append" icon="el-icon-search" @click="querySearch"></el-button>
          </el-input>
        </el-card>
      </el-col>
    </el-row>
    <div>
      <el-tabs v-model="activeName" >
        <el-tab-pane label="全部应用" name="all">
          <el-row :gutter="26" style="margin: 20px 20px">
            <el-col  v-for="o in images" :key="o.id" :span="6" >
              <el-card :body-style="{ padding: '0px'}"   >
                <div style="position: relative;padding: 10px; min-height: 140px">
                  <div class="appicon">
                    <img src="../../../../assets/img/mysql.jpeg" class="icon1" />
                  </div>
                  <div style="width: 60%;float: right;">
                    <h3>{{o.name}}</h3>
                    <ul style="list-style-type:none;">
                      <li><span>版本：{{o.version}}</span></li>
                      <li><span>大小：{{o.size}}</span></li>
                      <li><span>默认端口：{{o.defaultPort}}</span></li>
                      <li><span>{{o.remark}}</span></li>
                    </ul>
                  </div>
                  <div style="clear: both"></div>
                </div>
                <div class="bottom" style="clear: both">
                  <el-button type="primary"  v-on:click="install(o)"  plain style="width: 100%; border-radius:0px">安装</el-button>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<style>
  .el-tabs__header{
    background: white;
    margin-bottom: 0px;
  }
  .el-tabs__nav-wrap{
    padding:0px 10px;
  }
  .appicon{
    position: absolute;
    height: 100%;
    width:40%;
    text-align: center;
  }
  .icon1{
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -35px;
    margin-left: -35px;
    height: 70px;
    width: 70px;
  }
</style>
<script>
export default {
  data () {
    return {
      images: [],
      search: {},
      activeName: 'all'
    }
  },
  created: function () {
    this.getData()
  },
  methods: {
    getData: function (call) {
      let that = this
      that.$get({
        url: that.$config.getImages,
        params: that.search,
        success: function (data) {
          that.images = data.list
          if (call) {
            call(this.list)
          }
        }
      })
    },
    querySearch: function () {
      this.getData()
    },
    // 安装
    install: function (app) {
      let that = this
      that.$put({
        url: that.$config.getImages,
        data: app,
        success: function (data) {
          that.$toast(data)
        }
      })
    }
  }
}
</script>
