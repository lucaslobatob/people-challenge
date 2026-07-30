<script setup lang="ts">
import PersonForm from '../components/PersonForm.vue';
import { createPerson, getPersonById, updatePerson } from '../services/personService.js';
import { useRouter, useRoute } from 'vue-router';
import { ref, onMounted, computed } from 'vue';
import { useToast } from 'vue-toastification';

const router = useRouter()
const route = useRoute()

const person = ref(null)
const toast = useToast()

const isEditMode = computed(() => !!route.params.id)

async function loadPerson(){
    const id = route.params.id
    if(id){
        try{
            const response = await getPersonById(id)
            person.value = response.data
        } catch(error) {
            console.error(error)
        }
    }
}

async function savePerson(person) {
    try {
        if(person.id){
            await updatePerson(person.id, person)
            toast.success('Pessoa atualizada com sucesso!')
        } else {
            await createPerson(person)
            toast.success('Pessoa cadastrada com sucesso!')
        }
        router.push('/people')
    } catch(error){
        toast.error('CPF/CNPJ já existe ou Email já cadastrado.')
        toast.error('Erro ao salvar pessoa.')
    }
}

onMounted(() => {
    loadPerson()
})

</script>

<template>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-8 col-xl-6">
                <div class="mb-4">
                    <h1 class="h3">
                        {{ isEditMode ? 'Editar Pessoa' : 'Cadastrar Pessoa' }}
                    </h1>
                    <p class="text-muted mb-0">
                        {{
                            isEditMode
                            ? 'Altere os dados da pessoa.'
                            : 'Preencha os dados abaixo para cadastrar uma nova pessoa.'
                        }}
                    </p>
                </div>
                <PersonForm
                    :personData="person"
                    :submitLabel="isEditMode ? 'Salvar Alterações' : 'Cadastrar'"
                    @save="savePerson"
                />
            </div>
        </div>
    </div>
</template>