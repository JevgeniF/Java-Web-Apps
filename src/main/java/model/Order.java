package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;
    private String orderNumber;
    @Valid
    private List<OrderRow> orderRows;

    public void add(OrderRow orderRow) {
        if (orderRows == null) {
            orderRows = new ArrayList<>();
        }

        orderRows.add(orderRow);
    }

    public void add(List<OrderRow> orderRows) {
        if (this.orderRows == null) {
            this.orderRows = orderRows;
        }
    }

    @Override
    public String toString() {
        return "{" +
                    "\"id\": " + id +
                    ", \"orderNumber\": \"" + orderNumber + "\"" +
                    ", \"orderRows\":" + orderRows + "}";

    }

}
