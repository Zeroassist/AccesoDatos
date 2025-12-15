package org.example.Piezas;

public class Vampiro extends Ficha{

    public Vampiro() {
        super();
        setType("Vampiro");
    }
    public Vampiro(int id, int dmg, boolean owner, String pos, tipo type, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf(type), vida, vidamax);
        setType("Vampiro");
    }
    public Vampiro( int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(dmg, owner, pos, vida, vidamax);
        setType("Vampiro");
    }
}

