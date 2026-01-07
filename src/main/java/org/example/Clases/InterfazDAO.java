package org.example.Clases;

import org.example.Clases.Excepciones.ArgumentoInvalidoException;
import org.example.Clases.Excepciones.DataAccessException;
import org.example.Clases.Excepciones.IncompatibleVersionException;
import org.example.Piezas.Ficha;

import java.io.IOException;
import java.util.List;



public interface InterfazDAO {
    /**
     * Imprime la lista de los records que saca del almacen
     *
     * @return un mensaje con el top 3 de la base de datos
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     */
    public String records() throws DataAccessException;
    //Creado nuevo para la tabla de puntuaciones
    /**
     * Busca en el almacen el ReyPJ con el nombre que se le pasa y te devuelve su lista de piezas
     *
     * @return Lista del equipo del rey en funcion de su nombre
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws IncompatibleVersionException  si el almacen coniene alguna ficha que no se encuentre
     */
    public List<Ficha> equipodelRey(String name) throws DataAccessException;
    //Remplaza al listar reyes usados
    /**
     * Ordena las fichas en funcion del atributo que le mandes(daño o vida)
     *
     * @param atributo el atributo por el que quieres que ordene(daño o vida)
     *
     * @return Lista ordenada por el atributo que le hayas pasado(daño o vida)
     *
     * @throws DataAccessException si no encuentra el almacen
     * @throws ArgumentoInvalidoException si el atributo introducido no existe
     */
    public List<Ficha> ordenarFichasxAtributos(String atributo) throws DataAccessException, ArgumentoInvalidoException;
    /**
     * CRUD normal y corriente
     */

    /**
     * Metodo para buscar una ficha en el almacen segun su nomrbe(Primary key que es el tipo de ficha)
     *
     * @param nombre el tipo de la ficha a buscar(Peon, Caballo, Alfil...)
     *
     * @return Un objeto ficha que se haya buscado
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws IncompatibleVersionException  si el almacen coniene algo que no sea de la clase Ficha
     */
    public Ficha leer(String nombre)throws DataAccessException;
    /**
     * Metodo que lee el almacen de datos y devuelve una lista con todas las fichas que lee
     *
     * @return Una Lista de todas las fichas en el equipo
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws IncompatibleVersionException  si el almacen coniene algo que no sea de la clase Ficha
     */
    public List<Ficha> leerLista()throws DataAccessException;
    /**
     * Metodo para escribir una ficha en el almacen
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws java.io.IOException si sucede algun error en la escritura
     */
    public void escribir(Ficha f) throws DataAccessException,IOException;
    /**
     * Metodo para escribir una lista de fichas en el almacen
     *
     * @param fichas
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     * @throws java.io.IOException si sucede algun error en la escritura
     */
    public void escribirLista(List<Ficha> fichas) throws DataAccessException, IOException;
    /**
     * Metodo para actualizar dentro del almacen
     *
     * @param jr Nombre del record a modificar en el almacen
     * @param f Lista de fichas que se han usado en la run
     * @param pts puntos conseguidos en la run
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     */
    public void actualizar(String jr, List<Ficha> f, int pts) throws DataAccessException;
    /**
     * Metodo que borra el record de un jugador
     *
     * @param nombreJugador Jugador que se va a borrar del almacen
     *
     * @throws DataAccessException si no se puede acceder al almacen o no existe
     */
    public void eliminar(String nombreJugador) throws DataAccessException;
}
