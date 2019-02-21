package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.PriceCommand;
import tru.springframework.com.libaryapp.model.Currency;
import tru.springframework.com.libaryapp.model.Price;

import static org.junit.Assert.*;

public class PriceCommandToPriceTest {
    private final static Long ID_VALUE = 1L;
    private final static Currency CURRENCY = Currency.ZŁ;
    private final static Double VALUE = 25.50;

    private PriceCommandToPrice priceCommandToPrice;


    @Before
    public void setUp() {
        priceCommandToPrice = new PriceCommandToPrice();
    }

    @Test
    public void testNullObject() {
        assertNull(priceCommandToPrice.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(priceCommandToPrice.convert(new PriceCommand()));
    }
    @Test
    public void convert() {
        PriceCommand priceCommand  = new PriceCommand();
        priceCommand.setId(ID_VALUE);
        priceCommand.setValue(VALUE);
        priceCommand.setCurrency(CURRENCY);

        Price price = priceCommandToPrice.convert(priceCommand);

        assertEquals(ID_VALUE, price.getId());
        assertEquals(VALUE, price.getValue());
        assertEquals(CURRENCY,price.getCurrency());

    }
}