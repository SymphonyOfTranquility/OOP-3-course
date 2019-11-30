package lab;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaperParserHandlerTest {
    @Test
    void test_candy_parsing() {
        ParserInterface parserMock = Mockito.mock(SAXParser.class);
        Mockito.when(parserMock.isNextMap()).thenReturn(true).thenReturn(false);

        Map<String, String> retval = new HashMap<>();
        retval.put("id", "ID-1");
        retval.put("Title", "Mriya");
        retval.put("Type", "newspaper");
        retval.put("Monthly", "true");
        retval.put("Colored", "true");
        retval.put("Size", "30");
        retval.put("Glossy", "false");
        retval.put("SubscriptionIndex", "true");

        Mockito.when(parserMock.getNextMap()).thenReturn(retval).thenReturn(null);

        PaperParserHandler handler = new PaperParserHandler(parserMock);
        List<Paper> result = handler.parse();
        Paper actual = result.get(0);

        Paper expected = GeneratorForTests.generatePaper();

        assertTrue(expected.equalsTo(actual));
    }
}