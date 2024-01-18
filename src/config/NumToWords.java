/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author kulakesh
 */
public class NumToWords {

    String string;
    String st1[] = {"Zero", "One", "Two", "Three", "Four", "Five", "Six",
        "Seven", "Eight", "Nine",};
    String st2[] = {"Hundred", "Thousand", "Lac", "Crore"};
    String st3[] = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
        "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Ninteen",};
    String st4[] = {"Twenty", "Thirty", "Fourty", "Fifty", "Sixty", "Seventy",
        "Eighty", "Ninty"};

    public String convertAll(String number) {
        String str2 = "";
        String numSplt[] = number.split("\\.");
        int rupees = Integer.parseInt(numSplt[0]);
        String str1 = "Rupees ";
        str1 += convert(rupees);
        int paise = 0;
        if(numSplt.length>=2){
            paise = Integer.parseInt(numSplt[1]);
        }
        if (paise != 0) {
            str2 += " and";
            str2 += " Paise ";
            str2 += convert(paise);
        }
        return str1 + str2 + " Only";
    }

    public String convert(int number) {
        int n = 1;
        int word;
        string = "";
        while (number != 0) {
            switch (n) {
                case 1:
                    word = number % 100;
                    pass(word);
                    if (number > 100 && number % 100 != 0) {
                        show(" ");///PUT "and " for like one thousand two hundred AND thirty two (1,232)
                    }
                    number /= 100;
                    break;
                case 2:
                    word = number % 10;
                    if (word != 0) {
                        show(" ");
                        show(st2[0]);
                        show(" ");
                        pass(word);
                    }
                    number /= 10;
                    break;
                case 3:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[1]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;
                case 4:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[2]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;
                case 5:
                    word = number % 100;
                    if (word != 0) {
                        show(" ");
                        show(st2[3]);
                        show(" ");
                        pass(word);
                    }
                    number /= 100;
                    break;
            }
            n++;
        }
        return string;
    }

    public void pass(int number) {
        int word, q;
        if (number < 10) {
            show(st1[number]);
        }
        if (number > 9 && number < 20) {
            show(st3[number - 10]);
        }
        if (number > 19) {
            word = number % 10;
            if (word == 0) {
                q = number / 10;
                show(st4[q - 2]);
            } else {
                q = number / 10;
                show(st1[word]);
                show(" ");
                show(st4[q - 2]);
            }
        }
    }

    public void show(String s) {
        String st;
        st = string;
        string = s;
        string += st;
    }
}
