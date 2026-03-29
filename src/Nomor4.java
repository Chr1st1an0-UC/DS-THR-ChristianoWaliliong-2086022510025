import java.util.*;

public class Nomor4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();

        solve(input);
    }

    public static void solve(String input) {
        String[] cards = input.split(" ");

        List<List<String>> stacks = new ArrayList<>();
        List<Set<String>> used = new ArrayList<>();

        for (String card : cards) {
            boolean placed = false;

            // Try oldest stack first
            for (int i = 0; i < stacks.size(); i++) {
                if (!used.get(i).contains(card)) {
                    stacks.get(i).add(card);
                    used.get(i).add(card);
                    placed = true;
                    break;
                }
            }

            // If cannot place, create new stack
            if (!placed) {
                List<String> newStack = new ArrayList<>();
                Set<String> newSet = new HashSet<>();

                newStack.add(card);
                newSet.add(card);

                stacks.add(newStack);
                used.add(newSet);
            }
        }

        // Output (exact format)
        for (int i = 0; i < stacks.size(); i++) {
            List<String> stack = stacks.get(i);

            for (int j = 0; j < stack.size(); j++) {
                System.out.print(stack.get(j));
                if (j != stack.size() - 1) {
                    System.out.print(" ");
                }
            }

            if (i != stacks.size() - 1) {
                System.out.println();
            }
        }
    }
}