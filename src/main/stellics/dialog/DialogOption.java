package stellics.dialog;

public enum DialogOption {
    GLOBAL_CARGO("Request", " cargo from storages"), GLOBAL_SHIPS("Request", " ships from storages"),
    REQUEST_CARGO("Request", " cargo from storage"), REQUEST_SHIPS("Request", " ships from storage"),
    TRANSFER_CARGO("Transfer", " cargo to storage"), TRANSFER_SHIPS("Transfer", " ships to storage");

    private String action;
    private String rest;

    private DialogOption(String action, String rest) {
        this.action = action;
        this.rest = rest;
    }

    public String getAction() {
        return action;
    }

    public String getTitle() {
        return action + rest;
    }
}
