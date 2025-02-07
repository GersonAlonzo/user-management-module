import { createRouter, createWebHistory } from 'vue-router';
import UserManagement from '../views/UserManagement.vue';
import UserForm from '../components/UserForm.vue'; 

const routerd = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'UserManagement',
      component: UserManagement,
    },
    {
      path: '/create',
      name: 'CreateUser',
      component: UserForm, 
    },
    {
      path: '/edit/:id',  
      name: 'EditUser',
      component: UserForm, 
      props: true,  
    },
  ]
});

export default routerd;