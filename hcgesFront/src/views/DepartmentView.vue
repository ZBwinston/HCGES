<template>
	<div class="items-management">
		<h2 class="page-title">科室管理</h2>
		<div class="filter-bar">
			<span>科室名称:</span>
			<el-input v-model="filter.keyword" placeholder="请输入科室名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
			<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false" width="30%">
				<el-form :model="formData">
					<el-form-item label="科室名称">
						<el-input v-model="formData.department_name" />
					</el-form-item>
					<el-form-item label="负责人">
						<el-input v-model="formData.manager" />
					</el-form-item>
					<el-form-item label="科室介绍">
						<el-input v-model="formData.department_introduction" />
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="cancel">取 消</el-button>
					<el-button :disabled="isClicked" type="primary" @click="confirm">确 定</el-button>
				</div>
			</el-dialog>
			<el-button type="primary" icon="el-icon-plus" @click="add">新增</el-button>
		</div>
		<el-table :data="tableData" style="width: 100%">
			<el-table-column type="index" width="50"></el-table-column>
			<el-table-column prop="department_name" label="科室名称"></el-table-column>
			<el-table-column prop="manager" label="负责人"></el-table-column>
			<el-table-column prop="department_introduction" label="科室介绍"></el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button size="mini" @click="edit(scope.row)">修改</el-button>
					<el-button size="mini" type="danger" @click="remove(scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination :current-page.sync="currentPage" :page-size="pageSize" :total="total"
			@size-change="handleSizeChange" @current-change="handleCurrentChange"
			layout="total, sizes, prev, pager, next, jumper"></el-pagination>
	</div>
</template>

<script>
	import axios from 'axios';
	export default {
		name: 'Department',
		data() {
			return {
				isClicked: false, // 按钮是否已经点击
				filter: {
					keyword: '',
				},
				dialogTitle: '',
				dialogVisible: false,
				formData: {
					id: '',
					department_name: '',
					manager: '',
					department_introduction: '',
				},
				tableData: [],
				currentPage: 1,
				pageSize: 10,
				total: 0,
			};
		},
		mounted() {
			this.fetchData();
		},
		methods: {
			fetchData() {
				// 发送异步请求获取表格数据
				const params = {
					keyword: this.filter.keyword,
					page: this.currentPage,
					pageSize: this.pageSize,
				}
				
				axios.get('/web/selectDepartment', { params }).then(response => {
				    this.tableData = response.data.records;
				    this.total = response.data.total;
					console.log(response.data.length)
				});
			},
			search() {
				console.log(this.filter.project)
				this.currentPage = 1;
				this.fetchData();
			},
			add() {
				this.isClicked = false;
				this.dialogTitle = '添加科室';
				this.formData = {
					id: '',
					department_name: '',
					manager: '',
					department_introduction: '',
				};
				this.dialogVisible = true;
			},
			edit(row) {
				console.log(row)
				this.dialogTitle = '修改科室';
				this.formData = {
					id: row.ID,
					department_name: row.department_name,
					manager: row.manager,
					department_introduction: row.department_introduction,
				};
				this.dialogVisible = true;
			},
			remove(row) {
				// 发送请求删除数据
				axios.get('/web/deleteDepartment', { params: { id: row.ID }}).then(Response => {
						this.fetchData();
				});
			},
			cancel() {
				this.dialogVisible = false;
			},
			confirm() {
				if (this.isClicked) return // 如果已经点击，则直接返回
				this.isClicked = true // 标记为已点击
				
				if (!this.formData.department_name || !this.formData.manager || !this.formData.department_introduction ) {
					this.$message.error('请输入完整信息');
					this.isClicked = false;
					return;
				}
				
				// 发送异步请求新增或修改表格行数据
				if (this.formData.id) { // 修改
				console.log("正在修改。。。。。"+this.formData.id)
					axios.post('/web/updateDepartment', {
						    'ID':this.formData.id,
							'department_name': this.formData.department_name,
							'manager': this.formData.manager,
							'department_introduction': this.formData.department_introduction,
						})
						.then(response => {
							this.isClicked = true // 标记为未点击
							this.fetchData();
						})
						.catch(error => {
							console.log(error);
						});
				} else { // 新增
				axios.post('/web/addDepartment', {
						'department_name': this.formData.department_name,
						'manager': this.formData.manager,
						'department_introduction': this.formData.department_introduction,
					})
					.then(response => {
						this.isClicked = true // 标记为未点击
						this.fetchData();
					})
					.catch(error => {
						console.log(error);
					});
				}
				this.dialogVisible = false;
			},
			handleSizeChange(pageSize) {
				this.pageSize = pageSize;
				this.fetchData();
			},
			handleCurrentChange(currentPage) {
				this.currentPage = currentPage;
				this.fetchData();
			},
		},
	};
</script>

<style scoped>
	.items-management {
		max-width: 800px;
		margin: auto;
	}

	.page-title {
		text-align: center;
	}

	.filter-bar {
		display: flex;
		align-items: center;
		margin-bottom: 20px;
	}

	.filter-bar span {
		margin-right: 10px;
	}

	.el-button {
		margin-left: 20px;
	}
</style>