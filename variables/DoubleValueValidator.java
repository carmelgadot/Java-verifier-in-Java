package oop.ex6.variables;
import oop.ex6.parser.RegexTool;

/**
 *  Implements a ValueValidator for variables of double type.
 *
 * @author Avi Kogan.
 * @author Carmel Gadot.
 */
public class DoubleValueValidator implements ValueValidator {

    /**
     * Validate the value is valid for the variable type.
     * @param varValue the given value for the variable.
     * @throws ValueTypeException if the value invalid.
     */
    @Override
    public void validateValue(String varValue) throws ValueTypeException {
        if (!RegexTool.isMatch(varValue ,RegexTool.DOUBLE_VALUE_REGEX_PATTERN))
            throw new ValueTypeException();
    }

    /**
     * Check if the type of the assigned variable is valid to be assigned to the current variable.
     * @param assignedType the type of the assigned variable.
     * @throws ValueTypeException if the value invalid.
     */
    @Override
    public void validateTypeForAssignment(String assignedType) throws ValueTypeException {
        if(!RegexTool.isMatch(assignedType, RegexTool.TYPE_FOR_DOUBLE_ASSIGMENT)) throw new ValueTypeException();
    }
}
