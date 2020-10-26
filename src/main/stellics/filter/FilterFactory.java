package stellics.filter;

import java.util.Collections;
import java.util.List;

import stellics.button.ButtonManager;

public class FilterFactory {

    private ButtonManager buttonManager;

    public FilterFactory(ButtonManager buttonManager) {
        this.buttonManager = buttonManager;
    }

    public List<CargoStackFilter> getCargoStackFilters() {
        return Collections.emptyList();
    }

    public List<FleetMemberFilter> getFleetMemberFilters() {
        return Collections.emptyList();
    }
}
