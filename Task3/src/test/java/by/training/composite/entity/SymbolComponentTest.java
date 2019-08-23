package by.training.composite.entity;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Symbol compose test.
 * */
public class SymbolComponentTest {
    /**
     * @return two-dimension array.
     * */
    @DataProvider(name = "DataForComposeTest")
    public Object[][] testComposeMMethod() {
        Component component = new SymbolComponent('c');
        Component component1 = new SymbolComponent('1');
        Component component2 = new SymbolComponent('.');
        Component component3 = new SymbolComponent('\u0432');
        Component component4 = new SymbolComponent('\n');
        return new Object[][] {
                {component, "c"},
                {component1, "1"},
                {component2, "."},
                {component3, "\u0432"},
                {component4, "\n"}
        };
    }
    /**
     * Testing compose for symbol type.
     * @param component -component.
     * @param expect -string.
     * */
    @Test(dataProvider = "DataForComposeTest", groups = {"Compose group"})
    public void testCompose(final Component component, final String expect) {
        String actual = component.compose();
        assertEquals(actual, expect);
    }
}
