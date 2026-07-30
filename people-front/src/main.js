import { createApp } from 'vue'
import router from './router/index.js'
import { vMaska } from 'maska/vue'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import 'bootstrap-icons/font/bootstrap-icons.css'

import App from './App.vue'

const app = createApp(App)

app.directive('maska', vMaska)

app.use(router)

app.mount('#app')
