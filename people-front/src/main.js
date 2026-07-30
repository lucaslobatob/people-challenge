import { createApp } from 'vue'
import router from './router/index.js'
import Toast from 'vue-toastification'

import 'vue-toastification/dist/index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import 'bootstrap-icons/font/bootstrap-icons.css'

import App from './App.vue'

const app = createApp(App)

app.use(router)

app.use(Toast, {
    position: 'top-right',
    timeout: 3000,
    closeOnClick: true,
    pauseOnHover: true,
    hideProgressBar: false
}
)

app.mount('#app')
