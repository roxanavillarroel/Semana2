package org.pedidos;

public class PedidoComida extends Pedido implements Despachable, Cancelable {

    public PedidoComida(int id, String direccion, double distanciaKm) {
        super(id, direccion, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Georgia Figuereo";
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (15 + (distanciaKm * 2));
    }

    @Override
    public void despachar() {
        if (!estaDisponibleParaDespacho()) {
            System.out.println(" Pedido #" + id+ " no se puede despachar (Cancelado o  ya en proceso)");
            return;
        }
        setEstado(EstadoPedido.EN_REPARTO);
        System.out.println(" Pedido #" + id+ " - DESPACHADO");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setEstado(EstadoPedido.ENTREGADO);
        System.out.println(" Pedido #" + id+ " entregado con exito");
    }

    @Override
    public void cancelar() {
        if (getEstado() == EstadoPedido.ENTREGADO){
            System.out.println(" No se puede cancelar, pedido ha sido entregado");
            return;
        }
        cancelado = true;
        System.out.println(" Pedido #" + id+ " Cancelado exitosamente");
    }
}
