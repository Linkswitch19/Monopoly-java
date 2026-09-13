package monopoly.componentigioco.casella;

import monopoly.Coordinate;

import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;

import java.util.LinkedList;
import java.util.Random;

public abstract class Casella implements CasellaInterface {

    private int pedaggio = 0;
    private String colore;
    private String nome;
    private Coordinate coordinate;
    private LinkedList<String> giocatoriPresenti = new LinkedList<>();

    public LinkedList<String>
    getGiocatoriPresenti() {
        return this.giocatoriPresenti;
    }
    @Override
    public void setPedaggioDefault() {
        Random random = new Random();
        this.pedaggio = random.nextInt(Costanti.IMPORTO_PEDAGGIO_MIN,Costanti.IMPORTO_PEDAGGIO_MAX+1);
    }

    protected Casella(){ // assi
        setNomeColoreDefault();
        setPedaggioDefault();
    }

    protected void setCoordinate(Coordinate coordinate) {
        this.coordinate=coordinate;
    }

    public void aggiungiCarattere(String ch) {
        giocatoriPresenti.addFirst(ch);
    }
    public void togliCarattere(){
        giocatoriPresenti.removeLast();
    }


    public String casellaString(int d){
        return getColore() +
                dettagliCasella(d) +
                Costanti.ANSI_RESET;
    }

    public static boolean checkForNull(Casella casella){
        return casella==null;
    }

    public String dettagliCasella(int d) {
        StringBuilder dettagli=new StringBuilder();
        String spazio = " ";
        String trattino = "-";
        String righette=trattino.repeat((Costanti.LARGHEZZA_CASELLA));
        dettagli.append(spazio);

        boolean PrimaUltimaRiga=d>0 && d<Costanti.RIGHE_CASELLA-1;
        if (PrimaUltimaRiga) dettagli.append("|");

        switch (d) {
            case 0: case 6:
                dettagli.append(righette);
                dettagli.append(spazio.repeat(1));
                break;
            case 1:
                String primaRiga = this.nome; // Per sapere quanti spazi aggiungere
                int nSpazi=Costanti.LARGHEZZA_CASELLA-2- primaRiga.length();
                dettagli.append(primaRiga).append(spazio.repeat(nSpazi ));
                break;
            case 2:
                String secondaRiga = infoCasella();
                dettagli.append(secondaRiga).append(CostantiCaselle.SPAZIO.repeat(((Costanti.LARGHEZZA_CASELLA - 2) - secondaRiga.length())));
                break;
            case 3, 4:
                StringBuilder terzaRiga = new StringBuilder();
                dettagli.append(terzaRiga).append(spazio.repeat(((Costanti.LARGHEZZA_CASELLA - 2) - terzaRiga.length())));
                break;
            case 5: // proviamo a dividere i giocatori in prigione
                StringBuilder quintaRiga = new StringBuilder();
                int spaziDaFare = Costanti.LARGHEZZA_CASELLA-(Costanti.LARGHEZZA_CASELLA-(2*giocatoriPresenti.size()));

                for (String giocatore : giocatoriPresenti) {
                        if (!(giocatore.isBlank()))
                            quintaRiga.append(giocatore).append(Costanti.COLORE_SFONDO).append(" ");
                }

                dettagli.append(quintaRiga).append(spazio.repeat((Costanti.LARGHEZZA_CASELLA - 2) - spaziDaFare));
                dettagli.append(getColore());
                break;
        }
        if (PrimaUltimaRiga) dettagli.append("|").append(spazio); // if in una sola riga // spazio per avere tutte le caselle separate come sopra
        return  dettagli.toString();
    }

    public String getNome(){
        return this.nome;
    }
    public  int getPedaggio(){
        return this.pedaggio;
    }
    String getColore(){
        return this.colore;
    }

    @Override
    public void setNomeColoreDefault(){
        String[] nomeColore = NomiHelper.sceltaNomeColore();
        this.nome=nomeColore[0];
        this.colore=nomeColore[1];
    }

    protected void setNomeColore(String nome , String colore){
        this.nome=nome;
        this.colore=colore;
    }
    @Override
    public String infoCasella() {
        return "Paga " + Math.abs(this.pedaggio);
    }

    private void pagamento(Giocatore giocatorePagante){
        giocatorePagante.pagamentoPedaggio(this);
    }

    public void azioneCasella(Giocatore giocatoreCorrente){
       pagamento(giocatoreCorrente);
    }

    public boolean isGiocatore(Giocatore giocatoreCorrente){
        return  giocatoreCorrente!=null;
    }

    public void setPedaggio(int pedaggio) {
        this.pedaggio = pedaggio;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }


    protected void setCasella(Casella [][] caselle){
        int[] coordinate = NomiHelper.calcoloCoordinate(caselle);
        setCoordinate(new Coordinate(coordinate[0],coordinate[0]));
        caselle[coordinate[0]][coordinate[1]]=this;
    }



}