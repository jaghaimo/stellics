package stellics.button;

import stellics.filter.IsNotWarship;

public class Warships extends Button {

    public Warships() {
        super("Warships", new IsNotWarship());
    }
}
