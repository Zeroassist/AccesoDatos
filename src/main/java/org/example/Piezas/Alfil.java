package org.example.Piezas;

public class Alfil extends Ficha {

    public Alfil() {
        super();
        setType("Alfil");
    }
    public Alfil(int id, int dmg, boolean owner, String pos, tipo type, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf(type), vida, vidamax);
        setType("Alfil");
    }
    public Alfil( int dmg, boolean owner, String pos,  int vida, int vidamax) {
        super(dmg, owner, pos,  vida, vidamax);
        setType("Alfil");
    }
}
