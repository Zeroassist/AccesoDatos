package org.example.Clases.Ficheros;
  /*
import org.example.Piezas.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        try {
            // Crear 4 fichas
            Ficha f1 = new Caballo(9, 10, true, "A 1", Ficha.tipo.Caballo, 60, 100);
            Ficha f2 = new Alfil(10, 15, false, "A 2", Ficha.tipo.Alfil, 80, 100);
            Ficha f3 = new Rey(11, 20, true, "A 3", 60, 100, false);
            Ficha f4 = new Peon(12, 25, true, "A 4", Ficha.tipo.Peon, 80, 100);


            //Fichas para actualizar
            Ficha f1act = new Caballo(9, 0, true, "A 1", Ficha.tipo.Caballo, 60, 100);
            Ficha f2act = new Alfil(10, 0, false, "A 2", Ficha.tipo.Alfil, 60, 100);
            Ficha f3act = new Rey(11, 0, true, "A 3",  60, 100, true);

            // Crear lista con tres fichas
            List<Ficha> listaFichas = new ArrayList<>();
            listaFichas.add(f1);
            listaFichas.add(f2);
            listaFichas.add(f3);

            //lista actualizar
            List<Ficha> listaFichasactu = new ArrayList<>();
            listaFichas.add(f1act);
            listaFichas.add(f2act);
            listaFichas.add(f3act);

            File f = new File("Fichas.json");
            InterfazDAOJSONP jsonp = new InterfazDAOJSONP();

            //Eliminar
            jsonp.eliminar(f,f4);

            //Añadir 1 Ficha
            jsonp.escribir(f,f4);

            //leer listas
            System.out.println(jsonp.leerLista(f));

            //añadir varias fichas
            jsonp.escribirLista(f,listaFichas);

            //leer listas
            System.out.println(jsonp.leerLista(f));

            //leer
            if(jsonp.leer(10,f)==null){
                System.out.println("No se encontro la ficha que buscas");
            }else{
                System.out.println(jsonp.leer(10,f));
            }

            //leer ordenado por un atributo
            System.out.println(jsonp.ordenarFichasxAtributos("Vida", f));

            //actualizar
            jsonp.actualizar(listaFichasactu,f);

            //leer listas despues de actualizar
            System.out.println(jsonp.leerLista(f));

            //Listar reyes no usados
            System.out.println(jsonp.listarReyesNoUsados(f));

            //Eliminar el ficherio
            //eliminarfich(f);
            //crearfich(f);

        //PARTE XML --------------------------------------------------------------------------------------------------------------------------------
            /*
            InterfazDAO dao = new InterfazDAOXML();
            File archivo = new File("Fichas.xml");


            System.out.println("Leer lista de fichas");
            List<Ficha> todas = dao.leerLista(archivo);
            todas.forEach(System.out::println);


            System.out.println("Leer ficha con ID=3");
            Ficha ficha3 = dao.leer(3, archivo);
            System.out.println(ficha3);


            System.out.println("Ordenar fichas por vida");
            List<Ficha> ordenadasVida = dao.ordenarFichasxAtributos("vida", archivo);
            ordenadasVida.forEach(System.out::println);

            System.out.println("Listar reyes no usados");
            List<Rey> reyesNoUsados = dao.listarReyesNoUsados(archivo);
            reyesNoUsados.forEach(System.out::println);


            System.out.println("Escribir lista de fichas duplicada");
            List<Ficha> duplicada = new ArrayList<>(todas);
            dao.escribirLista(archivo, duplicada);
            System.out.println("Has hecho una lista con" + duplicada.size() + " fichas");

            System.out.println("Actualizar ficha ID 3");
            Ficha actualizarFicha = dao.leer(10, archivo);
            actualizarFicha.setDmg(1);
            List<Ficha> factu = new ArrayList<>();
            factu.add(actualizarFicha);
            dao.actualizar(factu, archivo);
            System.out.println("Ficha actualizada: " + dao.leer(3, archivo));

            System.out.println("Eliminar ficha con ID 1");
            Ficha eliminarFicha = dao.leer(1, archivo);
            dao.eliminar(archivo, eliminarFicha);
            System.out.println("Lista despues de quitar la ficha con id 1:");
            dao.leerLista(archivo).forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("No existe una ficha con ese ID");
        }
    }

    private static void crearfich(File f)  {
        if(!f.exists()) {
            try{
                f.createNewFile();
            }catch (IOException e){
                System.out.println("Ha sucedido un  error en la creacion del fichero");
            }
        }else{
            System.out.println("El fichero ya existe");
        }
    }

    private static void eliminarfich(File f) {
        if(f.isFile()){
            try {
                f.delete();
            }catch (SecurityException e) {
                System.out.println("No puedes eliminar este fihero");
            }
        }
    }
}*/