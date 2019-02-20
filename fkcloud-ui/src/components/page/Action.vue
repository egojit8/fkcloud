<template>
  <el-row>
    <el-col :span="5" >
      <div ref="left" :style="{height: h}" style="height: 100%;background-color: white; margin-right: 20px;padding-right: 10px">
        <el-input placeholder="请输入内容" class="input-with-select">
          <el-button slot="append" icon="el-icon-search"></el-button>
        </el-input>
        <el-menu
          default-active="1"
          class="el-menu-vertical-demo" style="height: 100%">
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>系统一</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="1-1">服务一</el-menu-item>
              <el-menu-item index="1-2">服务二</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title">系统二</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <i class="el-icon-document"></i>
            <span slot="title">系统三</span>
          </el-menu-item>
          <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">系统四</span>
          </el-menu-item>
          <el-menu-item index="5">
            <i class="el-icon-setting"></i>
            <span slot="title">系统五</span>
          </el-menu-item>

        </el-menu>
      </div>
    </el-col>
    <el-col :span="19" >
      <div ref="right">
        <el-card style="background: white">
          <div slot="header" class="clearfix">
            <span>系统列表</span>
            <el-dropdown style="float: right">
              <span class="el-dropdown-link">更多<i class="el-icon-arrow-down el-icon--right"></i></span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>集群属性配置</el-dropdown-item>
                <el-dropdown-item>集群日志设置</el-dropdown-item>
                <el-dropdown-item>导出服务定义</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <el-button type="text" @click="add" style="margin-left: 40px" ><i class="el-icon-edit"></i>新增-应用系统</el-button>
            <el-button type="text" @click="addWork" style="margin-left: 20px" ><i class="el-icon-edit"></i>新增-服务定义</el-button>
          </div>
          <el-tabs v-model="activeName" style="margin-left: 20px">
            <el-tab-pane label="服务详情" name="first">
              <el-card >
                <div slot="header" class="clearfix">
                  <span>基础信息</span>
                </div>
                <el-row class="app-detail-row">
                  <el-col :span="8"><label class="app-lbl">所属系统:</label><span class="app-txt">系统一</span></el-col>
                  <el-col :span="8"><label class="app-lbl">服务编号:</label><span class="app-txt">ant-server</span></el-col>
                  <el-col :span="8"><label class="app-lbl">服务标题:</label><span class="app-txt">服务一</span></el-col>
                </el-row>
                <el-row class="app-detail-row">
                  <el-col :span="8"><label class="app-lbl">服务端口:</label><span class="app-txt">9090</span></el-col>
                  <el-col :span="8"><label class="app-lbl">服务类型:</label><span class="app-txt">springboot</span></el-col>
                  <el-col :span="8"><label class="app-lbl">JVM参数:</label><span class="app-txt">-Xms256M -Xmx1G</span></el-col>
                </el-row>
                <el-row class="app-detail-row">
                  <el-col :span="8"><label class="app-lbl">退出信号:</label><span class="app-txt">9 - KILL (non-catchable, non-ignorable kill)</span></el-col>
                  <el-col :span="8"><label class="app-lbl">启用网关:</label><span class="app-txt">是</span></el-col>
                  <el-col :span="6"><label class="app-lbl">日志收集:</label><span class="app-txt">是</span></el-col>
                </el-row>
              </el-card>
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>运行参数</span>
                </div>

                <el-row class="app-detail-row">
                  <el-col :span="24">
                  <span class="app-txt">
                  {
    "name": "skynet.cloud.eureka",
    "context": {
        "svc": "default"
    }
                </span></el-col>
                </el-row>
              </el-card>
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>环境变量</span>
                </div>

                <el-row class="app-detail-row">
                  <el-col :span="24">
                  <span class="app-txt">
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                  </span>
                  </el-col>
                </el-row>
              </el-card>
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>健康检查</span>
                </div>
                <el-row>
                  健康检查未开启
                </el-row>
              </el-card>
              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>属性配置</span>
                </div>

                <el-row class="app-detail-row">
                  <el-col :span="24">
                  <span class="app-txt">
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                  </span>
                  </el-col>
                </el-row>
              </el-card>

              <el-card class="box-card">
                <div slot="header" class="clearfix">
                  <span>日志配置</span>
                </div>

                <el-row class="app-detail-row">
                  <el-col :span="24">
                  <span class="app-txt">
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                    SKYNET_HOME=/iflytek/server/skynet<br/>
                  </span>
                  </el-col>
                </el-row>
              </el-card>
            </el-tab-pane>
            <el-tab-pane label="服务依赖关系图" name="second"></el-tab-pane>
          </el-tabs>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<script>

import PluginActionEdit from './plugins/Edit'
import workEdit from './works/Edit'
export default {
  data () {
    return {
      activeName: 'first',
      h: '500px'
    }
  },
  created () {
    this.getPlugin()
  },
  mounted () {
    this.h = this.$refs['right'].offsetHeight + 'px'
  },
  methods: {
    add: function () {
      this.$showDialog({
        title: '系统编辑',
        component: PluginActionEdit,
        onOk: function () {
          PluginActionEdit.submit()
        }
      })
    },
    addWork: function () {
      this.$log.info('test', '添加')
      this.$showDialog({
        title: '服务编辑',
        component: workEdit,
        onOk: function () {
          this.component.submit()
          // JSESSIONID=3bbp27mrdaat1s61s6x4ihyrf
        }
      })
    },
    getPlugin: function () {
      this.$get({
        url: this.$config.get_plugin,
        success: function (data) {
        }
      })
    }
  }
}
</script>

<style>
  .box-card{
    margin-top: 24px;
  }
  .left {
    background: white;
  }
  .left ul {
    list-style-type: none;
    margin: 10px;
  }
  .right{
  }
  .top {
    background: white;
    height: 60px;
  }
</style>
