<template>
  <div id="main">
    <img src="../../assets/img/ifly-login-logo.png" class="img-rounded" alt="圆角图片">
    <div class="panel">
      <div class="panel-heading">
        <p>用户登录</p>
      </div>
      <div class="panel-body">
        <el-form :model="loginUser" :rules="rules" ref="ruleForm" label-width="0px" class="longin-form">
          <div class="form-group">
            <el-input v-model="loginUser.userName" placeholder="请输入用户名"></el-input>
          </div>
          <div class="form-group">
            <el-input v-model="loginUser.pwd" placeholder="请输入密码"></el-input>
          </div>
          <a href="javascript:void(0)"  class="btn btn-link pull-right update-pwd">
            修改密码
          </a>
        </el-form>
      </div>

    </div>
    <el-button type="primary" @click="submitForm" style="margin-top: 30px;width: 100%">登录</el-button>
  </div>
</template>
<script>
export default {
  data: function () {
    return {
      loginUser: {
        userName: 'admin',
        pwd: '123456'
      },
      rules: {
        userName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
        pwd: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm () {
      var that = this
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          that.$post({
            url: that.$config.login_url,
            data: that.loginUser,
            success: function (data) {
              that.$util.log(data)
              that.$util.cache.putUser(data.body) // 缓存用户
              that.$util.cookie.setCookie(that.$config.tokenKey, data.token, that.$config.cookieTimeOut) // 缓存token
              that.$router.push('/')
            }
          })
        } else {
          console.log('验证不通过')
          return false
        }
      })
    }
  }
}
</script>

<style type="text/css">
  body {
    background-image: url(../../assets/img/ifly-img.png);
    background-color: rgb(32, 80, 129);
    background-size: cover;
  }
  .el-input {
    background: transparent;
    border: 0px;
  }
  .panel-body {
    padding: 8px;
  }
  .panel-heading {
    padding: 5px 10px 0px 15px;
  }

  #main {
    background-color: rgba(25, 27, 74, 0.5);
    height: 100%;
    width: 350px;
    z-index: 1000;
    display: block;
    float: right;
    padding-top: 11%;
    padding-left: 3%;
    padding-right: 3%;
    padding-bottom: 11%;
    font-family: "Microsoft YaHei";
  }

  #main > .panel {
    border-radius: 3px;
  }

  #main > .panel > .panel-heading {
    padding-top: 10px;
    padding-bottom: 10px;
    text-align: center;
    background-color: rgb(239, 239, 239);
    border: none;
  }

  #main > .panel > .panel-body {
    background-color: rgb(239, 239, 239);
    border: none;
  }

  #main > .panel > .panel-heading > p {
    margin-bottom: 0px;
  }

  #main > img {
    margin-bottom: 30px;
    text-align: center;
    padding-left: 9%;
  }
  .form-group{
    margin-top: 20px;
  }
  .longin-form > .form-group > .icon {
    position: absolute;
    margin-top: 8px;
    margin-left: 10px;
  }

  .longin-form > .form-group > input {
    padding-left: 30px;
    border: none;
    background-color: #EFEFEF;
    border: none;
  }

  .longin-form > hr {
    margin-bottom: 10px;
    margin-top: 10px;
  }

  input:-webkit-autofill,
  textarea:-webkit-autofill,
  select:-webkit-autofill {
    box-shadow: 0 0 1000px #EFEFEF inset;
  }

  input.text {
    background-color: green
  }

  .longin-form > .form-group > input:focus {
    box-shadow: 0 0 1000px #EFEFEF inset;
  }

  .form-control {
    box-shadow: none;
  }

  #main > .btn {
    background-color: #0095C5;
    border: none;
    color: white;
    font-weight: normal;
    text-shadow: none;
    font-family: "Microsoft YaHei";
    height: 38px;
    font-size: 16px;
  }

  .update-pwd {
    padding: 0px;
    border: none;
    font-size: 10px;
    color: #5d5d5d;
  }

  .update-pwd:hover {
    background-color: transparent;
  }

  #update-pwd > .modal-dialog,
  #modal-import-zk > .modal-dialog {
    width: 500px;
    border-radius: 0px;
  }

  #update-pwd > .modal-dialog > .modal-content > .modal-header,
  #update-pwd > .modal-dialog > .modal-content > .modal-footer,
  #modal-import-zk > .modal-dialog > .modal-content > .modal-header,
  #modal-import-zk > .modal-dialog > .modal-content > .modal-footer {
    padding-top: 6px;
    padding-bottom: 6px;
  }
</style>
