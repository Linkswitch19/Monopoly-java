package monopoly.componentigioco.casella;

import monopoly.componentigioco.Tabellone;
import monopoly.utilita.Costanti;


import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 * {@link #caselleNotRandom} numero di tipo di caselle con posizione fisse. <p>

 * <i>Questa classe serve da supporto alla classe {@link Casella} e al Enum {@link NomiCaselle} per la scelta dei nomi</i>
 */
public abstract class  NomiHelper {

    private static final int caselleNotRandom = 2; // dato che nel enum NomiCaselle non serve mischiare Treno e singole


    private static LinkedList<NomiCaselle> nomiCaselleList=new LinkedList<>(Arrays.asList(NomiCaselle.values()).subList(caselleNotRandom,NomiCaselle.values().length-4));

    private static boolean fakeCaselle=false;


    public static int nArrayUltimoColore(){
        NomiCaselle categoriaCasella =nomiCaselleList.getFirst();
        return categoriaCasella.getPosizioneArrayCaselleGiocatore();
    }



    protected static  String[] sceltaNomeColore(){
        Collections.shuffle(nomiCaselleList);
        if (NomiHelper.nomiCaselleList.isEmpty()) return casellaSconosciuta();
        return sceltaRandom();
    }

    /**
     * Metodo per continuare a creare caselle {@link Proprieta} in assenza di nomi
     * @return String[] contenete il nome {@code ????}  e il colore corrispondente alla prima categoria del enum {@link NomiCaselle}.
     */
    private static String[] casellaSconosciuta(){
        fakeCaselle=true;
        return  new String[]{"????", NomiCaselle.values()[0].getColore()};
    }
    static boolean getFakeCaselle(){
        return fakeCaselle;
    }


    private static String[] sceltaRandom(){
        String[] nomeColore = new String[2];
        nomeColore[0]= controlloNomiRimasti();
        if (nomeColore[0]==null)return  casellaSconosciuta();
        nomeColore[1]= NomiHelper.nomiCaselleList.getFirst().getColore();
        return nomeColore;
    }




    private static String controlloNomiRimasti(){
        NomiCaselle tipo=NomiHelper.nomiCaselleList.peek();

        String nome=null;
        if (tipo!=null) nome=tipo.getNome();


        while (nome==null){

            if (NomiHelper.nomiCaselleList.isEmpty()) return null;
            NomiHelper.nomiCaselleList.removeFirst();
            nome=controlloNomiRimasti();

        }
        return nome;

    }



    public static int[] calcoloCoordinate(Casella [][]caselle){
        int y,x;
        do {
            Random random= new Random();
            y= random.nextInt(Costanti.RIGHE);
            x= random.nextInt(Costanti.CASELLE_PER_RIGA);

        } while (Tabellone.controlloPosizione(y,x) || (caselle[y][x]!=null) );
        return new int[]{y,x};
    }
}
