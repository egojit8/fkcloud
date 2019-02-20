<template>
  <div style="height: 100%">
    <el-row v-if="apps&&apps.length>0" style="height: 100%">
      <el-col :span="5" style="height: 100%">
        <el-menu style="height: 100%"
                 :default-active="defalutSelect"
                 class="el-menu-vertical-demo"
                 @select="onSelect"
        >
          <el-submenu  :index="group.id" v-for="group in groups" :key="group.id">
            <template slot="title">
              <i class="el-icon-tickets"></i>
              <span>{{group.title}}</span>
            </template>
            <el-menu-item-group>
              <el-menu-item v-for="app in getAppByGroup(group)" :index="app.containerId" :key="app.id">{{app.title}}</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
      <el-col :span="19">
        <div style="width: 100%;background: white;position: relative;padding: 20px 0px">
          <h2 style="width: 30%;float: left;margin-left: 20px">{{appDetail.Name}}</h2>
          <div style="width: 60%;float: right;text-align: right">
            <el-button-group>
              <el-button v-if="appDetail.State.Running" type="primary" @click="onStop" icon="el-icon-edit">停止</el-button>
              <el-button v-else type="primary" @click="onStart" icon="el-icon-share">启动</el-button>
              <el-button v-if="appDetail.State.Running" type="primary" @click="onRestart" icon="el-icon-share">重启</el-button>
              <el-button type="primary" @click="onDelete" icon="el-icon-delete">删除</el-button>
            </el-button-group>
            <el-dropdown style="margin-left: 20px;margin-right: 20px">
              <el-button type="primary">
                更多操作<i class="el-icon-arrow-down el-icon--right"></i>
              </el-button>
              <el-dropdown-menu slot="dropdown" >
                <el-dropdown-item>管理容器</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
          <div style="clear: both"></div>
        </div>
        <el-tabs v-model="activeName">
          <el-tab-pane label="总览" name="first">
            <div style="margin:20px">
              <el-row :gutter="20" >
                <el-col :span="12">
                  <el-card class="box-card" >
                    <div slot="header" class="clearfix">
                      <span>应用状态（{{appDetail.State.Status}}）</span>
                    </div>
                    <div style="text-align: center;min-height: 80px">
                      <i v-if="appDetail.State!=null&&appDetail.State.Running" class="el-icon-loading runing"></i>
                      <i v-else class="el-icon-error stoped"></i>
                    </div>
                  </el-card>
                </el-col>
                <el-col :span="12">
                  <el-card class="box-card" >
                    <div slot="header" class="clearfix">
                      <span>资源使用</span>
                    </div>
                    <div style="text-align: center;min-height: 80px">
                      待升级
                    </div>
                  </el-card>
                </el-col>
              </el-row>
              <el-card class="box-card" style="margin-top:40px; min-height: 200px" >
                <div slot="header" class="clearfix">
                  <span>操作日志</span>
                </div>
                <div style="text-align: center">
                  待升级
                </div>
              </el-card>
            </div>

          </el-tab-pane>
          <!--端口-->
          <el-tab-pane label="端口" name="second" >
            <el-card class="box-card" style="margin: 20px">
              <div slot="header" class="clearfix">
                <span>端口信息</span>
                <!--<el-button icon="el-icon-plus" style="float: right; padding: 3px 0" type="text">添加端口</el-button>-->
              </div>
              <div>
                <el-table
                  :data="net"
                  border
                  style="width: 100%">
                  <el-table-column
                    prop="port"
                    label="端口号"
                    width="80">
                  </el-table-column>
                  <el-table-column
                    prop="portal"
                    label="端口协议"
                    width="80">
                  </el-table-column>
                  <el-table-column
                    prop="addressInside"
                    label="内部信息"
                    width="300"
                  >
                    <template slot-scope="scope">
                      <el-row>
                        <el-col :span="12">内部地址</el-col>
                        <el-col :span="12">
                          {{scope.row.addressInside.address}}
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="12">别名访问</el-col>
                        <el-col :span="12">
                          {{scope.row.addressInside.aliasName}}
                        </el-col>
                      </el-row>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="addressOut"
                    label="外部信息"
                  >
                    <template slot-scope="scope">
                      <el-row>
                        <el-col :span="12">是否开启</el-col>
                        <el-col :span="12">
                          <el-switch
                            active-text=""
                            @change="onOpenOrClose(scope.row)"
                            style="display: block"
                            v-model="scope.row.addressOut.isOpen"></el-switch>
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="12">外部</el-col>
                        <el-col :span="12">
                          {{scope.row.addressOut.address}}
                        </el-col>
                      </el-row>
                    </template>
                  </el-table-column>
                  <el-table-column
                    prop="address"
                    label="操作"
                    width="120"
                  >
                    <template slot-scope="scope">
                      <i class="el-icon-delete"></i>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </el-card>
          </el-tab-pane>
          <!--/端口-->
          <el-tab-pane label="伸缩" name="third">伸缩</el-tab-pane>
          <el-tab-pane label="设置" name="four">设置</el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
    <div v-else style="text-align: center;margin-top: 200px">
      <i class="el-icon-goods" style="font-size: 70px;color: #409EFF">
      </i>
      <h1>暂无数据</h1>
    </div>
  </div>
</template>
<style>
  .el-tabs__nav{
    margin: 0px 10px;
  }
  .el-tabs__item{
    width: 80px;
    text-align: center;
  }
  .el-tabs__nav-wrap{
    background: white;
  }
  .el-card__body{
    padding: 10px;
  }
  .runing{
    font-size: 80px;
    color: green;
  }
  .stoped{
    font-size: 80px;
    color: red;
  }
</style>
<script>
export default {
  data () {
    return {
      images: [],
      search: {},
      activeName: 'first',
      apps: [],
      groups: [],
      defalutSelect: undefined,
      appDetail: {
        State: {}
      },
      ws: undefined,
      net: [],
      value4: true
    }
  },
  created: function () {
    this.getRuningApps()
  },
  methods: {
    getRuningApps: function () {
      let that = this
      that.$get({
        url: that.$config.runingApp,
        success: function (data) {
          if (data && data.length > 0) {
            that.apps = data
            that.defalutSelect = that.apps[0].containerId
            that.groups = that.getGroups(data)
            that.getAppDetail(that.defalutSelect) // index==container id
          }
        }
      })
    },
    getAppByGroup: function (group) {
      let that = this
      let apps = []
      if (that.apps != null && that.apps.length > 0) {
        for (let i = 0; i < that.apps.length; i++) {
          let a = this.apps[i]
          if (group.id === this.apps[i].group.id) {
            apps.push(a)
          }
        }
      }
      return apps
    },
    // 获取组
    getGroups: function (list) {
      let groups = []
      for (let i = 0; i < list.length; i++) {
        let app = list[i]
        if (!this.isExist(groups, app)) {
          groups.push(app.group)
        }
      }
      return groups
    },
    isExist: function (groups, app) {
      let isExit = false
      for (let i = 0; i < groups.length; i++) {
        if (groups[i].title === app.group.title) {
          isExit = true
        }
      }
      return isExit
    },
    onSelect: function (index) {
      this.defalutSelect = index
      this.getAppDetail(index) // index==container id
    },
    getAppDetail: function (id) {
      let that = this
      that.$get({
        url: that.$config.runingApp + '/' + id,
        success: function (data) {
          that.appDetail = data
          that.initNet()
        }
      })
      // if (that.ws) {
      //   that.ws.close()
      // }
      // that.initSocket(id)
    },
    // 端口初始化
    initNet: function () {
      let that = this
      that.net = []
      if (that.appDetail.Config.ExposedPorts && that.appDetail.Config.ExposedPorts) {
        let exposedPorts = that.appDetail.Config.ExposedPorts
        if (exposedPorts) {
          Object.keys(exposedPorts).forEach(function (key) {
            console.log(key, exposedPorts[key])
            let portAndPotal = key.split('/')
            let host = that.appDetail.HostConfig.PortBindings[key][0]
            console.log('绑定：', that.appDetail.HostConfig.PortBindings[key].length > 0)
            // let nt = that.appDetail.HostConfig.PortBindings && that.appDetail.HostConfig.PortBindings.length > 0
            // console.log('是否绑定：', that.appDetail.HostConfig.PortBindings && that.appDetail.HostConfig.PortBindings.length > 0, nt)
            that.net.push({containerPort: portAndPotal[0],
              portal: portAndPotal[1],
              port: host.HostPort,
              addressInside: {
                address: that.appDetail.NetworkSettings.IPAddress + ':' + host.HostPort,
                aliasName: that.appDetail.Name
              },
              addressOut: {
                address: host.HostIp === '' ? '暂无' : ':' + host.HostPort,
                isOpen: that.appDetail.HostConfig.PortBindings[key] && that.appDetail.HostConfig.PortBindings[key].length > 0
              }
            })
          })
        }
      }
    },
    // 停止
    onStop: function () {
      let that = this
      that.$put({
        url: that.$config.appStop + '/' + that.defalutSelect,
        success: function (data) {
          that.$toast(data)
          that.appDetail.State.Running = false
        }
      })
    },
    // 启动
    onStart: function () {
      let that = this
      that.$put({
        url: that.$config.appStart + '/' + that.defalutSelect,
        success: function (data) {
          that.$toast(data)
          that.appDetail.State.Running = true
        }
      })
    },
    onDelete: function () {
      let that = this
      that.$showConfirm({title: '确定删除',
        content: '确定删除此程序？',
        success: function () {
          that.$delete({
            url: that.$config.runingApp + '/' + that.defalutSelect,
            success: function (data) {
              that.$toast(data)
            }
          })
        }})
    },
    // 重启
    onRestart: function () {
      let that = this
      that.$put({
        url: that.$config.appRestart + '/' + that.defalutSelect,
        success: function (data) {
          that.$toast(data)
        }
      })
    },
    onSwitchClick: function (data) {
      console.log(data)
    },
    onOpenOrClose: function (data) {
      console.log(data)
      let that = this
      let cfContent = '服务将会重启！确定关闭外部访问端口？'
      if (data.addressOut.isOpen) {
        cfContent = '服务将会重启！确定打开外部访问端口？'
      }
      that.$showConfirm({title: '确定',
        content: cfContent,
        success: function () {
          that.$put({
            url: that.$config.runingApp + '/' + that.defalutSelect + '/' + data,
            success: function (data) {
              that.$toast(data)
            }
          })
        },
        cancel: function () {
          data.addressOut.isOpen = !data.addressOut.isOpen
        }
      })
    },
    initSocket: function (containerId) {
      let that = this
      that.ws = new WebSocket(that.$config.webSocket + '/stats/' + containerId)
      that.ws.onopen = function () {
        console.log('open')
      }
      that.ws.onmessage = function (evt) {
        console.log(evt.data)
        // let obj = eval('(' + evt.data + ')')
        // console.log(obj)
      }
    }
  }
}
</script>
