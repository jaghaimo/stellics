package stellics.handler;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.filter.FilterManager;

public interface ButtonHandler {

    public void handle(FilterManager filterManager, IntelUIAPI ui);
}
