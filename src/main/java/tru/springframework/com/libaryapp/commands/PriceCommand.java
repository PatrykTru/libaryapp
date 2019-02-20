package tru.springframework.com.libaryapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tru.springframework.com.libaryapp.model.Currency;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
public class PriceCommand {

    private Long id;

    @Min(1)
    @Max(100)
    private Double value;
    private Currency currency;
}
