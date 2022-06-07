package utilities;

import model.Order;
import model.OrderRow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Order order = new Order(resultSet.getLong("order_id"), resultSet.getString("order_number"), null);
        List<OrderRow> rows = new ArrayList<>();

        if (order.getId().equals(resultSet.getLong("order_id"))) {
            rows.add(new OrderRow(resultSet.getString("item_name"), resultSet.getInt("quantity"), resultSet.getInt("price")));
        }
        if (resultSet.next() && order.getId().equals(resultSet.getLong("order_id"))) {
            rows.add(new OrderRow(resultSet.getString("item_name"), resultSet.getInt("quantity"), resultSet.getInt("price")));

        }
        order.add(rows);
        return order;
    }
}
