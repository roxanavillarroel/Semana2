package org.pedidos;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> historial = new ArrayList<>();

        ZonaCarga zona = new ZonaCarga();

        Pedido pedido1 = new PedidoComida(202, "Cordillera de nahuelbuta #168", 2);
        Pedido pedido2 = new PedidoEncomienda(465, "Matico #548", 7);
        Pedido pedido3 = new PedidoExpress(303, "Avenida andalien #548", 3);

        zona.agregarPedido(pedido1);
        zona.agregarPedido(pedido2);
        zona.agregarPedido(pedido3);

        Thread r1 = new Thread(new Repartidor("Juanita Perez", zona));
        Thread r2 = new Thread(new Repartidor("Maria Lopez", zona));
        Thread r3 = new Thread(new Repartidor("Pedro Gomez", zona));

        r1.start();
        r2.start();
        r3.start();

        try {
            r1.join();
            r2.join();
            r3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("TODOS LOS PEDIDOS HAN SIDO ENTREGADOS EXITOSAMENTE");}

}