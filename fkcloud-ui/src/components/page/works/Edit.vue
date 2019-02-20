<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="90px">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="first">
          <el-row>
            <el-col :span="12">
              <el-form-item label="所属系统">
                <el-input
                  :disabled="true">
                </el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务编号" prop="code">
                <el-input v-model="form.code" placeholder="例:rest-ast.chin-v2.0[数字英文-._等字符组成]"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="系统标题" prop="name">
                <el-input v-model="form.name" required="true" placeholder="请输入系统标题"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="服务端口" prop="port">
                <el-input v-model="form.port" placeholder="端口号1-65535"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="服务类型">
                <el-select v-model="form.type" placeholder="请选择">
                  <el-option
                    v-for="item in typeOption"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="JVM参数">
                <el-input v-model="form.javaOpts" placeholder="例：-Xms256M -Xmx1G"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="工作目录" prop="workHome">
                <el-input required="true" v-model="form.workHome" placeholder="例：/iflytek/server/skynet"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="主类或jar" prop="mainJar">
                <el-input required="true" v-model="form.mainJar" placeholder="例：skynet.cloud.App 或 skynet.jar"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="退出信号" >
                <el-select v-model="form.killSignal" placeholder="请选择">
                  <el-option
                    v-for="item in exitTypes"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="服务描述">
                <el-input></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="8">
              <el-form-item label="启用网关">
                <el-select v-model="form.enabledGateway" placeholder="请选择">
                  <el-option
                    v-for="item in selectOption"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="日志收集">
                <el-select v-model="form.isLogCollection" placeholder="请选择">
                  <el-option
                    v-for="item in selectOption"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="启用Eureka">
                <el-select v-model="form.isDiscovery" placeholder="请选择">
                  <el-option
                    v-for="item in selectOption"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
      </el-tab-pane>
        <el-tab-pane label="运行参数" name="second">
          <el-row>
            <el-form-item label="运行参数">
              <el-input type="textarea" size="medium" v-model="form.param"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <div style="color: #999;margin-bottom: 20px">
              常用变量定义：
              <div>本机IP地址：${IP} &nbsp;&nbsp;&nbsp;&nbsp;本服务端口：${PORT} &nbsp;&nbsp;工作目录：${WORK_HOME}</div>
              <div>当前ZK地址：${SKYNET_ZK} &nbsp;&nbsp;&nbsp;&nbsp;Skynet根目录：${SKYNET_HOME}</div>
              <div>
                系统名称：${SKYNET_ACTION_PLUGIN}&nbsp;&nbsp;&nbsp;&nbsp;服务名称：${SKYNET_ACTION_CODE}
              </div>
              <div>ActionId：${SKYNET_ACTION_ID} &nbsp;&nbsp;&nbsp;&nbsp;Action名称：${SKYNET_ACTION_NAME}</div>
            </div>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="环境变量" name="third">
          <el-input type="textarea" size="medium" v-model="form.workArgs"></el-input>
        </el-tab-pane>
        <el-tab-pane label="健康检查" name="fourth">
          <el-row>
            <el-col :span="12">
              <el-form-item label="健康检查">
                <el-select v-model="form.isCheckHealth" placeholder="请选择">
                  <el-option
                    v-for="item in selectOption"
                    :key="item.value"
                    :label="item.name"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="日志配置" name="five">日志配置</el-tab-pane>
        <el-tab-pane label="属性配置" name="six">属性配置</el-tab-pane>
      </el-tabs>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <div style="width: 200px; margin: 0px auto ">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      activeName: 'first',
      selectOption: [
        {
          name: '是',
          value: 'true'
        },
        {
          name: '否',
          value: 'false'
        }
      ],
      typeOption: [
        {
          name: 'SpringBoot',
          value: 'SpringBoot'
        },
        {
          name: 'SkynetBoot',
          value: 'SkynetBoot'
        }
      ],
      exitTypes: [
        {
          name: '1-HUP (hang up)',
          value: '1'
        },
        {
          name: '2-INT (interrupt)',
          value: '2'
        },
        {
          name: '3-QUIT (quit)',
          value: '3'
        },
        {
          name: '6-ABRT (abort)',
          value: '6'
        },
        {
          name: '9-KILL (non-catchable, non-ignorable kill)',
          value: '9'
        }
      ],
      form: {
        code: '',
        name: '',
        type: '',
        mainJar: '',
        killSignal: '9',
        isDiscovery: 'true',
        isLogCollection: 'true',
        enabledGateway: 'true',
        isCheckHealth: 'false'
      },
      rules: {
        code: [
          {required: true, pattern: /^[a-zA-Z][a-zA-Z0-9_.@-]{4,30}$/g, message: '请输入服务编号[数字英文-._等字符组成]', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入服务标题', trigger: 'blur'}
        ],
        port: [
          {type: 'number', message: '请输入1-65535的数字', trigger: 'blur'}
        ],
        mainJar: [
          {required: true, message: '请输入主类或jar', trigger: 'blur'}
        ],
        workHome: [
          {required: true, message: '请输入工作目录', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    submit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    cancel () {
      this.$refs['form'].resetFields()
      this.$closeDialog()
    },
    handleClick () {
      this.$success('测试')
    }
  }
}
</script>
