package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UserInput {

    List<List<Integer>> lotteries = new ArrayList<>();
    final private int MAX_NUMBER = 45;
    final private int MIN_NUMBER = 1;
    final private int NUMBER_COUNT = 6;

    public Calculator inputMoney() {
        Message.INPUT_MONEY.print();
        String input = Console.readLine();
        validateMoney(input);
        long money = Long.parseLong(input);
        return new Calculator(money);
    }

    private void validateMoney(String input) {
        long money;
        try {
            money = Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NUMBER.printError());
        }
        if (money % 1000 != 0 || money == 0) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_1000.printError());
        }
    }

    void generateRandomLotto(long quantity) {
        for (int i = 0; i < quantity; i++) {
            List<Integer> tmpLotto =
                    new ArrayList<>(Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT));
            tmpLotto.sort(Comparator.naturalOrder());
            lotteries.add(tmpLotto);
        }
    }

    void printLotto() {
        Iterator<List<Integer>> it = lotteries.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public Lotto inputWinningNumber() {
        Message.INPUT_LOTTERY_NUMBER.print();
        String winningNumber = Console.readLine();
        List numbers = validateLotto(winningNumber);
        return new Lotto(numbers);
    }

    private List validateLotto(String winningNumber) {
        String[] splitInput = winningNumber.split(",");
        List<Integer> numbers = new ArrayList<>();
        validateLottoType(splitInput, numbers);

        return numbers;
    }

    private void validateLottoType(String[] splitInput, List<Integer> numbers) {
        try {
            for (int i = 0; i < splitInput.length; i++) {
                numbers.add(Integer.parseInt(splitInput[i].trim()));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_NUMBER.printError());
        }
    }
}
