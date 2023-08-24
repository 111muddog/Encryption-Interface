package secrecy;
/*
Name: Angus Webb
Date: March 10, 2023
Class Description: InvalidCodeException is thrown when a char outside of index 0 to 127 is detected.
 */
public class InvalidCodePointException extends NullPointerException {
    public InvalidCodePointException(String err){
        super(err); //error message that gets displayed
    }
}