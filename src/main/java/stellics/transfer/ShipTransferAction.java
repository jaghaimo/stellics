package stellics.transfer;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public interface ShipTransferAction {

    public List<FleetMemberAPI> getSource();

    public void transfer(List<FleetMemberAPI> fleet);
}
