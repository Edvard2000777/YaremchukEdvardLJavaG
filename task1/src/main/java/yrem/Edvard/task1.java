package yrem.Edvard;

public class task1 {
    public static void main(String[] args) {


           int n = Integer.parseInt(args[0]);
           int  m = Integer.parseInt(args[1]);

        System.out.println(args[0]);
        System.out.println(args[1]);

        StringBuilder path = new StringBuilder();
        StringBuilder intervals = new StringBuilder();
        int index = 0;
         StringBuilder n1 = new StringBuilder(new StringBuilder().append("1234567890", 0, n));
        System.out.println("\nРешение:");
        System.out.println("Круговой массив: " + n1);

        do {
            path.append(index + 1);
            StringBuilder interval = new StringBuilder(m);
            for (int i = 0; i < m; i++) {
                interval.append(((index + i) % n) + 1);
            }
            intervals.append(interval).append(", ");
            index = (index + m - 1) % n; // рассчитываем следующий индекс
        } while (index != 0);

        System.out.println("При длине обхода " + m + " получаем интервалы: " + intervals.substring(0, intervals.length() - 2) + ".");
        System.out.println("Полученный путь: " + path);
    }
}