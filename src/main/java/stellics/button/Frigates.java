package stellics.button;

import stellics.filter.IsNotFrigate;

public class Frigates extends Button {

    public Frigates() {
        super("Frigates", new IsNotFrigate());
    }
}
