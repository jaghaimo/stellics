package stellics.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class IsNotWeapon implements CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return !object.isWeaponStack();
    }
}
