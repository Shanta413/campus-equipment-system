package edu.cit.cantiller.christianjayson.campusequipmentloan.service.penalty;

public interface PenaltyStrategy {
    double calculatePenalty(long overdueDays);
}