<template>
	<div class="container">
		<div class="title">体检人员管理</div>
		<div class="search-bar">
			<span>单位名称:</span>
			<el-input placeholder="请输入单位名称" v-model="filter.keyword"></el-input>
			<el-button type="primary" @click="search()">查询</el-button>
			<el-button class="add-button" type="primary" @click="showAddDialog">新增人员</el-button>
			<el-button class="export-button" type="primary" @click="exportAll">导出Excel</el-button>
			<el-button class="template-button" type="primary" @click="exportWeb">下载名单模板</el-button>
		</div>
		<el-table class="table" :data="tableData" border stripe>
			<el-table-column prop="ID" label="编号" width="80"></el-table-column>
			<el-table-column prop="name" label="姓名"></el-table-column>
			<el-table-column prop="gender" label="性别"></el-table-column>
			<el-table-column prop="age" label="年龄"></el-table-column>
			<el-table-column prop="phone" label="电话"></el-table-column>
			<el-table-column prop="ID_card_number" label="身份证"></el-table-column>
			<el-table-column prop="unit_name" label="所在企业"></el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button size="mini" type="danger" @click="deleteItem(scope.row)">删除</el-button>
					<el-button size="mini" type="warning" @click="showBillDialog(scope.row)">开单</el-button>
				</template>
			</el-table-column>
		</el-table>
		<!-- <el-pagination class="pagination" :page-size="pageSize" :total="dataList.length" layout="prev, pager, next" :current-page="page" @current-change="handlePagination"></el-pagination> -->
		<el-pagination :current-page.sync="currentPage" :page-sizes="[5, 10, 15, 20]" :total="total"
			@size-change="handleSizeChange" @current-change="handleCurrentChange"
			layout="total, sizes, prev, pager, next, jumper"></el-pagination>
		<el-dialog title="添加人员" :visible.sync="addDialogVisible" width="30%">
			<el-form :model="addFormData" :rules="addFormRules" ref="addForm">
				<el-form-item label="单位名称" prop="company">
					<el-select v-model="addFormData.company" placeholder="请选择单位名称">
						<el-option v-for="company in companyList" :key="company.ID" :label="company.unit_name"
							:value="company.ID"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="姓名" prop="name">
					<el-input v-model="addFormData.name" placeholder="请输入姓名"></el-input>
				</el-form-item>
				<el-form-item label="性别" prop="gender">
					<el-select v-model="addFormData.gender" placeholder="请选择性别">
						<el-option label="男" value="男"></el-option>
						<el-option label="女" value="男"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="年龄" prop="age">
					<el-input v-model="addFormData.age" placeholder="请输入年龄"></el-input>
				</el-form-item>
				<el-form-item label="电话" prop="phone">
					<el-input v-model="addFormData.phone" placeholder="请输入电话"></el-input>
				</el-form-item>
				<el-form-item label="身份证" prop="idCard">
					<el-input v-model="addFormData.idCard" placeholder="请输入身份证"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="addDialogVisible = false">取消</el-button>
				<el-button type="primary" @click="add()">确定</el-button>
			</div>
		</el-dialog>
		<el-dialog title="个人开单" :visible.sync="billDialogVisible" width="30%">
			<el-form :model="billFormData" ref="billForm">
				<el-form-item label="单位名称">
					<el-input v-model="billFormData.company" disabled></el-input>
				</el-form-item>
				<el-form-item label="套餐">
					<el-select v-model="billFormData.package" clearable placeholder="请选择套餐" @change="handleChange">
						<el-option v-for="item in packageList" :key="item.ID" :label="item.package_name"
							:value="item.ID"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="价格">
					<el-input v-model="relatedText" placeholder="请选择套餐" disabled></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="billDialogVisible = false">取消</el-button>
				<el-button type="primary" @click="bill()">确定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios';
	export default {
		data() {
			return {
				keyAlways:'',
				tableData: [],
				dataList: [],
				companyList: [],
				packageList:[],
				addDialogVisible: false,
				addFormData: {
					company: '',
					name: '',
					gender: '',
					age: '',
					phone: '',
					idCard: ''
				},
				addFormRules: {
					company: [{
						required: true,
						message: '请选择单位名称',
						trigger: 'change'
					}],
					name: [{
						required: true,
						message: '请输入姓名',
						trigger: 'blur'
					}],
					gender: [{
						required: true,
						message: '请选择性别',
						trigger: 'change'
					}],
					age: [{
						required: true,
						message: '请输入年龄',
						trigger: 'blur'
					}],
					phone: [{
							required: true,
							message: '请输入电话',
							trigger: 'blur'
						},
						{
							validator: (rule, value, callback) => {
								if (!/^1[3456789]\d{9}$/.test(value)) {
									callback(new Error('请输入合法的手机号'));
								} else {
									callback();
								}
							},
							trigger: 'blur'
						}
					],
					idCard: [{
							required: true,
							message: '请输入身份证号码',
							trigger: 'blur'
						},
						{
							validator: (rule, value, callback) => {
								if (!/^\d{18}$/.test(value)) {
									callback(new Error('请输入合法的身份证号码'));
								} else {
									callback();
								}
							},
							trigger: 'blur'
						}
					]
				},
				billDialogVisible: false,
				billFormData: {
					company: '',
					package: '',
					price: ''
				},
				filter: {
					keyword: '',
				},
				// currentPage: 4
				currentPage: 1,
				pageSize: 10,
				total: 0,
				selectedOption: null, // 当前选中的选择器选项
				relatedText: null, // 选中选项对应的相关文本
				employeeId:'',
			}
		},
		mounted() {
			this.fetchData();
			this.selectData();
			this.selectProject();
		},
		methods: {
			fetchData() {
				// 发送异步请求获取表格数据
				const params = {
					keyword: this.filter.keyword,
					page: this.currentPage,
					pageSize: this.pageSize,
				}

				axios.get('/web/getEmployee', {
					params
				}).then(response => {
					this.tableData = response.data;
					this.total = response.data.total;
					console.log(response.data[0].ID_card_number)
				});
			},
			// 公司选择器
			selectData(){
				const params = {
					keyword: "",
					page: 1,
					pageSize: 10000,
				}
				
				axios.get('/web/queryUnit', {
					params
				}).then(response => {
					this.companyList = response.data.records;
				});
			},
			// 套餐选择器
			selectProject(){
				const params = {
					packageName: "",
					page: 1,
					pageSize: 1000,
				}
				
				axios.get('/web/selectPackage', {
					params
				}).then(response => {
					this.packageList = response.data.records;
				});
			},
			search() {
				// 处理搜索逻辑
				this.keyAlways = this.filter.keyword;
				this.currentPage = 1;
				this.fetchData();
			},
			showAddDialog() {
				this.addFormData = {
					company: '',
					name: '',
					gender: '',
					age: '',
					phone: '',
					idCard: ''
				}
				this.addDialogVisible = true;
			},
			add() {
				this.$refs.addForm.validate(valid => {
					if (valid) {
						// 处理添加逻辑
						axios.post('/web/addEmployee', {
								'name': this.addFormData.name,
								'gender': this.addFormData.gender,
								'age': this.addFormData.age,
								'phone': this.addFormData.phone,
								'ID_card_number': this.addFormData.idCard,
								'unit_name': this.addFormData.company,
							})
							.then(response => {
								this.isClicked = true
								this.fetchData();
							})
							.catch(error => {
								console.log(error);
							});
						this.addDialogVisible = false;
					}
				});
			},
			showBillDialog(index) {
				
				this.employeeId = index.ID;
				
				// 查询是否开单
				
				axios.get('/web/isBilling', {
				  params: {
				    id: index.ID
				  }
				}).then(response => {
				  this.$message.success(response.data);
				  this.billDialogVisible = true;
				}).catch(error => {
				  this.$message.error(error.response.data);
				});
				
				this.billFormData.company = index.unit_name
				
				this.billFormData.company = this.tableData[index].company;
				
				
			},
			bill() {
			      // 检查是否选中套餐
			      if (!this.billFormData.package) {
			        this.$message.error("请先选择套餐！");
			        return;
			      }
			
			      // 获取已选套餐的信息
			      const selectedOption = this.packageList.find(option => option.ID === this.billFormData.package);
			      if (selectedOption) {
			        const packageInfo = {
			          packageId: selectedOption.ID,
			          packageName: selectedOption.package_name,
			          packagePrice: selectedOption.price
			        };
					
			        // 执行开单操作，例如将套餐信息发送到服务器端
					const params = { id: this.employeeId, price: packageInfo.packagePrice,userId:this.employeeId,packageId: selectedOption.ID};
					
					axios.get('/web/toBilling', { params: params })
					    .then(response => {
					      console.log(response.data); // 打印响应结果
						  console.log("选择了"+selectedOption.ID)
						  this.$message({
						            message: '开单成功',
						            type: 'success'
						          });
						  this.billDialogVisible = false;
					    })
					    .catch(error => {
					      console.log(error.response.data); // 打印错误消息
						  this.$message.error(error.response.data);
					    });
					
			      }
				  
			    },
			deleteItem(row) {
				// 处理删除逻辑
				axios.get('/web/deleteEmployee', { params: { id: row.ID }}).then(Response => {
					this.fetchData();
				});
			},
			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
			},
			handleCurrentChange(val) {
				console.log(`当前页: ${val}`);
			},
			exportWeb(){
				console.log("kkkkkkkk"+this.keyAlways)
				axios.get('/web/exportWeb', { responseType: 'blob' })
				  .then(response => {
				    let fileUrl = window.URL.createObjectURL(new Blob([response.data], { type: response.headers['content-type'] }))
				    let a = document.createElement('a')
				    a.href = fileUrl
				    a.download = 'employee-table.xlsx'
				    a.click()
				  })
				  .catch(error => console.log(error))
			},
			exportAll(){
				axios.get('/web/exportAll', {
				    responseType: 'blob',
				    params: {
				      key: this.keyAlways // 将 key 参数添加到 params 对象中
				    }
				  })
				  .then(response => {
				    let fileUrl = window.URL.createObjectURL(new Blob([response.data], { type: response.headers['content-type'] }))
				    let a = document.createElement('a')
				    a.href = fileUrl
				    a.download = 'employee-table.xlsx'
				    a.click()
				  })
				  .catch(error => console.log(error))
			},
			 handleChange() {
			      // 根据选中选项的value值查找选项对象，并取出对应的相关文本
				 const selectedOption = this.packageList.find(option => option.ID === this.billFormData.package);
				 if (selectedOption) {
				   const packageInfo = {
				     packageId: selectedOption.ID,
				     packageName: selectedOption.package_name,
				     packagePrice: selectedOption.price
				   };
				   this.relatedText = selectedOption.price;
			    }
		}
	}}
</script>

<style>
	.container {
		padding: 20px;
	}

	.input {}
</style>