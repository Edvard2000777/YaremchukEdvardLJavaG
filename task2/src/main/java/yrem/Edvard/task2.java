package yrem.Edvard;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class task2{
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: java CommandLineArgsExample <путь_к_файлу_с_окружностью> <путь_к_файлу_с_точками>");
            return;
        }

        String circleFilePath = args[0];
        String pointsFilePath = args[1];

        try {
            // Читаем центр окружности и радиус из файла
            Scanner circleScanner = new Scanner(new File(circleFilePath));
            double centerX = circleScanner.nextDouble();
            double centerY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();
            circleScanner.close();

            // Читаем точки из файла
            Scanner pointsScanner = new Scanner(new File(pointsFilePath));
            while (pointsScanner.hasNextDouble()) {
                double x = pointsScanner.nextDouble();
                double y = pointsScanner.nextDouble();

                double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
                double radiusSquared = Math.pow(radius, 2);

                if (distanceSquared < radiusSquared) {
                    System.out.println("1"); // Точка внутри окружности
                } else if (distanceSquared > radiusSquared) {
                    System.out.println("2"); // Точка снаружи окружности
                } else {
                    System.out.println("0"); // Точка на окружности
                }
            }
            pointsScanner.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }
}