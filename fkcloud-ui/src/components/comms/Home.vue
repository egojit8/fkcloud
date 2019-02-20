<template>
  <div class="wrapper">
    <v-head></v-head>
    <v-sidebar></v-sidebar>
    <div class="content-box" :class="{'content-collapse':collapse}">
      <v-tags></v-tags>
      <el-scrollbar style="height: 100%">
        <div class="content" v-bind:style="{height:boxHeight}" style="padding: 0px">
          <transition name="move" mode="out-in">
            <keep-alive>
              <router-view></router-view>
            </keep-alive>
          </transition>
        </div>
      </el-scrollbar>
    </div>
    <el-dialog :title="dialogOption.title" :visible.sync="dialogOption.show">
      <div :is="dialogOption.component"></div>
    </el-dialog>
  </div>
</template>
<script>
import vHead from './Header.vue'
import vSidebar from './Sidebar.vue'
import vTags from './Tags.vue'
import bus from './bus'

export default {
  data () {
    return {
      dialogOption: {
        show: false,
        component: undefined,
        title: '提示'
      },
      collapse: false,
      boxHeight: '500px',
      opt: {
        barColor: '#959595',
        barWidth: 6,
        railColor: '#eee',
        barMarginRight: 0,
        barMaginBottom: 0,
        barOpacityMin: 0.3,
        zIndex: 'auto',
        autohidemode: true,
        horizrailenabled: false
      }
    }
  },
  components: {
    vHead, vSidebar, vTags
  },
  beforeMount () {
    const that = this
    /**
     * 首先在Virtual DOM渲染数据时，设置下背景图的高度．
     */
    var h = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
    that.boxHeight = (h - 105) + 'px'
    window.onresize = function temp () {
      var h1 = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
      that.boxHeight = (h1 - 105) + 'px'
    }
  },
  methods: {
  },
  created () {
    bus.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>
<style>
  .wrapper{
    width: 100%;
    height: 100%;
    background: #efefef;
  }
</style>
