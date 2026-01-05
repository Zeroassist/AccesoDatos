package org.example.Equipo;

import org.example.Piezas.*;

import java.util.ArrayList;
import java.util.List;

public class ReyPJ {
    private List<Ficha> subditos;
    private int tamaño_max=16;
    private int pts;
    public ReyPJ(int id) throws IllegalAccessException {
        switch(id){
            case 1://Rey básico, tiene un escudo que se recarga si descansas en hogeras
                subditos=new ArrayList<>(List.of(
                        new Rey("Rey",15,true,"E 1", 1, 1),
                        new Dama(20, true, "D 1", 60, 60),
                        new Alfil(10, true, "C 1", 40, 40),
                        new Alfil(10, true, "F 1", 40, 40),
                        new Caballo(15, true, "B 1", 60, 60),
                        new Caballo(15, true, "G 1", 60, 60),
                        new Torre(10, true, "H 1", 60, 60),
                        new Torre(10, true, "A 1", 60, 60),
                        new Peon(25, true, "A 2", 50, 50),
                        new Peon(25, true, "B 2", 50, 50),
                        new Peon(25, true, "C 2", 50, 50),
                        new Peon(25, true, "D 2", 50, 50),
                        new Peon(25, true, "E 2", 50, 50),
                        new Peon(25, true, "F 2", 50, 50),
                        new Peon(25, true, "G 2", 50, 50),
                        new Peon(25, true, "H 2", 50, 50)
                ));
                break;
            case 2://Rey vampiro que no tiene alfiles, tiene vampiros que curan al rey, pero sus fichas se curan(50%, peones 30%)
                subditos=new ArrayList<>(List.of(
                        new Rey("Dracula",15,true,"E 1", 25, 25),
                        new Dama(20, true, "D 1", 60, 60),
                        new Vampiro(15, true, "C 1", 60, 60),
                        new Vampiro(15, true, "F 1", 60, 60),
                        new Caballo(15, true, "B 1", 60, 60),
                        new Caballo(15, true, "G 1", 60, 60),
                        new Torre(10, true, "H 1", 60, 60),
                        new Torre(10, true, "A 1", 60, 60),
                        new Peon(25, true, "A 2", 50, 50),
                        new Peon(25, true, "B 2", 50, 50),
                        new Peon(25, true, "C 2", 50, 50),
                        new Peon(25, true, "D 2", 50, 50),
                        new Peon(25, true, "E 2", 50, 50),
                        new Peon(25, true, "F 2", 50, 50),
                        new Peon(25, true, "G 2", 50, 50),
                        new Peon(25, true, "H 2", 50, 50)
                ));
                break;
            case 3://El Rey no se mueve, puedes mover 2 fichas por turno
                subditos=new ArrayList<>(List.of(
                        new Rey("La Estatua",0,true,"E 1", 1, 1),
                        new Dama(20, true, "D 1", 60, 60),
                        new Alfil(10, true, "C 1", 40, 40),
                        new Alfil(10, true, "F 1", 40, 40),
                        new Caballo(15, true, "B 1", 60, 60),
                        new Caballo(15, true, "G 1", 60, 60),
                        new Torre(10, true, "H 1", 60, 60),
                        new Torre(10, true, "A 1", 60, 60),
                        new Peon(25, true, "A 2", 50, 50),
                        new Peon(25, true, "B 2", 50, 50),
                        new Peon(25, true, "C 2", 50, 50),
                        new Peon(25, true, "D 2", 50, 50),
                        new Peon(25, true, "E 2", 50, 50),
                        new Peon(25, true, "F 2", 50, 50),
                        new Peon(25, true, "G 2", 50, 50),
                        new Peon(25, true, "H 2", 50, 50)
                ));
                break;
            default:
                throw new IllegalAccessException("Tienes que elegir un rey");
        }
    }

}
