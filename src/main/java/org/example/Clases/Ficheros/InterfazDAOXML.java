package org.example.Clases.Ficheros;
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.example.Piezas.Ficha;
import org.example.Piezas.Rey;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.w3c.dom.*;
public class InterfazDAOXML implements InterfazDAO {

    @Override
    public List<Rey> listarReyesNoUsados(File archivoGuardado) {
        List<Rey> reyesSinUsar = new ArrayList<>();
        try {
            if (!archivoGuardado.exists()) throw new FileNotFoundException("Archivo no encontrado");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivoGuardado);
            NodeList fichas = doc.getElementsByTagName("ficha");

            for (int i = 0; i < fichas.getLength(); i++) {
                Element ficha = (Element) fichas.item(i);
                String tipo = ficha.getElementsByTagName("tipo").item(0).getTextContent();
                if (tipo.equalsIgnoreCase("rey")) {
                    boolean usado = Boolean.parseBoolean(ficha.getElementsByTagName("usado").item(0).getTextContent());
                    if (!usado) {
                        int id = Integer.parseInt(ficha.getElementsByTagName("ID").item(0).getTextContent());
                        int dmg = Integer.parseInt(ficha.getElementsByTagName("dmg").item(0).getTextContent());
                        String pos = ficha.getElementsByTagName("pos").item(0).getTextContent();
                        int vida = Integer.parseInt(ficha.getElementsByTagName("vidaActual").item(0).getTextContent());
                        int vidamax = Integer.parseInt(ficha.getElementsByTagName("vidaMax").item(0).getTextContent());

                        Rey rey = new Rey(id, dmg, usado, pos, vida, vidamax);
                        reyesSinUsar.add(rey);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error mostrado los reyes", e);
        }
        return reyesSinUsar;
    }

    @Override
    public List<Ficha> ordenarFichasxAtributos(String atributo, File archivoGuardado) {
        try {
            List<Ficha> fichas = leerLista(archivoGuardado);

            Comparator<Ficha> comparador;
            switch (atributo.toLowerCase()) {
                case "id" -> comparador = Comparator.comparing(Ficha::getID);
                case "vida" -> comparador = Comparator.comparing(Ficha::getVida);
                case "vidamax" -> comparador = Comparator.comparing(Ficha::getVidamax);
                case "dmg" -> comparador = Comparator.comparing(Ficha::getDmg);
                case "pos" -> comparador = Comparator.comparing(Ficha::getPos);
                case "tipo" -> comparador = Comparator.comparing(ficha -> ficha.getType().toString());
                default -> throw new IllegalArgumentException("Atributo no válido: " + atributo);
            }

            return fichas.stream().sorted(comparador).collect(Collectors.toList());
        } catch (Exception e) {

//Cambiar excepcion
            throw new RuntimeException("Error al ordenar las fichas", e);
        }
    }

    @Override
    public Ficha leer(int id, File archivoGuardado) {
        try {
            List<Ficha> fichas = leerLista(archivoGuardado);
            for (Ficha f : fichas) {
                if (f.getID() == id) return f;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al leer la ficha", e);
        }
        return null;
    }

    @Override
    public List<Ficha> leerLista(File archivoGuardado) {
        List<Ficha> fichas = new ArrayList<>();
        try {
            if (!archivoGuardado.exists()) throw new FileNotFoundException("Archivo no encontrado");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(archivoGuardado);
            NodeList fichasLista = doc.getElementsByTagName("ficha");

            for (int i = 0; i < fichasLista.getLength(); i++) {
                Element e = (Element) fichasLista.item(i);

                int id = Integer.parseInt(e.getElementsByTagName("ID").item(0).getTextContent());
                int dmg = Integer.parseInt(e.getElementsByTagName("dmg").item(0).getTextContent());
                boolean owner = Boolean.parseBoolean(e.getElementsByTagName("owner").item(0).getTextContent());
                String pos = e.getElementsByTagName("pos").item(0).getTextContent();
                int vida = Integer.parseInt(e.getElementsByTagName("vidaActual").item(0).getTextContent());
                int vidamax = Integer.parseInt(e.getElementsByTagName("vidaMax").item(0).getTextContent());
                String tipoStr = e.getElementsByTagName("tipo").item(0).getTextContent();
                tipoStr = tipoStr.substring(0,1).toUpperCase() + tipoStr.substring(1).toLowerCase();

                if (tipoStr.equalsIgnoreCase("Rey")) {
                    fichas.add(new Rey(id, dmg, owner, pos, vida, vidamax));
                } else {
                    fichas.add(new Ficha(id, dmg, owner, pos, tipoStr, vida, vidamax) {});
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo la lista de fichas", e);
        }
        return fichas;
    }

    @Override
    public void escribir(File archivoGuardado, Ficha f) throws FileNotFoundException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            Element root;

            if (archivoGuardado.exists()) {
                doc = builder.parse(archivoGuardado);
                root = doc.getDocumentElement();
            } else {
                doc = builder.newDocument();
                root = doc.createElement("fichas");
                doc.appendChild(root);
            }

            Element fichaElem = doc.createElement("ficha");
            Element id = doc.createElement("ID");
            id.setTextContent(String.valueOf(f.getID()));
            fichaElem.appendChild(id);

            Element dmg = doc.createElement("dmg");
            dmg.setTextContent(String.valueOf(f.getDmg()));
            fichaElem.appendChild(dmg);

            Element owner = doc.createElement("owner");
            owner.setTextContent(String.valueOf(f.isOwner()));
            fichaElem.appendChild(owner);

            Element pos = doc.createElement("pos");
            pos.setTextContent(f.getPos());
            fichaElem.appendChild(pos);

            Element vida = doc.createElement("vidaActual");
            vida.setTextContent(String.valueOf(f.getVida()));
            fichaElem.appendChild(vida);

            Element vidamax = doc.createElement("vidaMax");
            vidamax.setTextContent(String.valueOf(f.getVidamax()));
            fichaElem.appendChild(vidamax);

            Element tipo = doc.createElement("tipo");
            tipo.setTextContent(f.getType().toString());
            fichaElem.appendChild(tipo);

            root.appendChild(fichaElem);

            //Innecesarias, solo esteticas-----------------------------------------------
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(archivoGuardado));

        } catch (Exception e) {
            throw new RuntimeException("Error escribiendo la ficha", e);
        }
    }

    @Override
    public void escribirLista(File archivoGuardado, List<Ficha> piezas) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element root = doc.createElement("fichas");
            doc.appendChild(root);

            for (Ficha f : piezas) {

                Element fichaElem = doc.createElement("ficha");

                if (f instanceof Rey) {
                    Element id = doc.createElement("ID");
                    id.setTextContent(String.valueOf(f.getID()));
                    fichaElem.appendChild(id);

                    Element dmg = doc.createElement("dmg");
                    dmg.setTextContent(String.valueOf(f.getDmg()));
                    fichaElem.appendChild(dmg);

                    Element owner = doc.createElement("owner");
                    owner.setTextContent(String.valueOf(f.isOwner()));
                    fichaElem.appendChild(owner);

                    Element usado = doc.createElement("usado");
                    usado.setTextContent(String.valueOf(((Rey) f).isUsado()));
                    fichaElem.appendChild(usado);

                    Element pos = doc.createElement("pos");
                    pos.setTextContent(f.getPos());
                    fichaElem.appendChild(pos);

                    Element vida = doc.createElement("vidaActual");
                    vida.setTextContent(String.valueOf(f.getVida()));
                    fichaElem.appendChild(vida);

                    Element vidamax = doc.createElement("vidaMax");
                    vidamax.setTextContent(String.valueOf(f.getVidamax()));
                    fichaElem.appendChild(vidamax);

                    Element tipo = doc.createElement("tipo");
                    tipo.setTextContent(f.getType().toString());
                    fichaElem.appendChild(tipo);

                    root.appendChild(fichaElem);

                } else {
                    Element id = doc.createElement("ID");
                    id.setTextContent(String.valueOf(f.getID()));
                    fichaElem.appendChild(id);

                    Element dmg = doc.createElement("dmg");
                    dmg.setTextContent(String.valueOf(f.getDmg()));
                    fichaElem.appendChild(dmg);

                    Element owner = doc.createElement("owner");
                    owner.setTextContent(String.valueOf(f.isOwner()));
                    fichaElem.appendChild(owner);

                    Element pos = doc.createElement("pos");
                    pos.setTextContent(f.getPos());
                    fichaElem.appendChild(pos);

                    Element vida = doc.createElement("vidaActual");
                    vida.setTextContent(String.valueOf(f.getVida()));
                    fichaElem.appendChild(vida);

                    Element vidamax = doc.createElement("vidaMax");
                    vidamax.setTextContent(String.valueOf(f.getVidamax()));
                    fichaElem.appendChild(vidamax);

                    Element tipo = doc.createElement("tipo");
                    tipo.setTextContent(f.getType().toString());
                    fichaElem.appendChild(tipo);

                    root.appendChild(fichaElem);
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(archivoGuardado));
        } catch (Exception e) {
            throw new RuntimeException("Error escribiendo la lista de fichas", e);
        }
    }

    @Override
    public void actualizar(List<Ficha> f, File archivoGuardado) {
        List<Ficha> fichas = leerLista(archivoGuardado);
        try {
            for (Ficha fich : f) {
                fichas.removeIf(fi -> fi.getID() == fich.getID());
                fichas.add(fich);
            }
            escribirLista(archivoGuardado, fichas);
        } catch (Exception e) {
            throw new RuntimeException("La ficha no existe");
        }
    }

    @Override
    public void eliminar(File archivoGuardado, Ficha fic) {
        List<Ficha> fichas = leerLista(archivoGuardado);
        try {
            fichas.removeIf(Ficha -> Ficha.getID() == fic.getID());
            escribirLista(archivoGuardado, fichas);
        } catch (Exception e) {
            throw new RuntimeException("La ficha no existe");
        }
    }
}
*/