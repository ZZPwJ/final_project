<template>
  <div class="hello">
    <label for="login_field">Login:</label>
    <input type="text" id="login_field" /><br/>
    <label for="login_field">Email:</label>
    <input type="text" id="login_field" /><br/>
    <label for="password_field">Password:</label>
    <input type="password" id="password_field" /><br/>
    <label for="login_field">Repeat password:</label>
    <input type="password" id="password2_field" />
    <input type="submit" value="Log in" />
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
