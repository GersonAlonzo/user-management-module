<template>
  <div class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
      <h1 class="text-3xl font-bold text-gray-800 mb-6">Gestión de Usuarios</h1>

      <div class="flex flex-col md:flex-row justify-between items-center mb-6 gap-4">
        <div class="w-full md:w-2/3 relative">
           <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6 absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400">
            <path stroke-linecap="round" stroke-linejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
          </svg>

          <input type="text" placeholder="Buscar usuario..." class="pl-10 w-full border-2 border-gray-300 bg-white h-10 px-5 pr-4 rounded-lg text-sm focus:outline-none focus:border-blue-500" v-model="searchQuery" @input="searchUsers">
        </div>
        <button @click="goToCreateUser" class="w-full md:w-auto bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-md transition duration-200 ease-in-out">
          Crear Usuario
        </button>

      </div>

      <UserTable :users="filteredUsers" :loading="loading" @edit-user="goToEditUser" @user-deleted="deleteUser"  @request-error="handleRequestError"/>

      <div v-if="!loading && filteredUsers.length === 0 && !errorMessage" class="text-center text-gray-500 py-6">
        <p>No se encontraron usuarios.</p>
      </div>

      <div v-if="errorMessage" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-md mt-4" role="alert">
        <strong class="font-bold">Error: </strong> {{ errorMessage }}
      </div>
    </div>
  </div>
</template>

<script>
import UserTable from '../components/UserTable.vue';
import useUsers from '../composable/useUsers'; 
import { toRefs } from 'vue';

export default {
  components: {
    UserTable,
  },
  setup() {
    const {
      users,
      loading,
      errorMessage,
      searchQuery,
      filteredUsers, 
      fetchUsers,
      searchUsers,
      goToCreateUser,
      goToEditUser,
      deleteUser,
      handleRequestError
    } = useUsers();

    return {
      ...toRefs({  
        users,
        loading,
        errorMessage,
        searchQuery,
        filteredUsers
      }),
      fetchUsers,
      searchUsers,
      goToCreateUser,
      goToEditUser,
      deleteUser, 
      handleRequestError
    };
  },
};
</script>