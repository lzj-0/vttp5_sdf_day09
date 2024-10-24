import java.io.*;
import java.util.*;

public class Hangman {

    public static void hangmanFigure(Integer state) {
        StringBuilder sb = new StringBuilder();
        switch (state) {
            case 0:
                sb.append("\t\t\t");
                sb.append("       \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t \n");
                break;
            case 1:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t     \n");
                sb.append("\t\t\t \n");
                break;
            case 2:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|\n");
                break;
            case 3:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|\n");
                break;
            case 4:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|   O\n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|\n");
                break;
            case 5:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|   O\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|\n");
                break;
            case 6:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|   O\n");
                sb.append("\t\t\t|  /|\n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|\n");
                break;
            case 7:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|   O\n");
                sb.append("\t\t\t|  /|\\\n");
                sb.append("\t\t\t|    \n");
                sb.append("\t\t\t|\n");
                break;
            case 8:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|   O\n");
                sb.append("\t\t\t|  /|\\\n");
                sb.append("\t\t\t|  / \n");
                sb.append("\t\t\t|\n");
                break;
            case 9:
                sb.append("\t\t\t");
                sb.append("-------\n");
                sb.append("\t\t\t|   |\n");
                sb.append("\t\t\t|   O\n");
                sb.append("\t\t\t|  /|\\\n");
                sb.append("\t\t\t|  / \\\n");
                sb.append("\t\t\t|\n");
                break;
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Input <word file>");
            System.exit(-1);
        }
        ArrayList<String> words = new ArrayList<>();

        File file = new File(args[0]);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String word;
        while ((word = br.readLine()) != null) {
            words.add(word);
        }
        Random rand = new Random();
        String wordToGuess = words.get(rand.nextInt(0, words.size())).toUpperCase();
        String[] board = new String[wordToGuess.length()];
        Integer spaces = 0;
        for (int i = 0; i < board.length; i++) {
            if (wordToGuess.charAt(i) == ' ') {
                board[i] = " ";
                spaces++;
            } else {
                board[i] = "_";
            }
        }

        Console cons = System.console();
        System.out.println("Hangman Game");
        Integer round = 1;
        Integer hangmanState = 0;
        Integer letterLeft = wordToGuess.length() - spaces;
        ArrayList<String> guessedLetters = new ArrayList<>();

        while (true) {
            System.out.printf("Round %d:\n", round);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                sb.append(board[i] + " ");
            }
            sb.append("\n");
            System.out.println(sb.toString());
            Hangman.hangmanFigure(hangmanState);

            if (hangmanState == 9) {
                System.out.println("Game Over. You have lost.");
                System.out.println("Answer: " + wordToGuess);
                break;
            }

            if (letterLeft <= 0) {
                System.out.println("Game over! You have won!");
                break;
            }

            String letter = cons.readLine("Enter an alphabet: ").toUpperCase();
            if (!(letter.length() == 1) || letter.equals(" ")) {
                System.out.println("Invalid input,  please try again");
                continue;
            }
            
            if (guessedLetters.contains(letter)) {
                System.out.printf("Already guessed the letter %s\n", letter);
                continue;
            }

            if (wordToGuess.contains(letter)) {
                System.out.printf("%s exists\n", letter);
                for (int i = 0; i < board.length; i++) {
                    if (wordToGuess.substring(i, i+1).equals(letter)) {
                        board[i] = letter;
                        letterLeft--;
                    }
                }
            } else {
                System.out.printf("%s not present\n", letter);
                hangmanState++;
            }
            guessedLetters.add(letter);
            round++;
        }
        br.close();
        fr.close();
    }
}
