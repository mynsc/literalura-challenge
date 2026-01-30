# Literalura

![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=flat&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue?style=flat&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-3.x-red?style=flat&logo=apachemaven)

Literalura es una aplicación de consola desarrollada con Spring Boot que permite buscar, almacenar y gestionar un catálogo de libros. La aplicación consume la API pública de [Gutendex](https://gutendex.com) para obtener información de miles de libros del Proyecto Gutenberg y los almacena en una base de datos PostgreSQL para consultas posteriores.

### Menú Principal

<!-- screenshot -->
```
===========================================
MENÚ DE OPCIONES - LITERALURA
===========================================
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un determinado año
5. Listar libros por idioma
6. Mostrar estadísticas de descargas
7. Listar los 5 libros más descargados
8. Buscar autor por nombre
9. Listar autores de los 50s
0. Salir
```

## Características

La aplicación ofrece las siguientes funcionalidades a través de un menú interactivo:

1. **Buscar libro por título**: Busca libros en la API de Gutendex y los guarda en la base de datos local (evita duplicados)
2. **Listar libros registrados**: Muestra todos los libros almacenados en la base de datos
3. **Listar autores registrados**: Muestra todos los autores guardados
4. **Listar autores vivos en un determinado año**: Filtra autores que estuvieron vivos en un año específico
5. **Listar libros por idioma**: Filtra libros por idioma (Inglés, Español, Francés)
6. **Mostrar estadísticas de descargas**: Muestra el promedio de descargas, libro menos y más descargado
7. **Listar los 5 libros más descargados**: Ranking de los 5 libros con más descargas
8. **Buscar autor por nombre**: Búsqueda de autores por nombre (no sensible a mayúsculas)
9. **Listar autores de los 50s**: Muestra autores nacidos entre 1910 y 1930

## Tecnologías

El proyecto utiliza las siguientes tecnologías:

- **Java 17**: Lenguaje de programación
- **Spring Boot 4.0.2**: Framework principal de la aplicación
- **Spring Data JPA**: Capa de persistencia y acceso a datos
- **PostgreSQL 18**: Base de datos relacional
- **Jackson 2.20.1**: Procesamiento de JSON
- **Maven**: Gestión de dependencias y construcción del proyecto
- **Docker Compose**: Orquestación de contenedores (PostgreSQL)
- **Gutendex API**: API pública de libros del Proyecto Gutenberg

## Requisitos Previos

Antes de ejecutar la aplicación, asegúrate de tener instalado:

- Java 17 o superior
- Maven 3.x (o usar el wrapper incluido `mvnw`)
- Docker y Docker Compose

## Instalación

1. **Clonar el repositorio**:
```bash
git clone <url-del-repositorio>
cd literalura-project
```

2. **Configurar variables de entorno del sistema**:

   La aplicación requiere las siguientes variables de entorno configuradas en tu sistema operativo:

   - `DB_HOST`: Host de la base de datos (ejemplo: `localhost`)
   - `DB_NAME`: Nombre de la base de datos (ejemplo: `literalura_db`)
   - `DB_USER`: Usuario de la base de datos
   - `DB_PASSWORD`: Contraseña del usuario

   **En Linux/Mac**:
   ```bash
   export DB_HOST=localhost
   export DB_NAME=literalura_db
   export DB_USER=tu_usuario
   export DB_PASSWORD=tu_contraseña
   ```

   **En Windows (CMD)**:
   ```cmd
   set DB_HOST=localhost
   set DB_NAME=literalura_db
   set DB_USER=tu_usuario
   set DB_PASSWORD=tu_contraseña
   ```

   **En Windows (PowerShell)**:
   ```powershell
   $env:DB_HOST="localhost"
   $env:DB_NAME="literalura_db"
   $env:DB_USER="tu_usuario"
   $env:DB_PASSWORD="tu_contraseña"
   ```

3. **Iniciar la base de datos PostgreSQL con Docker**:
```bash
sudo docker compose up -d
```

4. **Compilar el proyecto**:
```bash
./mvnw clean install
```
   O en Windows:
```cmd
mvnw.cmd clean install
```

5. **Ejecutar la aplicación**:
```bash
./mvnw spring-boot:run
```
   O en Windows:
```cmd
mvnw.cmd spring-boot:run
```

## Uso

Al ejecutar la aplicación, se mostrará un menú interactivo en la consola con las opciones numeradas del 1 al 9, y la opción 0 para salir.

**Ejemplo de uso**:
- Selecciona la opción `1` para buscar un libro por título
- Ingresa el nombre del libro (ejemplo: "Don Quijote")
- La aplicación buscará en la API de Gutendex y guardará el resultado en la base de datos
- Puedes luego usar las opciones `2` y `3` para ver los libros y autores registrados

La aplicación continuará ejecutándose hasta que selecciones la opción `0` para salir.

## Estructura del Proyecto

```
src/main/java/me/mynsc/literalura/
├── LiteraluraProjectApplication.java    # Clase principal de Spring Boot
├── mains/
│   └── Main.java                        # Lógica del menú interactivo
├── models/
│   ├── Book.java                        # Entidad JPA - Libro
│   ├── Person.java                      # Entidad JPA - Autor
│   ├── Language.java                    # Enum de idiomas
│   ├── DataBook.java                    # DTO para datos de libro (API)
│   ├── DataPerson.java                  # DTO para datos de autor (API)
│   └── DataResults.java                 # DTO para resultados de la API
├── repository/
│   ├── BookRepository.java              # Repositorio JPA para libros
│   └── PersonRepository.java            # Repositorio JPA para autores
└── services/
    ├── ApiConsume.java                  # Cliente HTTP para consumir la API
    ├── DataConverter.java               # Convertidor de JSON a objetos
    └── IDataConverter.java              # Interfaz del convertidor
```

### Descripción de paquetes:

- **models**: Contiene las entidades JPA (Book, Person), enums (Language) y DTOs para mapear respuestas de la API
- **repository**: Interfaces de Spring Data JPA con consultas personalizadas usando JPQL
- **services**: Servicios para consumir la API externa y convertir datos JSON
- **mains**: Punto de entrada de la aplicación con el menú interactivo y lógica de negocio

---

Desarrollado como proyecto de práctica con Spring Boot y consumo de APIs REST para el curso de Desarrollo Java con Spring en Alura LATAM.
