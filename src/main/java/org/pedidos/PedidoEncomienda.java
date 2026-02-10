package org.pedidos;

public class PedidoEncomienda extends Pedido implements Despachable, Cancelable {

    public PedidoEncomienda(int id, String direccion, double distanciaKm) {
        super(id, direccion, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Roxana Villarroel";
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (20 + (distanciaKm * 3));
    }

    @Override
    public void despachar() {
        if (!estaDisponibleParaDespacho()) {
            System.out.println(" Pedido # " + id + " no se puede despachar o ya en proceso");
            return;
        }
        setEstado(EstadoPedido.EN_REPARTO);
        System.out.println(" Pedido # " + id + " - REPARTO");

        try {
            Thread.sleep(2500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        setEstado(EstadoPedido.ENTREGADO);
            System.out.println(" Pedido # " +id+ " - ENTREGADO correctamente");
        }

    @Override
    public void cancelar() {
        if (getEstado() == EstadoPedido.ENTREGADO){
            System.out.println(" Pedido # " + id + " No se puede cancelar, el pedido ya fue entregado");
            return;
        }
        cancelado = true;
        System.out.println(" Pedido # " + id + " CANCELADO correctamente");
    }
}
