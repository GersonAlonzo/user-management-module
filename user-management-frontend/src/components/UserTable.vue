<template>
  <div class="overflow-x-auto relative">
    <!-- Tabla (sin cambios) -->
    <table class="min-w-full bg-white border border-gray-200 rounded-lg">
      <thead class="bg-gray-100">
        <tr>
          <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">ID</th>
          <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Nombre de Usuario
          </th>
          <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Correo
            Electrónico</th>
          <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">MsisDN</th>
          <th class="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Acciones</th>
        </tr>
      </thead>
      <tbody class="divide-y divide-gray-200">
        <tr v-if="loading" class="animate-pulse">
          <td colspan="4" class="px-6 py-4 text-center">
            <div class="h-4 bg-gray-300 rounded-full w-3/4 mx-auto"></div>
          </td>
        </tr>
        <tr v-else v-for="user in users" :key="user.id" class="hover:bg-gray-50 transition duration-150 ease-in-out">
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ user.id }}</td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ user.username }}</td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ user.email }}</td>
          <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-900">{{ user.msisdn }}</td>
          <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
            <div class="flex items-center space-x-2">
              <button @click="editUser(user)"
                class="bg-orange-400 hover:bg-orange-300 text-white font-bold py-2 px-4 rounded">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                  stroke="currentColor" class="w-5 h-5">
                  <path stroke-linecap="round" stroke-linejoin="round"
                    d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L6.832 19.82a4.5 4.5 0 0 1-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 0 1 1.13-1.897L16.863 4.486Zm0 0L19.5 7.125" />
                </svg>
              </button>
              <button @click="showDeleteModal(user.id)"
                class="bg-red-400 hover:bg-red-300 text-white font-bold py-2 px-4 rounded">
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                  stroke="currentColor" class="w-5 h-5">
                  <path stroke-linecap="round" stroke-linejoin="round"
                    d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
                </svg>
              </button>
            </div>
          </td>
        </tr>
        <tr v-if="!users.length && !loading" class="text-center">
          <td colspan="4" class="px-6 py-4 text-gray-500">No hay registros disponibles</td>
        </tr>
      </tbody>
    </table>

    <!-- Modal de confirmación de eliminación -->
    <div v-if="showModal" class="fixed z-50 inset-0 overflow-y-auto " aria-labelledby="modal-title" role="dialog"
      aria-modal="true">
      <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
        <!-- Fondo del modal (ahora con backdrop-filter) -->
        <div class="fixed  transition-opacity" aria-hidden="true" @click="closeModal"></div>

        <!-- Contenedor del modal -->
        <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">​</span>
        <div
          class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
          <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div class="sm:flex sm:items-start">
              <div
                class="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10">
                <!-- Icono de advertencia -->
                <svg class="h-6 w-6 text-red-600" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                  stroke="currentColor" aria-hidden="true">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                    d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                </svg>
              </div>
              <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3 class="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                  Eliminar Usuario
                </h3>
                <div class="mt-2">
                  <p class="text-sm text-gray-500">
                    ¿Estás seguro de que quieres eliminar este usuario? Esta acción no se puede deshacer.
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
            <button @click="confirmDelete" type="button"
              class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-600 text-base font-medium text-white hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 sm:ml-3 sm:w-auto sm:text-sm">
              Aceptar
            </button>
            <button @click="closeModal" type="button"
              class="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm">
              Cancelar
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    users: {
      type: Array,
      required: true,
      default: () => []
    },
    loading: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      showModal: false,
      userIdToDelete: null,
    };
  },
  methods: {

    editUser(user) {
      this.$emit('edit-user', user);
    },
    showDeleteModal(userId) {
      this.userIdToDelete = userId;
      this.showModal = true;
    },
    confirmDelete() {

      this.$emit('user-deleted', this.userIdToDelete);
      this.closeModal();
    },
    closeModal() {
      this.showModal = false;
      this.userIdToDelete = null;
    },
  }
};
</script>