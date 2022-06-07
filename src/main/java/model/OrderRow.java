package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRow {
    private String itemName;
    @NotNull
    @Min(1)
    private Integer quantity;
    @NotNull
    @Min(1)
    private Integer price;

    @Override
    public String toString() {
        return "{\"itemName\":\"" + itemName + "\"" +
                ",\"quantity\":" + quantity +
                ",\"price\":" + price +
                "}";
    }

}
