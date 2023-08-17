<template>
  <div class="exam-query">
    <h1 class="title">体检单查询</h1>
    <div class="input-row">
      <div class="label-wrapper">
        <span class="label">单位名称：</span>
        <el-input v-model="companyName" placeholder="请输入单位名称"></el-input>
      </div>
      <div class="label-wrapper">
        <span class="label">人员姓名：</span>
        <el-input v-model="name" placeholder="请输入人员姓名"></el-input>
      </div>
      <div class="label-wrapper">
        <span class="label">查询状态：</span>
        <el-select v-model="status" placeholder="请选择查询状态" @change="handleChange">
          <el-option label="已做" value="1"></el-option>
          <el-option label="未做" value="0"></el-option>
        </el-select>
      </div>
      <el-button class="query-btn" type="primary" @click="handleSearch">查询</el-button>
    </div>
    <el-table :data="tableData" class="table-wrapper">
      <el-table-column prop="ID" label="编号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="project_name" label="体检项目"></el-table-column>
      <el-table-column prop="department_name" label="科室"></el-table-column>
      <el-table-column prop="payment_method" label="状态" :formatter="formatPaymentMethod"></el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="pageSize"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
	import axios from 'axios';
export default {
  data() {
    return {
      companyName: '', // 单位名称
      name: '', // 人员姓名
      status: '', // 查询状态
      tableData: [], // 表格数据
      total: 0, // 总数
      pageSize: 5, // 每页显示数量
      currentPage: 1 // 当前页码
    }
  },
  mounted(){
	this.handleSearch();  
  },
  methods: {
    // 查询按钮点击事件
    handleSearch() {
      const params = {
        unitName: this.companyName,
        employeeName: this.name,
        pageSize: this.pageSize,
        page: this.currentPage,
		state: this.status,
      }
	  
      axios.get('/web/getPhysicalExamList', { params })
        .then(res => {
			
			 const list = res.data.records
				
          this.tableData = list
          this.total = res.data.total
        })
        .catch(err => {
          console.error(err)
        })
		
		console.log(this.status)
		
    },
    // 分页器每页显示数量变化事件
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.handleSearch()
    },
    // 分页器当前页码变化事件
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage
      this.handleSearch()
    },
	formatPaymentMethod(payment_method) {
	    return payment_method === '0' ? '未做' : (payment_method === '1' ? '已做' : '未做')
	  },
	  handleChange(value) {
	      this.status = value
	    }
	}	
}
</script>

<style scoped>
.exam-query {
  max-width: 1200px;
  margin: auto;
  padding: 20px;
}

.title {
  text-align: center;
  margin-bottom: 20px;
}

.input-row {
  display: flex;
  justify-content: space-between;
}

.label-wrapper {
  display: flex;
  align-items: center;
}

.label {
  width: 80px;
  margin-right: 10px;
}

.query-btn {
  margin-left: 20px;
}

.table-wrapper {
  margin-top: 20px;
}
</style>