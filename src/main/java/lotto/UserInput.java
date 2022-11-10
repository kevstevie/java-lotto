package lotto;

import camp.nextstep.edu.missionutils.Console;

public class UserInput {

    private UserInput() {}

    static public long inputMoney() {
        System.out.println(Message.INPUT_MONEY);
        String input = Console.readLine();
        validateMoney(input);
        return Long.parseLong(input);
    }

    static private void validateMoney(String input) {
        try {
            long money = Long.parseLong(input);
            if (money % 1000 != 0) {
                Message.ERROR_INPUT_1000.printError();
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            Message.ERROR_INPUT_NUMBER.printError();
            throw new IllegalArgumentException();
        }
    }
}
