<template>
  <div class="container">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card body-style="{padding:20px}" shadow="never" >
          <div slot="header" class="clearfix">
            <el-input
              placeholder="输入关键字进行搜索"
              v-model="searchKey"
              clearable
            >
            </el-input>
          </div>
          <el-tree :expand-on-click-node="false"
            ref="treeDic" node-key="id" :filter-node-method="onTreeSearch" :data="treeData" :props="defaultProps" :default-expanded-keys="expandNodes" @node-click="onTreeNodeClick">
            <span class="custom-tree-node" slot-scope="{ node, data }">
              <span>{{ node.label }}</span>
              <span>
                <el-button type="text" size="mini" @click.stop="add(data)">添加</el-button>
              </span>
            </span>
          </el-tree>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-row>
          <el-col :spn="24">
            <el-form ref="form" :rules="rules" :model="form" label-width="100px">
              <el-row>
                <el-col :span="24">
                  <el-form-item label="父字典">
                    <el-input v-model="form.parentName" ></el-input>
                    <input type="hidden" v-model="form.parentId">
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="字典名称" prop="label">
                    <el-input v-model="form.label" placeholder="请输入字典名称"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="字典值" prop="value">
                    <el-input v-model="form.value" placeholder="请输入字典值"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item label="字典描述">
                    <el-input
                      type="textarea"
                      :autosize="{ minRows: 2, maxRows: 4}"
                      placeholder="请输入内容"
                      v-model="form.remarks">
                    </el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item>
                <el-button  type="primary" icon="el-icon-check" @click="submitForm('ruleForm')">保存</el-button>
                <el-button  icon="el-icon-refresh" @click="resetForm('ruleForm')">重置</el-button>
                <el-button  type="danger" @click="onDelete()" icon="el-icon-delete">删除</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  watch: {
    searchKey (val) {
      this.$refs.treeDic.filter(val)
    }
  },
  data () {
    return {
      tableData: [],
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      rules: {
        label: [
          { required: true, message: '请输入字典名称', trigger: 'blur' },
          { min: 1, max: 40, message: '长度在 1 到 40 个字符', trigger: 'blur' }
        ],
        value: [
          { required: true, message: '请输入字典值', trigger: 'blur' },
          { min: 1, max: 40, message: '长度在 1 到 40 个字符', trigger: 'blur' }
        ]
      },
      expandNodes: [],
      treeData: [],
      searchKey: '',
      selectData: undefined,
      form: {
        name: '',
        loginName: ''
      }
    }
  },
  beforeCreate () {

  },
  created () {
    this.getDicTree()
  },
  methods: {
    getDicTree () {
      let that = this
      that.$get({
        url: that.$config.upms_dic,
        data: that.parm,
        success: function (data) {
          let tree = []
          let expend = []
          if (data) {
            let tops = that.getTops(data)
            for (let i = 0; i < tops.length; i++) {
              let item = tops[i]
              // 获取父字典
              item.parentName = '无父字典'
              expend.push(item.id)
              if (that.haveChild(item.id, data)) {
                item.children = that.getSub(item, data)
              }
              tree.push(item)
            }
          }
          that.treeData = tree
          console.log('展开的节点', expend)
          that.expandNodes = expend
        }
      })
    },
    onTreeNodeClick (data) {
      console.log(data)
      this.selectData = data
      let obj = Object.assign({}, data)
      this.form = obj
    },
    add (data) {
      this.$log('添加', data)
      this.selectData = data
      this.form = {parentId: data.id, parentName: data.label}
    },
    submitForm () {
      let that = this
      that.$submit({
        url: that.$config.upms_dic,
        data: that.form,
        success: function (data) {
          // if (!that.selectData.children) {
          //   that.$set(that.selectData, 'children', [])
          // }
          // if (that.form.id) { // 编辑
          //   Object.assign(that.selectData, that.form)
          // } else { // 新增
          //   that.selectData.children.push(data)
          // }
          that.resetForm()
          that.getDicTree()
          that.$success('添加成功！')
        }
      })
    },
    resetForm () {
      this.form = {parentId: this.form.parentId, parentName: this.form.parentName}
    },
    onDelete (index, row) {
      let that = this
      let ids = [that.form.id]
      that.$delete({
        url: that.$config.upms_dic,
        data: ids,
        success: function (data) {
          that.getDicTree()
          that.$success('删除成功！')
        }
      })
    },
    getSub: function (parent, data) {
      let that = this
      let menus = []
      for (let i = 0; i < data.length; i++) {
        let item = data[i]
        if (parent.id === item.parentId) {
          if (that.haveChild(item.id, data)) {
            item.children = that.getSub(item, data)
          }
          item.parentName = parent.label
          menus.push(item)
        }
      }
      return menus
    },
    haveChild: function (id, data) {
      if (data) {
        for (let i = 0; i < data.length; i++) {
          let item = data[i]
          if (id === item.parentId) {
            return true
          }
        }
      }
      return false
    },
    getTops: function (data) {
      let tops = []
      for (let i = 0; i < data.length; i++) {
        let isTop = true
        let obj = data[i]
        for (let j = 0; j < data.length; j++) {
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
    },
    // 字典搜搜
    onTreeSearch: function (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    }
  }
}
</script>

<style scoped>
  .handle-box {
    margin-bottom: 10px;
  }

  .el-row {
    margin-bottom: 10px;
  }

  .el-cl {
    width: 120px;
  }
  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
</style>
