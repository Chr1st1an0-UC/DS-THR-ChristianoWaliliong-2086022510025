import java.util.*;

class Card {
    int value, category;

    Card(int v, int c) {
        value = v;
        category = c;
    }

    public String toString() {
        return value + "," + category;
    }
}

public class Nomor5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<List<Card>> players = new ArrayList<>();

        // Read 4 players
        for (int i = 0; i < 4; i++) {
            String[] input = sc.nextLine().split(" ");
            List<Card> hand = new ArrayList<>();

            for (String s : input) {
                String[] parts = s.split(",");
                hand.add(new Card(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1])
                ));
            }
            players.add(hand);
        }

        int current = sc.nextInt() - 1;
        sc.close();

        Stack<Card> stack = new Stack<>();

        int lastPlayer = -1; // last successful player

        while (true) {

            List<Card> hand = players.get(current);
            boolean played = false;

            // 🔥 FIRST MOVE OR NEW ROUND
            if (stack.isEmpty() || lastPlayer == current) {
                Card best = findSmallest(hand);
                stack.push(best);
                hand.remove(best);
                lastPlayer = current;
                played = true;
            } else {
                Card top = stack.peek();

                Card chosen = null;

                for (Card c : hand) {
                    if (c.category == top.category && c.value > top.value) {
                        if (chosen == null || c.value < chosen.value) {
                            chosen = c;
                        }
                    }
                }

                if (chosen != null) {
                    stack.push(chosen);
                    hand.remove(chosen);
                    lastPlayer = current;
                    played = true;
                }
            }

            // 🏆 Check win
            if (hand.isEmpty()) {
                System.out.println(current + 1);

                // print stack LIFO
                while (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
                return;
            }

            // next player
            current = (current + 1) % 4;
        }
    }

    // find smallest card (by category first, then value)
    static Card findSmallest(List<Card> hand) {
        Card best = hand.get(0);

        for (Card c : hand) {
            if (c.category < best.category ||
               (c.category == best.category && c.value < best.value)) {
                best = c;
            }
        }

        return best;
    }
}