import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GuessNumbers {
    public static void main(String[] args) {
        Random rand = new Random();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        ArrayList<Integer> guessed = new ArrayList<Integer>();
        Console cons = System.console();
        int score = 0;
        while (numbers.size() < 10) {
            Integer number = rand.nextInt(1, 100);
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }

        Collections.shuffle(numbers);
        //System.out.println(numbers.toString());
        //System.out.println(numbers.size());
        System.out.println("Guess if the next card is higher or lower than the previous");
        Integer prev = numbers.remove(0);
        guessed.add(prev);

        for (int i = 1; i < 10; i++) {
            int higher = 0;
            int lower = 0;
            for (Integer number: numbers) {
                if (number > prev) {
                    higher++;
                } else {
                    lower++;
                }
            }

            System.out.printf("\nRound %d\n", i);
            System.out.printf("Current card: %d\n", prev);
            System.out.printf("Hint: [%d H] [%d L]\n", higher, lower);
            String guess = cons.readLine("Higher (H) / Lower (L): ").toUpperCase();
            Integer next = numbers.remove(0);
            guessed.add(next);
            if ((next > prev && guess.equals("H")) || (next < prev && guess.equals("L"))) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
            prev = next;
        }
        System.out.printf("Game over! You got a total of %d points", score);
    }
}
