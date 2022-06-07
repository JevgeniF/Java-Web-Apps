package app;

import model.Installments;
import model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utilities.OrderSumCalculator;
import java.time.LocalDate;

@RestController
public class InstallmentsController {

    private final OrderDao dao;

    public InstallmentsController(OrderDao dao) {
        this.dao = dao;
    }

    @GetMapping("orders/{id}/installments")
    public String calculateInstallments(@PathVariable Long id,
                                      @RequestParam("start") String start,
                                      @RequestParam("end") String end)
    {
    Order order = dao.queryOrderByID(id);
    int sum = OrderSumCalculator.getTotalSum(order);
    LocalDate startDate = LocalDate.parse(start);
    LocalDate endDate = LocalDate.parse(end);
    Installments installments = new Installments(sum, startDate, endDate);
    return installments.toString();
    }

}
