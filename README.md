# Ejercicio de DDA

Este proyecto es una aplicación basada en microservicios compuesta por dos servicios principales: `accounts-service` y `customers-service`. La arquitectura utiliza **Spring Boot** y **Docker Compose** para la orquestación y despliegue de los servicios junto con una base de datos **PostgreSQL**.

---

## 🗂️ Estructura del Proyecto

```
.
├── accounts/                        # Microservicio de cuentas
│   ├── .mvn/
│   ├── src/
│   ├── .gitignore
│   ├── Dockerfile                   # Dockerfile para el servicio de cuentas
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml                      # Archivo POM de Maven
├── customers/                       # Microservicio de clientes
│   ├── .mvn/
│   ├── src/
│   ├── .gitignore
│   ├── Dockerfile                   # Dockerfile para el servicio de clientes
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   └── pom.xml                      # Archivo POM de Maven
├── DataBase/
│   └── init/
│       └── BaseDatos.sql            # Script de inicialización de la base de datos
├── postman/
│   └── DDA.postman_collection.json  # Colección de Postman para pruebas
├── .gitignore
├── deploy.sh                        # Script para compilar y levantar los servicios
├── docker-compose.yaml              # Orquestación de servicios con Docker Compose
└── README.md                        # Este archivo
```

---

## ✅ Prerrequisitos

Antes de ejecutar el proyecto, asegúrate de tener instaladas las siguientes herramientas:

- [Java 21](https://jdk.java.net/21/)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)
- [Postman](https://www.postman.com/)

---

## 🚀 Despliegue y Ejecución

El proyecto puede desplegarse fácilmente utilizando Docker Compose y el script `deploy.sh`.

### 1. Asegúrate de que Docker esté en ejecución.

### 2. Da permisos de ejecución al script de despliegue:

```bash
chmod +x deploy.sh
```

### 3. Ejecuta el script:

```bash
./deploy.sh
```

Este script realizará las siguientes acciones:

- Compilará `accounts-service` y `customers-service` con Maven.
- Construirá las imágenes Docker para ambos servicios y la base de datos.
- Levantará los contenedores definidos en `docker-compose.yaml`.

---

## 🌐 Servicios y Puertos

| Servicio           | URL                           |
|--------------------|-------------------------------|
| accounts-service   | http://localhost:8089/api/accounts         |
| accounts-service   | http://localhost:8089/api/transactions         |
| customers-service  | http://localhost:8088/api/customers     |
| PostgreSQL         | localhost:5432                |

---

## 📡 Endpoints de la API

### 📘 accounts-service
**Base URL:** `http://localhost:8089/api/accounts`  
**Base URL:** `http://localhost:8089/api/transactions`  

### 📗 customers-service
**Base URL:** `http://localhost:8088/api/customers`  

---

## 🧪 Uso de Postman

Dentro del directorio `postman/` se incluye una colección llamada `DDA.postman_collection.json` para facilitar las pruebas de los endpoints de ambos servicios.

Importa esta colección en Postman para comenzar a probar los servicios rápidamente.

---

## 🛑 Apagar los Contenedores

Para detener y eliminar los contenedores creados por Docker Compose, ejecuta:

```bash
docker-compose down
```

---