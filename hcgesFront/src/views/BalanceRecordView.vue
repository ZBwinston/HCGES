<template>
  <div class="exam-query">
    <h1 class="title">体检单查询</h1>
    <div class="input-row">
      <div class="label-wrapper">
        <span class="label">体检日期：</span>
        <el-date-picker type="datetime" placeholder="选择时间" v-model="startDate"></el-date-picker>
        <span>-</span>
        <el-date-picker type="datetime" placeholder="选择时间" v-model="endDate"></el-date-picker>
      </div>
      <div class="label-wrapper">
        <el-select v-model="status" placeholder="请选择公司">
			<el-option key="" label="默认" value=""></el-option>
          <el-option v-for="option in statusOptions" :key="option.ID" :label="option.unit_name" :value="option.ID"></el-option>
        </el-select>
      </div>
      <el-button class="query-btn" type="primary" @click="handleSearch">查询</el-button>
    </div>
    <el-table :data="tableData" class="table-wrapper">
      <el-table-column prop="ID" label="编号"></el-table-column>
      <el-table-column prop="unit_name" label="单位名称"></el-table-column>
      <el-table-column prop="action_type" label="发生事项"></el-table-column>
      <el-table-column prop="amount" label="金额"></el-table-column>
      <el-table-column prop="action_time" label="发生时间" :formatter="formatActionTime"></el-table-column>
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

const API_BASE_URL = '/web';  // 将 API 的基础 URL 地址提取到常量中

export default {
  data() {
    return {
      startDate: '', // 开始体检日期
      endDate: '', // 结束体检日期
      status: '', // 查询状态
      statusOptions: [], // 查询状态选项
      tableData: [], // 表格数据
      total: 0, // 总数
      pageSize: 5, // 每页显示数量
      currentPage: 1 ,// 当前页码
      companyName: '', // 公司名称
    }
  },
  mounted(){
	this.handleSearch();
  },
  methods: {
    // 获取查询状态选项
    getStatusOptions() {
      const params = {
        keyword: "",
        page: 1,
        pageSize: 10000,
      }
      axios.get(`${API_BASE_URL}/queryUnit`, { params }).then(response => {
        this.statusOptions = response.data.records;
        if (this.statusOptions && this.statusOptions.length > 0) {
          this.status = this.statusOptions[0].ID;
        }
      });
    },
    // 查询按钮点击事件
	handleSearch() {
	  let minTime = null
	  let maxTime = null
	  if (this.startDate !== '' && this.endDate !== '') {
	    minTime = Date.parse(this.startDate)
	    maxTime = Date.parse(this.endDate)
	  }
	  const params = {
	    uid: this.status,
	    minTime: minTime,
	    maxTime: maxTime,
	    pageSize: this.pageSize,
	    page: this.currentPage
	  }
	  axios.get(`${API_BASE_URL}/getBalanceRecord`, { params })
	    .then(res => {
	      const list = res.data
	      this.tableData = list
	      console.log(list)
	    })
	    .catch(err => {
	      console.error(err)
	    })
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
    // 格式化发生时间
    formatActionTime(timestamp) {
      const date = new Date(timestamp)
      const year = date.getFullYear()
      const month = this.padMonth(date.getMonth() + 1)
      const day = this.padMonth(date.getDate())
      const hour = this.padTwo(date.getHours())
      const min = this.padTwo(date.getMinutes())
      const sec = this.padTwo(date.getSeconds())
      return `${year}/${month}/${day} ${hour}:${min}:${sec}`
	  // 将时间戳转换为日期对象
	  // const date = new Date(parseInt(timestamp))
	  // // 格式化日期时间为 "YYYY/MM/DD HH:mm:ss" 格式
	  // const formattedDate = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
	  // return formattedDate
    },
    // 补齐日期、时间数字位数为 2 位
    padMonth(month) {
      return month<10 ? '0'+month : month
    },
    padTwo(n) {
      return n<10 ? '0'+n : n
    }
  },
  mounted() {
    this.getStatusOptions()
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
  min-width: 80px;
  margin-right: 10px;
}

.query-btn {
  margin-left: 20px;
}

.table-wrapper {
  margin-top: 20px;
}
</style>