package stellics.panel;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.StorageBoard;
import stellics.helper.StorageHelper;

public class CargoColumn extends BoardElement {

    private CargoAPI cargo;

    public CargoColumn(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
        cargo = StorageHelper.getAllCargo();
    }

    @Override
    public void render() {
        TooltipMakerAPI header = panel.createUIElement(width, 20f, false);
        header.addSectionHeading("Cargo", Alignment.MID, 5f);
        panel.addUIElement(header).inTL(0, 0);

        TooltipMakerAPI cargoView = panel.createUIElement(width, height - 25f, true);
        cargoView.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
        panel.addUIElement(cargoView).inTL(0, 25f);
    }
}
