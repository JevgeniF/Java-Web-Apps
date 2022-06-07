package validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;
import model.OrderRow;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.List;

public class ValidatorTest {

    public static void main(String[] args) throws JsonProcessingException {
        var validator = Validation.buildDefaultValidatorFactory().getValidator();

        Order order = new Order(1L,"A1", null);
        order.add(new OrderRow("Motherboard", null, 5));
        order.add(new OrderRow("Cpu", 5, 0));

        List<OrderRow> orderRows = order.getOrderRows();

        ValidationErrors errors = new ValidationErrors();

        for (OrderRow orderRow : orderRows) {
            var violations = validator.validate(orderRow);

            for (ConstraintViolation<OrderRow> violation : violations) {
                errors.addErrorMessage(violation.getMessage());
            }
        }
    }


}
