import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.neller.schneller_invoice.conversion.NumbertoText.getText;

public class NumbertoTextTest {
    @Test
    public void testSingle(){
        assertEquals("FÜNF", getText(5));
    }

    @Test
    public void testSpecial(){
        assertEquals("ZWÖLF", getText(12));
    }
    @Test
    public void testUnd(){
        assertEquals("EINUNDZWANZIG", getText(21));
    }
    @Test
    public void testTens(){
        assertEquals("FÜNFUNDVIERZIG", getText(45));
    }
    @Test
    public void testHundreds(){
        assertEquals("EINHUNDERT", getText(100));
    }
    @Test
    public void testThousands(){
        assertEquals("EINTAUSENDDREIHUNDERT", getText(1300));
    }



}
