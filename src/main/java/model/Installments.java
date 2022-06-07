package model;

import lombok.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Installments {

    private int sum;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Payment> paymentsList;
    private int totalMonths;

    public Installments(int sum, LocalDate startDate, LocalDate endDate) {
        this.sum = sum;
        this.startDate = startDate;
        this.endDate = endDate;

        int paymentsQtty = this.sum / 3 - 1;
        int sumLeft = this.sum - 3;
        LocalDate date = startDate;
        paymentsList = new ArrayList<>();
        Payment firstPayment = new Payment(3, date);
        paymentsList.add(firstPayment);
        if (sumLeft == 3) {
            date = date.withDayOfMonth(1).plusMonths(1);
            Payment payment = new Payment(sumLeft, date);
            paymentsList.add(payment);}
        else {
            int delimiter = paymentsQtty;
            for (int i = 0; i < paymentsQtty; i++) {
                int amount = sumLeft / delimiter;
                date = date.withDayOfMonth(1).plusMonths(1);
                Payment payment = new Payment(amount, date);
                paymentsList.add(payment);
                delimiter -= 1;
                sumLeft -= amount;
            }
        }

        this.totalMonths = 2 + monthsBetween(startDate, endDate);
    }

    private int monthsBetween(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        return period.getMonths();
    }

    @Override
    public String toString(){
        return paymentsList.toString();
    }

}
