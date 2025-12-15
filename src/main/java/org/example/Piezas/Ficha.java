package org.example.Piezas;

import org.example.Clases.Excepciones.DataAccessException;

import java.util.InputMismatchException;

public abstract class Ficha {
    private int ID;
    private static int contadorID;
    private boolean owner;
    private int vidamax;
    private int vida;
    private int dmg;
    private String pos;
    private tipo type;

    //Contructor para llamar a la lectura y no generar nuevos id
    public Ficha(int id,int dmg, boolean owner, String pos, String type, int vida, int vidamax) {
        if(vida >vidamax){
            throw new InputMismatchException();
        }else{
            setDmg(dmg);
            setID(id);
            setOwner(owner);
            setPos(pos);
            setType(type);
            setVida(vida);
            setVidamax(vidamax);
        }
    }
    //Contructor para crear fichas nuevas y darles un id distinto para que el id no se repita aun que tengas la misma ficha
    public Ficha() {
        contadorID++;
        setID(contadorID);
    }

    public Ficha(int dmg, boolean owner, String pos, int vida, int vidamax) {
        contadorID++;
        if(vida >vidamax){
            throw new InputMismatchException();
        }else{
            setDmg(dmg);
            setID(contadorID);
            setOwner(owner);
            setPos(pos);
            setVida(vida);
            setVidamax(vidamax);
        }
    }

    public enum tipo{
        Alfil, Dama, Caballo, Torre, Peon, Rey, Vampiro
    }
    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        if (dmg <= 0) {
            this.dmg=0;
        }else{
            this.dmg = dmg;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ides) {
        this.ID=ides;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        int num =Integer.parseInt(pos.split(" ")[1]);
        char letra= pos.split(" ")[0].toUpperCase().charAt(0);
        if(pos.split(" ").length != 2){
            throw new InputMismatchException();
        }else if((num < 1 || num > 8)||!(letra>='A' && letra<='H')){
            throw new InputMismatchException();
        }else{
            this.pos=pos;
        }
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0) {
            throw new IllegalArgumentException();
        }else{
            this.vida=vida;
        }
    }

    public int getVidamax() {
        return vidamax;
    }

    public void setVidamax(int vidamaxim) {
        if (vidamaxim <= 0) {
            throw new InputMismatchException();
        }else{
            this.vidamax=vidamaxim;
        }
    }

    @Override
    public String toString() {
        return "Ficha{" +
                "dmg=" + dmg +
                ", ID=" + ID +
                ", owner=" + owner +
                ", vidamax=" + vidamax +
                ", vida=" + vida +
                ", pos='" + pos + '\'' +
                ", type=" + type +
                '}';
    }

    public void setType(String gars){
        switch (gars){
            case "Alfil":
                this.type=tipo.Alfil;
                break;
            case "Dama":
                this.type=tipo.Dama;
                break;
            case "Caballo":
                this.type=tipo.Caballo;
                break;
            case "Torre":
                this.type=tipo.Torre;
                break;
            case "Peon":
                this.type=tipo.Peon;
                break;
            case "Rey":
                this.type=tipo.Rey;
                break;
            case "Vampiro":
                this.type=tipo.Vampiro;
            default:
                throw new IllegalArgumentException();
        }

    }
    public String getType(){
        switch (type){
            case Peon:
                return "Peon";
            case Alfil:
                return "Alfil";
            case Caballo:
                return "Caballo";
            case Torre:
                return "Torre";
            case Dama:
                return "Dama";
            case Rey:
                return "Rey";
            case Vampiro:
                return "Vampiro";
            default:
                throw new DataAccessException();
        }
    }

}
