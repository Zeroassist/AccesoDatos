package org.example.Clases.Bases_Datos;

import org.example.Clases.Bases_Datos.ConectionBD.ConexionFicha;
import org.example.Clases.Excepciones.ArgumentoInvalidoException;
import org.example.Clases.Excepciones.DataAccessException;
import org.example.Clases.Excepciones.IncompatibleVersionException;
import org.example.Clases.InterfazDAO;
import org.example.Piezas.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BDFicha implements InterfazDAO {

     Connection con;

    {
        try {
            con = new ConexionFicha().getConnection();
        } catch (SQLException e) {
            throw new DataAccessException("No se pudo establecer conexion con el almacen \n"+e);
        }
    }


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
    public Ficha leer(String nombre) throws DataAccessException {

        String sql = """
        SELECT nombre, dmg, vidamax
        FROM roguechess.ficha
        WHERE nombre = ?
        """;

        try (
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new DataAccessException(
                            "No existe ninguna ficha con nombre: " + nombre
                    );
                }

                String name = rs.getString("nombre");

                int dmg = rs.getInt("dmg");


                int vidamax = rs.getInt("vidamax");

                switch (name) {

                    case "Dama":
                        return new Dama(dmg,true,"E 1",vidamax, vidamax);

                    case "Alfil":
                        return new Alfil(dmg,true,"C 1",vidamax, vidamax);

                    case "Caballo":
                        return new Caballo(dmg,true,"B 1",vidamax, vidamax);

                    case "Torre":
                        return new Torre(dmg,true,"H 1",vidamax, vidamax);

                    case "Peon":
                        return new Peon(dmg,true,"E 2",vidamax, vidamax);

                    case "Vampiro":
                        return new Vampiro(dmg,true,"E 1",vidamax, vidamax);

                    default:
                        throw new IncompatibleVersionException(
                                "Tipo de ficha desconocido: " + nombre
                        );
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error al leer la ficha \n "+e);
        }
    }

    @Override
    public List<Ficha> leerLista() throws DataAccessException {
        List<Ficha> f= new ArrayList<>();

        String sql = """
        SELECT nombre, dmg, vidamax
        FROM roguechess.ficha
        """;

        try (
                PreparedStatement ps = con.prepareStatement(sql);
        ) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("nombre");
                    int dmg = rs.getInt("dmg");
                    int vidamax = rs.getInt("vidamax");
                    Ficha temp;
                    switch (name) {

                        case "Dama":
                            temp = new Dama(dmg,true,"E 1",vidamax, vidamax);
                            f.add(temp);
                            break;
                        case "Alfil":
                            temp = new Alfil(dmg,true,"C 1",vidamax, vidamax);
                            f.add(temp);
                            break;
                        case "Caballo":
                            temp = new Caballo(dmg,true,"B 1",vidamax, vidamax);
                            f.add(temp);
                            break;
                        case "Torre":
                            temp = new Torre(dmg,true,"H 1",vidamax, vidamax);
                            f.add(temp);
                            break;
                        case "Peon":
                            temp = new Peon(dmg,true,"E 2",vidamax, vidamax);
                            f.add(temp);
                            break;
                        case "Vampiro":
                            temp = new Vampiro(dmg,true,"E 1",vidamax, vidamax);
                            f.add(temp);
                            break;
                        default:
                            throw new IncompatibleVersionException(
                                    "Tipo de ficha desconocido: " + name
                            );
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error al leer la ficha \n "+e);
        }
        return f;
    }

    @Override
    public void escribir(Ficha f) throws DataAccessException, IOException {

    }

    @Override
    public void escribirLista(List<Ficha> fichas) throws DataAccessException, IOException {

    }

    @Override
    public void actualizar(String jr, List<Ficha> f, int pts) throws DataAccessException {

    }

    @Override
    public void eliminar(String nombreJugador) throws DataAccessException {

            String sql = """
        DELETE FROM roguechess.puntuacion_equipo
        WHERE nombre_jugador = ?
        """;

            try (
                    PreparedStatement ps = con.prepareStatement(sql)
            ) {
                ps.setString(1, nombreJugador);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas == 0) {
                    throw new DataAccessException(
                            "El jugador  " + nombreJugador + "No tiene puntuacion"
                    );
                }

            } catch (SQLException e) {
                throw new DataAccessException(
                        "Error al borrar la puntuación de "+ nombreJugador +"\n"+ e
                );
            }

    }
}
