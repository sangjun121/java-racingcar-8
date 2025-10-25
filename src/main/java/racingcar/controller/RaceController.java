package racingcar.controller;

import racingcar.controller.dto.Result;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.application.RaceService;

public class RaceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RaceService raceService;

    public RaceController(InputView inputView,
                          OutputView outputView,
                          RaceService raceService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.raceService = raceService;
    }

    public void run() {
        String carNamesInput = inputView.readCarNames();
        String tryCountInput = inputView.readTryCount();

        Result RaceResult = raceService.run(carNamesInput, tryCountInput);

        outputView.printRaceResult(RaceResult.roundResults());
        outputView.printWinners(RaceResult.winners());
    }
}
