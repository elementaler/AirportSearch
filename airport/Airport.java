package airport;

public class Airport {
    private String name;
    private String[] properties; // массив свойств (столбцов) аэропорта

    public Airport(String name, String[] properties) {
        this.name = name;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public String getProperty(int index) {
        if (index < 0 || index >= properties.length) {
            return null; // индекс выходит за границы массива свойств
        }
        return properties[index];
    }

    public int getPropertyCount() {
        return properties.length;
    }

    @Override
    public String toString() {
        return name; // возвращаем только название аэропорта при вызове toString()
    }
}