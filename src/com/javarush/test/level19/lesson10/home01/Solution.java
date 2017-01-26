package com.javarush.test.level19.lesson10.home01;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом

Для каждого имени посчитать сумму всех его значений
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени
Закрыть потоки. Не использовать try-with-resources

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        String file = args[0];
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Map<String, Double> map = new TreeMap<>();
            while (reader.ready()) {
                String[] nameAndNumber = reader.readLine().split(" ");
                if (!map.containsKey(nameAndNumber[0])) {
                    map.put(nameAndNumber[0], Double.parseDouble(nameAndNumber[1]));
                } else {
                    map.put(nameAndNumber[0], map.get(nameAndNumber[0]) + Double.parseDouble(nameAndNumber[1]));
                }
            }
            reader.close();


            for (Map.Entry<String, Double> pair : map.entrySet()) {
                System.out.println(pair.getKey() + " " + pair.getValue());
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
