package racingcar.exception;

public enum Message {
    INVALID_CAR("자동차 정보가 잘못되었습니다."),
    CAR_NAME_NULL_OR_BLANK("자동차의 이름은 빈 값이거나 공백 문자열일 수 없습니다."),
    CAR_NAME_LENGTH_OUT_OF_RANGE("자동차의 이름은 1자 이상 5자 이하여야 합니다."),
    INVALID_CARS("자동차들의 정보가 잘못되었습니다."),
    CAR_NAMES_COMMA_AT_START_OR_END("자동차 이름들 문자열 맨 앞 혹은 뒤에 쉼표가 위치합니다."),
    CAR_NAMES_CONSECUTIVE_COMMA_PRESENT("자동차 이름 문자열에 쉼표가 연속될 수 없습니다."),
    CAR_NAME_DUPLICATED("자동차 이름은 중복될 수 없습니다.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
