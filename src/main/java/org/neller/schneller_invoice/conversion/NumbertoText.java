package org.neller.schneller_invoice.conversion;


import javax.print.attribute.IntegerSyntax;

public class NumbertoText{
    public static void main(String[] args){
        System.out.println(getText(5));
        System.out.println(Ordinal.BILLION.toString());

    }

    public static String getText(int i){
        return getText(Integer.valueOf(i));
    }

    public static String getText(Integer input){
        //TODO handle negative numbers
        Integer integer = input.intValue();
        String integerString = integer.toString();
        String returnString = "";
        int ordinal=0;
        do{
            integer = Integer.valueOf(integerString);
            returnString= getHundreds(integer % 1000)+ Ordinal.values()[ordinal].toString()  + returnString;
            ordinal++;
            if(integerString.length()>3){
                integerString = integerString.substring(0,integerString.length()-3);
            }
        }while(integerString.length()>=3);
        return returnString;
    }
    private static String getHundreds(int i){
        String hundreds = "";
        if(i >=100){
            String s = Integer.toString(i);
            int hundredDigit = s.charAt(s.length()-3);
            hundreds = Digit.getUnd(hundredDigit) + "HUNDERT" ;
        }
        return hundreds + getTens(i % 100);
    }
    private static String getTens(int i)  {
        //TODO throw error on > 99
        if(0<=i &&i <=12){
            return Digit.values()[i].toString();
        }

        int modTen = i % 10;
        String tens = Tens.values()[(i - modTen) / 10 ].toString();
        if(modTen !=0){
            tens = Digit.getUnd(modTen) + "UND" + tens;
        }
        return tens;
    }
    private enum Digit{
        NULL,
        EINS,
        ZWEI,
        DREI,
        VIER,
        FÜNF,
        SECHS,
        SIEBEN,
        ACHT,
        NEUN,
        ZEHN,
        ELF,
        ZWÖLF;
        //DIGIT("");

        private static String getUnd(int i){
            return i==1? "EIN" : Digit.values()[i].toString();
        }



    }
    private enum Tens{
        NULL,
        ZEHN,
        ZWANZIG,
        DREIẞIG,
        VIERZIG,
        FÜNFZIG,
        SECHZIG,
        SIEBZIG,
        ACHTZIG,
        NEUNZIG
    }
    private enum Ordinal{
        NONE(""),
        TAUSEND(""),
        MILLION("en"),
        MILLIARDE("n"),
        BILLION("en");

        private final String multipleSuffix;

        private Ordinal(String multipleSuffix) {
            this.multipleSuffix = multipleSuffix;
        }

        public String toString(){
            if(this==Ordinal.NONE){
                return "";
            }
            return super.toString();
        }
    }
}
