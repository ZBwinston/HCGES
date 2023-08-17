<template>
	<div class="main-div">
		<el-container>
			
		
		<!-- 头部 -->
		<el-header class="header">
			<!-- 系统logo -->
			<div class="logo">
				<img src="../assets/logo.png">
			</div>
			<!-- 系统标题 -->
			<div class="title">
				健康体检团检系统
			</div>
			<!-- 用户名和退出按钮 -->
			<div class="user">
				<span>{{ username }}</span>
				<el-button type="primary" size="small" @click="handleLogout">退出</el-button>
			</div>
		</el-header>
		 <el-container>
		<!-- 左侧菜单 -->
		<el-aside width="200px" v-model="isCollapse" class="menu">
			<el-menu default-active="activeIndex" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" v-for="(item,index) in menu">
				<el-submenu index="item.mid+''" key="item.mid">
					<template slot="title">
						<span v-html="item.menuName"></span>
					</template>
					<el-menu-item-group>
						<el-menu-item index="'/main/'+item1.url+''" v-if="item.children.length>0"
							v-for="(item1, index) in item.children" v-html="item1.menuName"
							@click="handleSelectMenu(item1.menuLink)"></el-menu-item>
					</el-menu-item-group>
				</el-submenu>
			</el-menu>
		</el-aside>
		<!-- 右侧主体 -->
		<el-main class="main">
			<!-- 根据菜单展示对应的内容区域 -->
			<router-view></router-view>
		</el-main>
		</el-container>
		</el-container>
	</div>
</template>

<script>
	import axios from 'axios'
	let adminArr = []
	let menuObj = []
	export default {
		name: 'MainLayout',
		data() {
			return {
				username: '',
				isCollapsed: false, // 菜单是否折叠
				myMenu: [],
				allMenu: [],
				adminArr: [],
				menu: [],
				activeIndex: '', // 默认选中第一个二级菜单
			}
		},
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleSelectMenu(link) {
				// 处理菜单选择，跳转对应的页面
				// this.$router.push(`/main/${link}`)
				if (this.$route.path !== `/main/${link}`) {
				  this.$router.push(`/main/${link}`)
				}
			},
			// 退出登录
			handleLogout() {
				// 清空localStorage
				window.localStorage.clear();
				this.$router.push('/')
			},
			// 后台获取菜单
			searchMenu(userId) {
				axios.get('/web/getRoleIdById', {
					params: {
						userId: userId
					}
				}).then(Response => {
					// 根据角色id查询菜单
					axios.get('web/searchMenu', {
						params: {
							roleId: Response.data
						}
					}).then(Response => {
						this.myMenu = Response.data;
						console.log(this.myMenu[0].ID)
						let newArr = new Array();
						const myMenu1 = this.myMenu.map(obj => {
							return {
								menuId: obj.ID,
								menuName: obj.menu_name,
								menuPid: obj.parent_menu_ID,
								menuLink: obj.menu_link,
							};
						});
						this.getMenuArr(myMenu1, newArr, 0);
						this.menu = newArr;
					});
					// this.fetchData();
				});
			},
			getMenuArr(loginMenu, newArr, one) {
				loginMenu.forEach((item) => {
					if (item.menuPid == one) {
						menuObj = {
							menuId: item.ID,
							menuName: item.menuName,
							menuPid: item.menuPid,
							menuIcon: item.menuIcon,
							menuLink: item.menuLink,
							children: [],
						};
						newArr.push(menuObj);
						loginMenu.forEach((item1) => {
							if (item1.menuPid == item.menuId) {
								menuObj.children.push({
									menuId: item1.menuId,
									menuName: item1.menuName,
									menuPid: item1.menuPid,
									menuLink: item1.menuLink,
								})
							}
						});
					}
				});
			},
			updatePassword(){
				console.log("xxx")
			},
			
		},
		created() {
			this.username = localStorage.getItem("userName");
			this.searchMenu(localStorage.getItem("userId"));
		}
	}
</script>

<style>
	.main-div {
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
	}
	
	.header {
		display: flex;
		align-items: center;
		justify-content: space-between;
		background-color: #fff;
		padding: 0 20px;
		height: 60px;
		box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .1);
		z-index: 10;
	}

	.logo img {
		height: 40px;
	}

	.title {
		font-size: 20px;
	}

	.user {
		display: flex;
		align-items: center;
	}

	.menu {
		width: 220px;
	}

	.aside {
		background-color: #f8f8f8;
		min-height: 100vh;
		overflow-y: auto;
	}

	.main {
		/* height: 100%; */
		overflow: hidden;
		padding: 20px;
		background-color: #fff;
	}

	.collapse-button {
		position: absolute;
		bottom: 20px;
		left: 50%;
		transform: translateX(-50%);
	}

	.menu {
		border-right: none;
		background-color: #f8f8f8;
		overflow-y: auto;
		height: calc(100vh - 60px);
	}

	.el-submenu__title:hover {
		background-color: #f0f0f0;
		color: #409EFF;
	}

	.el-menu-item:hover,
	.el-menu-item.is-active {
		color: #409EFF;
	}

	.el-menu-item.is-active .el-menu-item__index {
		color: #409EFF;
	}
</style>