package ch.bbbaden.promillerechner;

/**
 *
 * @author Romain
 */
public class Spruch {

    private double alkoholPromille;

    public Spruch(double alkoholPromille) {
        this.alkoholPromille = alkoholPromille;
    }

    public String getSpruch() {
        if (alkoholPromille > 1) {
            return "Gut so.";
        } else {
            return "Einer geht noch.";
        }

    }
}
