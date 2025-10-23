package racingcar.exception;

public class InvalidCarException extends IllegalArgumentException {
    public InvalidCarException() {
        super(Message.INVALID_CAR.getMessage());
    }

    public InvalidCarException(String message) {
        super(message);
    }
}
