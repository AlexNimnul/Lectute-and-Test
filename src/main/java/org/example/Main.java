package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static int findNumberInArray(int[] vekConstantForIndex, int vekConstantIndexTemp) {
        for (int vekConstantIndex = 0; vekConstantIndex < vekConstantForIndex.length; vekConstantIndex++) {
            if (vekConstantForIndex[vekConstantIndex] == vekConstantIndexTemp) {
                return vekConstantIndex;
            }
        }
        return -1;
    }

public static void main(String[] args) {
        String buttonName = "Нажмите, чтобы расчитать дату";
        String windowName = "Калькулятор для дня недели";
        short windowWidth = 800;
        short windowHeight = 600;
        short buttonCalcHeight = 30;

        JButton buttonCalcTemp = new JButton(buttonName);
        FontMetrics fm = buttonCalcTemp.getFontMetrics(buttonCalcTemp.getFont());
        int textWidth = fm.stringWidth(buttonName);
        int buttonCalkWidth = textWidth + buttonCalcTemp.getInsets().left + buttonCalcTemp.getInsets().right;


        short yButtonCalk = 300;
        int xTemp = (windowWidth / 2) - (buttonCalkWidth / 2);
        short xButtonCalk = (short) xTemp;


        JFrame
        frame = new JFrame(windowName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setLayout(new BorderLayout());

        frame.setLayout(null);

        frame.getContentPane().remove(buttonCalcTemp);
        frame.revalidate();
        frame.repaint();

        JButton  buttonCalc= new JButton(buttonName);
        buttonCalc.setBounds(xButtonCalk,yButtonCalk,buttonCalkWidth, buttonCalcHeight);
        frame.getContentPane().add(buttonCalc);

        JLabel labelYear = new JLabel("Введите год 1600-2099");
        labelYear.setBounds(50, 20, 150, 30);
        frame.getContentPane().add(labelYear);

        JLabel labelData = new JLabel("Выберите месяц");
        labelData.setBounds(250, 20, 100, 30);
        frame.getContentPane().add(labelData);

        JLabel labelDay = new JLabel("Выберите дату");
        labelDay.setBounds(450, 20, 100, 30);
        frame.getContentPane().add(labelDay);


        JTextField textFieldYear = new JTextField(20);
        textFieldYear.setBounds(50, 50, 50, 30);
        frame.getContentPane().add(textFieldYear);

        String[] month = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};


        JComboBox<String> comboBoxMonth = new JComboBox<>(month);
        comboBoxMonth.setBounds(250, 50, 100, 30);
        frame.getContentPane().add(comboBoxMonth);

        int[] dayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        Integer[] monthDay = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};

        JComboBox<Integer> comboBoxDay = new JComboBox<>(monthDay);
        comboBoxDay.setBounds(450, 50, 100, 30);
        frame.getContentPane().add(comboBoxDay);

        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false); // Запрещаем редактирование
        infoTextArea.setBounds(150, 100, 300, 30);
        frame.getContentPane().add(infoTextArea);

        buttonCalc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String enteredText = textFieldYear.getText();
                int enteredYearInt = Integer.parseInt(enteredText);

                int selectedMonthIndex = comboBoxMonth.getSelectedIndex();

                Integer selectedDay = (Integer) comboBoxDay.getSelectedItem();
                int selectedDayInt = selectedDay;

                int visYear = enteredYearInt % 4;
                if (visYear == 0) {
                    dayInMonth[1] = 29;
                }else {
                    dayInMonth[1] = 28;
                }

                if (1600 > enteredYearInt || enteredYearInt > 2100)
                {
                    String infoText = ("Введите год из диапазона 1600 - 2099");
                    infoTextArea.setText("");
                    infoTextArea.append(infoText);
                }
                else if (selectedDay > dayInMonth[selectedMonthIndex])
                    {
                        String infoText = ("В месяце " + month[selectedMonthIndex] + " только " + dayInMonth[selectedMonthIndex] + " дней");
                        infoTextArea.setText("");
                        infoTextArea.append(infoText);
                    }
                else {
                        int[] vekConstant = {0, 5, 3, 1, 0};
                        int[] vekConstantForIndex = {16, 17, 18, 19, 20};
                        int vekConstantIndex;

                        int vekConstantIndexTemp = enteredYearInt /100;

                        vekConstantIndex = findNumberInArray(vekConstantForIndex, vekConstantIndexTemp);

                        int calcYearConstant = (((enteredYearInt % 100) / 4 + (enteredYearInt % 100)) % 7) + vekConstant[vekConstantIndex];

                        int[] monthConstant = {6, 2, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4};
                        int weakIndex;

                        if (visYear == 0 && selectedMonthIndex < 2) {
                               weakIndex = (- 1 + calcYearConstant + monthConstant[selectedMonthIndex] + selectedDayInt) % 7;
                           }
                            else {
                            weakIndex = (calcYearConstant + monthConstant[selectedMonthIndex] + selectedDayInt) % 7;
                        }

                        String[] week = {"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
                        infoTextArea.setText("");
                        infoTextArea.append(week[weakIndex]);}




            }
        });

        frame.setVisible(true);
    }
}