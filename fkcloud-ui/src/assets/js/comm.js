import util from './FkVueUtil'
// 这里是重点
const EgojitVuePlugin = {
  install: function (Vue) {
    // 4. 添加实例方法
    Vue.prototype.$showConfirm = function (option) {
      var that = this
      that.$confirm(option.content ? option.content : '提示内容', option.title ? option.title : '确定？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        option.success ? option.success() : that.$log('请检查是否确实success参数')
      }).catch(() => {
        option.cancel ? option.cancel() : that.$log('请检查是否确实cancel参数')
      })
    }
    Vue.prototype.$log = function (msg) {
      if (this.$config.isDebug) {
        console.log(msg)
      }
    }
    Vue.prototype.$showDialog = function (option) {
      option.show = true
      this.$parent.$parent.$data.dialogOption = option
    }
    Vue.prototype.$closeDialog = function () {
      this.$parent.$parent.$data.dialogOption.show = false
    }
    Vue.prototype.$toast = function (msg, type) {
      var that = this
      if (type) {
        if (type === 'error') {
          that.$message.error(msg)
        }
        if (type === 'success') {
          that.$message({
            message: msg,
            type: 'success'
          })
        }
        if (type === 'warning') {
          that.$message({
            message: msg,
            type: 'warning'
          })
        }
      } else {
        that.$message({
          message: msg,
          type: 'success'
        })
      }
    }
    Vue.prototype.$error = function (msg) {
      this.$toast(msg, 'error')
    }
    Vue.prototype.$success = function (msg) {
      this.$toast(msg, 'success')
    }
    /**
     * get获取
     * @param option
     * {
     *  url:请求地址,
     *  data:参数,
     *  success:成功回调,
     *  error:错误回调
     * }
     */
    Vue.prototype.$get = function (option) {
      let that = this
      let auth = util.cookie.getCookie(that.$config.tokenKey)
      let config = {}
      if (option.params) {
        config = {params: option.params}
      }
      if (auth && auth.length > 0) {
        config = Object.assign(config, {headers: {'Authorization': auth}})
      } else {
        that.$router.push('/login')
      }
      that.$showLoading()
      option.url = that.$config.baseHost + option.url
      that.$axios.get(option.url, config)
        .then(function (response) {
          console.log(response)
          if (response.data) {
            if (response.data.code === 200) {
              option.success ? option.success(response.data.body) : that.$success(response.data.body)
            } else if (response.data.code === 401) {
              that.$router.push({path: '/login'})
            } else {
              that.$toast(response.data.body, 'error')
            }
          } else {
            that.$error('未知错误')
          }
          that.$closeLoading()
        })
        .catch(function (error) {
          that.$closeLoading()
          that.$error('网络错误')
          console.log(error)
        })
    }

    /**
     * delete
     * @param option
     * {
     *  url:请求地址,
     *  data:参数,
     *  success:成功回调,
     *  error:错误回调
     * }
     */
    Vue.prototype.$delete = function (option) {
      var that = this
      var auth = util.cookie.getCookie(that.$config.tokenKey)
      var config = {}
      if (option.data) {
        config = {data: option.data}
      }
      if (auth && auth.length > 0) {
        config = Object.assign(config, {headers: {'Authorization': auth}})
      } else {
        that.$router.push('/login')
      }
      that.$showLoading()
      option.url = that.$config.baseHost + option.url
      that.$axios.delete(option.url, config)
        .then(function (response) {
          console.log(response)
          if (response.data) {
            if (response.data.code === 200) {
              option.success ? option.success(response.data.body) : (response.data.body ? that.$success(response.data.body) : that.$success('删除成功'))
            } else if (response.data.code === 401) {
              that.$router.push({path: '/login'})
            } else {
              that.$toast(response.data.body, 'error')
            }
          } else {
            that.$error('未知错误')
          }
          that.$closeLoading()
        })
        .catch(function (error) {
          that.$closeLoading()
          that.$error('网络错误')
          console.log(error)
        })
    }
    /**
     * post 提交
     * @param option
     */
    Vue.prototype.$post = function (option) {
      let that = this
      let auth = util.cookie.getCookie(that.$config.tokenKey)
      let config = {}
      if (option.parm) {
        config = {params: option.parm}
      }
      if (auth && auth.length > 0) {
        config = Object.assign(config, {headers: {'Authorization': auth}})
      } else {
        that.$router.push('/login')
      }
      that.$showLoading()
      option.url = that.$config.baseHost + option.url
      // that.$axios.defaults.withCredentials = true
      // 发送 POST 请求
      that.$axios.post(option.url, option.data, config)
        .then(function (response) {
          console.log(response)
          if (response.data) {
            if (response.data.code === 200) {
              option.success ? option.success(response.data.body) : that.$success(response.data.body)
            } else if (response.data.code === 401) {
              that.$router.push({path: '/login'})
            } else {
              that.$toast(response.data.body, 'error')
            }
          } else {
            that.$toast('未知错误', 'error')
          }
          that.$closeLoading()
        })
        .catch(function (error) {
          that.$toast('网络错误', 'error')
          console.log(error)
          that.$closeLoading()
        })
    }
    /**
     * put 提交
     * @param option
     */
    Vue.prototype.$put = function (option) {
      let that = this
      let auth = util.cookie.getCookie(that.$config.tokenKey)
      let config = {}
      if (option.parm) {
        config = {params: option.parm}
      }
      if (auth && auth.length > 0) {
        config = Object.assign(config, {headers: {'Authorization': auth}})
      } else {
        that.$router.push('/login')
      }
      that.$showLoading()
      option.url = that.$config.baseHost + option.url
      // that.$axios.defaults.withCredentials = true
      // 发送 POST 请求
      that.$axios.put(option.url, option.data, config)
        .then(function (response) {
          console.log(response)
          if (response.data) {
            if (response.data.code === 200) {
              option.success ? option.success(response.data.body) : that.$success(response.data.body)
            } else if (response.data.code === 401) {
              that.$router.push({path: '/login'})
            } else {
              that.$toast(response.data.body, 'error')
            }
          } else {
            that.$toast('未知错误', 'error')
          }
          that.$closeLoading()
        })
        .catch(function (error) {
          that.$toast('网络错误', 'error')
          console.log(error)
          that.$closeLoading()
        })
    }

    // 显示进度条
    Vue.prototype.$showLoading = function () {
      var that = this
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      that.loading = loading
    }
    //  关闭经度条
    Vue.prototype.$closeLoading = function () {
      var that = this
      if (that.loading) {
        that.loading.close()
      }
    }

    // 表单提交
    Vue.prototype.$submit = function (option) {
      let that = this
      let form = option.form ? option.form : 'form'
      that.$refs[form].validate((valid) => {
        if (valid) {
          that.$post({
            url: option.url,
            data: option.data,
            success: function (data) {
              option.success ? option.success(data) : (data ? that.$success(data) : that.$success('提交成功'))
            }
          })
        }
        return false
      })
    }
  }
}
export default EgojitVuePlugin
