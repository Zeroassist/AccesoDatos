package org.example.Clases;

import org.example.Clases.Excepciones.ArgumentoInvalidoException;
import org.example.Clases.Excepciones.DataAccessException;
import org.example.Clases.Excepciones.IncompatibleVersionException;
import org.example.Piezas.Ficha;
import org.example.Piezas.Rey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;



public interface InterfazDAO {
    /**
     * Busca en el almacen el ReyPJ con el nombre que se le pasa y te devuelve su lista de piezas
     *
     * @return Lista del equipo del rey en funcion de su nombre
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws IncompatibleVersionException  si el almacen coniene algo que no sea de la clase Rey
     */
    public List<Ficha> equipodelRey(String name) throws DataAccessException;
    //Remplaza al listar reyes usados
    /**
     * Ordena la lista de tus fichas actuales en funcion del atributo que le mandes
     *
     * @param atributo el atributo por el que quieres que ordene
     *
     * @return Lista ordenada por el atributo que le hayas pasado
     *
     * @throws DataAccessException si no encuentra el almacen
     * @throws ArgumentoInvalidoException si el atributo introducido no existe
     */
    public List<Ficha> ordenarFichasxAtributos(String atributo) throws DataAccessException, ArgumentoInvalidoException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas

    /**
     * CRUD normal y corriente
     */

    /**
     * Metodo para buscar una ficha en el almacen segun su id
     *
     * @param id el ID de la ficha a buscar
     *
     * @return Un objeto ficha que se haya buscado
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws IncompatibleVersionException  si el almacen coniene algo que no sea de la clase Ficha
     */
    public Ficha leer(int id)throws DataAccessException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas
    /**
     * Metodo que lee el almacen de datos y devuelve una lista con todas las fichas que lee
     *
     * @return Una Lista de todas las fichas en el equipo
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws IncompatibleVersionException  si el almacen coniene algo que no sea de la clase Ficha
     */
    public List<Ficha> leerLista()throws DataAccessException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas
    /**
     * Metodo para escribir una ficha en el almacen
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws java.io.IOException si sucede algun error en la escritura
     */
    public void escribir(Ficha f) throws DataAccessException,IOException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas
    /**
     * Metodo para escribir una lista de piezas en el almacen
     *
     * @param piezas
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws java.io.IOException si sucede algun error en la escritura
     */
    public void escribirLista(List<Ficha> piezas) throws DataAccessException, IOException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas
    /**
     * Metodo para actualizar cualquier cosa dentro del almacen
     *
     * @param f Una o mas ficha a modificar en el almacen
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     */
    public void actualizar(List<Ficha> f) throws DataAccessException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas
    /**
     * Metodo para eliminar fichas del archivo
     *
     * @param fic Objeto ficha a eliminar del almacen
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     */
    public void eliminar(Ficha fic) throws DataAccessException;
    //Para hacerlo mas generico, hemos quitado el archivo de guardado y hemos decidido poner nuestras
    // excepciones personalizadas
}
