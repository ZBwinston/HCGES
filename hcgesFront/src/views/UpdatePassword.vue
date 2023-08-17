<template>
  <div class="password-form">
    <el-card class="card" :body-style="{ padding: '20px' }" shadow="hover">
      <el-form ref="form" :model="formData" label-width="80px">
        <el-form-item label="账号" prop="account">
          <el-input v-model="formData.account" placeholder="请输入账号" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password1">
          <el-input v-model="formData.password1" :type="passwordShow1 ? 'text' : 'password'" placeholder="请输入新密码">
            <i class="el-input__icon" :class="passwordShow1 ? 'el-icon-view' : 'el-icon-close'" @click="togglePasswordShow1"></i>
          </el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="password2">
          <el-input v-model="formData.password2" :type="passwordShow2 ? 'text' : 'password'" placeholder="请再次输入新密码">
            <i class="el-input__icon" :class="passwordShow2 ? 'el-icon-view' : 'el-icon-close'" @click="togglePasswordShow2"></i>
          </el-input>
        </el-form-item>
      </el-form>
      <div class="btn-group">
        <el-button type="primary" @click="submitForm" :disabled="!isFormValid">确认</el-button>
        <el-button @click="cancelForm">取消</el-button>
      </div>
      <div class="msg-box" v-show="errorMessage">
        <i class="el-icon-error"></i>
        <span>{{ errorMessage }}</span>
      </div>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PasswordForm',
  data() {
    return {
      formData: {
        account: '',
        password1: '',
        password2: '',
      },
      passwordShow1: false,
      passwordShow2: false,
      isFormValid: false,
      errorMessage: '',
    };
  },
  methods: {
    /**
     * 提交表单
     */
    submitForm() {
      if (!this.formData.account || !this.formData.password1 || !this.formData.password2) {
        this.errorMessage = '请填写账号和密码';
        return;
      }
      if (this.formData.password1 !== this.formData.password2) {
        this.errorMessage = '两次输入的密码不一致';
        return;
      }
      
      // 调用后端接口，将账号密码发送过去
	  
	  axios.post('/web/updateById', this.formData).then(response => {
	    // 显示成功消息
	    this.$message.success(response.data);
	    this.$router.push({ path: '/' });
	  }).catch((error) => {
	    // 显示失败消息
	    let message = error.response.data;
	    if (message === undefined) {
	      // 如果未提供错误消息，则默认提供一个通用的消息
	      message = '密码修改失败，请稍后再试';
	    }
	    this.$message.error(message);
	  });
	  
    },
    /**
     * 取消表单
     */
    cancelForm() {
      this.$router.push({ path: '/' });
    },
    togglePasswordShow1() {
      this.passwordShow1 = !this.passwordShow1;
    },
    togglePasswordShow2() {
      this.passwordShow2 = !this.passwordShow2;
    },
  },
  watch: {
    formData: {
      handler() {
        this.isFormValid = !!(this.formData.account && this.formData.password1 && this.formData.password2 && this.formData.password1 === this.formData.password2);
        if (this.isFormValid) {
          this.errorMessage = '';
        }
      },
      deep: true,
    },
  },
};
</script>

<style scoped>
.password-form {
  margin: 40px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: calc(100vh - 80px);
}
.card {
  min-width: 320px;
}
.btn-group {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-top: 20px;
}
.msg-box {
  padding-top: 10px;
  display: flex;
  align-items: center;
  font-size: 14px;
  color: #f56c6c;
}
.msg-box i {
  margin-right: 5px;
}
.el-input__icon {
  cursor: pointer;
}
</style>