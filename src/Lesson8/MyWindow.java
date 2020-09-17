package Lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {

    CRC CRC8 = new CRC("CRC-8", 8, 0x07, 0x00, false, false, 0x00);
    CRC CRC8_CDMA2000 = new CRC("CRC-8/CDMA2000", 8, 0x9B, 0xFF, false, false, 0x00);
    CRC CRC8_DARC = new CRC("CRC-8/DARC", 8, 0x39, 0x00, true, true, 0x00);
    CRC CRC8_DVBS2 = new CRC("CRC-8/DVB-S2", 8, 0xD5, 0x00, false, false, 0x00);
    CRC CRC8_EBU = new CRC("CRC-8/EBU", 8, 0x1D, 0xFF, true, true, 0x00);
    CRC CRC8_ICODE = new CRC("CRC-8/ICODE", 8, 0x1D, 0xFD, false, false, 0x00);
    CRC CRC8_ITU = new CRC("CRC-8/ITU", 8, 0x07, 0x00, false, false, 0x55);
    CRC CRC8_MAXIM = new CRC("CRC-8/MAXIM", 8, 0x31, 0x00, true, true, 0x00);
    CRC CRC8_ROHC = new CRC("CRC-8/ROHC", 8, 0x07, 0xFF, true, true, 0x00);
    CRC CRC8_WCDMA = new CRC("CRC-8/WCDMA", 8, 0x9B, 0x00, true, true, 0x00);

    CRC[] CRC8_All = {CRC8, CRC8_CDMA2000, CRC8_DARC, CRC8_DVBS2, CRC8_EBU, CRC8_ICODE, CRC8_ITU, CRC8_MAXIM, CRC8_ROHC, CRC8_WCDMA};

    private int[] bufferForCountCRC;

    public MyWindow () {
        System.out.println( "\nДобро пожаловать!" +
                            "\nДанная программа преобразует введёную строку в ASCII код посимвольно" +
                            "\nи считает контрольную сумму CRC согласно выбранной формуле." +
                            "\nДля проверки корректности можно воспользоваться ресурсом " +
                            "\nhttps://crccalc.com/ . " +
                            "\nПриятного использования!\n");
        setTitle("CRC App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setBounds(0, 0, 400, 100);
        setLocationRelativeTo(null); // for start app in center of screen

        // support panel
        JPanel grid = new JPanel();

        String[] crcList = new String[CRC8_All.length];
        for(int i = 0; i < CRC8_All.length; i++){
            crcList[i] = CRC8_All[i].getCRC_name();
        }

        // List of form components
        JLabel      label_formula = new JLabel("Выберите формулу:");
        JComboBox   crcComboBox = new JComboBox(crcList);
        JTextField  textField_inputString = new JTextField();
        JTextField  resultTextField       = new JTextField();
        JLabel      label_inputString = new JLabel("Введите значение: ");
        JButton     btnCalc = new JButton("Посчитать"   );

        final String[] s = {""};

        crcComboBox.setEditable(false);
        crcComboBox.setAlignmentX(RIGHT_ALIGNMENT);


        crcComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s[0] = (String) crcComboBox.getSelectedItem();      //get the selected item
                System.out.println("\nВыбрана формула: " + s[0]);
            }
        });

        textField_inputString.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = textField_inputString.getText();
                char[] charInputString = inputString.toCharArray();
                int countOfElements  = charInputString.length;

                bufferForCountCRC = new int[countOfElements];

                for (int i = 0; i < countOfElements; i++) {
                    bufferForCountCRC[i] = (int) charInputString[i];
                }
                System.out.println("Строка для расчёта CRC: " + inputString);
            }
        });

        btnCalc.addActionListener(e -> {

            if(bufferForCountCRC != null) {
                for (int i = 0; i < CRC8_All.length; i++) {
                    if (CRC8_All[i].getCRC_name().equals(s[0])) {
                        resultTextField.setText("0x" + Long.toHexString(CRC8_All[i].CRC8Count(bufferForCountCRC, bufferForCountCRC.length)).toUpperCase());
                        System.out.println("Результат: 0x" + Long.toHexString(CRC8_All[i].CRC8Count(bufferForCountCRC, bufferForCountCRC.length)).toUpperCase());
                        break;
                    }
                    else {
                        if(i == CRC8_All.length - 1) {
                            System.out.println("Выберите формулу для расчёта CRC из выпадающего списка!");
                        }
                    }
                }
            }
            else{
                System.out.println("Массив для расчёта не инициализирован! \nВведите значение в поле под выпадающим списком, нажмите Enter и повторите попытку расчёта.");
            }
        });

        GridLayout layout = new GridLayout(2, 2, 0, 0);
        grid.setLayout(layout);
        grid.add(label_formula, 0, 0); grid.add(crcComboBox,0,1); grid.add(btnCalc,0,2);
        grid.add(label_inputString); grid.add(textField_inputString); grid.add(resultTextField);

        getContentPane().add(grid);
        setVisible(true);
    }
}

