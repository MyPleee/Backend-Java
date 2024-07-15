package ple.exceptions.exceptiontypes;

public interface PleErrorType {
    String getErrorName();
    
    int getErrorCode();
    
    String getErrorMessage();
    
    int getErrorStatus();
}
