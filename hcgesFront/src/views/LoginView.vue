<template>
	<div class="login-container">
		<div class="login-form">
			<h2>用户登录</h2>
			<div class="form-group">
				<input type="text" id="username" v-model="username" placeholder="请输入账号" />
			</div>
			<div class="form-group">
				<input type="password" id="password" v-model="password" placeholder="请输入密码" />
			</div>
			<button class="btn-login" @click="handleLogin">登录</button>
			<button class="btn-login" @click="updatePassword">修改密码</button>
		</div>
	</div>
</template>

<script>
	import axios from 'axios';

	export default {
		data() {
			return {
				username: 'winston',
				password: '111111'
			}
		},
		methods: {

			handleLogin() {
				//处理登录逻辑
				axios.post('/web/doLogin', JSON.stringify({
				  username: this.$data.username,
				  password: this.$data.password,
				}), {
				  headers: {
				    'Content-Type': 'application/json'
				  }
				}).then(response => {
				  console.log("___"+response.data);
				  
				  if(response.data.ID == null){
					  this.$message({
					            showClose: true,
					            message: '账号或密码有误',
					            type: 'error'
					          });
				  }else{
					  localStorage.setItem("userName",response.data.username);
					  localStorage.setItem("userId",response.data.ID);
					  this.$router.push('/main')
				  }
				});
			},
			updatePassword(){
				this.$router.push('/password')
			}

		}
	}
</script>

<style>
	html,
	body {
		margin: 0;
		padding: 0;
		height: 100%;
	}
	
	.login-container {
		display: flex;
		justify-content: center;
		align-items: center;
		height: 100vh;
	}

	.login-form {
		width: 300px;
		padding: 20px;
		border: 1px solid #eee;
		border-radius: 5px;
		background-color: #fff;
	}

	h2 {
		text-align: center;
	}

	.form-group {
		margin-bottom: 20px;
	}

	label {
		display: block;
		margin-bottom: 5px;
	}

	input[type="text"],
	input[type="password"] {
		width: 100%;
		height: 30px;
		padding: 5px 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
		outline: none;
	}

	.btn-login {
		display: block;
		width: 100%;
		height: 40px;
		margin-top: 20px;
		border: none;
		border-radius: 5px;
		background-color: #4CAF50;
		color: #fff;
		font-size: 16px;
		cursor: pointer;
	}
</style>