package org.example.Piezas;

public class Dama extends Ficha {

    public Dama() {
        super();
        setType("Dama");
    }
    public Dama(int id, int dmg, boolean owner, String pos, tipo type, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf(type), vida, vidamax);
        setType("Dama");
    }
    public Dama( int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(dmg, owner, pos,  vida, vidamax);
        setType("Dama");
    }
}
