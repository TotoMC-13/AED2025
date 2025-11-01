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

        return pedidoMenorId;
    }

    public String obtenerPedidosEnOrdenDeLlegada(){ // ej: [28, 71, 17, 261, 21]
        ListaEnlazada<ABB<Pedido>.HandleABB>.ListaIterador it = pedidosPorLlegada.iterador();
        StringBuilder res = new StringBuilder("[");
        boolean first = true;

        while(it.haySiguiente()) {
            ABB<Pedido>.HandleABB handle = it.siguiente();
            Pedido p = handle.valor();

            if (first) {
                res.append(p);
                first = false;
            } else {
                res.append(", ").append(p);
            }
        }

        res.append("]");

        return res.toString();
    }

    public String obtenerPedidosOrdenadosPorId(){ // ej: {17, 21, 28, 71, 261}
        return pedidosPorId.toString();
    }
}