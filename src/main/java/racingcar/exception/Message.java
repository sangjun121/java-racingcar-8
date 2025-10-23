package racingcar.exception;

public enum Message {
    INVALID_CAR("올바르지 않은 자동차 정보입니다."),
    CAR_NAME_NULL_OR_BLANK("자동차의 이름은 빈 값이거나 공백 문자열일 수 없습니다."),
    CAR_NAME_LENGTH_OUT_OF_RANGE("자동차의 이름은 1자 이상 5자 이하여야 합니다.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
