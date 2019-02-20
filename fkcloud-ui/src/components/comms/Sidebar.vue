<template>

  <div class="sidebar" style="height: 100%;background-color: rgb(50, 65, 87)">
    <el-scrollbar style="height: 100%">
      <el-menu style="height: 100%" class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse"
               background-color="#324157"
               text-color="#bfcbd9" active-text-color="#20a0ff" unique-opened router>
        <template v-for="item in items">
          <template v-if="item.subs">
            <el-submenu :index="item.index" :key="item.index">
              <template slot="title">
                <i class="icon egojiticon " :class="item.icon"></i><span slot="title">{{ item.title
                                }}</span>
              </template>
              <el-menu-item v-for="(subItem,i) in item.subs" :key="i" :index="subItem.index">
                {{ subItem.title }}
              </el-menu-item>
            </el-submenu>
          </template>
          <template v-else>
            <el-menu-item :index="item.index" :key="item.index">
              <i class="icon egojiticon " :class=" item.icon"></i><span slot="title">{{ item.title
                            }}</span>
            </el-menu-item>
          </template>
        </template>
      </el-menu>
    </el-scrollbar>
  </div>

</template>

<script>
import bus from './bus'
export default {
  data () {
    return {
      collapse: false,
      items: [
        {
          icon: 'icon-home',
          index: 'dashboard',
          title: '系统首页'
        },
        {
          icon: 'icon-system-setting',
          index: 'images',
          title: '创建应用',
          subs: [
            {
              icon: 'icon-user2',
              index: '/images/index',
              title: '应用市场'
            }
          ]
        },
        {
          icon: 'icon-system-setting',
          index: 'plantform',
          title: '控制台',
          subs: [
            {
              icon: 'icon-user2',
              index: 'action',
              title: '服务管理'
            },
            {
              icon: 'icon-dic',
              index: '/works/run',
              title: '字典管理'
            }
          ]
        },

        {
          icon: 'icon-setting',
          index: '3',
          title: '案例',
          subs: [
            {
              index: 'form',
              title: '基本表单'
            },
            {
              index: 'editor',
              title: '富文本编辑器'
            },
            {
              index: 'markdown',
              title: 'markdown编辑器'
            },
            {
              index: 'upload',
              title: '文件上传'
            },
            {
              icon: 'el-icon-message',
              index: 'tabs',
              title: 'tab选项卡'
            },
            {
              icon: 'el-icon-star-on',
              index: 'charts',
              title: 'schart图表'
            },
            {
              icon: 'el-icon-rank',
              index: 'drag',
              title: '拖拽列表'
            },
            {
              icon: 'el-icon-warning',
              index: 'permission',
              title: '权限测试'
            },
            {
              icon: 'el-icon-error',
              index: '404',
              title: '404页面'
            }
          ]
        }
      ]
    }
  },
  computed: {
    onRoutes () {
      return this.$route.path.replace('/', '')
    }
  },
  created () {
    bus.$on('collapse', msg => {
      this.collapse = msg
    })
    this.getMenus()
  },
  methods: {
    getMenus: function () {
      let that = this
      that.$get({
        url: that.$config.getMenus,
        success: function (data) {
          that.$log(that.items)
          let menus = that.initMenus(data)
          that.$log('处理后菜单：', menus)
          that.items = menus
        }
      })
    },
    initMenus: function (data) {
      let that = this
      let menus = []
      if (data) {
        let tops = this.getTops(data)
        for (let i = 0; i < tops.length; i++) {
          let item = tops[i]
          let m = {
            icon: item.icon,
            index: item.url,
            title: item.title
          }
          if (this.haveChild(item.id, data)) {
            m.subs = that.getSubMenus(item.id, data)
          }
          menus.push(m)
        }
      }
      return menus
    },
    getSubMenus: function (id, data) {
      var that = this
      var menus = []
      for (var i = 0; i < data.length; i++) {
        var item = data[i]
        if (id === item.parentId) {
          var m = {
            icon: item.icon,
            index: item.url,
            title: item.title
          }
          if (this.haveChild(item.id, data)) {
            m.subs = that.getSubMenus(item.id, data)
          }
          menus.push(m)
        }
      }
      return menus
    },
    haveChild: function (id, data) {
      if (data) {
        for (var i = 0; i < data.length; i++) {
          var item = data[i]
          if (id === item.parentId) {
            return true
          }
        }
      }
      return false
    },
    getTops: function (data) {
      var tops = []
      for (var i = 0; i < data.length; i++) {
        var isTop = true
        var obj = data[i]
        for (var j = 0; j < data.length; j++) {
          if (obj.parentId === data[j].id) {
            isTop = false
            break
          }
        }
        if (isTop) {
          tops.push(obj)
        }
      }
      return tops
    }
  }
}
</script>

<style scoped>
  .el-scrollbar__view {
    height: 100%;
  }

  .sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
  }

  .sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
  }

  .sidebar > ul {
    height: 100%;
  }
</style>
