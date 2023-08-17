<template>
	<div class="items-management">
		<h2 class="page-title">单位管理</h2>
		<div class="filter-bar">
			<span>单位名称:</span>
			<el-input v-model="filter.keyword" placeholder="请输入单位名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
			<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false" width="30%">
				<el-form :model="formData">
					<el-form-item label="单位名称">
						<el-input v-model="formData.unit_name" />
					</el-form-item>
					<el-form-item label="联系人">
						<el-input v-model="formData.contact_person" />
					</el-form-item>
					<el-form-item label="电话">
						<el-input v-model="formData.phone" />
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="cancel">取 消</el-button>
					<el-button :disabled="isClicked" type="primary" @click="confirm">确 定</el-button>
				</div>
			</el-dialog>

			<el-dialog :title="payDialogTitle" :visible.sync="payDialogTitleVisible" :close-on-click-modal="false"
				width="30%">
				<el-form :model="payData">
					<el-form-item label="请输入充值金额">
						<el-input v-model="payData.nums"></el-input>
					</el-form-item>
					<!-- <el-form-item label="充值金额">
					  <el-input v-model="payData.nums" type="text" placeholder="请输入充值金额" :pattern="/^\d+(\.\d+)?$/g" @input="handleInput"></el-input>
					</el-form-item> -->
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="cancel">取 消</el-button>
					<el-button :disabled="isClicked" type="primary" @click="payConfirm">确 定</el-button>
				</div>
			</el-dialog>
			
			<el-dialog :title="upDialogTitle" :visible.sync="upDialogTitleVisible" :close-on-click-modal="false" width="25%">
				<div>
				    <input type="file" ref="fileInput" @change="handleFileChange">
				    <button @click="uploadFile">上传文件</button>
				  </div>
			</el-dialog>

			<el-button type="primary" icon="el-icon-plus" @click="add">新增</el-button>
		</div>
		<el-table :data="tableData" style="width: 100%">
			<el-table-column prop="ID" label="编号" width="80"></el-table-column>
			<el-table-column prop="unit_name" label="单位名称" width="150"></el-table-column>
			<el-table-column prop="contact_person" label="联系人" width="100"></el-table-column>
			<el-table-column prop="account_balance" label="余额" width="100"></el-table-column>
			<el-table-column prop="phone" label="电话" width="100"></el-table-column>
			<el-table-column label="操作" width="500">
				<template slot-scope="scope">
					<el-button size="mini" type="danger" @click="remove(scope.row)">删除</el-button>
					<el-button type="primary" icon="el-icon-upload"
						@click="uploadExcel(scope.row)">导入人员Excel</el-button>
					<el-button type="primary" icon="el-icon-document-add" @click="createOrder(scope.row)">开单</el-button>
					<el-button type="primary" icon="el-icon-receiving" @click="recharge(scope.row)">充值</el-button>
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
				payDialogTitle: '充值',
				upDialogTitle:'员工信息导入',
				upDialogTitleVisible:false,
				payDialogTitleVisible: false,
				dialogVisible: false,
				formData: {
					ID: '',
					unit_name: '',
					contact_person: '',
					account_balance: '',
					phone: '',
				},
				payData: {
					unitId: '',
					nums: '',
				},
				tableData: [],
				currentPage: 1,
				pageSize: 10,
				total: 0,
				formData1: null,
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

				axios.get('/web/queryUnit', {
					params
				}).then(response => {
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
				this.dialogTitle = '添加单位';
				this.formData = {
					id: '',
					name: '',
					contact: '',
					phone: ''
				};
				this.dialogVisible = true;
			},
			remove(row) {
				// 发送请求删除数据
				axios.post('/web/deleteById', {
						"id": row.ID
					})
					.then(response => {
						this.fetchData();
					})
					.catch(error => {
						console.log(error);
					});
			},
			cancel() {
				this.dialogVisible = false;
				this.payDialogTitleVisible = false;
			},
			confirm() {
				if (this.isClicked) return // 如果已经点击，则直接返回
				this.isClicked = true // 标记为已点击

				if (!this.formData.unit_name || !this.formData.contact_person || !this.formData.phone) {
					this.$message.error('请输入完整信息');
					this.isClicked = false;
					return;
				}

				// 发送异步请求新增或修改表格行数据
				if (this.formData.id) { // 修改
					console.log("正在修改。。。。。" + this.formData.id)
					axios.post('/web/updateDepartment', {
							'ID': this.formData.id,
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
					axios.post('/web/unitRegister', {
							'unit_name': this.formData.unit_name,
							'contact_person': this.formData.contact_person,
							'phone': this.formData.phone,
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
			// 单位充值
			recharge(unit) {
				console.log("充值的ID：" + unit.ID)
				console.log("充值的数额"+this.payData.nums)
				this.payData.unitId = unit.ID;
				
				this.payDialogTitleVisible = true;
			},
			payConfirm() {
				axios.post('/web/pay',{
					'ID': this.payData.unitId,
					'account_balance': parseFloat(this.payData.nums),
				}).then(response => {
					
				}).catch(error => {
					
				});
				
				this.payDialogTitleVisible = false;
				this.fetchData();
			},
			uploadExcel(){
				this.upDialogTitleVisible=true;
			},
			uploadFile(){
				axios.post("/web/upload", this.formData).then(response => {
				        console.log(response.data);
				      }).catch(error => {
				        console.log(error);
				      });
				this.upDialogTitleVisible = false;
			},
			handleFileChange(event) {
			      // 选择文件后更新 data 中的 formData 属性
			      const file = event.target.files[0];
			      const formData = new FormData();
			      formData.append("file", file);
			      this.formData = formData;
			    },
			// handleInput(value) {
			//     this.payData.nums = value;
			//     this.$nextTick(() => {
			//       this.$refs.form.validateField('this.payData.nums');
			//     });
			//   },
			
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