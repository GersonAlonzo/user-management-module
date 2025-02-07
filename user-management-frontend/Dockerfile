# -----------------
# Etapa de Construcción
# -----------------
    FROM node:20-alpine AS build
    WORKDIR /app
    
    # Copia solo los archivos esenciales para la instalación de dependencias
    COPY package.json package-lock.json ./
    RUN npm ci
    
    # Copia el resto del código fuente
    COPY . .
    
    # -----------------
    # Etapa de Ejecución (Modo Desarrollo)
    # -----------------
    FROM node:20-alpine
    WORKDIR /app
    
    # Copia los archivos desde la etapa de construcción
    COPY --from=build /app ./
    
    # Expone el puerto (ajústalo si usas otro en tu framework, ejemplo: Vite usa 5173)
    EXPOSE 5173  
    
    # Comando para ejecutar la aplicación en modo desarrollo
    CMD ["npm", "run", "dev"]
    