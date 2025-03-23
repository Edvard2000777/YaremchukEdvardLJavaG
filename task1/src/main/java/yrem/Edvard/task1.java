package yrem.Edvard;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: java CommandLineArgsExample <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        if (n < 1 || m < 1) {
            System.out.println("Оба значения n и m должны быть больше или равны 1");
            return;
        }

        StringBuilder path = new StringBuilder();
        StringBuilder intervals = new StringBuilder();
        int index = 0;

        System.out.println("\nРешение:");
        System.out.println("Круговой массив: " + generateSequence(n));

        do {
            path.append(index + 1);
            intervals.append(generateInterval(index, m, n)).append(", ");
            index = (index + m - 1) % n; // рассчитываем следующий индекс
        } while (index != 0);

        System.out.println("При длине обхода " + m + " получаем интервалы: " + intervals.substring(0, intervals.length() - 2) + ".");
        System.out.println("Полученный путь: " + path);
    }

    private static String generateSequence(int n) {
        return new StringBuilder().append("1234567890", 0, n).toString();
    }

    private static String generateInterval(int start, int m, int n) { // рассчитываем интервал
        StringBuilder interval = new StringBuilder(m);
        for (int i = 0; i < m; i++) {
            interval.append(((start + i) % n) + 1);
        }
        return interval.toString();
    }
}