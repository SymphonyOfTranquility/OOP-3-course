package lab;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CandyParserHandlerTest {
    @Test
    void test_candy_parsing() {
        GeneralParserInterface parserMock = Mockito.mock(GeneralSAXParser.class);
        Mockito.when(parserMock.isNextMap()).thenReturn(true).thenReturn(false);

        Map<String, String> retval = new HashMap<>();
        retval.put("id", "ID-1");
        retval.put("Name", "Slivki linivki vaflya");
        retval.put("Energy", "420");
        retval.put("filling", "milk cream");
        retval.put("Type", "Chocolate with filling");
        retval.put("Water", "15");
        retval.put("Sugar", "30");
        retval.put("Fructose", "15");
        retval.put("ChocolateType", "Black chocolate");
        retval.put("Vanilla", "5");
        retval.put("Proteins", "15");
        retval.put("Fat", "5");
        retval.put("Carbon", "80");
        retval.put("Production", "Roshen");

        Mockito.when(parserMock.getNextMap()).thenReturn(retval).thenReturn(null);

        CandyParserHandler handler = new CandyParserHandler(parserMock);
        List<Candy> result = handler.parse();
        Candy actual = result.get(0);

        Candy expected = GeneratorForTests.generateCandy();

        assertTrue(expected.equalsTo(actual));
    }
}