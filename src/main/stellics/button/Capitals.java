package stellics.button;

import stellics.filter.IsNotCapital;

public class Capitals extends Button {

    public Capitals() {
        super("Capitals", new IsNotCapital());
    }
}
