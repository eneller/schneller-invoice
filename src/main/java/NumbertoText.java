public class NumbertoText{
    public static void main(String[] args){
        System.out.println(getText(40000));

    }

    public static String getText(int i){
        return getText(Integer.valueOf(i));
    }

    public static String getText(Integer input){
        Integer integer = input.intValue();
        String integerString = integer.toString();
        String returnString = "";
        int i=0;
        do{
            integer = Integer.valueOf(integerString);
            returnString= getHundreds(integer % 1000)+ getTens(integer % 100) + Ordinal.values()[i].toString()  + returnString;
            i++;
            integerString = integerString.substring(0,integerString.length()-3);
        }while(integerString.length()>=3);
        return returnString;
    }
    private static String getHundreds(int i){
        return "";
    }
    private static String getTens(int i){


        if(0<=i &&i <=12){
            return Digit.values()[i].toString();
        }
        return "";
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
