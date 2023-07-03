<script>
  import axios from "axios";

  export default {
    name: 'Login',
    data() {
      return {
        loginData: {
          login: '',
          password: ''
        },
      }
    },
    methods: {
      async submitLogin() {
        console.log(this.loginData);
        const response = await axios.post('http://localhost:8080/login', this.loginData);
        localStorage.setItem('token', response.data.token);
      }
    }
  }
</script>

<template>
  <body>
  <div class="container min-vh-100 d-flex justify-content-center align-items-center">
    <form class="test-form" @submit.prevent="submitLogin">
      <div class="form-group">
        <label for="exampleInputEmail1">Email</label>
        <input type="email"
               class="form-control"
               id="exampleInputEmail1"
               aria-describedby="emailHelp"
               placeholder="Enter email"
               v-model="loginData.login"
        >
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Hasło</label>
        <input type="password"
               class="form-control"
               id="exampleInputPassword1"
               placeholder=" Podaj hasło"
               v-model="loginData.password"
        >
      </div>
      <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">Zapamiętaj mnie</label>
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