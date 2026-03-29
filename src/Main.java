import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        
        int n = sc.nextInt();
        Stack<String> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();

        // Fill the Stack (First group of n)
        for (int i = 0; i < n; i++) {
            stack.push(sc.next());
        }

        // Fill the Queue (Second group of n)
        for (int i = 0; i < n; i++) {
            queue.add(sc.next());
        }

        long result = 0;
        String currentOp = "+"; // Default starting operator

        while (!stack.isEmpty() && !queue.isEmpty()) {
            String sVal = stack.pop();
            String qVal = queue.poll();

            // Logic: If either is an operator, update currentOp. 
            // If both are numbers, apply currentOp.
            // (Note: Specific 'confusing' logic depends on lecturer's secret priority)
            
            result = applyOp(result, sVal, qVal, currentOp);
        }

        System.out.println(result);
    }

    private static long applyOp(long res, String s, String q, String op) {
        // Custom logic to handle the "Confusing" algorithm
        // Usually involves checking if s or q is numeric vs operator
        return res; 
    }
}