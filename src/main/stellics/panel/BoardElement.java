package stellics.panel;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stellics.StorageBoard;
import stellics.button.Button;

public abstract class BoardElement {

    protected StorageBoard board;
    protected CustomPanelAPI panel;
    protected float width;
    protected float height;

    public BoardElement(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        this.board = board;
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    public abstract void render();

    protected float renderDoubleButton(Button buttonA, Button buttonB, float currentHeight) {
        float spacer = 80f;
        float widthB = 80f;
        float widthA = width - widthB - spacer;
        PositionAPI positionA = renderButton(buttonA, widthA);
        positionA.inTL(0, currentHeight);
        PositionAPI positionB = renderButton(buttonB, widthB);
        positionB.inTL(widthA + spacer - 10f, currentHeight);
        currentHeight += 30f;
        return currentHeight;
    }

    protected float renderFilters(List<Button> buttons, float currentHeight) {
        float buttonWidth = width;
        for (Button button : buttons) {
            PositionAPI position = renderButton(button, buttonWidth);
            position.inTR(5f, currentHeight);
            currentHeight += 30f;
        }
        return currentHeight;
    }

    protected float renderCargo(CargoAPI cargo, float currentHeight, float reservedSpace) {
        float actualHeight = height - currentHeight - reservedSpace;
        TooltipMakerAPI cargoView = panel.createUIElement(width, actualHeight, true);
        cargoView.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
        panel.addUIElement(cargoView).inTL(0, currentHeight);
        return currentHeight + actualHeight;
    }

    protected float renderShips(List<FleetMemberAPI> ships, float currentHeight, float reservedSpace) {
        float actualHeight = height - currentHeight - reservedSpace;
        TooltipMakerAPI shipView = panel.createUIElement(width, reservedSpace, true);
        shipView.showShips(ships, ships.size(), false, 5f);
        panel.addUIElement(shipView).inTL(0, currentHeight);
        return currentHeight + actualHeight;
    }

    private PositionAPI renderButton(Button button, float buttonWidth) {
        TooltipMakerAPI buttonElement = panel.createUIElement(buttonWidth, 25f, false);
        buttonElement.addButton(button.getTitle(), button, buttonWidth, 20f, 5f);
        return panel.addUIElement(buttonElement);
    }
}
