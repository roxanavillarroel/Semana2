![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Actividad Sumativa – Desarrollo Orientado a Objetos II

## 👤 Autor del proyecto
- **Nombre completo:** Roxana Villarroel Liberona  
- **Sección:** 001A  
- **Carrera:** Analista Programador Computacional  
- **Sede:** Online  

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la **Actividad Sumativa de la asignatura Desarrollo Orientado a Objetos II**.

Se desarrolló un sistema en **Java** que implementa un **CRUD completo (Crear, Listar, Actualizar y Eliminar)** para la gestión de:

- 👤 Repartidores  
- 📦 Pedidos  

El sistema utiliza:

- Arquitectura en capas (DAO – Model – View)
- Interfaz gráfica con **Java Swing**
- Conexión a base de datos mediante **JDBC**
- Patrón de diseño **DAO (Data Access Object)**

---

## 🏗️ Arquitectura del proyecto

El sistema está estructurado en los siguientes paquetes:
SpeedFast
│
├── pom.xml
├── README.md
│
└── src
    └── main
        └── java
            └── org
                └── pedidos
                    ├── dao
                    │   ├── PedidoDAO.java
                    │   └── RepartidorDAO.java
                    │
                    ├── model
                    │   ├── Pedido.java
                    │   └── Repartidor.java
                    │
                    ├── view
                    │   ├── PedidoFrame.java
                    │   └── RepartidorFrame.java
                    │
                    ├── util
                    │   └── ConexionDB.java
                    │
                    └── Main.java
