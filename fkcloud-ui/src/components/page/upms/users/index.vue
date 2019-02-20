<template>
  <div class="table">
    <div class="container">
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
              <el-button type="info" ref="btnRest" icon="el-icon-refresh" style="float: right; margin-left: 10px">重置
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
      <el-table :data="tableData" border style="width: 100%" ref="list">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="name" label="姓名" width="120">
        </el-table-column>
        <el-table-column prop="loginName" label="登录名">
        </el-table-column>
        <el-table-column prop="mobile" label="手机号" width="130">
        </el-table-column>
        <el-table-column prop="email" label="邮箱" width="130">
        </el-table-column>
        <el-table-column prop="createDate" label="日期" width="150">
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="onEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="mini" type="text" @click="onDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination ref="listPagination" layout="prev, pager, next" :total="total">
        </el-pagination>
      </div>
      <!-- 编辑弹出框 -->
      <el-dialog title="编辑" :visible.sync="editShow" width="50%" center>
        <upms-user-edit :id="selectId" :list="listObj"></upms-user-edit>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      tableData: [],
      total: 1,
      editShow: false,
      selections: [],
      select_word: '',
      editVisible: false,
      selectId: '',
      form: {
        name: '',
        loginName: ''
      }
    }
  },
  beforeCreate () {

  },
  created () {
    // //列表初始化
    this.listObj = this.$list.init({vue: this, url: this.$config.upms_dic_list}) // util.list.init({vue: this, url: this.$appconfig.userUrl, components: [UserEdit]})
  },
  computed: {
    data () {
      return this.tableData.filter((d) => {
        return d
      })
    }
  },
  methods: {
    formatter (row, column) {
      return row.address
    },
    onEdit (index, row) {
      // this.listObj.showEdit(index)
    },
    onDelete (index, row) {
      // this.listObj.delete(this.$appconfig.userDeleteUrl, index)
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
