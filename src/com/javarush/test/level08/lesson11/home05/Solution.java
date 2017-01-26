package com.javarush.test.level08.lesson11.home05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/* Мама Мыла Раму. Теперь с большой буквы
Написать программу, которая вводит с клавиатуры строку текста.
Программа заменяет в тексте первые буквы всех слов на заглавные.
Вывести результат на экран.

Пример ввода:
  мама     мыла раму.

Пример вывода:
  Мама     Мыла Раму.
*/

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();


        String[] str = s.split("\\s+");


        for (int i = 0; i < str.length; i++) {

            if (!Objects.equals(str[i], ""))
            {
                char[] l = str[i].toCharArray();
                l[0] = Character.toUpperCase(l[0]);
                str[i] = new String(l);
            }

        }


        String resultStr = "";
        int next = 0;
        char[] charArray = s.toCharArray();

        for (int i = 0; i < charArray.length; i++) {

            if (charArray[i] == ' ') {
                resultStr += ' ';
            }  else  if (charArray[i] != ' ') {
                resultStr += str[next];
                i += str[next].length() - 1;
                next++;
            }

        }

        resultStr =  resultStr.trim();
        System.out.println(resultStr);

    }


}
