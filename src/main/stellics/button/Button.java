package stellics.button;

import stellics.handler.ButtonHandler;

public abstract class Button {

    private String title;
    private ButtonHandler handler;
    private ButtonState state;

    public Button(String title) {
        this.title = title;
    }

    public Button(String title, ButtonHandler handler, ButtonState state) {
        this.title = title;
        this.handler = handler;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public ButtonHandler getHandler() {
        return handler;
    }

    public ButtonState getState() {
        return state;
    }

    public boolean isDisabled() {
        return ButtonState.DISABLED.equals(state);
    }
}
