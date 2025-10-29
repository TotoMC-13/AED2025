package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es der a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    private Nodo raiz;
    private int cardinal;

    private class Nodo {
        private T valor;
        private Nodo padre;
        private Nodo izq;
        private Nodo der;

        public Nodo(T v) {
            valor = v;
            padre = null;
            izq = null;
            der = null;
        }
    }

    public ABB() {
        raiz = null;
        cardinal = 0;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo(){
        if (raiz == null) {return null;}
        return recorrerIzquierda(raiz).valor;
    }

    private Nodo recorrerIzquierda(Nodo n){
        if (n.izq == null) {return n;}
        
        return recorrerIzquierda(n.izq);
    }

    public T maximo(){
        if (raiz == null) {return null;}
        return recorrerDerecha(raiz).valor;
    }

    private Nodo recorrerDerecha(Nodo n){
        if (n.der == null) {return n;}

        return recorrerDerecha(n.der);
    }

    public void insertar(T elem){
        if (cardinal == 0){
            raiz = new Nodo(elem);
            cardinal = cardinal + 1;
            return;
        }

        if (pertenece(elem) == true) {return;} // Si el elemento ya esta, no hacemos nada

        Nodo nuevoPadre = buscarElemento(raiz, elem);


        Nodo nuevoNodo = new Nodo(elem);
        nuevoNodo.padre = nuevoPadre;
        
        if (nuevoPadre.valor.compareTo(elem) < 0) {
            nuevoPadre.der = nuevoNodo;
        } else {
            nuevoPadre.izq = nuevoNodo;
        }

        cardinal = cardinal + 1;
    }

    public boolean pertenece(T elem){
        if (raiz == null) {return false;}
        return (buscarElemento(raiz, elem).valor.equals(elem));
    }

    private boolean esHoja(Nodo n){
        return n.der == null && n.izq == null;
    }

    private Nodo buscarElemento(Nodo n, T elem){
        if (esHoja(n) || (n.valor.equals(elem))){
            return n;
        } else if (n.valor.compareTo(elem) < 0) { // Comparacion < 0 quiere decir que elem > n.valor => Vamos para la derecha
            if (n.der != null) {return buscarElemento(n.der, elem);}
            return n;
        } else { // Seria comparacion > 0, elem < n.valor => Vamos para la izquierda
            if (n.izq != null) {return buscarElemento(n.izq, elem);}
            return n; 
        } // No nos fijamos si la comparacion es == 0 pues seria el caso n.valor = elem
    }

    private int posicionNodo(Nodo n) {
        if (n == null || n.padre == null) {
            return -1;
        } else if (n.padre.izq == n) { // Nodo izq
            return 0;
        } else if (n.padre.der == n) { // Nodo der
            return 1;
        }
        return -1;
    }

    private Nodo buscarSucesor(Nodo n) {
        return recorrerIzquierda(n.der);
    }

    private void cortarNodo(Nodo n) {
        if (n == raiz) {
            raiz = null;
        } else {
            int posNodo = posicionNodo(n);
            
            if (posNodo == 0) { // Si n es el izquierdo
                n.padre.izq = null;
            } else { // Si n es el derecho
                n.padre.der = null;
            }
        }
        cardinal--;
    }

    private Nodo obtenerHijoUnico(Nodo n) {
        if (n.der == null) {
            return n.izq;
        } else {
            return n.der;
        }
    }

    public void eliminar(T elem){
        if (!pertenece(elem)) {return;} // (Caso 1) No pertenece, no eliminamos nada
        
        Nodo n = buscarElemento(raiz, elem);

        if (esHoja(n)) { // (Caso 2) No tiene hijos
            cortarNodo(n);
        } else if (n.der == null || n.izq == null) { // (Caso 3) Tiene un hijo derecho
            Nodo hijoUnico = obtenerHijoUnico(n);
            
            if (raiz == n) {
                raiz = hijoUnico;
            } else if (posicionNodo(n) == 0) {
                n.padre.izq = hijoUnico;
            } else {
                n.padre.der = hijoUnico;
            }

            hijoUnico.padre = n.padre;

            cardinal --;
        } else { // (Caso 4) Tiene 2 hijos
            Nodo sucesor = buscarSucesor(n);

            n.valor = sucesor.valor;

            Nodo hijoSucesor = sucesor.der;

            if (hijoSucesor == null) {
                cortarNodo(sucesor);
            } else {
                hijoSucesor.padre = sucesor.padre;

                if (sucesor.padre.izq == sucesor) {
                    sucesor.padre.izq = hijoSucesor;
                } else {
                    sucesor.padre.der = hijoSucesor;
                }

                cardinal --;
            }
        }

    }

    public String toString(){
        if (raiz == null) {
            return "{}";
        }

        String res = "{";

        ABB_Iterador it = iterador();

        while (it.haySiguiente()) {
            T siguiente = it.siguiente(); 
            
            if (siguiente.equals(this.minimo())) {
                res += siguiente;
            } else {
                res += "," + siguiente;
            }
        }

        res += "}";

        return res;
    }

    public class ABB_Iterador {
        private Nodo actual = recorrerIzquierda(raiz);

        public boolean haySiguiente() {            
            return actual != null;
        }
    
        public T siguiente() {
            if (actual == null) {
                return null;
            }

            T valorADevolver = actual.valor;

            if (actual.der != null) {
                actual = recorrerIzquierda(actual.der);
            } else {
                Nodo hijo = actual;
                actual = actual.padre;

                while (actual != null && hijo == actual.der) {
                    hijo = actual;
                    actual = actual.padre;
                }
            }

            return valorADevolver;
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}