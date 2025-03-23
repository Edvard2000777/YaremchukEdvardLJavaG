package yrem.Edvard;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод пути к файлам через консоль
        System.out.print("Введите путь к файлу values.json: ");
        String valuesFilePath = scanner.nextLine();

        System.out.print("Введите путь к файлу tests.json: ");
        String testsFilePath = scanner.nextLine();

        System.out.print("Введите путь к файлу report.json (куда сохранить отчет): ");
        String reportFilePath = scanner.nextLine();

        scanner.close();

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Читаем values.json
            JsonNode valuesRoot = objectMapper.readTree(new File(valuesFilePath));
            Map<Integer, Integer> valuesMap = new HashMap<>();

            for (JsonNode node : valuesRoot.get("values")) {
                int id = node.get("id").asInt();
                int value = node.get("value").asInt();
                valuesMap.put(id, value);
            }

            // Читаем tests.json
            JsonNode testsRoot = objectMapper.readTree(new File(testsFilePath));

            // Заполняем поле "value"
            fillValues(testsRoot, valuesMap);

            // Записываем в report.json
            objectMapper.writeValue(new File(reportFilePath), testsRoot);
            System.out.println("Отчет успешно создан: " + reportFilePath);

        } catch (IOException e) {
            System.err.println("Ошибка при обработке JSON-файлов: " + e.getMessage());
        }
    }

    private static void fillValues(JsonNode node, Map<Integer, Integer> valuesMap) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;
            if (objectNode.has("id") && valuesMap.containsKey(objectNode.get("id").asInt())) {
                objectNode.put("value", valuesMap.get(objectNode.get("id").asInt()));
            }
            for (JsonNode child : node) {
                fillValues(child, valuesMap);
            }
        } else if (node.isArray()) {
            for (JsonNode child : node) {
                fillValues(child, valuesMap);
            }
        }
    }
}