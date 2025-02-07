<template>
  <div class="fixed inset-0 bg-gray-100 bg-opacity-50 overflow-y-auto h-full w-full flex items-center justify-center"
    @click.self="closeForm">
    <div class="bg-white rounded-lg shadow-xl w-full max-w-lg p-6 relative">
      <h3 class="text-2xl font-semibold text-gray-800 mb-4">{{ id ? 'Editar Usuario' : 'Crear Usuario' }}</h3>

      <!-- Mensaje de éxito -->
      <div v-if="successMessage" class="bg-green-100 border border-green-400 text-green-700 px-4 py-2 rounded-md mb-4"
        role="alert">
        {{ successMessage }}
      </div>

      <div v-if="errorMessage" class="bg-red-100 border border-red-400 text-red-700 px-4 py-2 rounded-md mb-4"
        role="alert">
        {{ errorMessage }}
      </div>

      <div class="space-y-4">
        <div>
          <label for="name" class="block text-sm font-medium text-gray-700">Nombre:</label>
          <input type="text" id="name" v-model="formUser.username"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-sm">
        </div>
        <div>
          <label for="email" class="block text-sm font-medium text-gray-700">Email:</label>
          <input type="email" id="email" v-model="formUser.email"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-sm">
        </div>
        <div>
          <label for="msisdn" class="block text-sm font-medium text-gray-700">Teléfono (MSISDN):</label>
          <input type="text" id="msisdn" v-model="formUser.msisdn"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-sm">
        </div>
        <div>
          <label for="password" class="block text-sm font-medium text-gray-700">Contraseña:</label>
          <input type="password" id="password" v-model="formUser.password"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-blue-500 focus:border-blue-500 text-sm">
          <small v-if="!id" class="text-gray-500 text-xs">La contraseña es obligatoria al crear un nuevo
            usuario.</small>
        </div>
      </div>

      <div class="mt-6 flex justify-end space-x-4">
        <button type="button" @click="closeForm"
          class="bg-gray-200 hover:bg-gray-300 text-gray-800 font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-400 transition duration-200 ease-in-out">
          Cancelar
        </button>
        <button type="button" @click="submitForm" :class="{ 'opacity-50 cursor-not-allowed': loading }"
          :disabled="loading"
          class="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 transition duration-200 ease-in-out">
          <span v-if="loading">
            <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none"
              viewBox="0 0 24 24">
              <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
              <path class="opacity-75" fill="currentColor"
                d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z">
              </path>
            </svg>
          </span>
          <span v-if="!loading">
            Guardar
          </span>
        </button>

      </div>
    </div>
  </div>
</template>

<script>
import useUserForm from '../composable/useUserForm';
import { toRefs } from 'vue';

export default {
  props: {
    id: {
      type: [String, Number],
      default: null
    },
  },
  setup(props) {
    const {
      formUser,
      errorMessage,
      successMessage,
      loading,
      submitForm,
      closeForm,
      handleRequestError // Si lo necesitas aquí y en el otro composable.
    } = useUserForm(props);

    return {
      ...toRefs({
        formUser,
        errorMessage,
        successMessage,
        loading,
      }),
      submitForm,
      closeForm,
      handleRequestError
    };
  }
};
</script>