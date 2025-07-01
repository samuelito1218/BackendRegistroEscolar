# 📚 Sistema de Registro Escolar

Este proyecto es un sistema CRUD para manejar registros escolares. Incluye backend en Java con Spring Boot y frontend en Angular. Permite registrar personas, convertirlas en estudiantes o profesores, crear cursos, inscribir estudiantes y gestionar administrativos.

---

## 🛠 Tecnologías utilizadas

### Backend

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Lombok  
- MySQL  
- Maven  

### Frontend

- Angular 16+  
- Angular Material  
- TypeScript  

---

## ✅ Requisitos previos

Asegúrate de tener instalado lo siguiente:

- java -version → Java 17 o superior  
- node -v → Node.js v16 o superior  
- ng version → Angular CLI  
- mysql --version → MySQL  

Si no tienes Angular CLI, instálalo con:  
npm install -g @angular/cli

---

## ⚙️ Configuración del Backend

### 1. Configurar conexión a la base de datos

Edita el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:

- spring.datasource.url=jdbc:mysql://localhost:3306/pruebatecnicabd?useSSL=false&serverTimezone=UTC  
- spring.datasource.username=root  
- spring.datasource.password=TU_CONTRASEÑA_AQUI  
- spring.jpa.hibernate.ddl-auto=update  
- spring.jpa.show-sql=true  

### 2. Ejecutar el backend

1. Ve al directorio del backend:  
   cd registro-escolar-backend  

2. Ejecuta la aplicación:  
   ./registroEscolar registroEscolarApplication.java:run  

3. Accede en tu navegador a:  
   http://localhost:8080/api/personas

---

## 💻 Configuración del Frontend

### 1. Instalar dependencias

Ve al directorio del frontend:  
cd registro-escolar-frontend  
Ejecuta:  
npm install

### 2. (Opcional) Instalar Angular Material

Ejecuta:  
ng add @angular/material  
Elige el tema que prefieras (por defecto: Indigo/Pink)

### 3. Ejecutar el frontend

Ejecuta:  
ng serve  

Abre el navegador en:  
http://localhost:4200

---

## 🧑‍🏫 Manual de Usuario

### Navegación General

Utiliza el menú lateral para navegar entre las siguientes secciones:

- Personas  
- Estudiantes  
- Profesores  
- Administrativos  
- Cursos  
- Inscripciones  

### Sección Personas

- Registra los datos básicos: nombre, apellido, email (único), teléfono y fecha de nacimiento (no futura).  
- Puedes crear, editar y eliminar personas.  
- Usa los filtros para buscar por nombre, apellido o email.  

### Sección Estudiantes

- Convierte una persona registrada en estudiante.  
- Asigna un grado (1 a 11).  
- El número de matrícula se genera automáticamente.  
- Permite editar grado o eliminar al estudiante.  

### Sección Profesores

- Convierte una persona en profesor.  
- Asigna una especialidad y fecha de contratación.  
- Puedes editar o eliminar.  

### Sección Administrativos

- Convierte una persona en administrativo.  
- Asigna un cargo y un departamento.  
- Puedes editar los datos o eliminar.  

### Sección Cursos

- Crea cursos con nombre, descripción, número de créditos y profesor asignado.  
- Puedes editar o eliminar cursos existentes.  
- Es necesario registrar profesores antes de crear cursos.  

### Sección Inscripciones

- Inscribe un estudiante en un curso.  
- Asigna fecha de inscripción (se puede editar).  
- Puedes listar, filtrar, editar o eliminar inscripciones.  

---

## 🔗 Endpoints del API

Si deseas probar directamente con Postman u otra herramienta, aquí tienes las rutas principales:

- Personas: http://localhost:8080/api/personas  
- Estudiantes: http://localhost:8080/api/estudiantes  
- Profesores: http://localhost:8080/api/profesores  
- Administrativos: http://localhost:8080/api/administrativos  
- Cursos: http://localhost:8080/api/cursos  
- Inscripciones: http://localhost:8080/api/inscripciones  

Todos los endpoints cuentan con métodos:

- GET (listar)  
- POST (crear)  
- PUT o PATCH (actualizar)  
- DELETE (eliminar)  

---

## 🧪 Tips

- Ejecuta siempre primero el script de la base de datos antes de iniciar el backend.  
- Verifica que tanto backend como frontend estén levantados antes de usar la app.  
- Usa los formularios de Angular para validar los datos antes de guardar.  
