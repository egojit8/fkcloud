<template>
  <div>
    <el-form ref="form" :model="form" label-width="90px">
      <el-row>
        <el-col :span="24">
          <el-form-item label="系统名称">
            <el-input :rules="[{required: true, message: '系统名称不能为空'}]"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="系统标题">
            <el-input required="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="系统版本">
            <el-input required="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="系统描述">
            <el-input ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
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
