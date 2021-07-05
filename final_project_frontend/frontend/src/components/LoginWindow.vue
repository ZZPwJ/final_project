<template>
    <div id="containter">
        <div class="background-image"></div>
        <div class="content">
            <div class="header_background teal">
                <div class="header">Sign up</div>
            </div>
            <div class="form">
                <form>
                    <div class="input_field">
                        <label for="login_field">Login:</label>
                        <input type="text" id="login_field" v-model="user" /><br/>
                    </div>
                    <div class="input_field">
                        <label for="email_field">Email:</label>
                        <input type="email" id="email_field" class="validate" /><br/>
                    </div>
                    <div class="input_field">
                        <label for="password_field">Password:</label>
                        <input type="password" id="password_field" /><br/>
                    </div>
                    <div>
                        <label for="password2_field">Repeat password:</label>
                        <input type="password" id="password2_field" />
                    </div>
                    <a v-on:click="registerUser()" class="waves-effect waves-light btn">Register</a>
                </form>
            </div>
        </div>
    </div>
</template>


<script>
import axios from 'axios';


export default {
    methods: {
        verifyUser() {
            axios.post('', ).then()
        },
        registerUser() {
            axios.post('http://localhost:8080/user', {
                email: "fj@gmail.com",
                password: "hahajio",
                displayName: "amamadsda"
            }).then((response) => {
                console.log(response);
            });
        }
    },
    data: function() {
        return {
            signUpData: {
                login: "",
                email: "",
                password: "",
                repeatedPassword: "",
            }
        }
    }
}

</script>

<style>
#containter {
    position: relative;
}

.header_background {
    background-color: rgba(0, 255, 0);
    margin: 0px;
    padding-top: 20px;
    padding-bottom: 20px;
    position: relative;
    color: lightgray;
}

.header {
    font-size: 18px;
}


.background-image {
  position: fixed;
  left: 0;
  right: 0;
  z-index: 1;
  display: block;
  background-image: url('../assets/wyspy_owcze.jpeg');
  background-size: 100%, 100%;
  width: 100%;
  height: 100%;
  filter: blur(1.5px) brightness(0.3);
}

.content {
  background: rgba(255, 255, 255, 0.9);
  border-radius: 8px;
  box-shadow: 0 5px 5px rgba(255, 255, 255, 0.2);
  font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
  position: fixed;
  z-index: 2;
  position: absolute;
  top: 40%;
  left: 50%;
  -ms-transform: translate(-50%, 40%);
  transform: translate(-50%, 40%);
  }

.form {
    padding: 10px 20px;
    margin: 10px 10px;
}

</style>