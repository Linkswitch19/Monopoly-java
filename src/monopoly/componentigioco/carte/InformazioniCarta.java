package monopoly.componentigioco.carte;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;
public interface InformazioniCarta {

    static LinkedList<Carta> readFromFile(File file) {
        LinkedList<Carta> carte = new LinkedList<>();

        if (file == null)
            return carte;

        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            while (scanner.hasNextLine()) {

                String[] tokens = scanner.nextLine().split(";");
                if (tokens.length != 3)
                    continue;

                Carta carta = CreaCarta(tokens[1].toLowerCase().trim(),tokens[0],tokens[2].trim().trim());
                carte.add(carta);
            }
        } catch (final IOException e) {
            System.out.println("Some errors occurred while reading the desired file");
            System.out.println(e.getMessage());
            return null;
        }
        Collections.shuffle(carte);
        return carte;
    }

    private static Carta CreaCarta(String tipo, String descrizione,String terzaInformazione) {
        return switch (tipo) {
            case "vai a" -> new VaiA(descrizione, terzaInformazione);
            case "ricevi"-> new ModificaBudget(descrizione,terzaInformazione);
            default -> new ModificaBudget( descrizione,  "-"+terzaInformazione);
        };
    }
}
