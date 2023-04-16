package airport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AirportSearch {
    private static List<Airport> airports; // список всех аэропортов
    private static final String EQUALS_OPERATOR = "=";
    private static final String NOT_EQUALS_OPERATOR = "!=";
    private static final String GREATER_THAN_OPERATOR = ">";
    private static final String LESS_THAN_OPERATOR = "<";

    public static void init() throws IOException {
        airports = ReadAirport.readAirports();
    }

    public static List<Airport> search(String name, String filter) {
        List<Airport> result = new ArrayList<>();
        if (airports == null || airports.isEmpty()) {
            return result; // список аэропортов еще не инициализирован
        }
        List<String> properties = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        List<String> values = new ArrayList<>();
        parseFilter(filter, properties, operators, values); // парсим фильтр
        for (Airport airport : airports) {
            if (name == null || airport.getName().toLowerCase().contains(name.toLowerCase())) { // фильтрация по названию аэропорта
                boolean matches = true;
                for (int i = 0; i < properties.size(); i++) { // фильтрация по свойствам (столбцам) аэропорта
                    String property = properties.get(i);
                    String operator = operators.get(i);
                    String value = values.get(i);
                    String propValue = airport.getProperty(Integer.parseInt(property));

                }
                if (matches) {
                    result.add(airport); // добавляем аэропорт, если он удовлетворяет всем условиям фильтра
                }
            }
        }
        return result;
    }

    private static void parseFilter(String filter, List<String> properties, List<String> operators, List<String> values) {
        String[] tokens = filter.split("[()&| ]"); // разбиваем фильтр на токены
        for (String token : tokens) {
            if (!token.isEmpty()) {
                if (token.contains("=")) {
                    String[] parts = token.split("=");
                    properties.add(parts[0]);
                    operators.add(EQUALS_OPERATOR);
                    values.add(parts[1]);
                } else if (token.contains("!=")) {
                    String[] parts = token.split("!=");
                    properties.add(parts[0]);
                    operators.add(NOT_EQUALS_OPERATOR);
                    values.add(parts[1]);
                } else if (token.contains(">")) {
                    String[] parts = token.split(">");
                    properties.add(parts[0]);
                    operators.add(GREATER_THAN_OPERATOR);
                    values.add(parts[1]);
                } else if (token.contains("<")) {
                    String[] parts = token.split("<");
                    properties.add(parts[0]);
                    operators.add(LESS_THAN_OPERATOR);
                    values.add(parts[1]);
                } else if (Character.isDigit(token.charAt(0))) {
                    properties.set(properties.size() - 1, properties.get(properties.size() - 1) + token); // объединяем номер свойства с операцией сравнения, если они были разделены пробелом
                    values.set(values.size() - 1, values.get(values.size() - 1) + token);
                }
            }
        }
    }

    private static boolean compare(String a, String operator, String b) {
        if (a == null || b == null) {
            return operator.equals(NOT_EQUALS_OPERATOR); // если одно из значений Null, то оператор != возвращает true
        }
        switch (operator) {
            case EQUALS_OPERATOR:
                return a.equals(b);
            case NOT_EQUALS_OPERATOR:
                return !a.equals(b);
            case GREATER_THAN_OPERATOR:
                return Double.parseDouble(a) > Double.parseDouble(b);
            case LESS_THAN_OPERATOR:
                return Double.parseDouble(a) < Double.parseDouble(b);
            default:
                return false;
        }
    }
}