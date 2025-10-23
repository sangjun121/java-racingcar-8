package racingcar.util;

public class StringValidator {

    private StringValidator() {
    }

    public static boolean isNullOrBlank(String target) {
        return target == null || target.trim().length() == 0;
    }

    public static boolean isWithinLengthRange(String target, int minLength, int maxLength) {
        return target.length() >= minLength && target.length() <= maxLength;
    }
}
