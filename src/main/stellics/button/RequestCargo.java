package stellics.button;

import java.awt.Color;

import com.fs.starfarer.api.util.Misc;

public class RequestCargo extends Button {

    public RequestCargo() {
        super("Request Cargo");
        // TODO finish me
        setEnabled(false);
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }
}
