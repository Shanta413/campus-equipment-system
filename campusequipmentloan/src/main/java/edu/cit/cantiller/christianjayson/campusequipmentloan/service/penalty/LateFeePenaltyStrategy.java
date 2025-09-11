package edu.cit.cantiller.christianjayson.campusequipmentloan.service.penalty;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component
public class LateFeePenaltyStrategy implements PenaltyStrategy {

    private static final int DAILY_PENALTY = 50;

    @Override
    public int calculatePenalty(Loan loan) {
        if (loan.getReturnDate() == null || loan.getDueDate() == null) {
            return 0;
        }

        long daysLate = ChronoUnit.DAYS.between(loan.getDueDate(), loan.getReturnDate());
        if (daysLate <= 0) {
            return 0;
        }
        return (int) (daysLate * DAILY_PENALTY);
    }
}
