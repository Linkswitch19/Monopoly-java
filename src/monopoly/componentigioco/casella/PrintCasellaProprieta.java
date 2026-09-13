package monopoly.componentigioco.casella;

import monopoly.componentigioco.giocatore.Giocatore;
import monopoly.utilita.Costanti;

public class PrintCasellaProprieta {

    private String printRighetta(){
        StringBuilder riga=new StringBuilder();
        riga.append("|");
        return riga.toString();
    }
    public String printRigaDue(Giocatore proprietario,int affitto,int terreno){
        StringBuilder secondaRiga = new StringBuilder();
        secondaRiga.append(CostantiCaselle.SPAZIO).append(printRighetta());
        if (proprietario != null) {
            secondaRiga.append("Paga ").append(Math.abs(affitto));
            secondaRiga.append(" a ").append(proprietario.getNome());
        } else {
            secondaRiga.append("Prezzo di vendita: ").append(terreno);
        }
        secondaRiga.append(CostantiCaselle.SPAZIO.repeat(((Costanti.LARGHEZZA_CASELLA ) - secondaRiga.length())));
        secondaRiga.append(printRighetta()).append(CostantiCaselle.SPAZIO);
        return secondaRiga.toString();

    }
    public String printRigaTre(Giocatore proprietario, String infoEdificiPrezzi, int pedaggio,StringBuilder dettagli){
        StringBuilder terzaRiga = new StringBuilder();
        terzaRiga.append(printRighetta());
        int nSpazi=1;
        if (proprietario!=null) {
            terzaRiga.append(infoEdificiPrezzi);
        } else {
            terzaRiga.append("Paga ").append(Math.abs(pedaggio)).append("CHF alla banca");
        }
        dettagli.append(terzaRiga).append(CostantiCaselle.SPAZIO.repeat(((Costanti.LARGHEZZA_CASELLA - nSpazi) - terzaRiga.length())));
        dettagli.append(printRighetta()).append(CostantiCaselle.SPAZIO);
        return dettagli.toString();
    }

    public String printRigaQuattro(Giocatore proprietario,String infoEdificiPrezzi,String edifici,StringBuilder dettagli,int nEdifici){
        StringBuilder quartaRiga = new StringBuilder();
        quartaRiga.append(printRighetta());
        int nSpazi=1;
        if (proprietario!=null) { // per 3 emoji 2 spazi e 1 minispazio // pwr 1 2 spazi e 2 mispazio // per 2 2 spazi 1 minispazio // per 4 2 mini 0
                quartaRiga.append(edifici);
        } else {
            quartaRiga.append(infoEdificiPrezzi);
        }
        dettagli.append(quartaRiga).append(CostantiCaselle.SPAZIO.repeat(((Costanti.LARGHEZZA_CASELLA-nSpazi ) - quartaRiga.length())));
        dettagli.append(printRighetta()).append(CostantiCaselle.SPAZIO);
        return dettagli.toString();
    }
}
