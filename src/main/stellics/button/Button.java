package stellics.button;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stellics.StorageBoard;
import stellics.filter.DummyFilter;

public class Button implements ButtonHandler {

    private String title;
    private boolean isEnabled;
    private boolean isStateOn;

    public Button() {
        isEnabled = true;
        isStateOn = true;
    }

    public Button(String title) {
        this.title = title;
    }

    public Object getFilter() {
        return new DummyFilter();
    }

    public String getTitle() {
        String onOff = isStateOn ? ": On" : ": Off";
        return title + onOff;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isStateOn() {
        return isStateOn;
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        if (!isEnabled) {
            return;
        }
        toggle();
        ui.updateUIForItem(board);
    }

    public void toggle() {
        isStateOn = !isStateOn;
    }
}
