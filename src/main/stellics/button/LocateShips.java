package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.util.Misc;

public class LocateShips extends Button {

    public LocateShips() {
        super("Locate Ships");
        setEnabled(false);// TODO
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }
}
