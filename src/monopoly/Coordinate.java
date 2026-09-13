package monopoly;

import monopoly.componentigioco.Tabellone;
import monopoly.utilita.Costanti;

public class Coordinate  {
    private int x; // casella
    private int y; // riga

    public Coordinate(int y, int x) {
        this.x = controlloX(x) ? Costanti.CASELLE_PER_RIGA : x;
        this.y = controlloY(y) ? Costanti.RIGHE : y;
    }
    private boolean controlloY (int y){
        return (y > Costanti.RIGHE || y<0 );
    }
    private boolean controlloX (int x){
        return (x > Costanti.CASELLE_PER_RIGA || x<0);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        if (controlloX(x)){
            System.out.println("Coordinata X non valida");
            return;
        }
        this.x = x;
    }

    public void setY(int y) {
        if (controlloY(y)){
            System.out.println("Coordinata Y non valida");
            return;
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return Tabellone.getCasella(x,y).getNome();
    }


}
