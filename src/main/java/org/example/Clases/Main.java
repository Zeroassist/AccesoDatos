package org.example.Clases;

import org.example.Clases.Bases_Datos.BDFicha;
import org.example.Clases.Excepciones.DataAccessException;
import org.example.Piezas.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        BDFicha bdf = new BDFicha();
        System.out.println(bdf.leer("Dama"));
        //bdf.eliminar("Francisco");
        System.out.println(bdf.leerLista());
        ArrayList<Ficha> fichas = new ArrayList<>();

        fichas.add(new Peon(10, true, "A 2", 8, 8));
        fichas.add(new Caballo(25, false, "C 4", 18, 25));
        fichas.add(new Dama(45, true, "H 7", 40, 50));
        ArrayList<Ficha> fichas2 = new ArrayList<>();

        fichas2.add(new Torre(35, false, "D 1", 30, 30));
        fichas2.add(new Alfil(20, true, "B 6", 15, 20));
        fichas2.add(new Vampiro(28, false, "G 4", 22, 25));
        List<List<Ficha>> fichas3 = new ArrayList<>();
        fichas3.add(fichas);
        fichas3.add(fichas2);
        List<String> names = new ArrayList<>();
        names.add("Francisco");
        names.add("Paulo");
        try {
            bdf.escribirLista(names, fichas3);
        }catch (IOException e) {
            throw new DataAccessException(e);
        }
        System.out.println(bdf.leerLista());
    }
}
