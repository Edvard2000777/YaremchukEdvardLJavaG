package yrem.Edvard;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class task4 {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }


        if (args.length < 1) {
            System.out.println("Использование: java MedianMovesCalculator <путь_к_файлу_с_числами>");
            return;
        }

        String filePath = args[0];

        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            // Читаем числа в массив
            int[] numbers = fileScanner.tokens()
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (numbers.length == 0) {
                System.out.println("Файл пуст или не содержит чисел.");
                return;
            }

            // Сортируем и находим медиану
            Arrays.sort(numbers);
            int median = numbers[numbers.length / 2];

            // Считаем минимальное количество ходов
            int moves = Arrays.stream(numbers)
                    .map(num -> Math.abs(num - median))
                    .sum();

            System.out.println("Минимальное количество ходов: " + moves);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: файл содержит некорректные данные.");
        }
    }
}