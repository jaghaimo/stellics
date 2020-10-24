package stellics;

public class FilterManager {
    public enum BoardView {
        Cargo, Ships;
    }

    private BoardView boardView;

    public FilterManager() {
        boardView = BoardView.Cargo;
    }

    public BoardView getBoardView() {
        return boardView;
    }
}
