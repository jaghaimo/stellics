package stellics.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsNotCivilian implements FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isCivilian();
    }
}
