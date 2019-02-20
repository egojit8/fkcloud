<template>
  <div class="container">
    <el-row :gutter="5">
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
                   :default-expand-all="true"
                   ref="officeTree" node-key="id" :filter-node-method="onTreeSearch" :data="treeData" :props="defaultProps"  @node-click="onTreeNodeClick">
            <span class="custom-tree-node" >
              <!--<span>{{ node.label }}</span>-->
              <span>
                <el-button type="text" size="mini" @click.stop="add(data)">添加</el-button>
              </span>
            </span>
          </el-tree>
        </el-card>
      </el-col>
      <el-col :span="18">
        <div class="handle-box">
          <el-form ref="form" :model="form" label-width="80px">
            <el-row>
              <el-col :span="6">
                <el-form-item label="名称">
                  <el-input v-model="form.name"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item label="登录名">
                  <el-input v-model="form.loginName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="6">
              </el-col>
              <el-col :span="6" style="float: right">
                <el-button  type="info" ref="btnRest" icon="el-icon-refresh" style="float: right; margin-left: 10px">重置
                </el-button>
                <el-button type="primary" ref="btnSearch" icon="el-icon-search" style="float: right">搜索
                </el-button>
              </el-col>
            </el-row>
          </el-form>
          <el-row>
            <el-col :span="6">
              <el-button type="primary" icon="el-icon-plus" class="handle-del mr10" @click="onEdit()">添加
              </el-button>
              <el-button type="danger" @click="onDelete()" icon="el-icon-delete">删除</el-button>
            </el-col>
            <el-col :span="6">
              <div class="grid-content bg-purple-light"></div>
            </el-col>
            <el-col :span="6">
              <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :span="6">
              <div class="grid-content bg-purple-light"></div>
            </el-col>
          </el-row>
        </div>
        <el-table @selection-change="onSelectChange" :header-row-style="{textAlign:'center'}" :data="tableData" border style="width: 100%" ref="list">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="name" label="名称">
          </el-table-column>
          <el-table-column prop="mobile" label="手机号" width="130">
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="130">
          </el-table-column>
          <el-table-column prop="createDate" label="日期" width="200">
          </el-table-column>
          <el-table-column label="操作" width="100" >
            <template slot-scope="scope">
              <div style="text-align: center">
                <i class="el-icon-edit easyweb-list-buttom-icon" title="编辑" style="color: #324157" @click="onEdit(scope.$index, scope.row)"></i>
                <i class="el-icon-delete easyweb-list-buttom-icon" title="删除" style="color: red" @click="onDelete(scope.$index, scope.row)"></i>
              </div>
              <!--<el-button size="mini" type="text" @click="onEdit(scope.$index, scope.row)">编辑</el-button>-->
              <!--<el-button size="mini" type="text" @click="onDelete(scope.$index, scope.row)">删除</el-button>-->
            </template>
          </el-table-column>
        </el-table>
        <div class="pagination">
          <el-pagination ref="listPagination" layout="prev, pager, next" :total="total">
          </el-pagination>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  watch: {
    searchKey (val) {
      this.$refs.officeTree.filter(val)
    }
  },
  data () {
    return {
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      selectData: undefined,
      tableData: [],
      tableSelects: [], // 表格中选中元素
      total: 0,
      treeData: [],
      searchKey: '',
      editShow: false,
      selections: [],
      select_word: '',
      editVisible: false,
      selectId: '',
      searchModel: {},
      form: {
        name: '',
        loginName: ''
      }
    }
  },
  beforeCreate () {

  },
  created () {
    let that = this
    // 获取左边树
    that.getTree()
    // //列表初始化
    this.$list.init({vue: this, params: that.searchModel, url: this.$config.upms_office}) // util.list.init({vue: this, url: this.$appconfig.userUrl, components: [UserEdit]})
  },
  computed: {
    data () {
      return this.tableData.filter((d) => {
        return d
      })
    }
  },
  methods: {
    getTree () {
      let that = this
      that.$get({
        url: that.$config.upms_office,
        data: that.parm,
        success: function (data) {
          let tree = []
          let allData = data.rows
          if (allData) {
            let tops = that.getTops(allData)
            for (let i = 0; i < tops.length; i++) {
              let item = tops[i]
              if (that.haveChild(item.id, allData)) {
                item.children = that.getSub(item, allData)
              }
              tree.push(item)
            }
          }
          that.treeData = tree
        }
      })
    },
    // 选中事件
    onSelectChange (selection) {
      this.tableSelects = selection
      this.$log(selection)
    },
    onTreeSearch: function (value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },
    onTreeNodeClick (data) {
      console.log(data)
      this.selectData = data
      this.searchModel.parentId = this.selectData.id
      this.$list.refresh({params: this.searchModel})
    },
    formatter (row, column) {
      return row.address
    },
    onEdit (index, row) {
      // this.listObj.showEdit(index)
    },
    onDelete (index, row) {
      let ids = []
      if (row) {
        ids.push(row.id)
      } else {
        for (let i = 0; i < this.tableSelects.length; i++) {
          let obj = this.tableSelects[i]
          ids.push(obj.id)
        }
      }
      var that = this
      that.$showConfirm({
        content: '确定删除选择的机构？',
        success: function () {
          that.$delete({
            url: that.$config.upms_office,
            data: ids,
            success: function (data) {
              that.$success('删除成功')
              that.getTree() // 刷新树
              this.$list.refresh({params: this.searchModel}) // 刷新列表
            }
          })
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
    }
  }
}
</script>

<style scoped>
  .handle-box {
    margin-bottom: 10px;
  }

  .handle-select {
    width: 120px;
  }

  .handle-input {
    width: 300px;
    display: inline-block;
  }
</style>
