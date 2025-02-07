import { createApp } from 'vue';
import App from './App.vue';
import routerd from '../src/route';
import './style.css'; 



const app = createApp(App);

app.use(routerd);

app.mount('#app');