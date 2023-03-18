import java.util.List;
import java.util.Scanner;

public class Main {
    static int a = 0;
    static int b = 0;
    static String[] expar;

    public static void main(String[] args) {
        String inp;
        String out;

        Scanner in = new Scanner(System.in);
        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами");
        System.out.println("Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.");
        System.out.println("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
        System.out.println("Калькулятор умеет работать только с целыми числами и с одной системой счисления одновременно");
        System.out.println("Введите выражение в формате 'a + b':");
        inp = in.nextLine();
            try {
                out = calc(inp);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Формат математической операции не удовлетворяет заданию");
            }
         if (out == null) {
            throw new ArithmeticException("В римской системе нет чисел меньше единицы");
        } else { System.out.println("Ответ: " + out); }

    }

    public static String calc(String input) throws ArithmeticException {
        expar = input.trim().split(" ");
        if (expar.length > 3) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию");
        }
        String aIn = expar[0];
        String bIn = expar[2];

        int interResult = 0;
        String result = null;
        Main calculate = new Main();




        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        List<RomanNum> romNumList = List.of(RomanNum.values());



        try {
            a = Integer.parseInt(aIn);
            b = Integer.parseInt(bIn);
            if ((a <= 10) && (b <= 10)) {
                interResult = calculate.calculate();
                result = Integer.toString(interResult);
            } else {
                throw new IllegalArgumentException("Входные числа должны быть не больше 10");
            }
        } catch (NumberFormatException e) {
            while ((aIn.length() > 0) && (i1 < romNumList.size())) {
                RomanNum symbA = romNumList.get(i1);
                if (aIn.startsWith(symbA.name())) {
                    a += symbA.getI();
                    aIn = aIn.substring(symbA.name().length());
                } else {
                    i1++;
                }

            }

            while ((bIn.length() > 0) && (i2 < romNumList.size())) {
                RomanNum symbB = romNumList.get(i2);
                if (bIn.startsWith(symbB.name())) {
                    b += symbB.getI();
                    bIn = bIn.substring(symbB.name().length());
                } else {
                    i2++;
                }
            }
            if ((aIn.length() > 0 ) || (bIn.length() > 0)) {
                throw new IllegalArgumentException("На входе должны быть использованы целые числа из одной системы счисления");
            }
            if ((a <= 10) && (b <= 10)) {
                interResult = calculate.calculate();
                StringBuilder sb = new StringBuilder();
                while ((interResult > 0) && (i3 < romNumList.size())) {
                    RomanNum symb = romNumList.get(i3);
                    if (symb.getI() <= interResult) {
                        sb.append(symb.name());
                        interResult -= symb.getI();
                    } else {
                        i3++;
                    }
                    result = sb.toString();
                }

            } else {
                throw new IllegalArgumentException("Входные числа должны быть не больше 10");
            }


        }


        return result;
    }

    int calculate() {
        int calcResult = 0;
        String oper = expar[1];
        switch (oper) {
            case "+" -> calcResult = a + b;
            case "-" -> calcResult = a - b;
            case "*" -> calcResult = a * b;
            case "/" -> {
                if (b > 0) {
                    calcResult = a / b;
                } else {
                    try {
                        throw new ArithmeticException();
                    } catch (ArithmeticException e) {
                        throw new ArithmeticException("На ноль делить нельзя");
                    }
                }
            }default -> throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию");
        } return (calcResult);
    }

}






