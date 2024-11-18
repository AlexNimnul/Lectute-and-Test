package org.example;


public class Main {
    public static void main(String[] args) {

        int monthReal = 11; //Месяц, для теста 1-12
        int year = 1600; //Год, для теста
        int day = 18; //День, для теста
        int monthIndex = monthReal - 1; //Приведение номера месяца из 1-12 к форме 0-11

        String[] month = {"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа",
                        "Сентября", "Октября", "Ноября", "Декабря"};//Месяцы
        int[] dayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};//Дней в месяце
        int[] vekConstant = {0, 5, 3, 1, 0}; //Константа для века
        int[] vekConstantForIndex = {16, 17, 18, 19, 20};//Константа для сравнения и поиска индекса
        int[] monthConstant = {6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//Константы месяца
        String[] week = {"Воскресенье", "Понедельник", "Вторник", "Среда",
                        "Четверг", "Пятница", "Суббота"};//Дни недели
        boolean test = true;

        //проверка на високосный год
        //Изменение констант с учетом високосного года
        int visYear = year % 4;
        if (visYear == 0) {
            dayInMonth[1] = 29;
            monthConstant[0] = 5;
            monthConstant[1] = 1;
        } else {
            //можно не писать, сейчас ни на что не влияет
            //Но в будущем при вводе дат из клавиатуры будет нужно для возврата значений обычного года п
            dayInMonth[1] = 28;
            monthConstant[0] = 6;
            monthConstant[1] = 2;
        }

        //Проверяем на вход года в диапазон дат 1600-2099
        if (1600 > year || year > 2099)
        {
            test = false;
            System.out.println("Неверный год");
            System.out.println("Введите год из диапазона 1600 - 2099");
        }

        //Проверяем на вход даты в месяц
        if (dayInMonth[monthIndex] < day)
        {
            test = false;
            System.out.println("Неверная дата");
            System.out.println("В месяце " + month[monthIndex] + " только " + dayInMonth[monthIndex] + " дней");
        }

        //Проверяем на вход месяца в интервал 1-12
        if (1 > monthReal || monthReal > 12)
        {
            test = false;
            System.out.println("Неверный месяц");
            System.out.println("Введите  месяц в диапазоне 1-12");
        }


        //Проверка на ошибки, если были ошибки, расчеты не выполняем. Ждем исправления

        if (test){
            int vekConstantValue = findVekConstant(vekConstantForIndex, vekConstant, year);//Вызываем метод расчета константы века
            int yearConstantValue = calcYearConstant(year);//Вызываем метод расчета индекса для константы года
            int weekIndex = (vekConstantValue + yearConstantValue + monthConstant[monthIndex] + day) %7;//Расчет дня недели
            System.out.println(day + " " + month[monthIndex] + " " + year + " это " + week[weekIndex]);
        }


    }

    //Поиск числа в массиве. Вычисляем константу века
    static int findVekConstant(int[] vekConstantForIndex, int[] vekConstantIndex, int year) {
        int vek = year / 100;//Век, оставляем только столетия
        for (int index = 0; index < vekConstantForIndex.length; index++) {
            if (vekConstantForIndex[index] == vek) {
                return vekConstantIndex[index];
            }
        }
        return -1;
    }

    static int calcYearConstant(int year){
        return  ((year % 100) / 4 + (year % 100)) % 7;
    }

}