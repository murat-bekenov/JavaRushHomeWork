package com.javarush.test.level22.lesson18.big01;

import java.util.ArrayList;

/**
 * Класс Field описывает "поле клеток" игры Тетрис
 */
public class Field
{
    //ширина и высота
    private int width;
    private int height;

    //матрица поля: 1 - клетка занята, 0 - свободна
    private int[][] matrix;

    public Field(int width, int height)
    {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int[][] getMatrix()
    {
        return matrix;
    }

    /**
     * Метод возвращает значение, которое содержится в матрице с координатами (x,y)
     * Если координаты за пределами матрицы, метод возвращает null.
     */
    public Integer getValue(int x, int y)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    /**
     *  Метод устанавливает переданное значение(value) в ячейку матрицы с координатами (x,y)
     */
    public void setValue(int x, int y, int value)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = value;
    }

    /**
     * Метод печатает на экран текущее содержание матрицы
     */
    public void print()
    {
        //Создаем массив, куда будем "рисовать" текущее состояние игры
        int[][] canvas = new int[height][width];

        //Копируем "матрицу поля" в массив
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                canvas[i][j] = matrix[i][j];
            }
        }

        //Копируем фигурку в массив, только непустые клетки
        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int[][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (top + i >= height || left + j >= width) continue;
                if (brickMatrix[i][j] == 1)
                    canvas[top + i][left + j] = 2;
            }
        }


        //Выводим "нарисованное" на экран, но начинаем с "границы кадра".
        System.out.println("---------------------------------------------------------------------------\n");

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int index = canvas[i][j];
                if (index == 0)
                    System.out.print(" . ");
                else if (index == 1)
                    System.out.print(" X ");
                else if (index == 2)
                    System.out.print(" X ");
                else
                    System.out.print("???");
            }
            System.out.println();
        }


        System.out.println();
        System.out.println();
    }

    /**
     * Удаляем заполненные линии
     */
    public void removeFullLines()
    {
        //Например так:
        //Создаем список для хранения линий
        //Копируем все непустые линии в список.
        //Добавляем недостающие строки в начало списка.
        //Преобразуем список обратно в матрицу


        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < getHeight(); i++) {
            int count = 0;
            for (int j = 0; j < getWidth(); j++) {
                if (matrix[i][j] != 0) {
                    count++;
                }
            }
            if (count == getWidth()) {
                list.add(i);
            }
        }


        System.out.println();

        for (Integer integer : list) {
            for (int i = 0; i < getHeight(); i++) {
                matrix[integer][i] = 0;
            }
        }

        for (Integer integer : list) {
            for (int i = 0; i < getHeight(); i++) {
                matrix[integer] = null;
            }
        }


        ArrayList<int[]> notNull = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++) {
            if (matrix[i] != null) {
                notNull.add(matrix[i]);
            }
        }


    }

    public static void main(String[] args)
    {
        Field field = new Field(4, 4);
        for (int i = 0; i < field.getWidth(); i++) {
            field.matrix[2][i] = 1;
            field.matrix[3][i] = 1;
            field.matrix[0][i] = 1;
        }


        field.matrix[1][2] = 1;

        field.myPrint();

        field.removeFullLines();

        field.myPrint();
    }

    public void myPrint() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if (matrix[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

}