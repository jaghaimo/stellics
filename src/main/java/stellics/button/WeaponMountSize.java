package stellics.button;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;

import stellics.filter.IsNotMountSize;

public class WeaponMountSize extends Button {

    public WeaponMountSize(WeaponSize weaponSize) {
        super(weaponSize.getDisplayName() + " Mount", new IsNotMountSize(weaponSize));
    }
}
