package org.pedidos;

import java.util.LinkedList;
import java.util.Queue;

public class ZonaCarga {
    private Queue<Pedido> colaPedidos = new LinkedList<>();

    public synchronized void agregarPedido(Pedido p) {
        colaPedidos.add(p);
        System.out.println(" Pedido agregado #" + p.getId() + " agregado a zona de carga");
    }
    public synchronized Pedido retirarPedido() {
        if (colaPedidos.isEmpty()) {
            return null;
        }
        return colaPedidos.poll();
    }
}
