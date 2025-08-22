package aed;

class Funciones {

/***  Primera parte: Funciones en java ***/

    int cuadrado(int x) {
        int res = x * x;
        return res;
    }

    double distancia(double x, double y) {
        double res = Math.sqrt((x * x + y * y));
        return res;
    }

    boolean esPar(int n) {
        boolean res = (n % 2 == 0);
        return res;
    }

    boolean esBisiesto(int n) {
        if ((n % 400 == 0) || (n % 4 == 0 && !(n % 100 == 0))){
            return true;
        }
        return false;
    }

    int factorialIterativo(int n) {
        int res = 1;

        for(int i = n; i != 0; i--){
            res = res * i;
        }

        return res;
    }

    int factorialRecursivo(int n) {
        if (n == 0 || n == 1){
            return 1;
        }

        return n * factorialIterativo(n - 1);
    }

    boolean esPrimo(int n) {
        if (n <= 1){
            return false;
        }

        for(int i = n - 1; i > 1; i--){
            if (n % i == 0){
                return false;
            }
        }

        return true;
    }

    int sumatoria(int[] numeros) {
        int res = 0;

        for (int i = 0; i < numeros.length; i++){
            res += numeros[i];
        }

        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        for (int i = 0; i < numeros.length; i++){
            if(numeros[i] == buscado){
                return i;
            }
        }
        return 0;
    }

    boolean tienePrimo(int[] numeros) {
        for (int i = 0; i < numeros.length; i++){
            if (esPrimo(numeros[i])){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int i = 0; i < numeros.length; i++){
            if (!esPar(numeros[i])){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        
        for (int i = 0; i < s1.length(); i++)
        {
            if (s1.length() > s2.length() || s1.charAt(i) != s2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    String invertirString(String palabra) {
        String res = "";

        for (int i = palabra.length() - 1; i > -1; i--){
            res += palabra.charAt(i);
        }

        return res;
    }

    boolean esSufijo(String s1, String s2) {
        String s1_reverse = invertirString(s1);
        String s2_reverse = invertirString(s2);

        return esPrefijo(s1_reverse, s2_reverse);
    }

/***  Segunda parte: Debugging ***/

    boolean xor(boolean a, boolean b) {
        return (!a && b) || (a && !b);
    }

    boolean iguales(int[] xs, int[] ys) {
        boolean res = true;

        if (xs.length != ys.length){
            return false;
        }

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] != ys[i]) {
                res = false;
            }
        }
        return res;
    }

    boolean ordenado(int[] xs) {
        boolean res = true;

        if (xs.length <= 1){
            return true;
        }

        for (int i = 1; i < xs.length; i++) {
            if (xs[i] < xs [i-1]) {
                res = false;
            }
        }
        return res;
    }

    int maximo(int[] xs) {
        int res = xs[0];
        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > res) res = xs[i];
        }
        return res;
    }

    boolean todosPositivos(int[] xs) {
        boolean res = true;
        for (int x : xs) {
            if (x <= 0) {
                return false;
            }
        }
        return res;
    }

}
