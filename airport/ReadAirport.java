package airport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAirport {
    private static final String FILE_NAME = "airports.csv"; // имя файла с данными аэропортов
    private static final int NAME_COL_INDEX = 1; // индекс столбца с названием аэропорта

    public static List<Airport> readAirports() throws IOException {
        List<Airport> airports = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(","); // разбиваем строку на столбцы
                String name = cols[NAME_COL_INDEX]; // получаем название аэропорта из нужного столбца
                String[] properties = new String[cols.length - 1]; // массив свойств (столбцов) аэропорта, кроме названия
                int j = 0;
                for (int i = 0; i < cols.length; i++) {
                    if (i != NAME_COL_INDEX) {
                        properties[j] = cols[i];
                        j++;
                    }
                }
                airports.add(new Airport(name, properties)); // создаем объект Airport и добавляем в список
            }
        }
        return airports;
    }
}