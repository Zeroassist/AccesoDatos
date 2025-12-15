package org.example.Piezas;

public class Torre extends Ficha{

    public Torre() {
        super();
        setType("Torre");
    }
    public Torre(int id, int dmg, boolean owner, String pos, tipo type, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf(type), vida, vidamax);
        setType("Torre");
    }
    public Torre( int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(dmg, owner, pos,  vida, vidamax);
        setType("Torre");
    }
}
