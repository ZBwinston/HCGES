import Vue from 'vue'
// import Vuex from 'vuex'
import Vuex, {
	mapActions
} from 'vuex' // 引入 mapActions 函数

Vue.use(Vuex)

export default new Vuex.Store({
	state: {},
	getters: {},
	mutations: {},
	actions: {
		deleteItem() {
			console.log("点击了删除")
		},
		uploadExcel() {},
		createOrder() {},
		recharge() {},
		addNewItem() {},
		changePage() {},
	},
	modules: {}
})