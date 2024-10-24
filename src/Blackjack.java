import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;

public class Blackjack {

    public static Integer sum(ArrayList<String> hand) {
        Integer total = 0;
        for (String card: hand) {
            if (hand.size() == 2) {
                if (card.contains("1-")) {
                    total += 11;
                } else {
                    total += ("JACK,QUEEN,KING".contains(card.split("-")[0]) ? 10 : Integer.parseInt(card.split("-")[0])); 
                }
            } else {
                total += ("JACK,QUEEN,KING".contains(card.split("-")[0]) ? 10 : Integer.parseInt(card.split("-")[0])); 
            }
        }
        return total;
    }

    public static Integer sum(ArrayList<String> hand, Boolean A) {
        Integer totalA1 = 0;
        Integer totalA10 = 0;
        for (String card: hand) {
            totalA10 += ("JACK,QUEEN,KING,1".contains(card.split("-")[0]) ? 10 : Integer.parseInt(card.split("-")[0])); 
            totalA1 += ("JACK,QUEEN,KING".contains(card.split("-")[0]) ? 10 : Integer.parseInt(card.split("-")[0])); 
        }
        if ((totalA10 > totalA1 && totalA10 <= 21) || (totalA10 < totalA1 && totalA1 > 21)) {
            return totalA10;
        } else if ((totalA10 > totalA1 && totalA10 > 21) || (totalA10 < totalA1 && totalA1 <= 21)) {
            return totalA1;
        } else {
            return totalA1;
        }
    }

    public static void main(String[] args) {
        String[] value = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING"};
        String[] suit = {"HEART", "SPADE", "DIAMOND", "CLUB"};
        ArrayList<String> cards = new ArrayList<String>();
        Console cons = System.console();

        for (int i = 0; i < value.length; i++) {
            for (int j = 0; j < suit.length; j++) {
                cards.add("%s-%s".formatted(value[i], suit[j]));
            }
        }
        Collections.shuffle(cards);
        //System.out.println(cards.toString());
        ArrayList<String> player = new ArrayList<>();
        ArrayList<String> computer = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            player.add(cards.remove(0));
            computer.add(cards.remove(0));
        }

        System.out.println(player.toString());
        System.out.println(Blackjack.sum(player));
        System.out.println(computer.toString());
        System.out.println(Blackjack.sum(computer));

        Boolean maxValue = false;
        Boolean computerDone = false;
        if (Blackjack.sum(player).equals(21) && !Blackjack.sum(computer).equals(21)) {
            System.out.println("Blackjack! You have won!");
        } else if (!Blackjack.sum(player).equals(21) && Blackjack.sum(computer).equals(21)) {
            System.out.println("Blackjack! Computer has won!");
        } else if ((Blackjack.sum(player).equals(21) && Blackjack.sum(computer).equals(21)) ||
        (Blackjack.sum(player).equals(22) && Blackjack.sum(computer).equals(22))) {
            System.out.println("It is a tie!");
        } else if (Blackjack.sum(player).equals(22) && Blackjack.sum(computer) <= 21) {
            System.out.println("Double Aces! You have won!");
        } else if (Blackjack.sum(player) <= 21 && Blackjack.sum(computer).equals(22)) {
            System.out.println("Double Aces! Computer has won!");
        } else {
            while (true) {
                String command = null;
                if (maxValue) {
                    command = cons.readLine("Pass: ").toLowerCase();
                    if (!command.equals("pass")) {
                        System.out.println("Error! Please try again.");
                        continue;
                    }
                } else {
                    command = cons.readLine("Draw/Pass: ").toLowerCase();
                }
                switch (command) {
                    case "draw":
                        player.add(cards.remove(0));
                        System.out.println(player.toString());
                        System.out.printf("Total Player hand value: %d\n", Blackjack.sum(player, true));
                        if (Blackjack.sum(player, true) >= 21) {
                            System.out.println("Maximum value allowed, can only pass");
                            maxValue = true;
                        }
                        break;
                    case "pass":
                        // computer's turn
                        while (Blackjack.sum(computer, true) <= 16) {
                            computer.add(cards.remove(0));
                        }
                        computerDone = true;
                        break;
                    default:
                        System.out.println("Invalid input, please try again.");
                }
                if (computerDone) {
                    break;
                }
            }
            System.out.println("\nResult:");
            System.out.println("Player's hand: " + player.toString());
            System.out.println("Computer's hand: " + computer.toString());
            System.out.printf("Player: %d, Computer: %d\n", Blackjack.sum(player, true), Blackjack.sum(computer, true));
            if ((Blackjack.sum(player, true) > Blackjack.sum(computer, true)) && Blackjack.sum(player, true) <= 21) {
                System.out.printf("Player wins with %d\n", Blackjack.sum(player, true));
            } else if (Blackjack.sum(player, true) < Blackjack.sum(computer, true) && Blackjack.sum(computer, true) <= 21) {
                System.out.printf("Computer wins with %d\n", Blackjack.sum(computer, true));
            } else {
                if ((Blackjack.sum(player, true) > Blackjack.sum(computer, true)) && Blackjack.sum(player, true) > 21 && Blackjack.sum(computer, true) <= 21) {
                    System.out.printf("Computer wins with %d\n", Blackjack.sum(computer, true));
                } else if (Blackjack.sum(player, true) < Blackjack.sum(computer, true) && Blackjack.sum(computer, true) > 21 && Blackjack.sum(player, true) <= 21) {
                    System.out.printf("Player wins with %d\n", Blackjack.sum(player, true));
                } else {
                    System.out.println("It's a tie!");
                }
            }
        }

        



        
    }
}
