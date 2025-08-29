package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        dia = fecha.dia;
        mes = fecha.mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }


    public String toString() {
        return dia() + "/" + mes();
    }

    @Override
    public boolean equals(Object otra) {
        boolean esNull = (otra == null);
        if(esNull){
            return false;
        }

        if (otra.getClass() != this.getClass()){
            return false;
        }

        Fecha otraFecha = (Fecha) otra;

        return dia == otraFecha.dia && mes == otraFecha.mes;
    }

    public void incrementarDia() {
        int cantidad_dias = diasEnMes(mes);

        if ((dia + 1) > cantidad_dias){
            if (mes == 12){
                mes = 1;
            }
            else{
                mes += 1;
            }
            dia = 1;
        }
        else{
            dia += 1;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
