package monopoly.componentigioco.giocatore.funzionalita;

import monopoly.Coordinate;
import monopoly.componentigioco.casella.Casella;


public class MovimentoGiocatore  implements MovimentoGiocatoreSupporto {
    /**
     * Coordinate giocatore
     */
    private Coordinate coordinate;
    /**
     * Limite in x del movimento
     */
    private final int xMax;
    /**
     * Limite in y del movimento
     */
    private final int yMax;

    /**
     * Costruttore che setta i limiti di movimento e le coordinate iniziali
     * @param xMax limite x
     * @param yMax limite y
     */
    public MovimentoGiocatore(int xMax,int yMax){
        this.xMax=xMax;
        this.yMax=yMax;
        this.coordinate=new Coordinate(yMax,xMax);
    }
    @Override
    public int getYMax() {
        return yMax;
    }


    /**
     * <p>Metodo più importante per il movimento del giocatore.</p>
     * <p>Attraverso il metodo {@link #controlloPosizione(int prodotto, int nuovaCoordinataY, int nuovaCoordinataX)} controlla se il giocatore
     * si trova nelle caselle dove il giocatore deve "salire o scendere".</p>
     * @return Vero se dopo l'aggiornamento delle Coordinate è stato completato un giro.
     * @see  #movimentoOrizzontale(int, int)
     * @see  #movimentoVerticale(int, int)
     * @see #isGiroCompleto()
     */
    public boolean cambioCoordinate(){ // boolean cosi so se ha fatto un giro
        int nuovaCoordinataX = this.coordinate.getX();
        int nuovaCoordinataY= this.coordinate.getY();
        int prodotto = nuovaCoordinataY*nuovaCoordinataX;

        int movimento = 1; // destraSotto = 1 // sinistraSopra = -1
        if (controlloPosizione(prodotto,nuovaCoordinataY,nuovaCoordinataX) ){
            if (nuovaCoordinataY==this.yMax){
                movimento*=-1;
            }
            movimentoOrizzontale(movimento,nuovaCoordinataX);
        } else{
            if (nuovaCoordinataX!=this.xMax){
                movimento*=-1;
            }
            movimentoVerticale(movimento,nuovaCoordinataY);
        }
        return isGiroCompleto();
    }

    /**
     *
     * @param prodotto prodotto coordinate
     * @param coordinataY ultimaCoordinata Y
     * @param coordinataX ultimaCoordinata X
     * @return {@code False} se la posizione del giocatore è in una delle caselle dove dobbiamo salire o scendere
     */
    private boolean controlloPosizione(int prodotto , int coordinataY, int coordinataX){
        return (prodotto!=0 && coordinataY==this.yMax) || (coordinataY==0 && coordinataX!=this.xMax);
    }

    /**
     *
     * @return {@code True} se si è completato un giro
     */
    private boolean isGiroCompleto(){
        return this.coordinate.getX() == this.xMax && this.coordinate.getY() == this.yMax;
    }

    /**
     * Metodo che cambia la coordinata x del giocatore.
     * @param movimento sinistra -1 destra 1
     * @param x coordinata x vecchia
     */
    private void movimentoOrizzontale(int movimento,int x){
        x+=movimento;
        this.coordinate.setX(x);
    }
    /**
     * Metodo che cambia la coordinata y del giocatore.
     * @param movimento sopra -1, sotto 1
     * @param y coordinata y vecchia
     */
    private void movimentoVerticale(int movimento,int y){ // sopra -1, sotto 1
        y+=movimento;
        this.coordinate.setY(y);
    }

    @Override
    public int getX() {
        return this.coordinate.getX();
    }
    @Override
    public int getY() {
        return this.coordinate.getY();
    }

    @Override
    public void spostaGiocatore(Coordinate coordinate) {
        this.coordinate.setY(coordinate.getY());
        this.coordinate.setX(coordinate.getX());
    }

}
