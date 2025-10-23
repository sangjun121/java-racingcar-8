package racingcar.exception;

public class InvalidCarsException extends IllegalArgumentException {
    public InvalidCarsException() {
        super(Message.INVALID_CARS.getMessage());
    }

    public InvalidCarsException(String message) {
        super(message);
    }
}
