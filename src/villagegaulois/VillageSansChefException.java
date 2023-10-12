package villagegaulois;

public class VillageSansChefException extends RuntimeException {
    public VillageSansChefException() {
    }

    public VillageSansChefException(String s) {
        super(s);
    }

    public VillageSansChefException(Throwable cause) {
        super(cause);
    }

    public VillageSansChefException(String s, Throwable cause) {
        super(s, cause);
    }
}
