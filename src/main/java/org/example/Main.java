package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    //Поиск числа в массиве. Ищем индекс константы года
    public static int findNumberInArray(int[] vekConstantForIndex, int vekConstantIndexTemp) {
        for (int vekConstantIndex = 0; vekConstantIndex < vekConstantForIndex.length; vekConstantIndex++) {
            if (vekConstantForIndex[vekConstantIndex] == vekConstantIndexTemp) {
                return vekConstantIndex;
            }
        }
        return -1;
    }

public static void main(String[] args) {
        String buttonName = "Нажмите, чтобы рассчитать дату"; //подпись кнопки
        String windowName = "Калькулятор для дня недели"; //подпись окна программы
        short windowWidth = 800; //Разрешение окна программы
        short windowHeight = 600; //Разрешение окна программы
        short buttonCalcHeight = 30; //Ширина кнопки

        //Создаем кнопку, временно. Для дальнейшего просчета координат.
        JButton buttonCalcTemp = new JButton(buttonName);
        FontMetrics fm = buttonCalcTemp.getFontMetrics(buttonCalcTemp.getFont());
        int textWidth = fm.stringWidth(buttonName);
        int buttonCalkWidth = textWidth + buttonCalcTemp.getInsets().left + buttonCalcTemp.getInsets().right;

        // Расчет центра, для размещения кнопки
        short yButtonCalk = 300;
        int xTemp = (windowWidth / 2) - (buttonCalkWidth / 2);
        short xButtonCalk = (short) xTemp;


        //Создание окна программы

        JFrame
        frame = new JFrame(windowName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setLayout(new BorderLayout());

    // Отключаем layout manager, чтобы размешать элемента интерфейса руками
        frame.setLayout(null);

        // удаляем временную кнопку
        frame.getContentPane().remove(buttonCalcTemp);
        frame.revalidate();
        frame.repaint();

        // создаем кнопку для начала вычисления
        JButton buttonCalc= new JButton(buttonName); // Создание новой кнопки
        buttonCalc.setBounds(xButtonCalk,yButtonCalk,buttonCalkWidth, buttonCalcHeight); //координаты и размер кнопки
        frame.getContentPane().add(buttonCalc); //добавляем кнопку на панель содержимого фрейма(окна)

        //Создаем фреймы с подписями полей
        JLabel labelYear = new JLabel("Введите год 1600-2099"); //Создание нового поля с надписью
        labelYear.setBounds(50, 20, 150, 30); //Координаты поля
        frame.getContentPane().add(labelYear);//Добавляем фрейм с надписью на панель содержимого фрейма(окна)

        //Создаем фреймы с подписями полей
        JLabel labelData = new JLabel("Выберите месяц"); //Создание нового поля с надписью
        labelData.setBounds(250, 20, 100, 30); //Координаты поля
        frame.getContentPane().add(labelData);//Добавляем фрейм с надписью на панель содержимого фрейма(окна)

        //Создаем фреймы с подписями полей
        JLabel labelDay = new JLabel("Выберите дату"); //Создание нового поля с надписью
        labelDay.setBounds(450, 20, 100, 30); //Координаты поля
        frame.getContentPane().add(labelDay);//Добавляем фрейм с надписью на панель содержимого фрейма(окна)

        //Создаем поле для ввода текста(года)
        JTextField textFieldYear = new JTextField(20); //Создание нового текстового поля, для ввода года
        textFieldYear.setBounds(50, 50, 50, 30); //Размер и координаты поля
        frame.getContentPane().add(textFieldYear); //Добавляем поле панель содержимого фрейма(окна)


        String[] month = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

        //Создаем выпадающий список, содержит месяцы, для выбора месяца
        JComboBox<String> comboBoxMonth = new JComboBox<>(month); //Создание нового объекта, выпадающий список
        comboBoxMonth.setBounds(250, 50, 100, 30);//Координаты и размер
        frame.getContentPane().add(comboBoxMonth);//Добавляем выпадающий список на панель содержимого фрейма(окна)

        int[] dayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Integer[] monthDay = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

        //Создаем выпадающий список, содержит дни, для выбора числа в месяце
        JComboBox<Integer> comboBoxDay = new JComboBox<>(monthDay);//Создание нового объекта, выпадающий список
        comboBoxDay.setBounds(450, 50, 100, 30);//Координаты и размер
        frame.getContentPane().add(comboBoxDay);//Добавляем выпадающий список на панель содержимого фрейма(окна)

        //Тестовое поле для вывода результата расчета или ошибок ввода
        JTextArea infoTextArea = new JTextArea();//Создание нового текстового поля, для ввода года
        infoTextArea.setEditable(false); // Запрещаем редактирование
        infoTextArea.setBounds(150, 100, 300, 30);//Координаты и размер
        frame.getContentPane().add(infoTextArea);//добавляем кнопку на панель содержимого фрейма(окна)

        //Добавляем слушатель нажатия кнопки расчета
        buttonCalc.addActionListener(new ActionListener() {
            @Override //Переопределение метода, actionPerformed переопределяет метод из интерфейса addActionListener
            public void actionPerformed(ActionEvent e) { //Метод, который обрабатывает событие нажатие кнопки

                //Получение данных из поля год
                String enteredText = textFieldYear.getText();
                int enteredYearInt = Integer.parseInt(enteredText);//Конвертируем строку в число

                //Получение индекса месяца из выпадающего списка
                int selectedMonthIndex = comboBoxMonth.getSelectedIndex();

                //Получение дня из выпадающего списка, для которого будет проведен расчет
                Integer selectedDay = (Integer) comboBoxDay.getSelectedItem();
                int selectedDayInt = selectedDay; //Конвертируем в Int. Что-то не работало с Integer, пришлось перевести в примитив.

                // Проверка на високосный год, запись для февраля 28 либо 29 дней
                int visYear = enteredYearInt % 4;//проверка на високосный год
                if (visYear == 0) {
                    dayInMonth[1] = 29;
                }else {
                    dayInMonth[1] = 28;
                }

                //Проверка на правильность ввода года. Год водим в диапазоне 1600-2099
                if (1600 > enteredYearInt || enteredYearInt > 2100)
                {
                    String infoText = ("Введите год из диапазона 1600 - 2099");
                    infoTextArea.setText(""); //очищаем поле для вывода результата расчета
                    infoTextArea.append(infoText); //выводим текст ошибки в поле для результата
                }
                //Проверяем, правильно ли выбрано число в месяце. По хорошему нужно сделать автоматически ограничивать месяцы по количеству дней
                // Но это я сделаю позже, идея есть.
                else if (selectedDay > dayInMonth[selectedMonthIndex])
                    {
                        String infoText = ("В месяце " + month[selectedMonthIndex] + " только " + dayInMonth[selectedMonthIndex] + " дней");
                        infoTextArea.setText(""); //очищаем поле для вывода результата расчета
                        infoTextArea.append(infoText); //выводим текст ошибки в поле для результата
                    }
                else {
                        int[] vekConstant = {0, 5, 3, 1, 0}; //Константа для века
                        int[] vekConstantForIndex = {16, 17, 18, 19, 20};//Константа для сравнения и поиска индекса

                        int vekConstantIndexTemp = enteredYearInt /100; //временные переменные, необходимы для вычислений

                        int vekConstantIndex = findNumberInArray(vekConstantForIndex, vekConstantIndexTemp);//Вызываем метод расчета индекса для константы века

                        int calcYearConstant = (((enteredYearInt % 100) / 4 + (enteredYearInt % 100)) % 7) + vekConstant[vekConstantIndex];//Расчет Константы года

                        int[] monthConstant = {6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};//Константы месяца
                        int weakIndex; //Индекс для недели


                        //Проверка на високосный год, и корректировка на январь и февраль для високосного года
                        if (visYear == 0 && selectedMonthIndex < 2) {
                               weakIndex = (- 1 + calcYearConstant + monthConstant[selectedMonthIndex] + selectedDayInt) % 7;
                           }
                            else {
                            weakIndex = (calcYearConstant + monthConstant[selectedMonthIndex] + selectedDayInt) % 7;
                        }

                        String[] week = {"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
                        infoTextArea.setText("");//Очищаем тестовое поле вывода результата
                        infoTextArea.append(week[weakIndex]);} //Выводим день недели после расчета

            }
        });

        frame.setVisible(true);//Делаем все кнопки видимыми
    }
}