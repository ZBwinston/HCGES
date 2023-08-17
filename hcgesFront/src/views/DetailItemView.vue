<template>
	<div class="items-management">
		<h2 class="page-title">细项管理</h2>
		<div class="filter-bar">
			<span>项目:</span>
			<el-select v-model="filter.project" clearable placeholder="请选择">
				<el-option v-for="item in projects" :key="item.value" :label="item.label"
					:value="item.value"></el-option>
			</el-select>
			<span>细项名称:</span>
			<el-input v-model="filter.keyword" placeholder="请输入细项名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
			<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false" width="30%">
				<el-form :model="formData">
					<el-form-item label="细项名称">
						<el-input v-model="formData.detail_item_name" />
					</el-form-item>
					<el-form-item label="计量单位">
						<el-input v-model="formData.measurement_unit" />
					</el-form-item>
					<el-form-item label="上限值">
						<el-input v-model="formData.upper_limit_value" />
					</el-form-item>
					<el-form-item label="下限值">
						<el-input v-model="formData.lower_limit_value" />
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
			<el-table-column prop="ID" label="细项编号"></el-table-column>
			<el-table-column prop="detail_item_name" label="细项名称"></el-table-column>
			<el-table-column prop="measurement_unit" label="计量单位"></el-table-column>
			<el-table-column prop="upper_limit_value" label="上限值"></el-table-column>
			<el-table-column prop="lower_limit_value" label="下限值"></el-table-column>
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
		name: 'ItemsManagement',
		data() {
			return {
				isClicked:false,
				projects: [{
						label: '项目1',
						value: '1'
					},
					{
						label: '项目2',
						value: '2'
					},
					{
						label: '项目3',
						value: '3'
					},
				],
				filter: {
					project: '',
					keyword: '',
				},
				dialogTitle: '',
				dialogVisible: false,
				formData: {
					id: '',
					detail_item_name: '',
					measurement_unit: '',
					upper_limit_value: '',
					lower_limit_value: '',
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
					project: this.filter.project,
					keyword: this.filter.keyword,
					page: this.currentPage,
					pageSize: this.pageSize,
				}
				
				axios.get('/web/selectDetail', { params }).then(response => {
				    this.tableData = response.data.records;
				    this.total = response.data.total;
					console.log(response.data.records)
				});
			},
			search() {
				console.log(this.filter.project)
				this.currentPage = 1;
				this.fetchData();
			},
			add() {
				this.isClicked = false;
				this.dialogTitle = '添加细项';
				this.formData = {
					id: '',
					detail_item_name: '',
					measurement_unit: '',
					upper_limit_value: '',
					lower_limit_value: '',
				};
				this.dialogVisible = true;
			},
			edit(row) {
				console.log(row)
				this.dialogTitle = '修改细项';
				this.formData = {
					id: row.ID,
					detail_item_name: row.detail_item_name,
					measurement_unit: row.measurement_unit,
					upper_limit_value: row.upper_limit_value,
					lower_limit_value: row.lower_limit_value,
				};
				this.dialogVisible = true;
			},
			remove(row) {
				// 发送请求删除项目-细项表数据数据
				axios.get('/web/deleteShip', { params: { id: row.ID }}).then(Response => {
					// 发送请求删除细项表数据数据
					axios.get('/web/deleteDetail', { params: { id: row.ID }}).then(Response => {
						this.fetchData();
					});
				});
			},
			cancel() {
				this.dialogVisible = false;
			},
			confirm() {
				if (this.isClicked) return // 如果已经点击，则直接返回
				this.isClicked = true // 标记为已点击
				
				if (!this.formData.detail_item_name || !this.formData.measurement_unit || !this.formData.upper_limit_value || !this.formData.lower_limit_value) {
					this.$message.error('请输入完整信息');
					this.isClicked = false;
					return;
				}
				
				// 发送异步请求新增或修改表格行数据
				if (this.formData.id) { // 修改
				console.log("正在修改。。。。。"+this.formData.id)
					axios.post('/web/updateDetail', {
						    'ID':this.formData.id,
							'detail_item_name': this.formData.detail_item_name,
							'measurement_unit': this.formData.measurement_unit,
							'upper_limit_value': this.formData.upper_limit_value,
							'lower_limit_value': this.formData.lower_limit_value,
						})
						.then(response => {
							this.isClicked = true
							this.fetchData();
						})
						.catch(error => {
							console.log(error);
						});
				} else { // 新增
				axios.post('/web/addDetail', {
						'detail_item_name': this.formData.detail_item_name,
						'measurement_unit': this.formData.measurement_unit,
						'upper_limit_value': this.formData.upper_limit_value,
						'lower_limit_value': this.formData.lower_limit_value,
					})
					.then(response => {
						this.isClicked = true
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