package stellics.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CommDirectoryAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.events.OfficerManagerEvent;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;

import stellics.filter.HasTag;

public class PersonHelper {

    public static String TAG = "stellics";

    public static void addMissing() {
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            addIfMissing(submarket.getMarket());
        }
    }

    private static void addIfMissing(MarketAPI market) {
        CommDirectoryAPI commDirectory = market.getCommDirectory();
        removeAll(commDirectory);
        if (hasOfficial(commDirectory)) {
            return;
        }
        PersonAPI person = getRandomPerson(market);
        commDirectory.addPerson(person);
    }

    private static void removeAll(CommDirectoryAPI commDirectory) {
        List<CommDirectoryEntryAPI> entries = commDirectory.getEntriesCopy();
        CollectionHelper.reduce(entries, new HasTag(TAG));
        for (CommDirectoryEntryAPI entry : entries) {
            commDirectory.removeEntry(entry);
        }
    }

    private static boolean hasOfficial(CommDirectoryAPI commDirectory) {
        List<CommDirectoryEntryAPI> entries = commDirectory.getEntriesCopy();
        CollectionHelper.reduce(entries, new HasTag(TAG));
        return !entries.isEmpty();
    }

    private static PersonAPI getRandomPerson(MarketAPI market) {
        PersonAPI officer = OfficerManagerEvent.createOfficer(market.getFaction(), 1, true);
        PersonAPI person = Global.getFactory().createPerson();
        person.addTag(TAG);
        person.setFaction(market.getFactionId());
        person.setGender(officer.getGender());
        person.setName(officer.getName());
        person.setPortraitSprite(officer.getPortraitSprite());
        person.setPostId(Ranks.POST_AGENT);
        person.setRankId(Ranks.AGENT);
        return person;
    }
}
