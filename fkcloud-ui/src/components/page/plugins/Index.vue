<template>
  <div>

        <el-card class="box-card">
          <div>
            <el-row>
              <el-col :span="15">
                <el-card class="box-card" shadow="never">
                  <div slot="header" class="clearfix">
                    <span>概览</span>
                  </div>
                  <div>

                  </div>
                </el-card>
              </el-col>
              <el-col :span="8" :offset="1">
                <el-card class="box-card" shadow="never">
                  <div slot="header" class="clearfix">
                    <span>操作</span>
                  </div>
                  <div>

                  </div>
                </el-card>
              </el-col>
            </el-row>
          </div>
        </el-card>

  </div>
</template>
<style>
  .dialog-footer{

  }
</style>
<script>
export default {
  data () {
    return {
      form: {}
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {})
    }

  }
}
</script>
