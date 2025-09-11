package edu.cit.cantiller.christianjayson.campusequipmentloan.service.penalty;

import edu.cit.cantiller.christianjayson.campusequipmentloan.entity.Loan;

public interface PenaltyStrategy {
    int calculatePenalty(Loan loan);
}