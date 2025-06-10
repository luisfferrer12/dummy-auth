# DummyJSON Auth API

API REST construida con Java 21 y Spring Boot 3 para autenticarse contra el API externa [DummyJSON](https://dummyjson.com), 
registrar el login exitoso en PostgreSQL y obtener la información del usuario autenticado.

---

## 🔧 Tecnologías

- Java 21
- Spring Boot 3+
- Maven
- PostgreSQL
- JPA (Hibernate)
- Feign Client
- Principios SOLID
- Pruebas unitarias con JUnit5 y Mockito

---

## 🛠️ Instrucciones para correr el proyecto

1. Clonar el repositorio:

git clone https://github.com/luisfferrer12/dummy-auth.git
cd dummy-auth

2. Crear base de datos PostgreSQL:

CREATE DATABASE dummy_auth;
CREATE USER dummy_user WITH PASSWORD 'dummy_pass';
GRANT ALL PRIVILEGES ON DATABASE dummy_auth TO dummy_user;

3. Verifica la configuración de tu application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/dummy_auth
spring.datasource.username=dummy_user
spring.datasource.password=dummy_pass

4. Corre el proyecto desde la clase principal (PruebaTelefonicaApplication)

## 🧪 Usuario de prueba
Puedes probar con este usuario (formato JSON) de DummyJSON:

{
    "username": "emilys",
    "password": "emilyspass"
}

---
## 🔐 Login con curl

curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"emilys","password":"emilyspass"}'

---
## 🗃️ ¿Qué se guarda en la base de datos?

Cuando el login es exitoso, se guarda en la tabla login_log:

username
login_time
access_token
refresh_token

---
## 🧪 Pruebas

Incluye pruebas positivas y negativas del flujo de login.

