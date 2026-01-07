package org.example.Clases;

import org.example.Clases.Bases_Datos.BDFicha;

public class Main {
    public static void main(String[] args) {
        BDFicha bdf = new BDFicha();
        System.out.println(bdf.leer("Dama"));
        //bdf.eliminar("Francisco");
        System.out.println(bdf.leerLista());
    }
}
