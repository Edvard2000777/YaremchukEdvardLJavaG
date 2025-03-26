package yrem.Edvard;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
public class Task3 {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Ошибка: укажите пути к файлам values.json, tests.json и report.json");
            return;
        }

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode valuesNode = objectMapper.readTree(new File(valuesFilePath));
            JsonNode testsNode = objectMapper.readTree(new File(testsFilePath));

            // Преобразуем values.json в Map для быстрого поиска по ID
            Map<String, String> valuesMap = StreamSupport.stream(valuesNode.spliterator(), false)
                    .filter(node -> node.has("id") && node.has("value"))
                    .collect(Collectors.toMap(
                            node -> node.get("id").asText(),
                            node -> node.get("value").asText()
                    ));

            // Заполняем отчет
            updateReport(testsNode, valuesMap);

            // Записываем результат
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsNode);

            System.out.println("Отчет успешно создан: " + reportFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void updateReport(JsonNode node, Map<String, String> valuesMap) {
        if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode) node;

            JsonNode idNode = objectNode.get("id");
            if (idNode != null && valuesMap.containsKey(idNode.asText())) {
                objectNode.put("value", valuesMap.get(idNode.asText()));
            }

            for (JsonNode childNode : objectNode) {
                updateReport(childNode, valuesMap);
            }
        } else if (node.isArray()) {
            for (JsonNode arrayNode : node) {
                updateReport(arrayNode, valuesMap);
            }
        }
    }
}