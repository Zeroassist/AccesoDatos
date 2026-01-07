package org.example.Clases.Bases_Datos;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.Clases.Excepciones.*;
import org.example.Piezas.*;

import java.util.*;
public class Main {

    public static void main(String[] args) {

        BDFicha dao = new BDFicha();

        System.out.println("PRUEBAS");

        //LeerNombre
        try {
            System.out.println("LEER DAMA");
            Ficha f = dao.leer("Dama");
            System.out.println(f);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }

        //leerLista
        List<Ficha> fichas = new ArrayList<>();
        try {
            System.out.println("LEER LISTA FICHAS");
            fichas = dao.leerLista();
            fichas.forEach(System.out::println);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }

       //Ordenarxatributo
        try {
            System.out.println("ORDENAR POR VIDA");
            dao.ordenarFichasxAtributos("vida")
                    .forEach(System.out::println);

            System.out.println("ORDENAR POR DAÑO");
            dao.ordenarFichasxAtributos("daño")
                    .forEach(System.out::println);

            System.out.println("ORDENAR POR TIPO");
            dao.ordenarFichasxAtributos("tipo")
                    .forEach(System.out::println);

        } catch (DataAccessException | ArgumentoInvalidoException e) {
            System.err.println(e.getMessage());
        }

        //Escribir
        try {
            System.out.println("ESCRIBIR");
            dao.escribir("Richard", fichas);
            System.out.println("Jugador insertado correctamente");
        } catch (DataAccessException | IOException | ArgumentoInvalidoException e) {
            System.err.println(e.getMessage());
        }

        //Records
        try {
            System.out.println("RECORDS");
            dao.records().forEach(System.out::println);
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }

        //Actualizar
        try {
            System.out.println("ACTUALIZAR");
            dao.actualizar("Francisco", fichas, 80000000);
        } catch (DataAccessException | ArgumentoInvalidoException e) {
            System.err.println(e.getMessage());
        }

        //EscribirLista
        try {
            System.out.println("ESCRIBIR LISTA");

            List<String> nombres = List.of("JugadorA", "JugadorB");
            List<List<Ficha>> listas = List.of(fichas, fichas);

            dao.escribirLista(nombres, listas);
            System.out.println("Lista escrita correctamente");

        } catch (DataAccessException | IOException e) {
            System.err.println(e.getMessage());
        }

        //Eliminar
        try {
            System.out.println("ELIMINAR");
            dao.eliminar("Richard");
            System.out.println("Jugador eliminado");
        } catch (DataAccessException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("FIN");
    }
}
