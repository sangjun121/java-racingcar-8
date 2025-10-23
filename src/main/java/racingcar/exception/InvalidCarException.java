package racingcar.exception;

public class InvalidCarException extends IllegalArgumentException {
    public InvalidCarException() {
        super("올바르지 않은 자동차 정보입니다.");
    }

    public InvalidCarException(String message) {
        super(message);
    }
}
