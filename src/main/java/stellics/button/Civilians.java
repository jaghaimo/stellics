package stellics.button;

import stellics.filter.IsNotCivilian;

public class Civilians extends Button {

    public Civilians() {
        super("Civilians", new IsNotCivilian());
    }
}
