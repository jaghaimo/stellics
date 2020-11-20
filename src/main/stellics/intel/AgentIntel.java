package stellics.intel;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stellics.helper.StarSystemHelper;

public class AgentIntel extends TransientIntel {

    private PersonAPI person;
    private MarketAPI market;

    public AgentIntel(PersonAPI person, MarketAPI market) {
        this.person = person;
        this.market = market;
        show();
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        String personName = person.getNameString();
        String marketName = market.getName();
        String systemName = StarSystemHelper.getName(market.getStarSystem());

        String title = "Stellar Logistics";
        String message = String.format(
                "Stellar Logistics branch on %s in %s has registered your storage. %s has been assigned as your liason and customer support.",
                marketName, systemName, personName);

        info.addPara(title, getTitleColor(mode), 0f);
        indent(info);
        info.addPara(message, 3f, getBulletColorForMode(mode), Misc.getHighlightColor(), marketName, systemName,
                personName);
        unindent(info);
    }

    @Override
    public String getIcon() {
        return Global.getSettings().getSpriteName("stellics", "agent");
    }
}
