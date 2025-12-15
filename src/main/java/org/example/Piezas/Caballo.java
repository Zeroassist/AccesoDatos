package org.example.Piezas;

public class Caballo extends Ficha {

    public Caballo() {
        super();
        setType("Caballo");
    }

    public Caballo(int id, int dmg, boolean owner, String pos, tipo type, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf(type), vida, vidamax);
        setType("Caballo");
    }
    public Caballo( int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(dmg, owner, pos,  vida, vidamax);
        setType("Caballo");
    }
}
