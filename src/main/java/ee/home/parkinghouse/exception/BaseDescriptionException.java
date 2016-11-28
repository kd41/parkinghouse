package ee.home.parkinghouse.exception;

public abstract class BaseDescriptionException extends RuntimeException {
    private static final long serialVersionUID = -950198689550184791L;
    private String description;

    public BaseDescriptionException(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
