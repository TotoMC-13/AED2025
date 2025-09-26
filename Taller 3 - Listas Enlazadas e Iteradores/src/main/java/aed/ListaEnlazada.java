package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v) {valor = v;}
    }

    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevoPrimero = new Nodo(elem);

        if (longitud == 0) {
            primero = nuevoPrimero;
            ultimo = nuevoPrimero;
        } else {
            nuevoPrimero.sig = primero;
            primero.ant = nuevoPrimero;
            primero = nuevoPrimero;
        }
        
        longitud ++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevoUltimo = new Nodo(elem);

        if (longitud == 0) {
            primero = nuevoUltimo;
            ultimo = nuevoUltimo;
        } else {
            ultimo.sig = nuevoUltimo;
            nuevoUltimo.ant = ultimo;
            ultimo = nuevoUltimo;
        }

        longitud ++;
    }

    public T obtener(int i) {
        ListaIterador it = iterador();
        int j = 0;
        T res = null;

        if (i == 0) {
            return primero.valor;
        } else if (i == longitud - 1) {
            return ultimo.valor;
        } else if (i >= longitud) {
            return null;
        }

        while (it.haySiguiente() && j < i) {
            res = it.siguiente();
            j++;
        }

        return res;
    }

    public void eliminar(int i) {
        int j = 0;
        Nodo nodoActual = primero;
        Nodo nodoDerecha;
        Nodo nodoIzquierda;

        if (i >= longitud) {
            return;
        }

        while(j != i) {
            nodoActual = nodoActual.sig;
            j++;
        }

        // "Conecto" los nodos anteriores y posteriores al eliminado entre si.
        // No trae problemas al eliminar el primer/ultimo nodo porque se les 
        // asignara null a sus conexiones y listo.
        
        nodoIzquierda = nodoActual.ant;
        nodoDerecha = nodoActual.sig;

        if (nodoIzquierda != null) {
            nodoIzquierda.sig = nodoDerecha;
        }

        if (nodoDerecha != null) {
            nodoDerecha.ant = nodoIzquierda;
        }

        // Re-asigno el primer/ultimo elemento de ser necesario.

        if (i == longitud - 1) {
            ultimo = nodoIzquierda;
        } else if (i == 0) {
            primero = nodoDerecha;
        }

        longitud --;
    }

    public void modificarPosicion(int indice, T elem) {
        int j = 0;
        Nodo nodoModificado = primero;

        while (j != indice) {
            nodoModificado = nodoModificado.sig;
            j++;
        }

        nodoModificado.valor = elem;
    }

    // Esto es el constructor por copia
    public ListaEnlazada(ListaEnlazada<T> lista) { 
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public class ListaIterador{
    	private Nodo actualNodo;

        public ListaIterador() {
            actualNodo = primero;
        }

        public boolean haySiguiente() {
            if (actualNodo == null) {
                return false;
            }

	        return actualNodo.sig != null;
        }
        
        public boolean hayAnterior() {
	        return actualNodo.ant != null;
        }

        public T siguiente() {
	        T val_siguiente = actualNodo.sig.valor;
            actualNodo = actualNodo.sig;
            return val_siguiente;
        }
        

        public T anterior() {
	        T val_anterior = actualNodo.ant.valor;
            actualNodo = actualNodo.ant;
            return val_anterior;
        }
    }

    public ListaIterador iterador() {
	    return new ListaIterador();
    }

}
