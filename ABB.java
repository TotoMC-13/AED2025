package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es der a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> {
    private Nodo raiz;
    private int cardinal;
    private int altura;

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
        altura = 0;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo(){
        if (raiz == null) {return null;}
        return recorrerIzquierda(raiz);
    }

    private T recorrerIzquierda(Nodo n){
        if (n.izq == null) {return n.valor;}
        
        return recorrerIzquierda(n.izq);
    }

    public T maximo(){
        if (raiz == null) {return null;}
        return recorrerDerecha(raiz);
    }

    private T recorrerDerecha(Nodo n){
        if (n.der == null) {return n.valor;}

        return recorrerIzquierda(n.der);
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
        return (buscarElemento(raiz, elem).valor == elem);
    }

    private Nodo buscarElemento(Nodo n, T elem){
        if ((n.der == null && n.izq == null) || (n.valor == elem)){
            return n;
        } else if (n.valor.compareTo(elem) < 0) { // Comparacion < 0 quiere decir que elem > n.valor => Vamos para la derecha
            return buscarElemento(n.der, elem);
        } else {
            return buscarElemento(n.izq, elem); // Seria comparacion > 0, elem < n.valor => Vamos para la izquierda
        } // No nos fijamos si la comparacion es == 0 pues seria el caso n.valor = elem
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public class ABB_Iterador {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public ABB_Iterador iterador() {
        return new ABB_Iterador();
    }

}
