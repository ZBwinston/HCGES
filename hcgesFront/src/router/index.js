import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import MainView from '../views/MainView.vue'
// import UnitView from '../views/UnitView.vue'

Vue.use(VueRouter)

const routes = [{
		path: '/',
		name: 'login',
		component: LoginView
	},
	{
		path: '/main',
		name: 'main',
		component: MainView,
		children: [{
				path: '/main/unit',
				name: 'unit',
				component: () => import( '../views/UnitView.vue')
			},
			{
				path: '/main/role',
				name: 'role',
				component: () => import( '../views/RoleView.vue')
			},
			{
				path: '/main/detail',
				name: 'detail',
				component: () => import( '../views/DetailItemView.vue')
			},
			{
				path: '/main/department',
				name: 'department',
				component: () => import( '../views/DepartmentView.vue')
			},
			{
				path: '/main/project',
				name: 'project',
				component: () => import( '../views/ProjectView.vue')
			},
			{
				path: '/main/personnel',
				name: 'personnel',
				component: () => import( '../views/PersonnelView.vue')
			},
			{
				path: '/main/user',
				name: 'user',
				component: () => import( '../views/UserView.vue')
			},
			{
				path: '/main/package',
				name: 'package',
				component: () => import( '../views/PackageView.vue')
			},
			{
				path: '/main/physicalExam',
				name: 'physicalExam',
				component: () => import( '../views/PhysicalExamView.vue')
			},
			{
				path: '/main/balance',
				name: 'balance',
				component: () => import( '../views/BalanceRecordView.vue')
			},
			{
				path: '/main/log',
				name: 'log',
				component: () => import( '../views/LogView.vue')
			},
		]
	},
	{
		path: '/password',
		name: 'password',
		component: () => import( '../views/UpdatePassword.vue'),
	}
	// {
	//   path: '/unit',
	//   name: 'unit',
	//   component: () => import(/* webpackChunkName: "about" */ '../views/UnitView.vue')
	// }
]

const router = new VueRouter({
	routes
})

export default router