package stellics.transfer;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

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
        CargoTransferMap transferMap = buildTransferMap(cargo);
        transfer(transferMap);
    }

    private CargoTransferMap buildTransferMap(CargoAPI cargo) {
        CargoTransferMap transferMap = new CargoTransferMap();
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            addToTransferMap(transferMap, storages, cargoStack);
        }
        return transferMap;
    }

    private void addToTransferMap(CargoTransferMap transferMap, List<SubmarketAPI> storages, CargoStackAPI cargoStack) {
        for (SubmarketAPI storage : storages) {
            addToTransferMap(transferMap, storage, cargoStack);
        }
    }

    private void addToTransferMap(CargoTransferMap transferMap, SubmarketAPI storage, CargoStackAPI cargoStack) {
        CargoAPI storageCargo = storage.getCargo();
        CargoStackAPI commonStack = getCommonStack(storageCargo, cargoStack);
        if (commonStack != null) {
            transferMap.addCargoStack(storage, commonStack);
            reduceCargoStackSize(cargoStack, commonStack);
        }
    }

    private void transfer(CargoTransferMap transferMap) {
        for (SubmarketAPI storage : transferMap.keySet()) {
            CargoAPI cargoToTransfer = transferMap.get(storage);
            CargoAPI sourceCargo = storage.getCargo();
            CargoTransferAction transferAction = new DirectCargoTransfer(sourceCargo, playerCargo, storage);
            transferAction.transfer(cargoToTransfer);
            sourceCargo.removeAll(cargoToTransfer);
        }
    }

    private CargoStackAPI getCommonStack(CargoAPI storageCargo, CargoStackAPI desiredCargoStack) {
        for (CargoStackAPI stackFromStorage : storageCargo.getStacksCopy()) {
            if (isSameCargoStack(stackFromStorage, desiredCargoStack)) {
                stackFromStorage.setSize(Math.min(desiredCargoStack.getSize(), stackFromStorage.getSize()));
                return stackFromStorage;
            }
        }
        return null;
    }

    private boolean isSameCargoStack(CargoStackAPI s1, CargoStackAPI s2) {
        if ((s1 == null || s2 == null) && s1 != s2) {
            return false;
        }
        if (s1.getType() != s2.getType()) {
            return false;
        }
        if ((s1.getData() == null || s2.getData() == null) && s1.getData() != s2.getData()) {
            return false;
        }
        if (!s1.getData().equals(s2.getData())) {
            return false;
        }
        return true;
    }

    private void reduceCargoStackSize(CargoStackAPI cargoStackToReduce, CargoStackAPI reducer) {
        float currentSize = cargoStackToReduce.getSize();
        float reduceBy = reducer.getSize();
        if (currentSize == 0 || reduceBy == 0) {
            return;
        }
        cargoStackToReduce.setSize(currentSize - reduceBy);
    }
}
