package aed;

public class Recordatorio {
    private Fecha fecha;
    private Horario horario;
    private String mensaje;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.fecha = new Fecha(fecha);
        this.horario = horario;
        this.mensaje = mensaje;
    }

    public Horario horario() {
        return horario;
    }

    public Fecha fecha() {
        return new Fecha(fecha);
    }

    public String mensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return mensaje + " @ " + fecha + " " + horario;
    }

    @Override
    public boolean equals(Object otro) {
        boolean esNull = (otro == null);
        if(esNull){
            return false;
        }

        if (otro.getClass() != this.getClass()){
            return false;
        }

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        return this.fecha.equals(otroRecordatorio.fecha) &&
               this.horario.equals(otroRecordatorio.horario) &&
               this.mensaje.equals(otroRecordatorio.mensaje);
    }

        public Recordatorio(Recordatorio recordatorio) {
            this.fecha = new Fecha(recordatorio.fecha);
            this.horario = recordatorio.horario;
            this.mensaje = recordatorio.mensaje;
    }


}
