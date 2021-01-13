package stellics.button;

import stellics.filter.IsNotOther;

public class Others extends Button {

    public Others() {
        super("Other Items", new IsNotOther());
    }
}
