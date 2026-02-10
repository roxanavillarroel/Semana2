package org.pedidos;


public class PedidoExpress extends Pedido implements Despachable, Cancelable{

    public PedidoExpress(int id, String direccion, double distanciaKm) {
        super(id, direccion, distanciaKm);
    }

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Jose Villarroel";
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (10 + (distanciaKm * 1));
    }

    @Override
    public void despachar(){
        if (!estaDisponibleParaDespacho()){
            System.out.println(" Pedido # " +id+ " no se puede despachar (cancelado o ya en proceso)");
            return;
        }
        setEstado(EstadoPedido.EN_REPARTO);
        System.out.println(" Pedido # " +id+ " - EN REPARTO EXPRESS");
        try {
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        setEstado(EstadoPedido.ENTREGADO);
        System.out.println(" Pedido # " +id+ " - ENTREGADO CORRECTAMENTE");
    }

    @Override
    public void cancelar(){
        if (getEstado() == EstadoPedido.ENTREGADO){
            System.out.println(" No se puede cancelar, pedido entregado");
            return;
        }
        cancelado = true;
        System.out.println(" Pedido # "+id+ " CANCELADO");
    }
}
