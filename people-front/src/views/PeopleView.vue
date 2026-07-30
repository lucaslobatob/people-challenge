<script setup>
import { onMounted, ref, computed } from 'vue';
import PersonTable from '../components/PersonTable.vue';
import { deletePerson, getPeople } from '../services/personService.js';
import { useRouter } from 'vue-router';
import ConfirmModal from '../components/ConfirmModal.vue';

const people = ref([])
const router = useRouter()
const showModal = ref(false)
const selectedPersonId = ref(null)
const selectedPerson = ref(null)
const nameFilter = ref('')
const documentFilter = ref('')

const filteredPeople = computed(() => {
  return people.value.filter(person => {

    const matchName =
      person.fullName
        .toLowerCase()
        .includes(nameFilter.value.toLowerCase())

    const matchDocument =
      person.document
        .toLowerCase()
        .includes(documentFilter.value.toLowerCase())

    return matchName && matchDocument
  })
})

async function loadPeople(){
  try {
    const response = await getPeople()
    people.value = response.data
  } catch(error){
    console.error(error)
  }
}

function editPerson(id){
  router.push(`/people/${id}/edit`)
}

async function openDeleteModal(id){
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
    loadPeople()
  } catch(error) {
    console.error(error)
  }
}

function cancelDelete() {
  showModal.value = false
  selectedPersonId.value = null
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
          <div class="col-md-6">
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
          <div class="col-md-6">
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
        </div>
        <PersonTable 
          :people="filteredPeople"
          @edit="editPerson"
          @delete="openDeleteModal"
        />
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