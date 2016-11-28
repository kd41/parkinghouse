package ee.home.parkinghouse.exception;

public class NotFoundException extends BaseDescriptionException {
    private static final long serialVersionUID = -6705577152479737731L;

    public NotFoundException(String description) {
        super(description);
    }

}
