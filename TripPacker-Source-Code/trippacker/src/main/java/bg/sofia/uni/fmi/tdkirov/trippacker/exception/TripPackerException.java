package bg.sofia.uni.fmi.tdkirov.trippacker.exception;

public class TripPackerException extends RuntimeException {
    public TripPackerException(String message) {
        super(message);
    }

    public TripPackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
