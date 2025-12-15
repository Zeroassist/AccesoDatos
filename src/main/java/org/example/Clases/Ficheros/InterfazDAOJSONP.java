package org.example.Clases.Ficheros;
/*
import jakarta.json.*;
import jakarta.json.stream.JsonGenerator;
import org.example.Clases.Excepciones.DataAccessException;
import org.example.Piezas.*;

import java.io.*;
import java.util.*;

public class InterfazDAOJSONP implements InterfazDAO {

    @Override
    public List<Rey> listarReyesNoUsados(File archivoGuardado) throws FileNotFoundException {
        // Leemos todas las fichas del archivo
        List<Ficha> fichero = leerLista(archivoGuardado);
        System.out.println(fichero);
        List<Rey> r = new ArrayList<>();
        // Recorremos las fichas y filtramos solo los reyes no usados
        for (Ficha f : fichero)
            if (f instanceof Rey rey) {
                Rey temp = (Rey) rey;
                //Rey temp = new Rey(rey.getID(), rey.getDmg(), rey.isOwner(), rey.getPos(), rey.getVida(), rey.getVidamax(), rey.isUsado());
                if (!temp.isUsado()) {
                    r.add(temp);
                }
            }
        return r;
    }

    @Override
    public List<Ficha> ordenarFichasxAtributos(String atributo, File archivoGuardado) throws FileNotFoundException {
        // Leemos todas las fichas del archivo
        List<Ficha> listaorden = leerLista(archivoGuardado);
        // Creamos comparadores según el atributo
        Comparator<Ficha> compID = Comparator.comparing(p -> p.getID());
        Comparator<Ficha> compDMG = Comparator.comparing(p -> p.getDmg());
        Comparator<Ficha> compHP = Comparator.comparing(p -> p.getVida());
        Comparator<Ficha> compMXHP = Comparator.comparing(p -> p.getVidamax());
        Comparator<Ficha> compType = Comparator.comparing(p -> p.getType());
        // Dependiendo del atributo elegido, ordenamos la lista
        switch (atributo.toLowerCase()) {
            case "id":
                Collections.sort(listaorden, compID);
                break;
            case "daño":
                Collections.sort(listaorden, compDMG);
                break;
            case "vida":
                Collections.sort(listaorden, compHP);
                break;
            case "vidamax":
                Collections.sort(listaorden, compMXHP);
                break;
            case "tipo":
                Collections.sort(listaorden, compType);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return listaorden;
    }

    @Override
    public Ficha leer(int id, File archivoGuardado) throws FileNotFoundException {
        // Leemos todas las fichas del archivo y buscamos por ID
        List<Ficha> fichas = leerLista(archivoGuardado);
        for (Ficha f : fichas) {
            if (f.getID() == id) {
                return f;
            }
        }
        return null; // si no se encuentra, devolvemos null
    }

    @Override
    public List<Ficha> leerLista(File archivoGuardado) throws FileNotFoundException {
        // Lista donde guardaremos todas las fichas leídas del JSON
        List<Ficha> fichas = new ArrayList<>();
        Ficha f = null;
        try (FileReader fileReader = new FileReader(archivoGuardado);
             JsonReader jsonReader = Json.createReader(fileReader)) {

            var jsonArray = jsonReader.readArray();
            // Recorremos el array de objetos JSON
            for (JsonValue jsonValue : jsonArray) {
                JsonObject jsonFicha = jsonValue.asJsonObject();

                // Según el tipo, creamos la ficha correspondiente
                switch (jsonFicha.getString("Tipo")) {
                    case "Peon":
                        f = new Peon(
                                jsonFicha.getInt("id"),
                                jsonFicha.getInt("Daño"),
                                jsonFicha.getBoolean("Owner"),
                                jsonFicha.getString("Posicion"),
                                Ficha.tipo.Peon,
                                jsonFicha.getInt("Vida"),
                                jsonFicha.getInt("VidaMax")
                        );
                        break;
                    case "Caballo":
                        f = new Caballo(
                                jsonFicha.getInt("id"),
                                jsonFicha.getInt("Daño"),
                                jsonFicha.getBoolean("Owner"),
                                jsonFicha.getString("Posicion"),
                                Ficha.tipo.Caballo,
                                jsonFicha.getInt("Vida"),
                                jsonFicha.getInt("VidaMax")
                        );
                        break;
                    case "Alfil":
                        f = new Alfil(
                                jsonFicha.getInt("id"),
                                jsonFicha.getInt("Daño"),
                                jsonFicha.getBoolean("Owner"),
                                jsonFicha.getString("Posicion"),
                                Ficha.tipo.Alfil,
                                jsonFicha.getInt("Vida"),
                                jsonFicha.getInt("VidaMax")
                        );
                        break;
                    case "Torre":
                        f = new Torre(
                                jsonFicha.getInt("id"),
                                jsonFicha.getInt("Daño"),
                                jsonFicha.getBoolean("Owner"),
                                jsonFicha.getString("Posicion"),
                                Ficha.tipo.Torre,
                                jsonFicha.getInt("Vida"),
                                jsonFicha.getInt("VidaMax")
                        );
                        break;
                    case "Dama":
                        f = new Dama(
                                jsonFicha.getInt("id"),
                                jsonFicha.getInt("Daño"),
                                jsonFicha.getBoolean("Owner"),
                                jsonFicha.getString("Posicion"),
                                Ficha.tipo.Dama,
                                jsonFicha.getInt("Vida"),
                                jsonFicha.getInt("VidaMax")
                        );
                        break;
                    case "Rey":
                        f = new Rey(
                                jsonFicha.getInt("id"),
                                jsonFicha.getInt("Daño"),
                                jsonFicha.getBoolean("Owner"),
                                jsonFicha.getString("Posicion"),
                                jsonFicha.getInt("Vida"),
                                jsonFicha.getInt("VidaMax"),
                                jsonFicha.getBoolean("Usado")
                        );
                        break;
                }
                // Añadimos la ficha a la lista
                fichas.add(f);
            }
        } catch (IOException e) {
            throw new DataAccessException(e);
        }
        return fichas;
    }

    @Override
    public void escribir(File archivoGuardado, Ficha f) throws IOException {
        // Leemos el contenido actual (si existe) y añadimos la nueva ficha
        List<Ficha> ficheroExistente = new ArrayList<>();
        if (archivoGuardado.exists() && archivoGuardado.length() > 0) {
            ficheroExistente = leerLista(archivoGuardado);
        }
        ficheroExistente.add(f);
        // Reescribimos el archivo completo con todas las fichas
        escribirLista(archivoGuardado, ficheroExistente);
    }

    @Override
    public void escribirLista(File archivoGuardado, List<Ficha> piezas) throws FileNotFoundException {
        // Leemos el archivo existente para evitar duplicados
        List<Ficha> fichero = leerLista(archivoGuardado);
        List<Ficha> listaUnicos = new ArrayList<>();
        Set<Integer> idsVistos = new HashSet<>();

        // Recorremos la lista nueva y agregamos fichas únicas
        for (Ficha p : piezas) {
            if (!idsVistos.contains(p.getID())) {
                listaUnicos.add(p);
                idsVistos.add(p.getID());
            }
        }

        // Recorremos la lista antigua para conservar las que no se repiten
        for (Ficha f : fichero) {
            if (!idsVistos.contains(f.getID())) {
                listaUnicos.add(f);
                idsVistos.add(f.getID());
            }
        }

        // Creamos el array JSON con todas las fichas
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Ficha f : listaUnicos) {
            if (f instanceof Rey) {
                jsonArrayBuilder.add(escrbirrey((Rey) f));
            } else {
                JsonObject jsonFicha = Json.createObjectBuilder()
                        .add("id", f.getID())
                        .add("Daño", f.getDmg())
                        .add("Owner", f.isOwner())
                        .add("Posicion", f.getPos())
                        .add("Vida", f.getVida())
                        .add("VidaMax", f.getVidamax())
                        .add("Tipo", f.getType())
                        .build();
                jsonArrayBuilder.add(jsonFicha);
            }
        }

        // Configuramos el pretty printing para guardar bonito
        Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);

        // Borramos el archivo antiguo y escribimos el nuevo
        eliminarfich(archivoGuardado);
        try (FileWriter fileWriter = new FileWriter(archivoGuardado);
            JsonWriter jsonWriter = writerFactory.createWriter(fileWriter)) {
            jsonWriter.writeArray(jsonArrayBuilder.build());
            System.out.println("Se han escrito las piezas en el archivo " + archivoGuardado);
        } catch (IOException e) {
            throw new DataAccessException(e);
        }
    }

    private JsonObject escrbirrey(Rey f) {
        // Crea el objeto JSON específico para un Rey, incluyendo su campo 'usado'
        JsonObject jsonRey = Json.createObjectBuilder()
                .add("id", f.getID())
                .add("Daño", f.getDmg())
                .add("Owner", f.isOwner())
                .add("Posicion", f.getPos())
                .add("Vida", f.getVida())
                .add("VidaMax", f.getVidamax())
                .add("Tipo", f.getType())
                .add("Usado", f.isUsado())
                .build();
        return jsonRey;
    }

    @Override
    public void actualizar(List<Ficha> nuevas, File archivoGuardado) throws FileNotFoundException {
        // Leemos las fichas actuales del archivo
        List<Ficha> fichasExistentes = leerLista(archivoGuardado);

        // Recorremos la lista nueva para actualizar por ID
        for (Ficha nueva : nuevas) {
            boolean reemplazada = false;
            for (int i = 0; i < fichasExistentes.size(); i++) {
                if (fichasExistentes.get(i).getID() == nueva.getID()) {
                    // Si es un Rey, conservamos su valor 'usado'
                    if (fichasExistentes.get(i) instanceof Rey && nueva instanceof Rey rNueva) {
                        ((Rey) rNueva).setUsado(((Rey) fichasExistentes.get(i)).isUsado());
                    }
                    fichasExistentes.set(i, nueva);
                }
            }
            // Si no existía, se añade
            if (!reemplazada) {
                fichasExistentes.add(nueva);
            }
        }
        // Reescribimos todo el archivo actualizado
        escribirLista(archivoGuardado, fichasExistentes);
    }

    @Override
    public void eliminar(File archivoGuardado, Ficha fic) throws FileNotFoundException {
        // Si el archivo existe y está vacío, lo borramos
        if (archivoGuardado.isFile()) {
            if (archivoGuardado.length() == 0) {
                archivoGuardado.delete();
            }
        } else {
            // Si tiene contenido, filtramos y eliminamos por ID
            List<Ficha> fichasfich = leerLista(archivoGuardado);
            List<Ficha> nuevasfichas = new ArrayList<>();
            Iterator<Ficha> it = fichasfich.iterator();
            while (it.hasNext()) {
                Ficha f = it.next();
                if (f.getID() == fic.getID()) {
                    it.remove();
                } else {
                    nuevasfichas.add(f);
                }
            }
            // Guardamos la nueva lista sin la ficha eliminada
            escribirLista(archivoGuardado, nuevasfichas);
        }
    }

    public void eliminarfich(File archivoGuardado) {
        // Borra completamente el archivo si existe
        if (archivoGuardado.isFile()) {
            if (archivoGuardado.delete()) {
                System.out.println("--------------------------------------------------");
                System.out.println("Borrado con exito");
            }
        }
    }
}
*/