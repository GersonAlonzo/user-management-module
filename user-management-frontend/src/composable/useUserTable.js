// src/composables/useUserTable.js
import { ref } from 'vue';

export default function useUserTable() {
  const showModal = ref(false);
  const userIdToDelete = ref(null);

  const showDeleteModal = (userId) => {
    userIdToDelete.value = userId;
    showModal.value = true;
  };

  const confirmDelete = () => {
    // No se emite desde aca, se emite del user-management
    closeModal();
     // Devuelve el userIdToDelete
    return userIdToDelete.value;
  };

  const closeModal = () => {
    showModal.value = false;
    userIdToDelete.value = null;
  };

  return {
    showModal,
    userIdToDelete, // Aunque ya no es estrictamente necesario, es útil para el template.
    showDeleteModal,
    confirmDelete,
    closeModal,
  };
}