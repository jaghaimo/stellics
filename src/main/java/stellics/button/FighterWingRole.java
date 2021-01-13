package stellics.button;

import com.fs.starfarer.api.loading.WingRole;
import com.fs.starfarer.api.util.Misc;

import stellics.filter.IsNotFighterWingRole;

public class FighterWingRole extends Button {

    public FighterWingRole(WingRole wingRole) {
        super(Misc.ucFirst(wingRole.name().toString().toLowerCase() + "s"), new IsNotFighterWingRole(wingRole));
    }
}
