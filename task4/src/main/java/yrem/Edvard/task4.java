package yrem.Edvard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: java CommandLineArgsExample <путь_к_файлу_с_числами>");
            return;
        }

        String filePath = args[0];

        try {
            // Читаем числа из файла
            List<Integer> numbers = new ArrayList<>();
            Scanner fileScanner = new Scanner(new File(filePath));

            while (fileScanner.hasNextInt()) {
                numbers.add(fileScanner.nextInt());
            }
            fileScanner.close();

            // Проверяем, есть ли числа в файле
            if (numbers.isEmpty()) {
                System.out.println("Файл пуст или не содержит чисел.");
                return;
            }

            // Сортируем массив и находим медиану
            Collections.sort(numbers);
            int median = numbers.get(numbers.size() / 2);

            // Считаем количество ходов
            int moves = 0;
            for (int num : numbers) {
                moves += Math.abs(num - median);
            }

            // Выводим результат
            System.out.println("Минимальное количество ходов: " + moves);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}