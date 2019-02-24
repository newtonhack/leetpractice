package code.leet.hard.string;

public class StringValidNumber_65 {
    public static void main(String[] args) {
        System.out.println(isNumber("32.e-.123"));
    }

    public static boolean isNumber(String s) {
        char[] ch = s.toCharArray();
        boolean isValid = true;
        boolean inNumber = false;
        boolean inExponent = false;
        boolean inDecimal = false;
        boolean containsSign = false;
        boolean spacesStill = false;
        boolean spacesAfter = false;


        if (ch.length == 0) {
            return false;
        }

        if (ch.length == 1) {
            return isInteger(ch[0]);
        }

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                if (!inNumber && !inDecimal && !containsSign && !inExponent) {
                    spacesStill = true;
                    continue;
                } else {
                    spacesAfter = true;
                    continue;
                }
            } else {
                spacesStill = false;
                if (!spacesAfter) {
                    if (isInteger(ch[i])) {
                        inNumber = true;
                        continue;
                    }
                    if (ch[i] == 'e' && inNumber) {
                        if (!inExponent) {
                            inExponent = true;
                            inNumber = false;
                            containsSign = false;
                            inDecimal = false;
                            continue;
                        }
                    }
                    if (ch[i] == '.') {
                        if (!inDecimal && !inExponent) {
                            inDecimal = true;
                            continue;
                        }
                    }
                    if (ch[i] == '-' || ch[i] == '+') {
                        if (!containsSign && !inNumber && !inDecimal) {
                            containsSign = true;
                            continue;
                        }
                    }
                }
            }
            isValid = false;
            break;
        }
        return (!inNumber || spacesStill) ? false : isValid;
    }

    public static boolean isInteger(char ch) {
        return (ch >= '0' && ch <= '9');
    }
}
