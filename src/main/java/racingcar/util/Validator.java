package racingcar.util;

import java.util.List;

public class Validator {

    private Validator() {
    }

    public static boolean isNullOrBlank(String target) {
        return target == null || target.trim().length() == 0;
    }

    public static boolean isWithinLengthRange(String target, int minLength, int maxLength) {
        return target.length() >= minLength && target.length() <= maxLength;
    }

    public static boolean startsOrEndsWith(String target, String substring) {
        return target.startsWith(substring) || target.endsWith(substring);
    }

    public static boolean containsConsecutiveSubstring(String target, String substring) {
        String doubleSub = substring + substring;
        return target.contains(doubleSub);
    }

    public static boolean hasDuplicates(List<String> target) {
        return target.stream()
                .distinct()
                .count() != target.size();
    }

    public static boolean isLessThan(int target, int threshold) {
        return target < threshold;
    }

    public static boolean isBiggerThan(int target, int threshold) {
        return target > threshold;
    }
}
