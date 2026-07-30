<script setup>

const props = defineProps({
    people: {
        type: Array,
        required: true
    }
})

const emit = defineEmits([
    'edit',
    'delete'
])

function editPerson(id) {
    emit('edit', id)
}

function deletePerson(id){
    emit('delete', id)
}

</script>

<template>
    <div class="card shadow">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover align-middle mb-0">
                    <thead class="table-light">
                        <tr>
                            <th scope="col">
                                Nome Completo
                            </th>
                            <th scope="col">
                                CPF/CNPJ
                            </th>
                            <th scope="col">
                                Telefone
                            </th>
                            <th scope="col">
                                Email
                            </th>
                            <th 
                                scope="col"
                                class="text-end"
                            >
                                Ações
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr
                            v-if="!people.length"
                        >
                            <td
                                colspan="5"
                                class="text-center text-muted py-4"
                            >
                                Nenhuma pessoa cadastrada.
                            </td>
                        </tr>

                        <tr
                            v-for="person in people"
                            :key="person.id"
                        >
                            <td data-label="Nome Completo">
                                {{ person.fullName }}
                            </td>
                            <td data-label="CPF/CNPJ">
                                {{ person.document }}
                            </td>
                            <td data-label="Telefone">
                                {{ person.phone }}
                            </td>
                            <td data-label="Email">
                                {{ person.email }}
                            </td>

                            <td 
                                data-label="Ações"
                                class="text-end"
                            >
                                <div class="btn-group">
                                    <button
                                        class="btn btn-sm btn-outline-primary"
                                        title="Editar"
                                        @click="editPerson(person.id)"
                                    >
                                        <i class="bi bi-pencil"></i>
                                    </button>
                                    <button
                                        class="btn btn-sm btn-outline-danger"
                                        title="Excluir"
                                        @click="deletePerson(person.id)"
                                    >
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>



<style scoped>
@media (max-width: 767.98px) {
    table thead {
        display: none;
    }

    table tbody tr {
        display: block;
        margin-bottom: 1rem;
        border: 1px solid var(--bs-border-color);
        border-radius: 0.5rem;
    }

    table tbody td {
        display: flex;
        justify-content: space-between;
        align-items: center;
        text-align: right;
        padding: 0.75rem;
        border: none;
        border-bottom: 1px solid var(--bs-border-color-translucent);
    }

    table tbody td::before {
        content: attr(data-label);
        font-weight: 600;
        text-align: left;
    }

    table tbody td:last-child {
        border-bottom: none;
    }

    table tbody td[data-label="Ações"] {
        justify-content: flex-end;
    }
}
</style>