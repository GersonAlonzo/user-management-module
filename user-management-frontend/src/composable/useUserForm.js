// src/composables/useUserForm.js
import { ref, reactive, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import api from '../http/axios';

export default function useUserForm(props) {
  const router = useRouter();
  const formUser = reactive({
    username: '',
    email: '',
    msisdn: '',
    password: ''
  });
  const errorMessage = ref(null);
  const successMessage = ref(null);
  const loading = ref(false);

  const fetchUser = async () => {
    if (!props.id) return; // No hacer nada si no hay ID.

    loading.value = true;
    errorMessage.value = null;
    try {
      const response = await api.get(`/users/get/${props.id}`);
      Object.assign(formUser, response.data); // Usar Object.assign en lugar de spread
      delete formUser.password; //Eliminar la contraseña para evitar mostrarla
    } catch (error) {
      handleRequestError(error);
    } finally {
      loading.value = false;
    }
  };

  onMounted(fetchUser);


  const submitForm = async () => {
    errorMessage.value = null;
    successMessage.value = null;

    if (!formUser.username || !formUser.email || !formUser.msisdn) {
      errorMessage.value = 'Por favor, completa todos los campos obligatorios.';
      return;
    }
    if (!props.id && !formUser.password) {
      errorMessage.value = "La contraseña es obligatoria";
      return;
    }

    loading.value = true;
    try {
      let response;
      if (props.id) {
        const updateData = { ...formUser };
          delete updateData.password; 

        if (formUser.password) { 
          updateData.password = formUser.password;
        }
        response = await api.put(`/users/update/${props.id}`, updateData);
        successMessage.value = 'Usuario actualizado correctamente.';
      } else {
        response = await api.post('/users/create', formUser);
        successMessage.value = 'Usuario creado correctamente.';
      }

        setTimeout(() => {
            router.push({ name: 'UserManagement' });
        }, 1000)

    } catch (error) {
      handleRequestError(error);
    } finally {
      loading.value = false;
    }
  };

  const closeForm = () => {
    router.push({ name: 'UserManagement' });
  };

  const handleRequestError = (error) => {
    if (error.response) {
      if (error.response.status === 404 && error.config.url.includes('/users/get/')) {
        errorMessage.value = 'El usuario solicitado no existe.';
        router.push({ name: 'UserManagement' });
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

  return {
    formUser,
    errorMessage,
    successMessage,
    loading,
    submitForm,
    closeForm,
    handleRequestError 
  };
}