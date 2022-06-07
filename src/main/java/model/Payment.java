package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    private int amount;
    private LocalDate date;

    @Override
    public String toString(){
        return "{\"amount\":" + amount
                + ", \"date\":\"" + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "\"}";
    }
}
