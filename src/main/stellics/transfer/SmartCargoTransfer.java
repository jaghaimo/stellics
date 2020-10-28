package stellics.transfer;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stellics.helper.MonthlyReportHelper;
import stellics.helper.StorageHelper;

public class SmartCargoTransfer implements CargoTransferAction {

    private CargoAPI filteredCargo;
    private CargoAPI playerCargo;

    public SmartCargoTransfer(CargoAPI filteredCargo) {
        this.filteredCargo = filteredCargo;
        this.playerCargo = Global.getSector().getPlayerFleet().getCargo();
    }

    @Override
    public CargoAPI getSource() {
        return filteredCargo;
    }

    @Override
    public void transfer(CargoAPI cargo) {
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            CargoAPI storageCargo = storage.getCargo();
            CargoAPI commonCargo = getCommonCargo(cargo, storageCargo);
            MonthlyReportHelper.registerTransfer(storage, cargo);
            playerCargo.addAll(commonCargo);
            storageCargo.removeAll(commonCargo);
            cargo.removeAll(commonCargo);
            if (cargo.isEmpty()) {
                break;
            }
        }
    }

    private CargoAPI getCommonCargo(CargoAPI cargoA, CargoAPI cargoB) {
        CargoAPI aLessB = cargoA.createCopy();
        aLessB.removeAll(cargoB);
        CargoAPI bLessA = cargoB.createCopy();
        bLessA.removeAll(cargoA);
        CargoAPI intersection = cargoA.createCopy();
        intersection.addAll(cargoB);
        intersection.removeAll(aLessB);
        intersection.removeAll(bLessA);
        halveQuantities(intersection);
        return intersection;
    }

    private void halveQuantities(CargoAPI cargo) {
        for (CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            float size = cargoStack.getSize();
            cargoStack.setSize(size / 2);
        }
    }
}
