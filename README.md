# Sistema de Votos Electrónicos - Backend

Este es el backend del proyecto **Sistema de Votos Electrónicos** desarrollado con **Java 17** y **Spring Boot**, enfocado para la Universidad Mayor de San Andrés (UMSA). El sistema permite a estudiantes y docentes con correo institucional (@umsa.bo) autenticarse y emitir su voto por un partido político que presenta dos candidatos (decano y vicedecano).

---

## Tecnologías

- **Java 17**
- **Spring Boot**
- **PostgreSQL** (Base de datos)
- Gestión de dependencias con **Maven** (`pom.xml`)
- Configuraciones en `src/main/resources` (incluye conexión a la base de datos)

---

## Estructura del proyecto

- **model**: Clases de entidades que representan las tablas de la base de datos.
- **dto**: Objetos de transferencia de datos para encapsular información entre capas.
- **repository**: Interfaces que gestionan el acceso y consulta a la base de datos.
- **service**: Lógica de negocio y procesamiento de datos.
- **controller**: Endpoints REST para interacción con el frontend o clientes.
- **validator**: Clases encargadas de validar datos y reglas específicas del sistema.

---

## Funcionalidades principales

- **Autenticación** para estudiantes y docentes con correo institucional UMSA (@umsa.bo).
- **Roles**: Usuarios normales (estudiantes, docentes) y administrador con acceso completo al sistema.
- **Gestión de votos**:
  - Votación por partidos políticos.
  - Cada partido cuenta con dos candidatos: decano y vicedecano.
  - Solo docentes pueden ser candidatos.
- **Auditoría**:
  - Trigger en la base de datos para registrar cambios realizados sobre estudiantes.
- **Dashboard y estadísticas**:
  - Función que muestra la cantidad de votos por partido.
  - Función que muestra el ranking top 3 de carreras con más votos.
- **CRUD completo** para:
  - Estudiantes
  - Docentes
  - Candidatos
  - Votos
  - Partidos
- Endpoints REST para todos los CRUDs y funcionalidades, incluyendo el acceso a las funciones y el trigger.

---

## Configuración

- La conexión a la base de datos PostgreSQL está configurada en `src/main/resources/application.properties`.
- Dependencias gestionadas en el archivo `pom.xml`.

---

## Uso Y Pasos para ejecutar

1. Clonar el repositorio.
2. Configurar la conexión a la base de datos en el archivo de configuración, Abrir Postgresql crear una base de datos con el mismo nombre de la base de datos 
en application.properties, configurar puerto, user, password)
   (en src/main/resources/application.properties se configura el nombre de la base de datos)
4. Ejecutar el proyecto, posicionarse en "VotosElectronicosApplication" y presionar run en la derecha.
5. Cargar los datos del archivo "data.sql" a la Base de datos en portgresql (llenamos la base de datos).
6. Cargar el trigger y las funciones que estan en "fpt.sql" en la Base de datos en postgresql.
7. Consumir los endpoints para autenticación, votación y gestión desde Postman.

---
## Diagrama de la Base de Datos

<img width="484" alt="Captura de Pantalla 2025-07-07 a la(s) 17 11 14" src="https://github.com/user-attachments/assets/23713e93-9dda-4fc8-8bc5-9622c61a970f" />

## Endpoints 

<img width="1023" alt="Captura de Pantalla 2025-07-07 a la(s) 17 13 35" src="https://github.com/user-attachments/assets/67044924-0712-4b8d-929e-a487b6b45974" />

<img width="749" alt="Captura de Pantalla 2025-07-07 a la(s) 17 15 07" src="https://github.com/user-attachments/assets/2997ad5a-9a8e-406b-99fd-9b756bd02db0" />
<img width="752" alt="Captura de Pantalla 2025-07-07 a la(s) 17 15 54" src="https://github.com/user-attachments/assets/6aa9df90-a237-482e-a1a2-6ab3027cc8c8" />


Si necesitas ayuda otienes alguna consulta sobre el backend, no dudes en contactarme.
