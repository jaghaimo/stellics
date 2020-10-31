package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.util.Misc;

public class LocateCargo extends Button {

    public LocateCargo() {
        super("Locate Cargo");
        setEnabled(false);// TODO
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }
}
