package org.neller.schneller_invoice.conversion;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NumbertoText{
    public static void main(String[] args) throws IOException {
        String url = "http://mobile.ding.eu/mobile/DMR?l=de&qr=1&n_dm=1200&m=d&n=din#";
        Document doc = Jsoup.connect(url).get();
        Elements departures = doc.select("tr.st_9001200");
        for (Element ele : departures){
            Element time = ele.select("td.clock").first();
            System.out.print(time.text() + ": ");
            Element line = ele.select("td.line").first();
            System.out.println(line.text() );
        }
    }

    public static String getText(int i){
        return getText(Integer.valueOf(i));
    }

    public static String getText(Integer input){
        if(input.equals(1)){
            return "EINS";
        }
        String returnString = "";
        Integer integer = input.intValue();
        String integerString = integer.toString();

        if(integerString.length()>=2 &&
            integerString.endsWith("01")){
           returnString = "EINS";
        }

        int ordinal=0;
        while(integerString.length()>0){
            integer = Integer.valueOf(integerString);
            returnString = getHundreds(integer % 1000) + Ordinal.values()[ordinal] + returnString;
            ordinal++;
            if(integerString.length()>=3){
                integerString = integerString.substring(0,integerString.length()-3);
            }
            else{break;}
        }
        /*
        do{
            integer = Integer.valueOf(integerString);
            returnString= getHundreds(integer % 1000)+ Ordinal.values()[ordinal].toString()  + returnString;
            ordinal++;
            if(integerString.length()>=3){
                integerString = integerString.substring(0,integerString.length()-3);
            }
        }while(integerString.length()>=1);
         */
        return returnString;
    }
    private static String getHundreds(int i){
        String hundreds = "";

        if(i<100) return getTens(i);
        String s = Integer.toString(i);
        int hundredDigit = Integer.valueOf(String.valueOf(s.charAt(s.length()-3)));

        if(i%100==0) return Digit.getUnd(hundredDigit) + "HUNDERT";
        return Digit.getUnd(hundredDigit)+ "HUNDERT" + getTens(i % 100);
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
        EIN,
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

        Ordinal(String multipleSuffix) {
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
