import java.io.Console;
import java.util.Random;

public class GuessNumber {
    public static void main(String[] args) {
        Console cons = System.console();
        Random rand = new Random();
        int round = 1;


        while (true) {
            int answer = rand.nextInt(100000, 999999);
            System.out.printf("Round %d: Guess the right 6 digit number\n", round);
            System.out.println(answer);
            while (true) {
                int guess = Integer.parseInt(cons.readLine("Make a guess: "));
                if (guess < 100000 || guess > 999999) {
                    System.out.println("Invalid nunmber, enter a 6 digit number from 100000 to 999999");
                    continue;
                }
                // do not use Integer class when comparing
                if (guess == answer) {
                    System.out.println("Correct number!");
                    break;
                } else if (guess > answer) {
                    System.out.println("Guess is too large");
                } else {
                    System.out.println("Guess is too small");
                }
            }
            round++;
        }

    }
}
