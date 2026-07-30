<script setup>

const props = defineProps({
    personName: {
        type: String,
        required: true
    }
})

const emit = defineEmits([
    'confirm',
    'cancel'
])

function confirmDelete(){
    emit('confirm')
}

function cancelDelete(){
    emit('cancel')
}

</script>

<template>

<div 
    class="modal fade show d-block"
    tabindex="-1"
>
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content shadow">
            <div class="modal-header">
                <h5 class="modal-title">
                    Confirmar exclusão
                </h5>
                <button
                    type="button"
                    class="btn-close"
                    @click="cancelDelete"
                ></button>
            </div>

            <div class="modal-body">
                <p class="mb-0">
                    Deseja realmente excluir
                    <strong>
                        {{ personName }}
                    </strong>
                    ?
                </p>
            </div>

            <div class="modal-footer">
                <button
                    type="button"
                    class="btn btn-secondary"
                    @click="cancelDelete"
                >
                    Cancelar
                </button>
                <button
                    type="button"
                    class="btn btn-danger"
                    @click="confirmDelete"
                >
                    <i class="bi bi-trash me-1"></i>
                    Excluir
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal-backdrop fade show"></div>
</template>