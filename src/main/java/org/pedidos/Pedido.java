package org.pedidos;

public abstract class Pedido {

    protected int id;
    protected String direccion;
    protected double distanciaKm;
    protected String repartidorAsignado;
    protected boolean cancelado;
    protected EstadoPedido estado;

    public Pedido(int id, String direccion, double distanciaKm) {
        this.id = id;
        this.direccion = direccion;
        this.distanciaKm = distanciaKm;
        this.repartidorAsignado = "No asignado";
        this.cancelado = false;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + id);
        System.out.println("Direccion: "+ direccion);
        System.out.println("Distancia: "+ distanciaKm+ "km");
        System.out.println("Repartidor Asignado: "+ repartidorAsignado);
        System.out.println("Tiempo estimado: "+calcularTiempoEntrega() + " Minutos");
        System.out.println("Estado: "+estado);
    }

    public abstract int calcularTiempoEntrega();
    public abstract void asignarRepartidor();

    public void asignarRepartidor(String nombre) {
        this.repartidorAsignado = nombre;
    }

    public synchronized EstadoPedido getEstado() {
        return this.estado;
    }
    public synchronized void setEstado (EstadoPedido nuevoEstado){
        this.estado = nuevoEstado;
    }

    public synchronized boolean estaDisponibleParaDespacho(){
        return this.estado == EstadoPedido.PENDIENTE && !cancelado;}


    public int getId() {
        return id;
    }

    public String getRepartidorAsignado() {
        return repartidorAsignado;
    }
}
