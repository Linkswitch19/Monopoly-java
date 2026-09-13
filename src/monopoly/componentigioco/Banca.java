package monopoly.componentigioco;

import monopoly.utilita.Costanti;

public abstract  class  Banca {
    private static int importo = Costanti.IMPORTO_INIZIALE_BANCA;

    public Banca(int importo) {
        Banca.importo = isValido(importo) ? Costanti.IMPORTO_INIZIALE_BANCA : importo;
    }

    private boolean isValido(int importo) { return (importo <= 0); }

    public static void addImporto(final int importo) {

        if (controlloImportoBanca(importo)) {
            System.out.println("Soldi aggiunti alla banca");
            aggiungiSoldiImporto();
        }

        Banca.importo += importo; // nota i soldi delle caselle sono negativi

    }

    private static boolean controlloImportoBanca(final int importo){
        return ((Banca.importo+importo)<=0);
    }


    private static void aggiungiSoldiImporto() {
        Banca.importo += Costanti.IMPORTO_PER_BANCAROTTA;
    }
}
