package edu.cit.cantiller.christianjayson.campusequipmentloan.exception;

public class LoanLimitExceededException extends RuntimeException {
    public LoanLimitExceededException(String message) {
        super(message);
    }
}
