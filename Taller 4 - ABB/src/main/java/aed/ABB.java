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
        return (buscarElemento(raiz, elem).valor == elem);
    }

    private boolean tieneHijos(Nodo n){
        return n.der == null && n.izq == null;
    }

    private Nodo buscarElemento(Nodo n, T elem){
        if (tieneHijos(n) || (n.valor == elem)){
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
        if (n.der.der != null) { // Tiene subarbol a su derecha
            return recorrerIzquierda(n.der); // Devuelvo el minimo de su subarbol derecho
        }

        return n.der;
    }

    public void eliminar(T elem){
        if (pertenece(elem) == false) {return;} // No pertenece, no eliminamos nada (Caso 1)
        
        Nodo n = buscarElemento(raiz, elem);
        Nodo sucesor = null; // El nodo que tomara su lugar

        if (tieneHijos(n) == false) { // n no tiene hijos, solo hay que borrarlo del padre (Caso 2)
            if (posicionNodo(n) == 0) { // Es el izquierdo
                n.padre.izq = sucesor;
            } else { // Es el derecho
                n.padre.der = sucesor;
            }
            return;
        }

        if (n.izq == null) { // Si estamos aca sabemos que tiene por lo menos un hijo, vemos si tiene solo 1 (Caso 3)
            n.valor = n.der.valor;
        } else if (n.der == null) {
            n.valor = n.izq.valor;
        } else { // Tiene dos hijos, buscamos el sucesor (Caso 4)
            sucesor = buscarSucesor(n);
            n.valor = sucesor.valor;
        }

        // Ahora saco al sucesor de donde estaba antes
        if (posicionNodo(sucesor) == 0) { // Es el izquierdo
            sucesor.padre.izq = null;
        } else { // Es el derecho
            sucesor.padre.der = null;
        }

        // Reemplazo a n por su sucesor
        if (posicionNodo(n) == 0) { // Es el izquierdo
            n.padre.izq.valor = sucesor.valor;
        } else { // Es el derecho
            n.padre.der.valor = sucesor.valor;
        }

        cardinal = cardinal - 1;
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
