package org.pedidos;

public class Repartidor implements Runnable {

    private String nombre;
    private ZonaCarga zona;

    public Repartidor(String nombre, ZonaCarga zona) {
        this.nombre = nombre;
        this.zona = zona;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zona.retirarPedido();
            if (pedido == null) {
                System.out.println("  REPARTIDOR: " +nombre+ "no se encuentran mas pedidos, FINALIZANDO..");
                break;
            }
             pedido.asignarRepartidor(nombre);
            System.out.println(" REPARTIDOR: " +nombre+ "Retirando pedido # " +pedido.getId());
            ((Despachable) pedido).despachar();
        }
    }
}
