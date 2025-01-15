# FORO HUB API (Alura latam)
ForoHub API es una aplicación RESTFULL desarrollada para gestionar mensajes de un foro , con autenticación basada en JWT y documentación de API integrada con Swagger.

## 🚀 Características

- **Gestión de tópicos:** Crear, leer, actualizar y eliminar tópicos.
- **Autenticación JWT:** Seguridad para proteger los endpoints.
- **Documentación Swagger:** Navegación y prueba de la API.
- **Validaciones:** Manejo de datos con anotaciones de validación.

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
- **Swagger/OpenAPI**
- **JSON Web Tokens (JWT)**
- **Hibernate/JPA**
- **MYSQL SERVER**

## 📋 Requisitos Previos

- Java 17+
- Maven
- Base de datos configurada (H2, SQL Server, u otra)

## Puedes acceder a la documentacion Swagger en:
- **http://localhost:8080/swagger-ui/index.html**

## Operaciones CRUD PRINCIPALES

- Método	Endpoint	Descripción	Seguridad
- POST	/auth/login	Inicio de sesión con JWT	❌
- GET	/api/topicos	Obtener todos los tópicos	✔️
- GET	/api/topicos/{id}	Obtener un tópico por ID	✔️
- POST	/api/topicos	Crear un nuevo tópico	✔️
- PUT	/api/topicos/{id}	Actualizar un tópico existente	✔️
- DELETE	/api/topicos/{id}	Eliminar un tópico	✔️
