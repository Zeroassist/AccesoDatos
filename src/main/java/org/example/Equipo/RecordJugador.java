package org.example.Equipo;

public class RecordJugador {
    private String nombre_jugador;
    private String equipo;
    private int puntos;
    private String fecha;

    public RecordJugador(String nombre_jugador, String equipo, int puntos, String fecha) {
        this.nombre_jugador = nombre_jugador;
        this.equipo = equipo;
        this.puntos = puntos;
        this.fecha = fecha;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Nombre_jugador='" + nombre_jugador +
                ", equipo='" + equipo +
                ", puntos=" + puntos +
                ", fecha='" + fecha +"\n";
    }
}
