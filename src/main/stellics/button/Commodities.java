package stellics.button;

import stellics.filter.IsNotCommodity;

public class Commodities extends Button {

    public Commodities() {
        super("Commodities", new IsNotCommodity());
    }
}
