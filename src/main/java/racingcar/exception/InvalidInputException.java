package racingcar.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
        super(Message.INVALID_INPUT.getMessage());
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
