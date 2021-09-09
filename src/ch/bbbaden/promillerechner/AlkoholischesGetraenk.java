package ch.bbbaden.promillerechner;

/**
 *
 * @author Romain
 */
public class AlkoholischesGetraenk {

    public final double BIER_ALKOHOLGEHALT = 0.05;
    public final double WEIN_ALKOHOLGEHALT = 0.10;
    public final double SCHNAPS_ALKOHOLGEHALT = 0.40;
    private final double DICHTE_ALKOHOL = 0.8;
    private int volumenInMilliLiter;
    private double alkoholgehalt;
    private java.util.Date getrunkenAm;

    public AlkoholischesGetraenk(int volumenInMilliLiter, int alkoholgehalt, java.util.Date getrunkenAm) {
        this.alkoholgehalt = alkoholgehalt;
        this.volumenInMilliLiter = volumenInMilliLiter;
        this.getrunkenAm = getrunkenAm;
    }

    public double getStundenSeitEinnahme(java.util.Date jetzt) {
        double diffInMillies = Math.abs(jetzt.getTime() - getrunkenAm.getTime());

        double douDif = diffInMillies / 3600000;

        return douDif;
    }

    public double getAlkoholMasseInGramm() {
        double alkoholMasseInGramm = 0;

        if (this.alkoholgehalt == 0) {
            this.alkoholgehalt = this.BIER_ALKOHOLGEHALT;
        } else {
            if (this.alkoholgehalt == 1) {
                this.alkoholgehalt = this.WEIN_ALKOHOLGEHALT;
            } else {
                this.alkoholgehalt = this.SCHNAPS_ALKOHOLGEHALT;
            }
        }

        alkoholMasseInGramm = this.alkoholgehalt * this.volumenInMilliLiter * this.DICHTE_ALKOHOL;
        return alkoholMasseInGramm;
    }
}
