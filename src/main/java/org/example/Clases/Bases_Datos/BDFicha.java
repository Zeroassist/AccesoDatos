package org.example.Clases.Bases_Datos;

import org.example.Clases.Bases_Datos.ConectionBD.ConexionFicha;
import org.example.Clases.Excepciones.ArgumentoInvalidoException;
import org.example.Clases.Excepciones.DataAccessException;
import org.example.Clases.InterfazDAO;
import org.example.Piezas.Ficha;

import java.io.IOException;
import java.util.List;

public class BDFicha implements InterfazDAO {

     ConexionFicha con=new ConexionFicha();

    @Override
    public String records() throws DataAccessException {
        return "";
    }

    @Override
    public List<Ficha> equipodelRey(String name) throws DataAccessException {
        return List.of();
    }

    @Override
    public List<Ficha> ordenarFichasxAtributos(String atributo) throws DataAccessException, ArgumentoInvalidoException {
        return List.of();
    }

    @Override
    public Ficha leer(int id) throws DataAccessException {
        return null;
    }

    @Override
    public List<Ficha> leerLista() throws DataAccessException {
        return List.of();
    }

    @Override
    public void escribir(Ficha f) throws DataAccessException, IOException {

    }

    @Override
    public void escribirLista(List<Ficha> piezas) throws DataAccessException, IOException {

    }

    @Override
    public void actualizar(List<Ficha> f) throws DataAccessException {

    }

    @Override
    public void eliminar(Ficha fic) throws DataAccessException {

    }
}
