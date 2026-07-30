<script setup>
import { onMounted, ref } from 'vue';
import PersonTable from '../components/PersonTable.vue';
import { deletePerson, getPeople } from '../services/personService.js';
import { useRouter } from 'vue-router';
import ConfirmModal from '../components/ConfirmModal.vue';
import { useToast } from 'vue-toastification';

const people = ref([])
const router = useRouter()
const showModal = ref(false)
const selectedPersonId = ref(null)
const selectedPerson = ref(null)
const nameFilter = ref('')
const documentFilter = ref('')
const toast = useToast()
const totalPages = ref(0)
const itemsPerPage = 5
const currentPage = ref(0)

async function loadPeople() {
  try {
    const response = await getPeople({
      name: nameFilter.value,
      document: documentFilter.value,
      page: currentPage.value,
      size: itemsPerPage
    })
    people.value = response.data.content
    totalPages.value = response.data.totalPages
  } catch(error) {
    console.error(error)
    toast.error("Erro ao carregar pessoas.")
  }
}

function searchPeople() {
  currentPage.value = 0
  loadPeople()
}

function clearFilters() {
  nameFilter.value = ''
  documentFilter.value = ''
  currentPage.value = 0
  loadPeople()
}


function editPerson(id) {
  router.push(`/people/${id}/edit`)
}

function openDeleteModal(id) {
  selectedPersonId.value = id
  selectedPerson.value = people.value.find(
    person => person.id === id
  )
  showModal.value = true
}

async function confirmDelete() {
  try {
    await deletePerson(selectedPersonId.value)
    showModal.value = false
    toast.success("Pessoa excluída com sucesso!")
    await loadPeople()
  } catch(error) {
    console.error(error)
    toast.error("Erro ao excluir pessoa.")
  } 
}

function cancelDelete() {
  showModal.value = false
  selectedPersonId.value = null
  selectedPerson.value = null
}

function nextPage() {
  if(currentPage.value < totalPages.value - 1) {
    currentPage.value++
    loadPeople()
  }
}


function previousPage() {
  if(currentPage.value > 0) {
    currentPage.value--
    loadPeople()
  }
}

function changePage(page) {
  currentPage.value = page
  loadPeople()
}

onMounted(() => {
  loadPeople()
})
</script>

<template>
  <div class="container mt-5">
    <div class="d-flex justify-content-center align-items-center mb-4">
      <div>
        <h1 class="h3 mb-1">
          Pessoas Cadastradas
        </h1>
        <p class="text-muted mb-0">
          Gerencie as pessoas cadastradas no sistema.
        </p>
      </div>
    </div>

    <div class="card shadow">
      <div class="card-body">
        <div class="row mb-4">
          <div class="col-md-5">
            <label class="form-label">
              Filtrar por Nome
            </label>
            <input
              type="text"
              class="form-control"
              placeholder="Digite o nome..."
              v-model="nameFilter"
            />
          </div>
          <div class="col-md-5">
            <label class="form-label">
              Filtrar por CPF/CNPJ
            </label>
            <input
              type="text"
              class="form-control"
              placeholder="Digite o CPF ou CNPJ..."
              v-model="documentFilter"
            />
          </div>
          <div class="col-md-2 d-flex align-items-end gap-2">
    <button
        class="btn btn-primary"
        @click="searchPeople"
    >
        Buscar
    </button>
    <button
        class="btn btn-outline-secondary"
        @click="clearFilters"
    >
        Limpar
    </button>
</div>
</div>

        <PersonTable
          :people="people"
          @edit="editPerson"
          @delete="openDeleteModal"
        />
        <nav
          v-if="totalPages > 1"
          class="mt-4"
        >
          <ul class="pagination justify-content-center">
            <li
              class="page-item"
              :class="{ disabled: currentPage === 0 }"
            >
              <button
                class="page-link"
                @click="previousPage"
              >
                Anterior
              </button>
            </li>
            <li
              v-for="page in totalPages"
              :key="page"
              class="page-item"
              :class="{ active: currentPage === page - 1 }"
            >
              <button
                class="page-link"
                @click="changePage(page - 1)"
              >
                {{ page }}
              </button>
            </li>
            <li
              class="page-item"
              :class="{ disabled: currentPage === totalPages - 1 }"
            >
              <button
                class="page-link"
                @click="nextPage"
              >
                Próxima
              </button>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>

  <ConfirmModal
    v-if="showModal"
    :personName="selectedPerson?.fullName"
    @confirm="confirmDelete"
    @cancel="cancelDelete"
  />
</template>