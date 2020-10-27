package stellics.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsNotCapital implements FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isCapital();
    }
}
