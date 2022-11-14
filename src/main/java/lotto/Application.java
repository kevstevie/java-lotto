package lotto;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        if (inputView.inputMoney()) {
            return;
        }
        if (inputView.inputWinningNumber()) {
            return;
        }
        int bonus = lotto.inputBonus();
        for (List<Integer> randomLotto : inputView.calculator.lotteries) {
            lotto.compareLotto(randomLotto, bonus);
        }
        inputView.calculator.printResult();
    }
}
