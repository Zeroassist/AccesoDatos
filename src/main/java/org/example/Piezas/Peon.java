package org.example.Piezas;

public class Peon extends Ficha{

    public Peon() {
        super();
        setType("Peon");
    }
    public Peon(int id, int dmg, boolean owner, String pos, tipo type, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf(type), vida, vidamax);
        setType("Peon");
    }
    public Peon( int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(dmg, owner, pos, vida, vidamax);
        setType("Peon");
    }
}
