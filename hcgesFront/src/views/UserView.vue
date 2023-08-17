<template>
	<div class="items-management">
		<h2 class="page-title">人员设置</h2>
		<div class="filter-bar">
			<span>人员名称:</span>
			<el-input v-model="filter.keyword" placeholder="请输入人员名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
			<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false" width="30%">
				<el-form :model="formData">
					<el-form-item label="名称">
						<el-input v-model="formData.username" />
					</el-form-item>
					<el-form-item label="真实姓名">
						<el-input v-model="formData.real_name" />
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
			<el-table-column prop="ID" label="账号"></el-table-column>
			<el-table-column prop="username" label="名称"></el-table-column>
			<el-table-column prop="real_name" label="真实姓名"></el-table-column>
			<el-table-column label="操作" width="400">
				<template slot-scope="scope">
					<el-button type="primary" :disabled="loading" @click="toggleEnable(scope.row)">
						{{ scope.row.state === 0 ? '启用' : '禁用' }}
					</el-button>
					<el-button size="mini" @click="edit(scope.row)">修改</el-button>
					<el-button size="mini" type="danger" @click="remove(scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination :current-page.sync="currentPage" :page-sizes="[5, 10, 15, 20]" :total="total"
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
				loading: false, // 控制按钮是否处于正在加载状态
				isClicked: false, // 按钮是否已经点击
				filter: {
					keyword: '',
				},
				dialogTitle: '',
				dialogVisible: false,
				formData: {
					ID: '',
					username: '',
					password: '',
					real_name: '',
					state: '',
				},
				tableData: [],
				currentPage: 1,
				pageSize: 10,
				total: 0,
			};
		},
		mounted() {
			this.fetchData();
			// this.fetchState(); // 组件挂载后，立即获取状态值
		},
		methods: {
			fetchData() {
				// 发送异步请求获取表格数据
				const params = {
					keyword: this.filter.keyword,
					page: this.currentPage,
					pageSize: this.pageSize,
				}

				axios.get('/web/getUser', {
					params
				}).then(response => {
					this.tableData = response.data.records;
					this.total = response.data.total;
					console.log(response.data.length)
				});
			},
			search() {
				this.currentPage = 1;
				this.fetchData();
			},
			add() {
				this.isClicked = false;
				this.dialogTitle = '添加人员';
				this.formData = {
					ID: '',
					username: '',
					password: '',
					real_name: '',
				};
				this.dialogVisible = true;
			},
			edit(row) {
				console.log(row)
				this.dialogTitle = '修改人员';
				this.formData = {
					ID: row.ID,
					username: row.username,
					password: row.password,
					real_name: row.real_name,
				};
				this.dialogVisible = true;
			},
			remove(row) {
				// 发送请求删除数据
				axios.get('/web/deleteUser', {
					params: {
						id: row.ID
					}
				}).then(Response => {
					this.fetchData();
				});
			},
			cancel() {
				this.dialogVisible = false;
			},
			confirm() {
				if (this.isClicked) return // 如果已经点击，则直接返回
				this.isClicked = true // 标记为已点击

				if (!this.formData.username || !this.formData.real_name) {
					this.$message.error('请输入完整信息');
					this.isClicked = false;
					return;
				}

				// 发送异步请求新增或修改表格行数据
				if (this.formData.ID) { // 修改
					axios.post('/web/updateUser', {
							'ID': this.formData.ID,
							'username': this.formData.username,
							'password': this.formData.password,
							'real_name': this.formData.real_name,
							'state': this.formData.state,
						})
						.then(response => {
							this.isClicked = false // 标记为未点击
							this.fetchData();
						})
						.catch(error => {
							console.log(error);
						});
				} else { // 新增
					axios.post('/web/userRegister', {
							'userName': this.formData.username,
							'password': this.formData.password,
							'real_name': this.formData.real_name,
						})
						.then(response => {
							console.log("注册结果" + response.data.code)
							if (response.data.code = 0) {
								// this.open4()
								this.$message.error('该名称已存在');
								this.isClicked = true // 标记为未点击
								this.fetchData();
							} else {
								this.isClicked = true // 标记为未点击
								this.fetchData();
							}
						})
						.catch(error => {
							console.log(error);
						});
				}
				this.dialogVisible = false;
			},
			toggleEnable(row) {
				this.loading = true; // 设置按钮为正在加载状态
				console.log("看看是什么" + row.state)
				const s = 1 - row.state;

				// 调用后端API切换状态
				axios.post('/web/updateUser', {
						'ID': row.ID,
						'state': s,
					})
					.then(response => {
						this.isClicked = false // 标记为未点击
						this.fetchData();
					})
					.catch(error => {
						console.log(error);
					});
				setTimeout(() => {
					this.loading = false;
				}, 1000);
			},
			handleSizeChange(pageSize) {
				this.pageSize = pageSize;
				this.fetchData();
			},
			handleCurrentChange(currentPage) {
				this.currentPage = currentPage;
				this.fetchData();
			},
			open4() {
				this.$message.error('该名称已存在');
			}
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