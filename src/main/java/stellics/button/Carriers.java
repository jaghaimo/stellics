package stellics.button;

import stellics.filter.IsNotCarrier;

public class Carriers extends Button {

    public Carriers() {
        super("Carriers", new IsNotCarrier());
    }
}
