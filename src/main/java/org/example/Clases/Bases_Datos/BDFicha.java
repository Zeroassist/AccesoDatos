package org.example.Clases.Bases_Datos;

import org.example.Clases.Bases_Datos.ConectionBD.ConexionFicha;
import org.example.Clases.Excepciones.*;
import org.example.Clases.InterfazDAO;
import org.example.Equipo.RecordJugador;
import org.example.Piezas.*;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BDFicha implements InterfazDAO {

    static Scanner sc =new Scanner(System.in);
    Connection con;

    {
        try {
            con = new ConexionFicha().getConnection();
        } catch (SQLException e) {
            throw new DataAccessException("No se pudo establecer conexion con el almacen \n"+e);
        }
    }


    @Override
    public List<RecordJugador> records() throws DataAccessException {
        String sql = """
        SELECT *
        FROM roguechess.puntuacion_equipo
        """;
        List<RecordJugador> devuelta = new ArrayList<>();
        try (
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre= rs.getString("nombre_jugador");
                    String equipo = rs.getString("equipo");
                    int pts = rs.getInt("puntos");
                    String fecha = rs.getString("fecha");

                    RecordJugador temp = new RecordJugador(nombre, equipo, pts,fecha);
                    devuelta.add(temp);
                }
                devuelta.sort(Comparator.comparingInt(RecordJugador::getPuntos));
               return devuelta.subList(0, Math.min(3, devuelta.size()));
            }
        } catch (SQLException e) {
            throw new DataAccessException("No se pudo leer la tabla de records \n"+e);
        }
    }

    @Override
    public List<Ficha> ordenarFichasxAtributos(String atributo) throws DataAccessException, ArgumentoInvalidoException {
        atributo=atributo.toLowerCase().trim();
        List<Ficha>organi=leerLista();
        switch(atributo){
            case "vida":
                organi.sort(Comparator.comparingInt(Ficha::getVidamax));
                break;
            case "daño":
                organi.sort(Comparator.comparingInt(Ficha::getDmg));
                break;
            case "tipo":
                organi.sort(Comparator.comparing(Ficha::getType));
        }
        return organi;
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
    public void escribir(String name, List<Ficha> f) throws DataAccessException, IOException, ArgumentoInvalidoException {
        String sql = """
        INSERT INTO roguechess.puntuacion_equipo (nombre_jugador,equipo,puntos,fecha) VALUES (?, ?, ?, CURRENT_TIMESTAMP);
                """;

        String equipo = "";
        try (
                PreparedStatement prep= con.prepareStatement(sql)
                ){
            equipo = "";
            for (Ficha ficha : f) {
                equipo=equipo+ficha.getType()+",";
            }
            int pts = (int)((Math.random()*1000+1));
            prep.setString(1,name);
            prep.setString(2,equipo);
            prep.setInt(3,pts);
            int filasAfectadas = prep.executeUpdate();
            if(filasAfectadas!=1){
                throw new DataAccessException(
                        "No se pudo escribir la ficha \n "+f
                );
            }
        }catch(PSQLException e) {
                System.out.println("================================================");
                System.out.println("Pulse 1 si quiere actualizar su record ");
                int op;
                        try{
                            op=sc.nextInt();
                        }catch (InputMismatchException ex){
                            op=0;
                        }
                if(op!=1&&op!=0){
                    System.out.println("no ha seleccionado ninguno de los dos");
                }else{
                    if(op==1){
                        actualizar(name,f, 1000);
                    }
                }
        } catch (SQLException e) {
            throw new DataAccessException("Error al leer la ficha \n " + e);
        }
    }

    @Override
    public void escribirLista(List<String> names, List<List<Ficha>> fichas) throws DataAccessException, IOException {
        if(names.size()!=fichas.size()) {
            throw new DistintaLongitudException("Las listas deben ser de la misma longitud");
        }
        int cont=0;
        for(List<Ficha> lista : fichas){
            try {
                escribir(names.get(cont),lista);
            } catch (ArgumentoInvalidoException e) {
                System.out.print("no se ha podido actualizar el jugador");
            }
            cont++;
        }
    }

    @Override
    public void actualizar(String jr, List<Ficha> f, int pts) throws DataAccessException, ArgumentoInvalidoException {

        String sql = """
                UPDATE roguechess.puntuacion_equipo
                SET equipo = ?
                    puntos = ?,
                    fecha = CURRENT_TIMESTAMP
                WHERE nombre_jugador = ? AND puntos >= ?;
                """;
        try(
                PreparedStatement pst = con.prepareStatement(sql)
            ){

            String equipo = "";
            for(Ficha ficha : f){
                equipo=equipo+ficha.getType()+",";
            }
            pst.setString(1,equipo);
            pst.setInt(2,pts);
            pst.setString(3,jr);
            pst.setInt(4,pts);
            int filasAfectadas = pst.executeUpdate();
            if(filasAfectadas==0){
                throw new ArgumentoInvalidoException("No se ha podido encontrar el jugador: "+jr+" con menos puntos que "+pts);
            }else{
                System.out.println("Todo ok Con actualizar");
            }

        }catch(SQLException e){
            throw new DataAccessException("Error al leer la tabla de records \n "+e);
        }
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
