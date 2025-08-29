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
        // Implementar
        return null;
    }

    public void quitarAtras() {
        // Implementar
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        return null;
    }
}
