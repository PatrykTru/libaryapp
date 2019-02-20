package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.PriceCommand;
import tru.springframework.com.libaryapp.model.Price;

@Component
public class PriceCommandToPrice implements Converter<PriceCommand, Price> {


    @Nullable
    @Synchronized
    @Override
    public Price convert(PriceCommand priceCommand) {
        if(priceCommand == null)
        return null;

        Price price = new Price();

        price.setCurrency(priceCommand.getCurrency());
        price.setId(priceCommand.getId());
        price.setValue(priceCommand.getValue());

        return price;
    }
}
