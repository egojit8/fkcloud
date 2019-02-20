import config from './config'
function FkVueUtil () {
  this.version = {
    version: 'v1.0.0'
  }
  this.getIds = function (objs) {
    let ids = []
    for (var i = 0; i < objs.length; i++) {
      ids.push(objs[i].id)
    }
    return ids
  }
  this.log = function () {
    if (config.isDebug) {
      if (arguments) {
        for (var i = 0; i < arguments.length; i++) {
          console.log(arguments[i])
        }
      }
    }
  }
}
// cookie
FkVueUtil.prototype.cookie = {
  setCookie: function (cname, cvalue, exminute) {
    var d = new Date()
    d.setTime(d.getTime() + (exminute * 60 * 1000))
    var expires = 'expires=' + d.toUTCString()
    console.info(cname + '=' + cvalue + '; ' + expires)
    document.cookie = cname + '=' + cvalue + '; ' + expires
    console.info(document.cookie)
  },
  refreshCookie: function (exminute) {

  },
  getCookie: function (cname) {
    let name = cname + '='
    let ca = document.cookie.split(';')
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i]
      while (c.charAt(0) === ' ') c = c.substring(1)
      if (c.indexOf(name) !== -1) return c.substring(name.length, c.length)
    }
    return ''
  },
  // 清除cookie
  clearCookie: function (name) {
    this.setCookie(name, '', -1)
  }
}
FkVueUtil.prototype.cache = {
  put: function (key, value) {
    localStorage.setItem(key, value)
  },
  get: function (key) {
    return localStorage.getItem(key)
  },
  clear: function () {
    return localStorage.clear()
  },
  putUser: function (user) {
    let userStr = JSON.stringify(user)
    fkVueUtil.log('缓存的用户信息：', userStr)
    this.put(config.userKey, userStr)
  },
  getUser: function () {
    let strUser = this.get(config.userKey)
    if (strUser) {
      fkVueUtil.log('获取到缓存的用户信息：', strUser)
      let user = JSON.parse(strUser)
      return user
    } else {
      return undefined
    }
  }
}
let fkVueUtil = new FkVueUtil()
export default fkVueUtil
