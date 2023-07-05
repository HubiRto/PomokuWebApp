<script>
import axios from "axios";
import router from "@/router";
import data from "bootstrap/js/src/dom/data";

export default {
    name: 'ResetPassword',
    data() {
      return {
        password: '',
        confirmPassword: '',
        validToken: false
      }
    },
    methods: {
      data() {
        return data
      },
      resetPassword() {
        const urlParams = new URLSearchParams(window.location.search);
        const requestBody = {
          token: urlParams.get('token'),
          changedPassword: this.password
        };
        axios.post('http://localhost:8080/change-password', requestBody)
            .then(() => {
            router.push("/");
        })
            .catch(error => {
              if (error.response && error.response.status === 404) {
                console.log("blad");
                router.push("/");
              }
            });
      },
      verifyToken() {
        const urlParams = new URLSearchParams(window.location.search);
        const token = urlParams.get('token');
        axios.post('http://localhost:8080/verify-token', { token })
            .then(() => {
              this.validToken = true;
            })
            .catch(error => {
              // Token jest nieprawidłowy lub wygasł
              if (error.response && error.response.status === 404) {

              }
            });
      }
    },
    mounted() {
      this.verifyToken();
    }
  }
</script>

<template>
  <body>
  <div class="container min-vh-100 d-flex justify-content-center align-items-center">
    <template v-if="validToken">
    <form class="test-form" @submit.prevent="resetPassword">
      <div class="form-group">
        <label for="exampleInputPassword1">Hasło</label>
        <input type="password"
               class="form-control"
               id="exampleInputPassword1"
               placeholder=" Podaj hasło"
               v-model="password"
        >
      </div>
      <div class="form-group">
        <label for="exampleInputPassword1">Powtórz hasło</label>
        <input type="password"
               class="form-control"
               id="exampleInputPassword1"
               placeholder=" Podaj hasło"
        >
      </div>
      <button type="submit" class="btn btn-success w-100">Zmień hasło</button>
    </form>
    </template>
    <template v-else>
      <p>Invalid token.</p>
    </template>
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