package stellics.filter;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.characters.PersonAPI;

public class HasTag implements Filter<CommDirectoryEntryAPI> {

    private String tag;

    public HasTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean accept(CommDirectoryEntryAPI object) {
        PersonAPI person = (PersonAPI) object.getEntryData();
        return person.getTags().contains(tag);
    }
}
