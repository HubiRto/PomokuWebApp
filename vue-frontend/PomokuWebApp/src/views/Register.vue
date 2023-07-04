<script>
  import axios from "axios";
  import router from "@/router";

  export default {
    name: 'Register',
    data() {
      return {
        registerData: {
          firstName: '',
          lastName: '',
          email: '',
          password: ''
        }
      }
    },
    methods: {
      submitRegister() {
        axios.post('http://localhost:8080/register', this.registerData);
        router.push('/login');
      }
    }
  }
</script>

<template>
  <body>
  <div class="container min-vh-100 d-flex justify-content-center align-items-center">
    <form class="test-form" @submit.prevent="submitRegister">

      <div class="form-group">
        <label for="firstNameInput">Imię</label>
        <input type="text" class="form-control" id="firstName"
               v-model="registerData.firstName" placeholder="Imię">
      </div>

      <div class="form-group">
        <label for="lastNameInput">Nazwisko</label>
        <input type="text" class="form-control" id="lastName"
               v-model="registerData.lastName" placeholder="Nazwisko">
      </div>

      <div class="form-group">
        <label for="emailInput">Email</label>
        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
               v-model="registerData.email" placeholder="Email">
      </div>

      <div class="form-group">
        <label for="passwordInput">Hasło</label>
        <input type="password" class="form-control" id="password"
               v-model="registerData.password" placeholder="Hasło">
      </div>
      <div class="form-group">
        <label for="repeatPasswordInput">Powtórz Hasło</label>
        <input type="password" class="form-control" id="repeatPassword" placeholder="Powtórz Hasło">
      </div>
      <button type="submit" class="btn btn-success w-100">Zarejestruj</button>
      <RouterLink to="/login">Masz już konto?</RouterLink>
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