package stellics.panel;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.StorageBoard;
import stellics.button.Button;
import stellics.button.RequestCargo;
import stellics.button.ShowShips;

public class CargoControl extends BoardElement {

    public CargoControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        float currentHeight = 0;
        for (Button button : getButtons()) {
            TooltipMakerAPI buttonElement = panel.createUIElement(width, 25f, false);
            buttonElement.addButton(button.getTitle(), button.getHandler(), width, 20f, 5f);
            panel.addUIElement(buttonElement).inTR(0, currentHeight);
            currentHeight += 25f;
        }
    }

    private List<Button> getButtons() {
        return Arrays.asList(new RequestCargo(), new ShowShips());
    }
}
