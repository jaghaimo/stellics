package stellics.panel;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.CustomPanelAPI;

import stellics.StorageBoard;
import stellics.button.RequestCargo;
import stellics.button.ShowShips;
import stellics.filter.FilterFactory;
import stellics.helper.StorageHelper;

public class CargoDisplay extends BoardElement {

    private CargoAPI cargo;

    public CargoDisplay(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
        FilterFactory filterFactory = board.getFilterFactory();
        cargo = StorageHelper.getAllCargo(filterFactory.getCargoStackFilters());
    }

    @Override
    public void render() {
        float currentHeight = 0;
        currentHeight = renderCargo(cargo, currentHeight, 25f);
        currentHeight = renderDoubleButton(new RequestCargo(), new ShowShips(), currentHeight);
    }
}
