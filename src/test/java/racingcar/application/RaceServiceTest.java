package racingcar.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Result;
import racingcar.exception.InvalidInputException;
import racingcar.exception.Message;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RaceServiceTest {
    private static final String VALID_TRY_COUNT_INPUT = "5";

    private RaceService raceService;

    @BeforeEach
    void setUp() {
        raceService = new RaceService();
    }

    @Test
    void 올바른_자동차_이름_문자열이_입력된_경우() {
        String carNamesInput = "pobi,woni,jun";
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        Result result = raceService.run(carNamesInput, tryCountInput);
        List<String> actualCarNames = result.roundResults().getFirst()
                        .carPositions().keySet().stream().toList();

        assertThat(String.join(",", actualCarNames)).isEqualTo(carNamesInput);
    }

    @ParameterizedTest
    @ValueSource(strings = {",pobi,woni,jun", "pobi,woni,jun,"})
    void 자동차_이름들_문자열에_쉼표가_맨앞_혹은_맨뒤에_있는_경우_예외_발생(String carNamesInput) {
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_COMMA_AT_START_OR_END.getMessage());
    }

    @Test
    void 자동차_이름들_문자열에_쉼표가_연속으로_있는_경우_예외_발생() {
        String carNamesInput = "pobi,,woni,jun";
        String tryCountInput = VALID_TRY_COUNT_INPUT;

        assertThatThrownBy(() -> raceService.run(carNamesInput, tryCountInput))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage(Message.CAR_NAMES_CONSECUTIVE_COMMA_PRESENT.getMessage());
    }
}
