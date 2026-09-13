package monopoly.componentigioco.casella;

import monopoly.utilita.Costanti;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
/*
 metodi enum statici ma solo se chiamo una categoria
 se aggiungo a un metodo static posso utilizzarlo direttamente con solo il nome del enum.
 */


/**
 * <i>Enum utile per gestire i nomi, colori e tipi delle caselle</i><p>
 * Ogni istanza contiene un <i>array String</i> con i rispettivi nomi e un <i>int </i> corrispondente al colore. <p>
 */
public enum NomiCaselle {
    //notRandom
    TRENO(new LinkedList<>(Arrays.asList("Stazione Nord","Stazione Sud","Stazione Ovest","Stazione Est")),CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA),
    SINGOLE (new LinkedList<>(Arrays.asList("VIA","Parcheggio","Prigione","Vai in prigione")),CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA),
    //random
    MARRONE(new LinkedList<>(Arrays.asList("Vicolo Corto", "Vicolo Stretto")),172),
    AZZURO(new LinkedList<>(Arrays.asList("Bastioni Gran Sasso", "Viale Monterosa", "Viale Vesuvio")),32),
    ROSA(new LinkedList<>(Arrays.asList("Via Accademia", "Corso Ateneo", "Piazza Università")),200),
    GRIGIO(new LinkedList<>(Arrays.asList("Via Verdi", "Corso Raffaello", "Piazza Dante")),244),
    ROSSO(new LinkedList<>(Arrays.asList("Via Marco Polo", "Corso Magellano", "Largo Colombo")),9),
    GIALLO(new LinkedList<>(Arrays.asList("Viale Costantino", "Viale Traiano", "Piazza Giulio Cesare")),220),
    VERDE(new LinkedList<>(Arrays.asList("Via Roma", "Corso Impero", "Largo Augusto")),28),
    BLU(new LinkedList<>(Arrays.asList("Viale dei Giardini", "Parco della Vittoria")),21),
    TASSE(new LinkedList<>(Arrays.asList("Tassa di Lusso","Tassa Patrimoniale")),CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA),
    SOCIETA(new LinkedList<>(Arrays.asList("Società Acqua Potabile", "Società Elettrica")),CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA),
    IMPREVISTI(new LinkedList<>(Arrays.asList("Imprevisti 1", "Imprevisti 2", "Imprevisti 3")),CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA),
    PROBABILITA(new LinkedList<>(Arrays.asList("Probabilita 1", "Probabilita 2", "Probabilita 3")),CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA);

    private LinkedList<String> nomi; //usare peek

    private final String colore;
    private final int posizioneArrayCaselleGiocatore;
    private static int ultimaPosizioneArray;


    private static int calcoloUltimaPosizione(){
        NomiCaselle.ultimaPosizioneArray++;

        return NomiCaselle.ultimaPosizioneArray-1;
    }
    public static int getUltimaPosizione(){
        return NomiCaselle.ultimaPosizioneArray;
    }

    public String getColore(){
        return colore;
    }

    /**
     * Costruttore delle istanze del Enum si occupa di creare il codice ANSI per il colore.
     * @param nomi Linked list di nomi
     * @param colore numero corrispondente al colore con i codici ANSI
     */
    NomiCaselle(LinkedList<String>  nomi , int colore){
        this.nomi=nomi;
        this.colore= "\u001B[1;38;5;"+colore+"m"+Costanti.COLORE_SFONDO ; // bold;background/coloreScritta;? ; colore
        if (colore!= CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA) this.posizioneArrayCaselleGiocatore=NomiCaselle.calcoloUltimaPosizione();
        else this.posizioneArrayCaselleGiocatore=CostantiCaselle.COLORE_CASELLE_NON_PROPRIETA;
    }



    public String getNome(){

        Collections.shuffle(this.nomi);
        return getNome(1);
    }
    public String getNome(int posizione){
        String nome=this.nomi.peek();
        if (nome!=null)this.nomi.removeFirst();
        return nome;
    }

    public int getPosizioneArrayCaselleGiocatore() {
        return posizioneArrayCaselleGiocatore;
    }



}
