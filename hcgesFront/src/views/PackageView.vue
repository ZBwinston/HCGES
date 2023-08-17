<template>
	<div class="items-management">
		<h2 class="page-title">套餐管理</h2>
		<div class="filter-bar">
			<span>价格:</span>
			<el-input v-model="minPrice" placeholder="最低价"></el-input>
			<span>至</span>
			<el-input v-model="maxPrice" placeholder="最高价"></el-input>
			<span>套餐名称:</span>
			<el-input v-model="filter.keyword" placeholder="请输入套餐名称"></el-input>
			<el-button type="primary" icon="el-icon-search" @click="search">查询</el-button>

			<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false" width="30%">
				<el-form :model="formData">
					<el-form-item label="套餐名称">
						<el-input v-model="formData.package_name" />
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="cancel">取 消</el-button>
					<el-button :disabled="isClicked" type="primary" @click="addConfirm()">确 定</el-button>
				</div>
			</el-dialog>

			<el-dialog :title="dialogTitle1" :visible.sync="dialogVisible1" :close-on-click-modal="false" width="60%">
				<div style="text-align: center">
					<el-transfer style="text-align: left; display: inline-block" v-model="value" filterable
						:left-default-checked="[2, 3]" :right-default-checked="[1]" :render-content="renderFunc"
						:titles="['已拥有的项目', '未拥有的项目']" :button-texts="['添加到套餐', '从套餐移除']" :format="{
				          noChecked: '${total}',
				          hasChecked: '${checked}/${total}'
				        }" @change="handleChange" :data="data">
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
			<el-table-column prop="ID" label="套餐编号"></el-table-column>
			<el-table-column prop="package_name" label="套餐名称"></el-table-column>
			<el-table-column prop="price" label="套餐价格"></el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<el-button size="mini" @click="edit(scope.row)">项目详情</el-button>
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
				minPrice: '',
				maxPrice: '',
				isClicked: false,
				filter: {
					project: '',
					keyword: '',
				},
				dialogTitle: '',
				dialogVisible: false,
				dialogTitle1: '',
				dialogVisible1: false,
				formData: {
					ID: '',
					package_name: '',
					price: '',
				},
				tableData: [],
				currentPage: 1,
				pageSize: 10,
				total: 0,
				data: generateData(),
				value: [1],
				value4: [1],
				renderFunc(h, option) {
					return < span > {
						option.key
					} - {
						option.label
					} < /span>;
				}
			};
		},
		mounted() {
			this.fetchData();
		},
		methods: {
			fetchData() {
				// 发送异步请求获取表格数据
				const params = {
					minPrice: this.minPrice ? parseFloat(this.minPrice) : null,
					maxPrice: this.maxPrice ? parseFloat(this.maxPrice) : null,
					packageName: this.filter.keyword,
					page: this.currentPage,
					pageSize: this.pageSize,
				}

				console.log("明明启动了")

				axios.get('/web/selectPackage', {
					params
				}).then(response => {
					this.tableData = response.data.records;
					this.total = response.data.total;
					console.log(this.tableData)
				});
			},
			search() {
				this.currentPage = 1;
				this.fetchData();
			},
			add() {
				this.isClicked = false;
				this.dialogTitle = '添加套餐';
				this.formData = {
					ID: '',
					package_name: '',
					price: '',
				};
				this.dialogVisible = true;
			},
			edit(row) {
				console.log(row)
				this.dialogTitle1 = '项目详情';
				this.formData = {
					ID: row.ID,
					package_name: row.package_name,
					price: row.price,
				};
				this.dialogVisible1 = true;
			},
			// 删除
			remove(row) {
				
				// 发送请求删除套餐数据
				axios.get('/web/deletePackage', { params: { id: row.ID }}).then(Response => {
					this.fetchData();
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

				if (!this.formData.package_name) {
					this.$message.error('请输入完整信息');
					this.isClicked = false;
					return;
				}
				
				console.log("运行下一步"+this.formData.packageName)

				// 新增科室-项目
				
				axios.get('/web/addPackage?packageName=' + this.formData.package_name)
					.then(response => {
						this.fetchData();
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