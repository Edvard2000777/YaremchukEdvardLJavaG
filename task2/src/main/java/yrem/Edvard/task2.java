package yrem.Edvard;

import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;
import java.util.Arrays;

public class task2 {
    public static void main(String[] args) throws IOException
        {
        if (args.length < 2) {
            System.out.println("Использование: java task2 <путь_к_файлу_с_окружностью> <путь_к_файлу_с_точками>");
            return;
        }
            try (BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
                 BufferedReader reader2 = new BufferedReader(new FileReader(args[1]))) {

                // Читаем первую строку (центр и радиус окружности)
                int[] circleData = Arrays.stream(reader1.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int centerX = circleData[0], centerY = circleData[1], radius = circleData[2];

                String line;
                while ((line = reader2.readLine()) != null) {
                    int[] point = Arrays.stream(line.split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    int dx = point[0] - centerX, dy = point[1] - centerY;
                    double distance = Math.sqrt(dx * dx + dy * dy);

                    System.out.println(distance < radius ? "1" : (distance == radius ? "0" : "2"));
                }
            }

        }
    }
