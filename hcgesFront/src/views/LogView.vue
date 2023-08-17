<template>
  <div class="log-management">
    <h2 class="title">日志管理</h2>
	
	<el-button type="danger" size="small" @click="handleBatchDelete">
	  批量删除
	</el-button>
	
    <el-table
      :data="tableData"
      style="width: 100%;"
      :row-key="row => row.id"
      :header-cell-style="headerRender"
      v-model="selection"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="编号" prop="ID"></el-table-column>
      <el-table-column label="操作人员" prop="operator"></el-table-column>
      <el-table-column label="操作内容" prop="action_content"></el-table-column>
	  <el-table-column label="操作时间" prop="action_time"></el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="action-bar">
      
      <el-pagination
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
        :page-sizes="[5, 10, 15, 20]"
        :current-page.sync="currentPage"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "LogManagement",
  data() {
    return {
      pageSize: 10, // 每页显示的记录数
      currentPage: 1, // 当前页码
      total: 100, // 总记录数
      tableData: [],
      selection: []
    };
  },
  mounted() {
	  this.fetchData();
  },
  methods: {
	  fetchData() {
	  	// 发送异步请求获取表格数据
	  	const params = {
	  		page: this.currentPage,
	  		pageSize: this.pageSize,
	  	}
	  	
	  	axios.get('/web/listLogs', { params }).then(response => {
	  	    this.tableData = response.data;
	  	    this.total = response.data.total;
			console.log(response.total)
	  	});
	  },
    // 表头样式渲染函数
    headerRender({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return {
          textAlign: "center",
          backgroundColor: "#f5f7fa",
          fontWeight: "bold",
        };
      } else if (columnIndex === 0) {
        return { textAlign: "center" };
      } else {
        return { textAlign: "center", fontWeight: "bold" };
      }
    },
    // 删除日志记录
    handleDelete(row) {
      // 删除操作逻辑
      alert(`删除记录 ${row.ID}`);
	  axios.get('/web/deleteLog', {
	    params: {
	      id: row.ID
	    }
	  }).then(res => {
	    this.fetchData()
	  })
    },
    // 批量删除日志记录
    handleBatchDelete() {
      if (this.selection.length === 0) {
        alert("请先选择需要删除的记录");
        return;
      }
      // 执行批量删除操作
      // const ids = this.selection.map((row) => row.id);
      // alert(`删除记录 ${ids}`);
	  
	  console.log(this.selection[0].ID)
	  for(let i = 0;i <=this.selection.length;i++){
		  // console.log(this.selection.length)
		  axios.get('/web/deleteLog', {
		    params: {
		      id: this.selection[i].ID
		    }
		  }).then(res => {
		    this.fetchData()
		  })
	  }
	  
    },
    // 监听表格选中项变化
    handleSelectionChange(selection) {
      this.selection = selection;
    },
	handleSizeChange(pageSize) {
		this.pageSize = pageSize;
		this.fetchData();
	},
	handleCurrentChange(currentPage) {
		this.currentPage = currentPage;
		this.fetchData();
	},
	handleChange(value, direction, movedKeys) {
		console.log(value, direction, movedKeys);
	}
  },
};
</script>

<!-- 样式 -->
<style>
.log-management {
  max-width: 960px;
  margin: 0 auto;
  padding: 20px;
}

.title {
  font-weight: bold;
  font-size: 24px;
  margin-bottom: 20px;
  text-align: center;
}

.action-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}
</style>