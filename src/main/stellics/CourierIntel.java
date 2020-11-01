package stellics;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.dialog.DialogFactory;
import stellics.dialog.DialogOption;
import stellics.helper.ConfigHelper;
import stellics.helper.DistanceHelper;

public class CourierIntel extends BaseIntelPlugin {

    private SubmarketAPI storage;

    public CourierIntel(SubmarketAPI storage) {
        this.storage = storage;
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        DialogOption option = (DialogOption) buttonId;
        InteractionDialogPlugin dialogPlugin = DialogFactory.get(option, storage, ui, this);
        ui.showDialog(null, dialogPlugin);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara(getMarketName(), getTitleColor(mode), 0);
        bullet(info);
        info.addPara("Faction: %s", 3f, getBulletColorForMode(mode), getFactionForUIColors().getColor(),
                getFactionName());
        info.addPara("Distance: %sLY", 3f, getBulletColorForMode(mode), Misc.getHighlightColor(),
                String.format("%.2f", DistanceHelper.getDistanceToPlayerLY(getMapLocation(null))));
        unindent(info);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        Color baseColor = getFactionForUIColors().getBaseUIColor();
        Color darkColor = getFactionForUIColors().getDarkUIColor();

        info.addSectionHeading(getMarketName() + " Cargo", baseColor, darkColor, Alignment.MID, 5f);
        fakeVerticalSeparator(info, width, 10f);
        showCargo(info);
        showConditionalButtons(info, width, DialogOption.REQUEST_CARGO, DialogOption.TRANSFER_CARGO);

        fakeVerticalSeparator(info, width, 20f);

        info.addSectionHeading(getMarketName() + " Ships", baseColor, darkColor, Alignment.MID, 5f);
        fakeVerticalSeparator(info, width, 10f);
        showShips(info);
        showConditionalButtons(info, width, DialogOption.REQUEST_SHIPS, DialogOption.TRANSFER_SHIPS);
    }

    @Override
    public FactionAPI getFactionForUIColors() {
        return storage.getMarket().getFaction();
    }

    @Override
    public String getIcon() {
        return getFactionForUIColors().getCrest();
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add("Logistics");
        return tags;
    }

    @Override
    public SectorEntityToken getMapLocation(SectorMapAPI map) {
        return storage.getMarket().getPrimaryEntity();
    }

    @Override
    public String getSortString() {
        return String.format("%06.2f", DistanceHelper.getDistanceToPlayerLY(getMapLocation(null)));
    }

    @Override
    public boolean isNew() {
        return false;
    }

    private String getFactionName() {
        return getFactionForUIColors().getDisplayName();
    }

    private String getMarketName() {
        return storage.getMarket().getName();
    }

    private void showConditionalButtons(TooltipMakerAPI info, float width, DialogOption take, DialogOption put) {
        if (ConfigHelper.allowTransfer()) {
            fakeVerticalSeparator(info, width, 10f);
            info.addButton(take.getAction(), take, width, 20f, 5f);
            info.addButton(put.getAction(), put, width, 20f, 5f);
        }
    }

    private void fakeVerticalSeparator(TooltipMakerAPI info, float width, float height) {
        info.addImage("", width, height, 0);
    }

    private void showCargo(TooltipMakerAPI info) {
        CargoAPI cargo = storage.getCargo().createCopy();
        cargo.sort();
        info.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
    }

    private void showShips(TooltipMakerAPI info) {
        List<FleetMemberAPI> ships = storage.getCargo().getMothballedShips().getMembersListCopy();
        info.showShips(ships, ships.size(), false, 5f);
    }
}
