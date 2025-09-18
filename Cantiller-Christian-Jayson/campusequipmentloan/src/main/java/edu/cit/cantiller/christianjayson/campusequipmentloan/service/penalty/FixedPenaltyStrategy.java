package edu.cit.cantiller.christianjayson.campusequipmentloan.service.penalty;

import org.springframework.stereotype.Component;

@Component
public class FixedPenaltyStrategy implements PenaltyStrategy {

    private static final double DAILY_PENALTY = 50.0;

    @Override
    public double calculatePenalty(long overdueDays) {
        return overdueDays * DAILY_PENALTY;
    }
}
