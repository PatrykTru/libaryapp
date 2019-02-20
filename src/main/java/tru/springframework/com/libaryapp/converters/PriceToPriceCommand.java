package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.PriceCommand;
import tru.springframework.com.libaryapp.model.Price;

@Component
public class PriceToPriceCommand implements Converter<Price, PriceCommand> {

    @Nullable
    @Synchronized
    @Override
    public PriceCommand convert(Price price) {
        if(price == null)
        return null;

        PriceCommand priceCommand = new PriceCommand();

        priceCommand.setCurrency(price.getCurrency());
        priceCommand.setId(price.getId());
        priceCommand.setValue(price.getValue());

        return priceCommand;
    }
}
