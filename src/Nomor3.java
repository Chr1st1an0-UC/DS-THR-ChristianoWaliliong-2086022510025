import java.util.*;

public class Nomor3 {

    static class Person {
        String name;
        int key;
        int priority;
        int order; // untuk jaga urutan FIFO

        Person(String n, int k, int p, int o) {
            name = n;
            key = k;
            priority = p;
            order = o;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        // queue key (tidak terlalu dipakai tapi sesuai soal)
        Queue<Integer> keyQueue = new LinkedList<>();
        String[] keys = sc.nextLine().split(" ");
        for (String k : keys) {
            keyQueue.add(Integer.parseInt(k));
        }

        // input nama + key
        String[] data = sc.nextLine().split(" ");

        // input priority
        String[] pr = sc.nextLine().split(" ");

        List<Person> list = new ArrayList<>();

        int idx = 0, order = 0;
        for (int i = 0; i < data.length; i += 2) {
            String name = data[i];
            int key = Integer.parseInt(data[i + 1]);
            int priority = Integer.parseInt(pr[idx++]);

            list.add(new Person(name, key, priority, order++));
        }

        // sort berdasarkan priority lalu order
        Collections.sort(list, (a, b) -> {
            if (a.priority != b.priority)
                return a.priority - b.priority;
            return a.order - b.order;
        });

        // output
        for (Person p : list) {
            System.out.println(p.name + " | " + p.key);
        }
    }
}