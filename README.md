# Task Manager

API REST para la gestión de tareas, desarrollada como parte de una prueba técnica utilizando Java 21, Spring Boot y PostgreSQL. El proyecto sigue una arquitectura hexagonal.

## Tecnologías

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven
- Swagger / OpenAPI
- Docker (Dockerfile para despliegue)

## Características

- Crear, actualizar, eliminar y consultar tareas.
- Gestión de estados: `PENDING`, `IN_PROGRESS` y `COMPLETED`.
- Validación de datos de entrada mediante DTO.
- Migraciones de base de datos con Flyway.
- Manejo global de excepciones.
- Documentación de la API con Swagger / OpenAPI.

## Para empezar

### Requisitos previos

- Java 21
- Maven (opcional si se utiliza Maven Wrapper).
- PostgreSQL

### Clonar el repositorio

```bash
git clone https://github.com/AngelLY12/task-manager.git
cd task-manager
```

### Configurar la base de datos

Crear una base de datos en PostgreSQL y configurar las siguientes variables de entorno:

- `DB_URL`: URL de conexión JDBC.
- `DB_USERNAME`: nombre de usuario de la base de datos.
- `DB_PASSWORD`: contraseña de la base de datos.

Ejemplo de URL de conexión:

```text
jdbc:postgresql://localhost:5432/task_manager
```

**Nota:** Flyway aplica automáticamente las migraciones pendientes al iniciar la aplicación.

### Compilar el proyecto

```bash
./mvnw clean package
```

En Windows:

```cmd
mvnw.cmd clean package
```

### Ejecutar el proyecto

```bash
./mvnw spring-boot:run
```

En Windows:

```cmd
mvnw.cmd spring-boot:run
```

## Documentación de la API

- **API desplegada:** [Task Manager en Render](https://task-manager-llo2.onrender.com)
- **Swagger desplegado:** [Swagger en Render](https://task-manager-llo2.onrender.com/swagger-ui/index.html)
- **Swagger UI local:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **Especificación OpenAPI local:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Arquitectura y decisiones de diseño

El proyecto utiliza una **arquitectura hexagonal** para separar el dominio, la lógica de aplicación y los detalles de infraestructura, reduciendo el acoplamiento y facilitando el mantenimiento.

- **Dominio:** contiene las entidades de negocio, enums, excepciones de dominio y contratos de repositorio.
- **Aplicación:** contiene los servicios de aplicación que coordinan los casos de uso.
- **Infraestructura:** contiene los adaptadores de persistencia, repositorios JPA, entidades de persistencia, mapeadores, controladores REST, DTO y configuración de la API.

### Adaptaciones para el despliegue

Debido a que los servicios de Azure contemplados en la prueba técnica no estuvieron disponibles para su uso, se utilizaron alternativas para publicar la API y alojar la base de datos.

- **Render:** se utilizó para desplegar la API REST y permitir el acceso a sus endpoints desde internet.
- **Neon:** se utilizó como alternativa para alojar la base de datos PostgreSQL en la nube mediante un servicio administrado.
- **Latencia entre servicios:** la API está desplegada en Render, en la región de Oregon, mientras que la base de datos de Neon se encuentra en AWS US East 2 (Ohio). La comunicación entre ambas regiones puede incrementar los tiempos de respuesta. Durante las pruebas se observaron tiempos promedio cercanos a 300 ms en las peticiones GET y una petición POST de 1.8 segundos. Estos valores son orientativos y pueden variar según las condiciones de conexión, la inicialización de los servicios y las operaciones de persistencia.

### Patrones y mecanismos aplicados

- **Repository:** los contratos de repositorio separan la lógica de aplicación de los detalles de acceso a datos. Los adaptadores de infraestructura implementan estos contratos mediante Spring Data JPA.
- **Inyección de dependencias:** Se utilizó inyección por constructor. Spring administra las dependencias entre componentes, permitiendo que los servicios dependan de abstracciones.
- **Gestión transaccional:** Spring Data JPA administra las transacciones de persistencia. Para el alcance actual del CRUD no se requiere una implementación propia de Unidad de Trabajo (*Unit of Work*).

## Control de versiones

El desarrollo utiliza Git con una estrategia de ramas:

- `main`: versión estable.
- `develop`: rama de integración.
- `feature/*`: ramas para desarrollar funcionalidades específicas, integradas mediante *pull requests*.

Los commits describen cambios específicos para mantener un historial de desarrollo claro y trazable.
