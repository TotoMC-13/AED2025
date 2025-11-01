package aed;

public class SistemaPedidos {
    ABB<Pedido> pedidosPorId;
    ListaEnlazada<ABB<Pedido>.HandleABB> pedidosPorLlegada;

    public SistemaPedidos(){
        pedidosPorId = new ABB<Pedido>();
        pedidosPorLlegada = new ListaEnlazada<ABB<Pedido>.HandleABB>();
    }

    public void agregarPedido(Pedido pedido){
        ABB<Pedido>.HandleABB nuevoPedido = pedidosPorId.insertar(pedido); // Inserto el pedido y guardo el handle
        pedidosPorLlegada.agregarAtras(nuevoPedido); // Inserto el handle en la lista por llegada
    }

    public Pedido proximoPedido(){
        if (pedidosPorLlegada.longitud() == 0) {return null;} // No hay pedidos

        ABB<Pedido>.HandleABB pedidoHandle = pedidosPorLlegada.obtener(0); // Agarro el siguiente pedido (el primero)
        Pedido siguientePedido = pedidoHandle.valor(); // Agarro el valor del pedido

        pedidoHandle.eliminar(); // Lo elimino del ABB
        pedidosPorLlegada.eliminar(0); // Lo elimino de la lista enlazada
        
        return siguientePedido;
    }

    public Pedido pedidoMenorId(){
        if (pedidosPorId.cardinal() == 0) {return null;}

        Pedido pedidoMenorId = pedidosPorId.minimo();
        pedidosPorId.eliminarNodo(pedidoMenorId);

        return pedidoMenorId;
    }

    public String obtenerPedidosEnOrdenDeLlegada(){
        throw new UnsupportedOperationException("No implementado aún");
    }

    public String obtenerPedidosOrdenadosPorId(){
        throw new UnsupportedOperationException("No implementado aún");
    }
}