package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CommDirectoryAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;

import stellics.filter.HasTag;

public class PersonHelper {

    private static String TAG = "stellics";

    public static void addMissing() {
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            addIfMissing(submarket.getMarket());
        }
    }

    private static void addIfMissing(MarketAPI market) {
        if (hasOfficial(market.getCommDirectory())) {
            return;
        }
        PersonAPI person = getRandomPerson(market);
        market.addPerson(person);
    }

    private static boolean hasOfficial(CommDirectoryAPI commDirectory) {
        List<CommDirectoryEntryAPI> entries = commDirectory.getEntriesCopy();
        CollectionHelper.reduce(entries, new HasTag(TAG));
        return !entries.isEmpty();
    }

    private static PersonAPI getRandomPerson(MarketAPI market) {
        PersonAPI person = Global.getFactory().createPerson();
        person.addTag(TAG);
        person.setFaction(market.getFactionId());
        person.setPostId("Stellar Logistics agent");
        return person;
    }
}
