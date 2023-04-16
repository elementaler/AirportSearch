package airport;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print("Введите название аэропорта: ");
            String name = sc.nextLine();
            if (name.equals("!quit")) {
                break; // если введён текст "!quit", выходим из цикла
            }
            System.out.print("Введите фильтр поиска: ");
            String filter = sc.nextLine();

            long startTime = System.currentTimeMillis();
            AirportSearch.init();
            List<Airport> result = AirportSearch.search(name, filter); //"Bo", "2>20000 & 6='America/New_York'"
            long endTime = System.currentTimeMillis();

            System.out.println("Найдено: " + result.size() + " строк");
            System.out.println("Время выполнения запроса: " + (endTime - startTime) + " мс");

            System.out.print("Введите 'Enter' для нового поиска или '!quit' для выхода: ");
            input = sc.nextLine();
            if (input.equals("!quit")) {
                sc.close();
                break;
            }
        }
    }
}