![Duoc UC](https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png)

# 🧠 Actividad Sumativa – Desarrollo Orientado a Objetos I (POO)

## 👤 Autor del proyecto
- **Nombre completo:** Roxana Villarroel Liberona  
- **Sección:** 001A  
- **Carrera:** Analista Programador Computacional  
- **Sede:** Online  

---

## 📘 Descripción general del sistema

Este proyecto corresponde a la **Actividad Sumativa de la asignatura Desarrollo Orientado a Objetos II**.  

Se desarrolló un sistema en **Java** que modela la gestión de pedidos de una empresa de delivery llamada **SpeedFast**, incorporando principios de Programación Orientada a Objetos y **concurrencia con hilos (Threads)**.

El sistema permite:

- Registrar pedidos
- Asignar repartidores
- Despachar pedidos
- Cancelar pedidos
- Gestionar estados del pedido
- Procesar pedidos concurrentemente usando múltiples repartidores

---

## 🎯 Principios aplicados

### 🔹 Programación Orientada a Objetos

- **Abstracción** → Clase abstracta `Pedido`
- **Herencia**
  - `PedidoComida`
  - `PedidoEncomienda`
  - `PedidoExpress`
- **Polimorfismo**
  - **Override** → `asignarRepartidor()`
  - **Overload** → `asignarRepartidor(String nombre)`
- **Encapsulamiento**
- **Interfaces**
  - `Despachable`
  - `Cancelable`
  - `Rastreable`

---

### 🔹 Concurrencia y sincronización

El sistema implementa procesamiento concurrente de pedidos mediante:

- Uso de **Threads**
- Clase `Repartidor` implementa `Runnable`
- Cola sincronizada de pedidos en `ZonaCarga`
- Métodos sincronizados:
  ```java
  public synchronized void agregarPedido(Pedido p)
  public synchronized Pedido retirarPedido()
