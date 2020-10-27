package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.util.Misc;

public class RequestShips extends Button {

    public RequestShips() {
        super("Request Ships");
        // TODO finish me
        setEnabled(false);
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }
}
