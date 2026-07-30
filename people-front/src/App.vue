<script setup>
</script>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-4 shadow-sm">
    <div class="container">
      <router-link
        class="navbar-brand fw-semibold"
        to="/people"
      >
        Cadastro de Pessoas
      </router-link>
      <div class="d-flex">
        <router-link
          class="btn btn-light btn-sm fw-semibold"
          to="/people/create"
        >
          <i class="bi bi-person-plus me-1"></i>
          Nova Pessoa
        </router-link>
      </div>
    </div>
  </nav>
  <main class="container pb-5">
    <router-view />
  </main>
</template>

<style scoped>
main {
  min-height: 80vh;
}
</style>
