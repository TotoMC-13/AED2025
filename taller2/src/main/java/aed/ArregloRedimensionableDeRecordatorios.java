package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatoriosVector;

    public ArregloRedimensionableDeRecordatorios() {
        this.recordatoriosVector = new Recordatorio[0];
    }

    public int longitud() {
        return recordatoriosVector.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoVector = new Recordatorio[this.longitud() + 1];

        for (int j = 0; j < this.longitud(); j++){
            nuevoVector[j] = recordatoriosVector[j];
        }

        nuevoVector[this.longitud()] = i;
        recordatoriosVector = nuevoVector;
    }

    public Recordatorio obtener(int i) {
        return recordatoriosVector[i];
    }

    public void quitarAtras() {
        Recordatorio[] nuevoVector = new Recordatorio[this.longitud() - 1];

        for (int j = 0; j < this.longitud() - 1; j++){
            nuevoVector[j] = recordatoriosVector[j];
        }

        recordatoriosVector = nuevoVector;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        recordatoriosVector[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this.recordatoriosVector = new Recordatorio[vector.longitud()];

        for (int j = 0; j < vector.longitud(); j ++){
            this.recordatoriosVector[j] = new Recordatorio(vector.obtener(j));
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);
    }
}
