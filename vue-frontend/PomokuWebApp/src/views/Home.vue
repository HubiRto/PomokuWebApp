<script>
  import axios from "axios";

  export default {
    name: 'Home',
    mounted() {
      axios
          .get("http://localhost:8080/messages", {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            }
          })
          .then(res => {
            console.log(res);
          });
    }
  }
</script>

<template>
  <body>
  <h1>Witaj</h1>
  </body>
</template>

<style scoped>

</style>