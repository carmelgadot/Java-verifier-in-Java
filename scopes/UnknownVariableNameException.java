package oop.ex6.scopes;

import oop.ex6.main.IllegalCodeException;

/**
 * Exception thrown when trying to get a undeclared variable.
 * Created to allow specific error if will be needed later.
 *
 * @author Avi Kogan.
 * @author Carnml Gadot.
 */
public class UnknownVariableNameException extends IllegalCodeException {

    private static final long serialVersionUID = 1L;
}
