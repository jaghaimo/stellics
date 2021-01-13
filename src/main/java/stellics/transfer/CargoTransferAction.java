package stellics.transfer;

import com.fs.starfarer.api.campaign.CargoAPI;

public interface CargoTransferAction {

    public CargoAPI getSource();

    public void transfer(CargoAPI cargo);
}
