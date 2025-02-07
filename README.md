# 📦 Proyecto User Management: Microservicio y Frontend

[![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-green?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.x-orange?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Vue.js](https://img.shields.io/badge/Vue.js-3.x-green?style=for-the-badge&logo=vuedotjs)](https://vuejs.org/)
[![Tailwind CSS](https://img.shields.io/badge/Tailwind%20CSS-3.x-blue?style=for-the-badge&logo=tailwindcss)](https://tailwindcss.com/)
[![Vite](https://img.shields.io/badge/Vite-5.x-purple?style=for-the-badge&logo=vite)](https://vitejs.dev/)

## 📖 Descripción

**User Management** es una aplicación completa que consta de un microservicio backend construido con **Spring Boot** y un frontend interactivo desarrollado con **Vue.js**.  Esta aplicación proporciona una solución para la gestión de usuarios, incluyendo:

*   **CRUD Completo:** Creación, lectura, actualización y eliminación de usuarios.
*   **Arquitectura Moderna:** Separación clara entre backend y frontend, utilizando APIs REST para la comunicación.
*   **Base de Datos Relacional:** Uso de MySQL para el almacenamiento persistente de datos.
*   **Documentación Integrada:** Swagger UI para una fácil exploración y prueba de la API.

<div align="center">
  <!-- <img src="" alt="User Management Screenshot" width="600"> -->
</div>

## ✨ Características Principales

*   **Backend (Spring Boot):**
    *   API RESTful bien definida.
    *   Spring Data JPA para interactuar con MySQL.
    *   Manejo de errores y respuestas informativas.
    *   Documentación con Swagger (OpenAPI).
    *   ModelMapper para DTOs.

*   **Frontend (Vue.js):**
    *   Interfaz intuitiva y responsiva (Tailwind CSS).
    *   Componentes reutilizables.
    *   Comunicación con el backend via Axios.
    *   Tabla interactiva con búsqueda.
    *   Formulario modal (crear/editar).
    *   Construcción rápida con Vite.

## 🛠️ Tecnologías Utilizadas

### Backend

*   Java 17
*   Spring Boot 3.4.2
*   Spring Data JPA
*   MySQL Connector/J
*   Lombok
*   ModelMapper
*   SpringDoc OpenAPI (Swagger)
*   Spring Boot Starter Cache

### Frontend

*   Vue.js 3.x
*   Vue Router
*   Axios
*   Tailwind CSS
*   Vite
*   Headless UI (opcional)


## 🚀 Instalación y Ejecución con Docker Compose (Recomendado)

Esta sección explica cómo instalar y ejecutar la aplicación completa utilizando **Docker Compose**. Este es el método recomendado, ya que simplifica la configuración y asegura un entorno consistente.

### Prerrequisitos

*   **Docker:**  Motor de Docker instalado y en ejecución.  (Docker Desktop es la forma más fácil).
*   **Docker Compose:**  Herramienta para definir y ejecutar aplicaciones multi-contenedor (normalmente se incluye con Docker Desktop).
* **Git:** Para clonar el repositorio.

### Pasos

1.  **Clonar el Repositorio:**

    ```bash
    git clone https://github.com/GersonAlonzo/user-management-module ; cd ./user-management-module
    ```

2.  **Ejecutar con Docker Compose:**

    Desde el directorio raíz del proyecto (donde está `docker-compose.yml`):

    ```bash
    docker-compose up --build -d
    ```
    2.1 Una vez instalado validar la conexion a la base de datos mediante un cliente: 
    ![image](https://github.com/user-attachments/assets/42bf5deb-7b45-4f52-809a-e71325b78912)

    2.2 Validar la estabilidad del proyecto en docker de almenos 2 minutos o mas
    ![image](https://github.com/user-attachments/assets/432bf2ec-04c5-4e54-b2d8-10efd12e827b)



4.  **Acceder a la Aplicación:**

    *   **Frontend:** Abre tu navegador y ve a `http://localhost:5173` (o el puerto que hayas configurado).
    *   **Backend (Swagger UI):** Abre tu navegador y ve a `http://localhost:8080/swagger-ui.html`.
     *   **Backend (API):** `http://localhost:8080`

5.  **Detener los Contenedores:**

    ```bash
    docker-compose down
    ```

    Esto detendrá y eliminará los contenedores, la red y los volúmenes (si no son volúmenes externos).
    
6.  **Detener los Contenedores y Eliminar Volumenes:**

    ```bash
    docker-compose down -v
    ```

    Esto detendrá y eliminará los contenedores, la red y los volúmenes, incluyendo la información de la base de datos.
