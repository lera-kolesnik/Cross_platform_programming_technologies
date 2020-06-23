package tasks;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class task_5 {
    public static int[] encrypt(String textLine)// 1 - функция возвращает зашифрованное сообщение
    {
        int[] array = new int[textLine.length()];
        array[0]=textLine.charAt(0);// запрет на первый символ
        for (int i = 1; i<textLine.length();i++)
            array[i]=textLine.charAt(i)-textLine.charAt(i-1);// код другого символа: current_char_code - previous_char_code
        return array;
    }
    public static String decrypt(int[] array)// функция возвращает расшифрованное сообщение
    {
        StringBuilder line = new StringBuilder();
        line.append((char) array[0]);// запрет на первый симвл
        for (int i = 1; i<array.length;i++)
            line.append((char) (array[i] + (int) line.charAt(i - 1)));// код другого символа: current_char_code + previous_char_code
        return line.toString();
    }
    public static boolean canMove(String figure, String startPos, String endPos)// 2 - функция проверяет, может ли фигура достичь цели
    {
        boolean canItMove = false;
        figure = figure.toLowerCase();
        //get position
        char sPosLetter = Character.toUpperCase(startPos.charAt(0));
        char ePosLetter = Character.toUpperCase(endPos.charAt(0));
        int sPosDigit = Integer.parseInt(String.valueOf((char)startPos.charAt(1)));
        int ePosDigit = Integer.parseInt(String.valueOf((char)endPos.charAt(1)));
       
        int dPos = Math.abs(ePosDigit-sPosDigit);// считаю расстояник
        int dPosL = Math.abs(ePosLetter-sPosLetter);
        //проверяю если line item доступна
        if (ePosDigit>0&&ePosDigit<9&&sPosDigit>0&&sPosDigit<9&&ePosLetter>64&&ePosLetter<
                91&&sPosLetter>64&&sPosLetter<91&&startPos.length()==2&&endPos.length()==2)
            switch (figure){
                case "pawn":
                case "пешка":// проверка на пешку
                    if (dPosL==0)
                        if (sPosDigit==2)
                        {
                            if ((dPos==1) || (sPosDigit + 2 == ePosDigit))
                                canItMove = true;
                        }
                        else if (sPosDigit==7)
                        {
                            if ((dPos==1) || (sPosDigit - 2 == ePosDigit))
                                canItMove = true;
                        }
                        else if (dPos==1)
                            canItMove = true;
                    break;
                case "knight":
                case "конь":// проверка на коня
                    if (dPos<3&&dPosL<3&&dPos!=0&&dPosL!=0)
                        if (dPosL==1&&dPos==2)
                            canItMove=true;
                        else if (dPosL==2&&dPos==1)
                            canItMove=true;
                    break;
                case "bishop":
                case "слон":// проверка на слона
                    if (dPos==dPosL&&dPos!=0&&dPosL!=0)
                        canItMove=true;
                    break;
                case "rook":
                case "ладья":// проверка на ладью
                    if (dPos==0||dPosL==0)
                        if (dPos!=dPosL)
                            canItMove=true;
                    break;
                case "king":
                case "король":// проверка на короля
                    if (dPos<2&&dPosL<2)
                        canItMove=true;
                    break;
                case "queen":
                case "ферзь":// проверка на королеву
                    if (dPos==0||dPosL==0)
                        if (dPos!=dPosL)
                            canItMove=true;
                    if (dPos==dPosL&&dPos!=0&&dPosL!=0)
                        canItMove=true;
                    break; 
                default:
                    break;
            }
        return canItMove;
    }
    public static boolean canComplete(String line, String endLine){// 3 - функция проверяет, может ли первое слово быть
    {                                                             // дополнено вторым, используя только существующие буквы
        String tempLine = endLine;
        boolean canBeCompleted = true;
        for(char cChar : line.toCharArray()){// удаляю существующий символ
            if (tempLine.indexOf(cChar) != -1)
                tempLine = tempLine.substring(0, tempLine.indexOf(cChar)) + tempLine.substring(tempLine.indexOf(cChar) + 1);
            else
                canBeCompleted=false;
        }
        String resultLine="";
        if (!line.equals("")) {
            if (canBeCompleted) {
                int sPos = 0;// начало (стартовая позиция)
                int bPos = 0;

                while ((tempLine.length() != 0 && canBeCompleted) || (sPos < line.length() && bPos < endLine.length()-1 && canBeCompleted)) {
                    // проверка на все символы
                    if (sPos < line.length()&&line.charAt(sPos) == endLine.charAt(bPos))
                    {
                        resultLine += line.charAt(sPos);
                        bPos++;
                        sPos++;
                    } else if (tempLine.indexOf(endLine.charAt(bPos)) != -1) {
                        resultLine += tempLine.charAt(tempLine.indexOf(endLine.charAt(bPos)));
                        tempLine = tempLine.substring(0, tempLine.indexOf(endLine.charAt(bPos))) + tempLine.substring(tempLine.indexOf(endLine.charAt(bPos)) + 1);
                        bPos++;
                    } else
                        canBeCompleted = false;
                }
            }
        }
        return canBeCompleted;
    }
}
    public static int sumDigProd(int ... array)// 4 - Функция суммирует все элементы, а затем умножает
    {                                          // каждую цифру из полученных чисел до значения => 10, int result = 0
        int result = 0;
        for (int value : array) result += value;// сумма
        while (result>=10)// умножение
        {
            int tempResult=1;
            for (char ch:String.valueOf(result).toCharArray())
                tempResult*=Integer.parseInt(String.valueOf((char)ch));
            result=tempResult;
        }
        return array.length==0 ? -1 : result;
    }
    public static String sameVowelGroup(String[] array)// 5 - функция ищет слова, где уникальные гласные = 
    {                                                  // = уникальным гласным в первом слове
        String resultLine="";
        String vowels ="";
        for (char cChar:array[0].toCharArray()) // запоминание всех гласных
            vowels+= (cChar=='a' || cChar=='e' ||cChar=='i' || cChar=='o' || cChar=='u')&&vowels.indexOf(cChar)==-1 ? cChar : "";
        for (String cString : array)
        {
            String tVowels="";
            boolean allVowel=true;
            for (char cChar : cString.toCharArray()) { // запоминание всех гласных в текущем слове
                if ((cChar == 'a' || cChar == 'e' || cChar == 'i' || cChar == 'o' || cChar == 'u') && tVowels.indexOf(cChar) == -1)
                    tVowels += cChar;
            }
                if (tVowels.length()==vowels.length()) {
                    for (char cChar : vowels.toCharArray())
                        allVowel = tVowels.indexOf(cChar) != -1 ? allVowel : false;// если все гласные взяты из
                    resultLine += allVowel ? cString + " " : "";                   // первого слова в текущем слове
                }
        }
        return resultLine;
    }
    public static boolean validateCard(long number)// 6 - функция проверяет, является ли номер номером банковской карты
    {
        String cNubmder =String.valueOf(number); // получаю номер строки
        int summ = 0;
        //Luhn algorithm
        for (int i = cNubmder.length()-2;i>=0;i--) { // обратный номер, без последнего (check_value)
            if ((i + 1) % 2 == 1)
            { // двойное значение на нечетном месте
                int temp = 2 * Integer.parseInt(String.valueOf((char) cNubmder.charAt(i)));
                while (temp >= 10) { // суммирует каждую цифру, если она больше 10ти
                    int tempResult = 0;
                    for (char ch : String.valueOf(temp).toCharArray())
                        tempResult += Integer.parseInt(String.valueOf((char) ch));
                    temp = tempResult;
                }
                summ += temp;
            } else
                summ += Integer.parseInt(String.valueOf((char) cNubmder.charAt(i)));
        }
        summ = 10-(summ-10*(int)(summ/10));// 10 - last_numeral
        return Integer.valueOf(String.valueOf(cNubmder.charAt(cNubmder.length()-1)))==summ; // проверяю с check_value
    }
    public static String numToEng(int value)// 7 - функция переводит целое число в строковое представление этого целого числа
    {
        String result = "";
        if (value > 0 && value < 1000) {
            String[] variables_eng = new String[]{"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
                    "nineteen", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
            result += value / 100 != 0 ? variables_eng[value / 100] + " hundred " : "";
            if (value % 100 >= 20 || value % 100 < 10) {
                result += (value / 10) % 10 != 0 ? variables_eng[(value / 10) % 10 + 19] + " " : "";
                result += variables_eng[(value % 10)];
            } else
                result += (value / 10) % 10 != 0 ? variables_eng[(value % 100)] + " " : "";
        }
        return value == 0 ? "Eng = zero" : result;
    }
    public static String numToRus(int value) // функция переводит целое число в строковое представление этого целого числа
    {
        String result="";
        if (value>0&&value<1000) {
            String[] variables_rus = new String[]{"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять","десять",
                    "одиннадцать", "двенадцать", "ринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать",
                    "девятнадцать", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто",
                    "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
            result += value / 100 != 0 ? variables_rus[value / 100 + 28] + " " : "";
            if (value%100>=20||value%100<10)
            {
                result += (value / 10) % 10 != 0 ? variables_rus[(value / 10) % 10 + 19] + " " : "";
                result += variables_rus[(value % 10)];
            }
            else
                result += (value / 10) % 10 != 0 ? variables_rus[(value % 100)]+ " ": "";
        }
        else
            result="Неверное значение";
        return value==0 ? "ноль" : result;
    }
    public static String getSha256Hash(String line) {//8-sha256 хэш
        byte[] hash = new byte[]{};
        try {
            hash = MessageDigest.getInstance("SHA-256").digest(line.getBytes(StandardCharsets.UTF_8));
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("I can`t do it: " + e);
        }
        StringBuilder hexString = new StringBuilder(new BigInteger(1, hash).toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    public static String correctTitle(String line)// 9 - сделать "правильный заголовок", 
    {                 //все первые буквы в верхнем регистре, без "и", "the", "of" и "in"
        String result = "";
        String pos="";
        while (line.indexOf("-")!=-1)//Hyphenated words are considered separate words.
        {
            pos+=String.valueOf(line.indexOf("-"))+" ";
            line=line.substring(0,line.indexOf("-"))+" "+line.substring(line.indexOf("-")+1);
        }
        for (String cString:line.split(" ")) {
            if(cString.length()==0)
                result+=cString.toLowerCase() + " ";// сохраняю пробелы
            else
            if (cString.length()==1)
                result+=Character.isLetter(cString.charAt(0)) ? Character.toUpperCase(cString.charAt(0))+" " : cString+" ";// сохраняю слово
            else
            if(cString.toLowerCase().equals("and")||cString.toLowerCase().equals("the")||cString.toLowerCase().equals("of")||cString.toLowerCase().equals("in"))
                result+=cString.toLowerCase() + " ";
            else
                result+=Character.toUpperCase(cString.charAt(0))+cString.substring(1).toLowerCase()+" "; // сохраняю символы
        }
        if (!pos.equals(""))
            for (String cPos:pos.split(" "))
                result=result.substring(0,Integer.parseInt(cPos))+"-"+result.substring(Integer.parseInt(cPos)+1);

        return result.substring(0,result.length()-1);
    }
    public static String hexLattice(int value) // 10 - Построение функции Hexagonal по CenteredHexagonalNumbers
    {
        int countOfDots = 1;
        int cCenteredHexagonalNumbers = 1;
        int neededEvil=0;
        int maxLength = 1;
        while (value>cCenteredHexagonalNumbers)
        {
            countOfDots++;// количество точек в линии
            maxLength+=2;//length of central line
            cCenteredHexagonalNumbers=1+6*(countOfDots*(countOfDots-1)/2);// в CenteredHexagonalNumbers
            neededEvil+=cCenteredHexagonalNumbers/3>0 ? 1: 0;// для исправления правой части
        }
        countOfDots = cCenteredHexagonalNumbers==value ? countOfDots: 0;
        String result = "";
        int disp = countOfDots %2==0 ? 2 : 1; // отображение правильных пробелов
        boolean check = true;
        int countOfSpace = 2 * (maxLength - countOfDots + 1);
        for (int i = 0; i < maxLength; i++) {
            if (check)
                countOfSpace -= 2; // пока вехняя часть строится
            else
                countOfSpace += 2; // нижняя часть
            if (countOfSpace == 0)
                check = false;
            for (int j = 0; j < 2 * maxLength; j++) { // выводится Hexagonal
                if (j < countOfSpace / 2) {
                    result += " ";
                } else if (j >= 2 * maxLength - countOfDots - countOfSpace / 2 + neededEvil)
                    result += " ";
                else if ((j + i + disp) % 2 == 0) {
                    result += " ";
                } else
                    result += "o";
            }
            result += "\n";
        }
        return countOfDots!=0 ? result : "Invaid";
    }
}