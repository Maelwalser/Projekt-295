package ch.noser.immobilien.domain.application;

public enum Status {
    ACCEPTED("accepted"), DENIED("denied"), PENDING("pending");
    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
