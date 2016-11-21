package ee.home.parkinghouse.service.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6705577152479737731L;
    private String description;

    public NotFoundException(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
