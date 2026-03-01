package org.pedidos.model;

public class Pedido {

    private int id;
    private String tipo;
    private String estado;
    private int repartidorId;

    public Pedido(int id, String tipo, String estado, int repartidorId) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.repartidorId = repartidorId;
    }

    public Pedido(String tipo, String estado, int repartidorId) {
        this.tipo = tipo;
        this.estado = estado;
        this.repartidorId = repartidorId;
    }

    public int getId() { return id; }

    public String getTipo() { return tipo; }

    public String getEstado() { return estado; }

    public int getRepartidorId() { return repartidorId; }
}