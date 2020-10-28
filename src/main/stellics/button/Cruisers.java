package stellics.button;

import stellics.filter.IsNotCruiser;

public class Cruisers extends Button {

    public Cruisers() {
        super("Cruisers", new IsNotCruiser());
    }
}
