package ch.bbbaden.promillerechner;

import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Romain
 */
public class GUI {

    private Person askPersonData() {
        String eingabeGeburtsdatum;
        //Gewicht
        double koerperMasse = Double.parseDouble(JOptionPane.showInputDialog(null,
                "Geben Sie ihr Gewicht ein",
                "Körpergewicht",
                JOptionPane.PLAIN_MESSAGE));

        //Grösse
        double koerperGroesseInCm = Double.parseDouble(JOptionPane.showInputDialog(null,
                "Geben Sie ihre Grösse in cm ein",
                "Körpergrösse",
                JOptionPane.PLAIN_MESSAGE));

        //Geburtsdatum
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date geburtsdatum = null;
        try {
            eingabeGeburtsdatum = JOptionPane.showInputDialog(null,
                    "Geben Sie ihr Geburtsdatum ein",
                    "Geburtsdatum",
                    JOptionPane.PLAIN_MESSAGE);
            geburtsdatum = formatter.parse(eingabeGeburtsdatum);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Kein gültiges Datum.");
        }

        //Geschlecht
        Object[] options = {"Männlich", "Weiblich"};

        int geschlecht = JOptionPane.showOptionDialog(null,
                "Treffen Sie eine Auswahl",
                "Geschlecht",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        Person x = new Person(koerperMasse, koerperGroesseInCm, geburtsdatum, geschlecht);

        return x;
    }

    private AlkoholischesGetraenk askAlkoholischeGetraenke(Date trinkDatum) {
        //Volumen
        int mengeGetrunken = parseInt(JOptionPane.showInputDialog(null,
                "Geben Sie die getrunkene Menge in Milliliter ein",
                "Z. B. 1000",
                JOptionPane.PLAIN_MESSAGE));
        //Alkoholgehalt
        String[] options = {"Bier", "Wein", "Schnaps"};
        int alkGehalt = JOptionPane.showOptionDialog(null, "Was haben sie getrunken?",
                "Click a button",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        //Datum getrunken am

        AlkoholischesGetraenk placeholder = new AlkoholischesGetraenk(mengeGetrunken, alkGehalt, trinkDatum);
        return placeholder;
    }

    public void promilleRechner() {

        Date eingabeTrinkDatum = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        final Date jetzt = new Date();
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Trinkdatum und -zeit ein.",
                    "Trinkzeit",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) { // Cancel
                System.exit(0);
            }
            try {
                eingabeTrinkDatum = formatter.parse(eingabe);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, eingabe + " ist keine gültige Trinkzeit.");
            }
        } while (eingabeTrinkDatum == null || !jetzt.after(eingabeTrinkDatum));

        Person Richi = askPersonData();
        AlkoholischesGetraenk draenk = askAlkoholischeGetraenke(eingabeTrinkDatum);

        Richi.trinke(draenk);
        Double promille = Richi.getAlkoholPromille();
        Spruch funny = new Spruch(promille);
        String lustig = funny.getSpruch();
        JOptionPane.showMessageDialog(null, promille + "Promille. " + lustig);

    }
}
