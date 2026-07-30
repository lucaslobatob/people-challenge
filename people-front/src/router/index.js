import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: '/people',
        component: () => import('../views/PeopleView.vue')
    },
    {
        path: '/people/create',
        component: () => import('../views/PersonRegisterView.vue')
    },
    {
        path: '/people/:id/edit',
        component: () => import('../views/PersonRegisterView.vue')
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
