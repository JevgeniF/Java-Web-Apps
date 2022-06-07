package utilities;

import model.Order;
import model.OrderRow;

import java.util.List;

public class OrderSumCalculator {

    public static Integer getTotalSum(Order order) {
        int sum = 0;
        List<OrderRow> rows = order.getOrderRows();
        for (OrderRow row : rows) {
            int price = row.getPrice();
            sum +=price;
        }

        return sum;
    }
}
