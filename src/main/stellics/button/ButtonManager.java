package stellics.button;

import java.util.Arrays;
import java.util.List;

public class ButtonManager {

    private List<Button> cargoTypeButtons;
    private List<Button> cargoWeaponButtons;
    private List<Button> cargoFighterWingsButtons;
    private List<Button> shipSizeButtons;
    private List<Button> shipTypeButtons;

    public ButtonManager() {
        cargoTypeButtons = Arrays.asList(new Weapons(), new FighterWings(), new Others());
        cargoWeaponButtons = Arrays.asList(new Small(), new Medium(), new Large());
        cargoFighterWingsButtons = Arrays.asList(new Interceptors(), new Fighters(), new Bombers());
        shipSizeButtons = Arrays.asList(new Frigates(), new Destroyers(), new Cruisers(), new Capitals());
        shipTypeButtons = Arrays.asList(new Warships(), new Carriers(), new Civilians());
    }

    public List<Button> getCargoTypeButtons() {
        return cargoTypeButtons;
    }

    public List<Button> getCargoWeaponButtons() {
        return cargoWeaponButtons;
    }

    public List<Button> getCargoFighterWingsButtons() {
        return cargoFighterWingsButtons;
    }

    public List<Button> getShipSizeButtons() {
        return shipSizeButtons;
    }

    public List<Button> getShipTypeButtons() {
        return shipTypeButtons;
    }
}
