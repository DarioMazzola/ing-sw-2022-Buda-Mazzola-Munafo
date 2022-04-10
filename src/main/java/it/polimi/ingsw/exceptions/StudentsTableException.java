/**
 * StudentsTableException is used to throw exceptions related to the presence or absence of students in the Dining Hall
 */

package it.polimi.ingsw.exceptions;

public class StudentsTableException extends Exception{

    public StudentsTableException(String message){
        super(message);
    }
}
