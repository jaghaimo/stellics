package stellics.button;

import stellics.filter.IsNotDestroyer;

public class Destroyers extends Button {

    public Destroyers() {
        super("Destroyers", new IsNotDestroyer());
    }
}
