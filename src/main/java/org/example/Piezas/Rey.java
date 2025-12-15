package org.example.Piezas;

public class Rey extends Ficha{
    private String name;

    public Rey(String name) {
        super();
        setType("Rey");
    }
    public Rey( String name, int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(dmg, owner, pos, vida, vidamax);
        setType("Rey");
    }
    public Rey(String name, int id, int dmg, boolean owner, String pos, int vida, int vidamax) {
        super(id, dmg, owner, pos, String.valueOf("Rey"), vida, vidamax);
        setType("Rey");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name!=null && !name.isEmpty()){
            this.name=name;
        }else{
            throw new IllegalArgumentException("El nombre no puede ser nulo");
        }
    }
}
