package stellics.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;

public class IsNotMountType implements CargoStackFilter {

    private WeaponType weaponType;

    public IsNotMountType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public boolean accept(CargoStackAPI object) {
        if (!object.isWeaponStack()) {
            return true;
        }
        return object.getWeaponSpecIfWeapon().getType() != weaponType;
    }
}
