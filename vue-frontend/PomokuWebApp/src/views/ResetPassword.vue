<script>
  import axios from "axios";
  import router from "@/router";

  export default {
    name: 'ResetPassword',
    data() {
      return {
        loginData: {
          email: ''
        },
      }
    },
    methods: {
      async submitLogin() {
        await axios.post('http://localhost:8080/reset-password-request', this.loginData);
      }
    }
  }
</script>

<template>
  <body>
  <div class="container min-vh-100 d-flex justify-content-center align-items-center">
    <form class="test-form" @submit.prevent="submitLogin">
      <div class="form-group">
        <label for="exampleInputPassword1">Hasło</label>
        <input type="password"
               class="form-control"
               id="exampleInputPassword1"
               placeholder=" Podaj hasło"
               v-model="loginData.password"
        >
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Powtórz hasło</label>
        <input type="password"
               class="form-control"
               id="exampleInputPassword1"
               placeholder=" Podaj hasło"
               v-model="loginData.password"
        >
      </div>
      <button type="submit" class="btn btn-success w-100">Zaloguj</button>
    </form>
  </div>
  </body>
</template>

<style scoped>
body {
  background: blue;
}
.test-form {
  background: white;
  padding: 15px;
  border-radius: 10px;
}
.test-form button {
  margin-top: 15px;
}
.form-group {
  margin-top: 10px;
}
</style>