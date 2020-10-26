package stellics.filter;

public class DummyFilter implements Filter<Object> {

    @Override
    public boolean accept(Object object) {
        return true;
    }
}
