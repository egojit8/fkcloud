
/**
 * 列表操作
 * @author 高露
 */
function List () {
}
List.prototype.init = function (option) {
  let that = this
  that.url = option.url
  that.vue = option.vue
  that.params = option.params
  that.refresh()
}

List.prototype.refresh = function (option) {
  let that = this
  that.data = (option && option.params) ? option.params : that.params
  that.vue.$get({
    url: that.url,
    params: that.params,
    success: function (data) {
      that.vue.tableData = data.rows
      that.vue.total = data.rows.total
    }
  })
}
var vueList = new List()
const vueListPlugin = {
  install: function (Vue) {
    Vue.prototype.$list = vueList
  }
}

export default vueListPlugin
