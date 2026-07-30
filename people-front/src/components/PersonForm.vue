<script setup>
import { reactive, watch, computed } from 'vue';
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

const touched = reactive({
    fullName: false,
    document: false,
    phone: false,
    email: false
})

const validName = computed(() => {
    return /^[A-Za-zÀ-ÿ\s]+$/.test(person.fullName.trim())
})

const validForm = computed(() => {
    return (
        validName.value &&
        person.document.trim() !== '' &&
        person.phone.trim() !== '' &&
        person.email.trim() !== ''
    )
})

const errors = computed(() => ({
    fullName: {
        required: person.fullName.trim() === '',
        invalid: !validName.value
    },
    document: {
        required: person.document.trim() === ''
    },
    phone: {
        required: person.phone.trim() === ''
    },
    email: {
        required: person.email.trim() === ''
    }
}))

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
                            Nome Completo *
                        </label>
                        <input
                            type="text"
                            class="form-control"
                            :class="{ 'is-invalid': touched.fullName && (errors.fullName.required || errors.fullName.invalid) }"
                            placeholder="Digite o nome completo (ex: João da Silva)"
                            v-model="person.fullName"
                            @blur="touched.fullName = true"
                        />
                        <div
                            v-if="touched.fullName && errors.fullName.required"
                            class="invalid-feedback"
                        >
                            O nome é obrigatório.
                        </div>
                        <div
                            v-else-if="touched.fullName && errors.fullName.invalid"
                            class="invalid-feedback"
                        >
                            O nome deve conter apenas letras.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">
                            CPF/CNPJ *
                        </label>
                        <input
                            type="text"
                            class="form-control"
                            :class="{ 'is-invalid': touched.document && errors.document.required }"
                            placeholder="Digite o CPF ou CNPJ (ex: 123.456.789-00)"
                            v-model="person.document"
                            @input="person.document = formatDocument($event.target.value)"
                            @blur="touched.document = true"
                            />
                            <div
                                v-if="touched.document && errors.document.required"
                                class="invalid-feedback"
                            >
                            O CPF/CNPJ é obrigatório.
                            </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">
                            Telefone *
                        </label>
                        <input
                            type="text"
                            class="form-control"
                            :class="{ 'is-invalid': touched.phone && errors.phone.required }"
                            placeholder="Digite o telefone (ex: (11) 997788-9900)"
                            v-model="person.phone"
                            @input="person.phone = formatPhone($event.target.value)"
                            @blur="touched.phone = true"
                        />
                            <div
                                v-if="touched.phone && errors.phone.required"
                                class="invalid-feedback"
                            >
                            O telefone é obrigatório.
                            </div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label">
                            Email *
                        </label>
                        <input
                            type="email"
                            class="form-control"
                            :class="{ 'is-invalid': touched.email && errors.email.required }"
                            placeholder="Digite o email (ex: joão@gmail.com)"
                            v-model="person.email"
                            @blur="touched.email = true"
                        />
                            <div
                                v-if="touched.phone && errors.phone.required"
                                class="invalid-feedback"
                            >
                            O email é obrigatório.
                            </div>
                    </div>

                    <div class="d-flex justify-content-end">
                        <router-link
                            class="btn btn-outline-secondary me-2"
                            to="/people"
                        >
                            Voltar
                        </router-link>
                        <button
                            :disabled="!validForm"
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