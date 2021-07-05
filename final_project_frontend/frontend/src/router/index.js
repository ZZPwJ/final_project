import { createWebHistory, createRouter } from "vue-router";
import StartWindow from "../components/StartWindow.vue";
import LoginWindow from "../components/LoginWindow.vue";

const routes = [
    {
        path: "/",
        name: "Login",
        component: StartWindow,
    },
    {
        path: "/registration",
        name: "",
        component: LoginWindow,
    },
    {
        path: '/',
         redirect: {
           name: 'Login'
          },
      },
]

const router = createRouter({history: createWebHistory(), routes});
export default router;