package app;

import model.Order;
import model.OrderRow;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import utilities.OrderMapper;

import java.util.List;

@Repository
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Order> queryOrder() {
        String sql = "SELECT orders.order_id, orders.order_number, order_rows.item_name, order_rows.quantity, order_rows.price FROM orders LEFT JOIN order_rows ON orders.order_id = order_rows.order_id";

        return jdbcTemplate.query(sql, new OrderMapper());

    }

    public Order queryOrderByID(Long idQuery) {
        String sql = String.format("SELECT orders.order_id, orders.order_number, " +
                "order_rows.item_name, order_rows.quantity, order_rows.price " +
                "FROM orders LEFT JOIN order_rows ON orders.order_id = order_rows.order_id " +
                "WHERE orders.order_id = %d", idQuery);

        return jdbcTemplate.query(sql, new OrderMapper()).get(0);
    }

    public Order addOrder(Order order) {
        SqlParameterSource orderParameters = new MapSqlParameterSource()
                .addValue("order_number", order.getOrderNumber());

        String id = "order_id";
        Number number = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders")
                .usingGeneratedKeyColumns(id)
                .executeAndReturnKey(orderParameters);

        order.setId(number.longValue());

        if (order.getOrderRows() != null) {
            for (OrderRow row : order.getOrderRows()) {
                SqlParameterSource rowParameters = new MapSqlParameterSource()
                        .addValue(id, number.longValue())
                        .addValue("item_name", row.getItemName())
                        .addValue("quantity", row.getQuantity())
                        .addValue("price", row.getPrice());

                new SimpleJdbcInsert(jdbcTemplate)
                        .withTableName("order_rows")
                        .usingGeneratedKeyColumns("row_id")
                        .execute(rowParameters);
            }
        }
        return order;
    }

    public void deleteOrderByID(Long idQuery) {
        String sqlRows = String.format("DELETE FROM order_rows WHERE order_id = %d", idQuery);
        String sqlOrders = String.format("DELETE FROM orders WHERE order_id = %d", idQuery);
        jdbcTemplate.execute(sqlRows);
        jdbcTemplate.execute(sqlOrders);
    }

}
