import java.util.*;

public class Nomor1 {
    static class Pair {
        int time;
        int idx;

        Pair(int t, int i) {
            time = t;
            idx = i;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int T = sc.nextInt();

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(sc.nextInt(), i + 1);
        }

        // sort berdasarkan waktu
        Arrays.sort(arr, Comparator.comparingInt(a -> a.time));

        int time = 0;
        int left = n - 1;

        List<String> steps = new ArrayList<>();
        Set<Integer> survivors = new HashSet<>();

        while (left >= 3) {
            int a = arr[0].time;
            int b = arr[1].time;
            int y = arr[left - 1].time;
            int z = arr[left].time;

            int strategy1 = a + 2 * b + z;
            int strategy2 = 2 * a + y + z;

            int cost = Math.min(strategy1, strategy2);

            if (time + cost > T) {
                steps.add(arr[left - 1].idx + " " + arr[left].idx + " ->");
                survivors.add(arr[left - 1].idx);
                survivors.add(arr[left].idx);
                break;
            }

            if (strategy1 <= strategy2) {
                // a b ->
                steps.add(arr[0].idx + " " + arr[1].idx + " ->");
                survivors.add(arr[0].idx);
                survivors.add(arr[1].idx);

                // a <-
                steps.add(arr[0].idx + " <-");

                // y z ->
                steps.add(arr[left - 1].idx + " " + arr[left].idx + " ->");
                survivors.add(arr[left - 1].idx);
                survivors.add(arr[left].idx);

                // b <-
                steps.add(arr[1].idx + " <-");

                time += strategy1;
            } else {
                // a z ->
                steps.add(arr[0].idx + " " + arr[left].idx + " ->");
                survivors.add(arr[0].idx);
                survivors.add(arr[left].idx);

                // a <-
                steps.add(arr[0].idx + " <-");

                // a y ->
                steps.add(arr[0].idx + " " + arr[left - 1].idx + " ->");
                survivors.add(arr[0].idx);
                survivors.add(arr[left - 1].idx);

                // a <-
                steps.add(arr[0].idx + " <-");

                time += strategy2;
            }

            left -= 2;
        }

        // handle sisa
        if (left == 2) {
            int cost = arr[0].time + arr[1].time + arr[2].time;
            if (time + cost <= T) {
                steps.add(arr[0].idx + " " + arr[1].idx + " ->");
                survivors.add(arr[0].idx);
                survivors.add(arr[1].idx);

                steps.add(arr[0].idx + " <-");

                steps.add(arr[0].idx + " " + arr[2].idx + " ->");
                survivors.add(arr[0].idx);
                survivors.add(arr[2].idx);
            }
        } else if (left == 1) {
            if (time + arr[1].time <= T) {
                steps.add(arr[0].idx + " " + arr[1].idx + " ->");
                survivors.add(arr[0].idx);
                survivors.add(arr[1].idx);
            }
        } else if (left == 0) {
            if (time + arr[0].time <= T) {
                steps.add(arr[0].idx + " ->");
                survivors.add(arr[0].idx);
            }
        }

        for (int i = 0; i < steps.size(); i++) {
            System.out.print(steps.get(i));
            if (i < steps.size() - 1) System.out.print(" ");
        }
        System.out.println();

        // cari non-survivors
        List<Integer> dead = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!survivors.contains(i)) {
                dead.add(i);
            }
        }

        if (!dead.isEmpty()) {
            System.out.print("Non-survivors: [");
            for (int i = 0; i < dead.size(); i++) {
                System.out.print(dead.get(i));
                if (i < dead.size() - 1) System.out.print(",");
            }
            System.out.println("]");
        }
    }
}