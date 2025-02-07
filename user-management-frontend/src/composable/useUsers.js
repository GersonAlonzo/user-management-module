
import { ref, onMounted, computed } from 'vue';
import api from '../http/axios';
import { useRouter } from 'vue-router';

export default function useUsers() {
  const users = ref([]);
  const loading = ref(false);
  const errorMessage = ref(null);
  const searchQuery = ref('');
  const router = useRouter();


  const fetchUsers = async () => {
    loading.value = true;
    errorMessage.value = null;
    try {
      const response = await api.get('/users');
      users.value = response.data;
    } catch (error) {
      handleRequestError(error);
    } finally {
      loading.value = false;
    }
  };

  const searchUsers = async () => {
    loading.value = true;
    errorMessage.value = null;
    try {
      const response = await api.get(`/users?search=${searchQuery.value}`);
      users.value = response.data;
    } catch (error) {
      handleRequestError(error);
    } finally {
      loading.value = false;
    }
  };

  const goToCreateUser = () => {
    router.push({ name: 'CreateUser' });
  };

  const goToEditUser = (user) => {
    router.push({ name: 'EditUser', params: { id: user.id } });
  };

  const deleteUser = async (userId) => {
    try {
      await api.delete(`/users/delete/${userId}`);
      await fetchUsers();
    } catch (error) {
      handleRequestError(error);
    } finally {
      loading.value = false; 
    }
  }

  const handleRequestError = (error) => {
    if (error.response) {
      if (error.response.status === 404) {
        if (error.config.url.includes('/users/get/')) {
          errorMessage.value = 'El usuario solicitado no existe.';
        } else {
          users.value = [];
          errorMessage.value = 'No se encontraron usuarios.';
        }
      } else if (error.response.status === 500) {
        errorMessage.value = 'Error interno del servidor.';
      } else {
        errorMessage.value = `Error: ${error.response.status} - ${error.response.data.message || 'Error desconocido'}`;
      }
    } else if (error.request) {
      errorMessage.value = 'No se pudo conectar con el servidor. Verifica tu conexión a internet.';
    } else {
      errorMessage.value = `Error al realizar la petición: ${error.message}`;
    }
  };

    const filteredUsers = computed(() => {
        fetchUsers
    if (!searchQuery.value) {
      return users.value;
    }
    return users.value.filter(user =>
      user.username.toLowerCase().includes(searchQuery.value.toLowerCase())
    );
  });

  onMounted(fetchUsers);

  return {
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
    handleRequestError, 
  };
}