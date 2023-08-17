<template>
	<div class="items-management">
		<h2 class="page-title">项目管理</h2>
		<div class="filter-bar">
			<span>科室:</span>
			<el-select v-model="filter.project" clearable placeholder="请选择">
				<el-option v-for="item in projects" :key="item.ID" :label="item.department_name"
					:value="item.ID"></el-option>
			</el-select>
			<span>项目名称:</span>
			<el-input v-model="filter.keyword" placeholder="请输入项目名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>
			<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false" width="30%">
				<el-form :model="formData">
					<el-form-item label="项目名称">
						<el-input v-model="formData.project_name" />
					</el-form-item>
					<el-form-item label="项目价格">
						<el-input v-model="formData.price" />
					</el-form-item>
					<span>科室:</span>
					<el-select v-model="filter.project1" clearable placeholder="请选择">
						<el-option v-for="item in projects1" :key="item.ID" :label="item.department_name"
							:value="item.ID"></el-option>
					</el-select>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="cancel">取 消</el-button>
					<el-button :disabled="isClicked" type="primary" @click="addConfirm()">确 定</el-button>
				</div>
			</el-dialog>
			
			<el-dialog :title="dialogTitle1" :visible.sync="dialogVisible1" :close-on-click-modal="false" width="60%">
				    <div style="text-align: center">
				      <el-transfer
				        style="text-align: left; display: inline-block"
				        v-model="value"
				        filterable
				        :left-default-checked="[2, 3]"
				        :right-default-checked="[1]"
				        :render-content="renderFunc"
				        :titles="['已拥有的细项', '未拥有的细项']"
				        :button-texts="['添加到项目', '从项目移除']"
				        :format="{
				          noChecked: '${total}',
				          hasChecked: '${checked}/${total}'
				        }"
				        @change="handleChange"
				        :data="data">
				      </el-transfer>
				    </div>
				<div slot="footer" class="dialog1-footer">
					<el-button @click="cancel1()">取 消</el-button>
					<el-button :disabled="isClicked" type="primary" @click="confirm()">确 定</el-button>
				</div>
			</el-dialog>
			
			<el-button type="primary" icon="el-icon-plus" @click="add">新增</el-button>
		</div>
		<el-table :data="tableData" style="width: 100%">
			<el-table-column prop="ID" label="项目编号"></el-table-column>
			<el-table-column prop="project_name" label="项目名称"></el-table-column>
			<el-table-column prop="price" label="项目价格"></el-table-column>
			<el-table-column prop="department_name" label="科室"></el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button size="mini" @click="edit(scope.row)">细项详情</el-button>
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
		name: 'project',
		data() {
			 const generateData = _ => {
			        const data = [];
			        for (let i = 1; i <= 15; i++) {
			          data.push({
			            key: i,
			            label: `备选项 ${ i }`,
			            disabled: i % 4 === 0
			          });
			        }
			    return data;
			  };
			
			return {
				isClicked:false,
				projects: [],
				projects1: [],
				filter: {
					project: '',
					keyword: '',
				},
				dialogTitle: '',
				dialogVisible: false,
				dialogTitle1: '',
				dialogVisible1: false,
				formData: {
					id: '',
					project_name: '',
					price: '',
					department_name: '',
				},
				tableData: [],
				currentPage: 1,
				pageSize: 10,
				total: 0,
				data: generateData(),
				        value: [1],
				        value4: [1],
				        renderFunc(h, option) {
				          return <span>{ option.key } - { option.label }</span>;
				        }
			};
		},
		mounted() {
			this.selecDate();
			this.fetchData();
		},
		methods: {
			selecDate(){
				axios.get('/web/selectDepartmentAll', ).then(response => {
					const list = response.data;
					this.projects = list;
					this.projects1 = list;
				});
			},
			fetchData() {
				// 发送异步请求获取表格数据
				const params = {
					department: this.filter.project,
					keyword: this.filter.keyword,
					page: this.currentPage,
					pageSize: this.pageSize,
				}
				
				axios.get('/web/selectProject', { params }).then(response => {
				    this.tableData = response.data.records;
				    this.total = response.data.total;
				});
			},
			search() {
				this.currentPage = 1;
				this.fetchData();
			},
			add() {
				this.isClicked = false;
				this.dialogTitle = '添加项目';
				this.formData = {
					id: '',
					project_name: '',
					price: '',
					department_name: '',
				};
				// this.filter.project1 = '';
				this.dialogVisible = true;
			},
			edit(row) {
				console.log(row)
				this.dialogTitle1 = '细项详情';
				this.formData = {
					id: row.ID,
					project_name: row.project_name,
					price: row.price,
					department_name: row.department_name,
				};
				this.dialogVisible1 = true;
			},
			// 删除
			remove(row) {
				console.log(row.ID)
				// 发送请求删除科室-项目表数据
				axios.get('/web/deleteDepartmentProject', { params: { id: row.ID }}).then(Response => {
					// 发送请求删除项目表数据
					axios.get('/web/deleteProject', { params: { id: row.ID }}).then(Response => {
						this.fetchData();
					});
				});
			},
			cancel() {
				this.dialogVisible = false;
			},
			cancel1() {
				this.dialogVisible1 = false;
			},
			// 新增确认
			addConfirm() {
				if (this.isClicked) return // 如果已经点击，则直接返回
				this.isClicked = true // 标记为已点击
				
				if (!this.formData.project_name || !this.formData.price || !this.filter.project1 ) {
					this.$message.error('请输入完整信息');
					this.isClicked = false;
					return;
				}
				
				// 获取选取的科室名字
				axios.get('/web/selectNameById', { params: { id: this.filter.project1 }}).then(response => {
				    const params = {
				    	projectName: this.formData.project_name,
				    	departmentName: response.data,
				    	price: this.formData.price,
				    }
					// 新增项目信息并获取项目ID
					axios.get('/web/addProject', { params }).then(response => {
						const params = {
							departmentId: this.filter.project1,
							projectId: response.data
						}
						// 新增科室-项目
						axios.get('/web/addDepartmentProject', { params }).then(response => {
							this.fetchData();
						});
					});
				});
				
				this.dialogVisible = false;
			},
			confirm() {
				this.dialogVisible1 = false;
				console.log("笑嘻了")
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