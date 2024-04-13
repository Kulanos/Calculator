import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = calc(input);
        System.out.println(output);

    }

    public static String[] parsingSring(String input) {
        String[] str = input.split("\\s*[\\+\\-\\*\\/]+\\s*");

        if (str.length > 2) {
            throw new IllegalArgumentException("т.к. формат математической операции не удовлетворяет заданию");
        } else if (str.length < 2) {
            throw new IllegalArgumentException("т.к. строка не является математической операцией");
        } else {
            return str;
        }

    }

    public static String calc(String input) {
        String[] str = parsingSring(input);
        String output = "";
        int value1 = 0;
        int value2 = 0;
        int result = 0;

        if (BoolianIsNums.isArabic(str[0]) && BoolianIsNums.isArabic(str[1])) {
            value1 = Integer.parseInt(str[0]);
            value2 = Integer.parseInt(str[1]);
            result = Arifmetics.resultArithmetic(value1, value2, input);
            output = String.valueOf(result);
        } else if (BoolianIsNums.isRoman(str[0]) && BoolianIsNums.isRoman(str[1])) {
            value1 = BoolianIsNums.romanToArabic(str[0]);
            value2 = BoolianIsNums.romanToArabic(str[1]);
            result = Arifmetics.resultArithmetic(value1, value2, input);
            output = ConverterRomanToArabic.arabicToRoman(result);
            if (result < 1) {
                throw new IllegalArgumentException("т.к. в римской системе нет отрицательных чисел");
            }
        } else if(BoolianIsNums.isRoman(str[0]) && BoolianIsNums.isArabic(str[1]) || BoolianIsNums.isArabic(str[0]) && BoolianIsNums.isRoman(str[1])){
            throw new IllegalArgumentException("т.к. используются разные системы счисления");
        } else {
            throw new IllegalArgumentException("т.к диапазон значений превышает допустимые от 1 до 10 включительно");
        }


        return output;
    }
}


class BoolianIsNums {

    private static final String[] ARABIC = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] ROMAN = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static boolean isArabic(String s) {
        for (String a : ARABIC) {
            if (a.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRoman(String s) {
        for (String a : ROMAN) {
            if (a.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int romanToArabic(String s) {
        int result = 0;
        for (int i = 0; i < ROMAN.length; i++) {
            if (s.equals(ROMAN[i])) {
                result = Integer.parseInt(ARABIC[i]);
            }
        }
        return result;
    }
}

class ConverterRomanToArabic {
    private static final int[] ARABIC = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String arabicToRoman(int n) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (n > 0) {
            while (n >= ARABIC[i]) {
                sb.append(ROMAN[i]);
                n -= ARABIC[i];
            }
            i++;
        }
        return sb.toString();
    }

}

class Arifmetics {
    public static int resultArithmetic(int n1, int n2, String input) {
        int result = 0;

        if (n1 < 1 || n1 > 10 && n2 < 1 || n2 > 10) {
            throw new IllegalArgumentException("т.к. не удовлетворяте заданному диапазону значений от 1 до 10");
        }
        if (input.contains("+")) {
            result = n1 + n2;
        } else if (input.contains("-")) {
            result = n1 - n2;
        } else if (input.contains("*")) {
            result = n1 * n2;
        } else if (input.contains("/")) {
            result = n1 / n2;
        }
        return result;
    }
}





