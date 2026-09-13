package monopoly.componentigioco.casella;


import monopoly.Coordinate;
import monopoly.utilita.Costanti;

/**
 * <i>Classe figlia di Casella riguardante le stazioni.</i> <p>
 * {@link #stazioniRimaste} serve per adesso a controllare il numero di stazioni da generare.
 */

public class Stazione extends Casella{
    static int stazioniRimaste=4; // da migliorare

    public Stazione(Casella [][] caselle) {
        super();
        setCasella(caselle);
    }

    /**
     *
     * Il metodo, tramite uno switch che controlla che stazione è da creare grazie a {@link #stazioniRimaste}, si occupa di trovare le coordinate corrispondenti alla stazione creata.<p>
     * Una volta trovate le coordinate possiamo salvare nel array {@code caselle} l'oggetto stazione nella posizione giusta.<p>
     * Le modifiche a {@code caselle}influenzeranno anche {@link monopoly.componentigioco.Tabellone#caselle} dato che si passa una <i>reference</i>.<p>
     * Infine diminuisco l'attributo di classe {@link #stazioniRimaste}
     * @param caselle per rendere più facile la creazione delle caselle ho preferito crearle dentro la classe Stazione.<p>
     */

    protected void setCasella(Casella [][] caselle){
        int y=0 ,x=0;
        x = switch (stazioniRimaste) {
            case 1 -> Costanti.CASELLE_PER_RIGA / 2;
            case 2 -> {
                y = Costanti.RIGHE - 1;
                yield Costanti.CASELLE_PER_RIGA / 2;
            }
            case 3 -> {
                y = Costanti.RIGHE / 2;
                yield 0;
            }
            case 4 -> {
                y = Costanti.RIGHE / 2;
                yield Costanti.CASELLE_PER_RIGA - 1;
            }
            default -> x;
        };
        setCoordinate(new Coordinate(y,x));
        caselle[y][x]=this;
        stazioniRimaste--;

    }

    /**
     * A differenza del metodo della classe padre {@link Casella} il nome è scelto dall'istanza treno del enum {@link NomiCaselle}.<p>
     * Per far ciò salviamo in una variabile detta {@code tipo} l'istanza del enum {@link NomiCaselle} da prendere in considerazione,<p>
     * Infine chiamiamo il metodo della classe padre per settare Nome e colore {@link #setNomeColore(String nome, String colore)}.<p>
     * @see NomiCaselle#getNome(int indiceNome) tipo.getNome(int indiceNome);
     * @see NomiCaselle#getColore() tipo.getColore().
     */
    @Override
    public void setNomeColoreDefault(){
        NomiCaselle tipo = NomiCaselle.TRENO;
        super.setNomeColore(tipo.getNome(stazioniRimaste-1),tipo.getColore());

    }
}
