<script>
  import axios from "axios";
  import router from "@/router";

  export default {
    name: 'Login',
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
        <label for="exampleInputEmail1">Email</label>
        <input type="email"
               class="form-control"
               id="exampleInputEmail1"
               aria-describedby="emailHelp"
               placeholder="Enter email"
               v-model="loginData.email"
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