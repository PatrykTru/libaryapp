package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.PriceCommand;
import tru.springframework.com.libaryapp.model.Price;

import static org.junit.Assert.*;

public class PriceToPriceCommandTest {
    private final static Long ID_VALUE = 1L;
    private final static String STRING = "ZŁ";
    private final static Double VALUE = 25.50;

    PriceToPriceCommand priceToPriceCommand;


    @Before
    public void setUp() {
        priceToPriceCommand = new PriceToPriceCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(priceToPriceCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(priceToPriceCommand.convert(new Price()));
    }

    @Test
    public void convert() {
        Price price = new Price();
        price.setValue(VALUE);
        price.setId(ID_VALUE);
        price.setCurrency(STRING);

        PriceCommand priceCommand =priceToPriceCommand.convert(price);

        assertEquals(ID_VALUE , priceCommand.getId());
        assertEquals(VALUE , priceCommand.getValue());
        assertEquals(STRING, priceCommand.getCurrency());
    }
}