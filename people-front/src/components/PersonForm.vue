<script setup>
import { reactive, watch } from 'vue';
import { formatPhone, formatDocument } from '../utils/formatters'

const props = defineProps({
    personData: {
        type: Object,
        default: null
    },
    submitLabel: {
        type: String,
        default: 'Salvar'
    }
})

const person = reactive({
    fullName: '',
    document: '',
    phone: '',
    email: ''
})

watch(
    () => props.personData,
    (newPerson) => {
        if (newPerson){
            Object.assign(person, newPerson)
        }
    },
    {
        immediate: true
    }
)

const emit = defineEmits([
    'save'
])

function handleSubmit() {
    emit('save', { ...person })
}

</script>

<template>
    <div class="container mt-5">
        <div class="card shadow">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">
                    Cadastro de Pessoa
                </h5>
            </div>
            <div class="card-body">
                <form @submit.prevent="handleSubmit">
                    <div class="mb-3">
                        <label class="form-label">
                            Nome Completo
                        </label>
                        <input
                            type="text"
                            class="form-control"
                            placeholder="Digite o nome completo (ex: João da Silva)"
                            v-model="person.fullName"
                        />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">
                            CPF/CNPJ
                        </label>
                        <input
                            type="text"
                            class="form-control"
                            placeholder="Digite o CPF ou CNPJ (ex: 123.456.789-00)"
                            v-model="person.document"
                            @input="person.document = formatDocument($event.target.value)"
                        />
                    </div>

                    <div class="mb-3">
                        <label class="form-label">
                            Telefone
                        </label>
                        <input
                            type="text"
                            class="form-control"
                            placeholder="Digite o telefone (ex: (11) 997788-9900)"
                            v-model="person.phone"
                            @input="person.phone = formatPhone($event.target.value)"
                        />
                    </div>

                    <div class="mb-4">
                        <label class="form-label">
                            Email
                        </label>
                        <input
                            type="email"
                            class="form-control"
                            placeholder="Digite o email (ex: joão@gmail.com)"
                            v-model="person.email"
                        />
                    </div>

                    <div class="d-flex justify-content-end">
                        <router-link
                            class="btn btn-outline-secondary me-2"
                            to="/people"
                        >
                            Voltar
                        </router-link>
                        <button
                            type="submit"
                            class="btn btn-primary"
                        >
                        {{ submitLabel }}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>