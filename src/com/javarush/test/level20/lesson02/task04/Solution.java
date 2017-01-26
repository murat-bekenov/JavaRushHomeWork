package com.javarush.test.level20.lesson02.task04;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* Читаем и пишем в файл статики
Реализуйте логику записи в файл и чтения из файла для класса ClassWithStatic
Метод load должен инициализировать объект включая статические поля данными из файла
Метод main реализован только для вас и не участвует в тестировании
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            ClassWithStatic classWithStatic = new ClassWithStatic();
            classWithStatic.i = 3;
            classWithStatic.j = 4;
            classWithStatic.save(outputStream);
            outputStream.flush();

            ClassWithStatic classWithStatic2 = new ClassWithStatic();
            classWithStatic2.save(outputStream);
            outputStream.flush();


            ClassWithStatic loadedObject = new ClassWithStatic();
//            loadedObject.staticString = "something";
//            loadedObject.i = 6;
//            loadedObject.j = 7;

            loadedObject.load(inputStream);
            //check here that classWithStatic object equals to loadedObject object - проверьте тут, что classWithStatic и loadedObject равны

            System.out.println(loadedObject.i);
            System.out.println(loadedObject.j);


            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class ClassWithStatic {
        public static String staticString = "it's test static string";
        public int i;
        public int j;

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(staticString + '\n');
            writer.write(i + "" + '\n');
            writer.write(j + "" + '\n');
            writer.write('\n');
            writer.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<String> list = new ArrayList<>();
            String string;
            while (!(string = reader.readLine()).equals("")) {
                list.add(string);
            }

            if (!list.isEmpty())
            {
                staticString = list.get(0);
                i = Integer.parseInt(list.get(1));
                j = Integer.parseInt(list.get(2));
            }
        }
    }
}