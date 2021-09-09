package ch.bbbaden.promillerechner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Romain
 */
public class Person {

    public final int MAENNLICH = 0;
    public final int WEIBLICH = 1;
    private final double ABBAU_WARTEZEIT_STUNDEN = 1.0;
    private final double ABBAU_PRO_STUNDE = 0.1;
    private final double ANTEIL_WASSER_IM_BLUT = 0.8;
    private final double DICHTE_BLUT_GRAMM_PRO_CCM = 1.055;
    private double koerpermasse;
    private double koerpergroesseInCm;
    private Date geburtsdatum;
    private int geschlecht;
    private double alkoholPromille = 0.0;

    public Person(double koerpermasse, double koerpergroesseInCm, java.util.Date geburtsdatum, int geschlecht) {
        this.koerpermasse = koerpermasse;
        this.koerpergroesseInCm = koerpergroesseInCm;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
    }

    private double getAlterInJahren() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        double alterUnrounded;
        TimeUnit timeUnit = TimeUnit.DAYS;

        long diffInMillies = geburtsdatum.getTime() - date.getTime();
        alterUnrounded = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
        long alter = Math.round(alterUnrounded / 365);

        return alter;
    }

    public void trinke(AlkoholischesGetraenk alkoholischesGetraenk) {

        Date jetzt = new Date();
        double stundenSeitEinnahme = alkoholischesGetraenk.getStundenSeitEinnahme(jetzt);
        this.alkoholPromille = (ANTEIL_WASSER_IM_BLUT * alkoholischesGetraenk.getAlkoholMasseInGramm()) / (this.DICHTE_BLUT_GRAMM_PRO_CCM * getGKW());

        double abbauInPromille = 0;
        if (stundenSeitEinnahme >= 1) {
            abbauInPromille = stundenSeitEinnahme * 0.2;
        } else {

        }
        this.alkoholPromille = this.alkoholPromille - abbauInPromille;
    }

    public double getAlkoholPromille() {
        return this.alkoholPromille;
    }

    private double getGKW() {
        //GKW
        double gkw;
        if (geschlecht == MAENNLICH) {
            gkw = 2.447 - (0.09516 * getAlterInJahren()) + (0.1074 * this.koerpergroesseInCm) + (0.3362 * this.koerpermasse);
        } else {
            gkw = 0.203 - (0.07 * getAlterInJahren()) + (0.1069 * this.koerpergroesseInCm) + (0.2466 * this.koerpermasse);
        }
        return gkw;
    }
}
