package app;

import model.Order;
import model.OrderRow;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import validation.ValidationErrors;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.List;

@RestController
public class ValidationController {

    @PostMapping("manual-validation")
    public ResponseEntity<Object> manualValidation(@RequestBody Order order) {

        var validator = Validation.buildDefaultValidatorFactory().getValidator();

        List<OrderRow> orderRows = order.getOrderRows();

        ValidationErrors errors = new ValidationErrors();

        for (OrderRow orderRow : orderRows) {
            var violations = validator.validate(orderRow);

            for (ConstraintViolation<OrderRow> violation : violations) {
                errors.addErrorMessage(violation.getMessage());
            }
        }

        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("validation")
    public void validation(@RequestBody @Valid OrderRow row) {}

}