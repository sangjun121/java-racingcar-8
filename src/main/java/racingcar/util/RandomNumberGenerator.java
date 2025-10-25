package racingcar.util;

import camp.nextstep.edu.missionutils.Randoms;

public final class RandomNumberGenerator {
    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;

    private RandomNumberGenerator() {
    }

    public static int generate() {
        return Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX);
    }
}
