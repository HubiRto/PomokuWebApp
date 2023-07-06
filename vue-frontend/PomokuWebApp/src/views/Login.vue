<script>
  import axios from "axios";
  import router from "@/router";

  export default {
    name: 'Login',
    data() {
      return {
        loginData: {
          email: '',
          password: ''
        },
      }
    },
    methods: {
      async submitLogin() {
        const response = await axios.post('http://localhost:8080/login', this.loginData);
        localStorage.setItem('token', response.data.token);
        await router.push('/');
      },
      async submitLoginGoogle() {
        await router.push('http://localhost:8080/oauth2/authorization/github');
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
               v-model="loginData.email"
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
      <button type="submit" class="login btn btn-success w-100">Zaloguj</button>
      <RouterLink to="/register">Nie masz jeszcze konta?</RouterLink><br>
      <RouterLink to="/forgot-password">Zapomniałeś hasła?</RouterLink>
      <hr>
      <p class="text-center"> Zaloguj przy pomocy</p>
      <button type="button" class="btn btn-danger w-100" @click="submitLoginGoogle">Google</button>
      <button type="button" class="btn btn-dark w-100">Github</button>
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