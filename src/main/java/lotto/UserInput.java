package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UserInput {

    List<List<Integer>> lotteries = new ArrayList<>();

    public long inputMoney() {
        Message.INPUT_MONEY.print();
        String input = Console.readLine();
        validateMoney(input);
        return Long.parseLong(input);
    }

    private void validateMoney(String input) {
        try {
            long money = Long.parseLong(input);
            if (money % 1000 != 0 || money == 0) {
                throw new IllegalArgumentException(Message.ERROR_INPUT_1000.printError());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_NUMBER.printError());
        }
    }

    void generateRandomLotto(long quantity) {
        for (int i = 0; i < quantity; i++) {
            List<Integer> tmpLotto = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
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
                numbers.add(Integer.parseInt(splitInput[i]));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_NUMBER.printError());
        }
    }

    public int inputBonus() {
        Message.INPUT_BONUS_NUMBER.print();
        String bonus = Console.readLine();
        int result = validateBonus(bonus);
        return result;
    }

    private int validateBonus(String bonus) {
        int result;
        try {
            result = Integer.parseInt(bonus);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_NUMBER.printError());
        }
        if (result > 45 || result < 1) {
            throw new IllegalArgumentException(Message.ERROR_INPUT_RANGE.printError());
        }
        return result;
    }
}
