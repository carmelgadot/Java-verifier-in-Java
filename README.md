# Java-verifier-in-Java

## File description 


#### Sjavac.java - abstract class contain the main function that validate the given file.
#### RegexTool.java - abstract class contain all the relevant regex (compiled) for the Sjava file process.
#### FileProcessor.java - abstract class implements the processing of Sjava file.
#### SemiColonProcessor.java - abstract class implements the processing of line ends with semicolomn in Sjava file.
#### Scope.java - abstract class that represent a scope in the Sjava file.
#### ScopeFactory.java - implements factory of scopes.
#### Global.java - Represent the global scope in the Sjava file.
#### Condition.java - Represent a Condition scope in the Sjava file.
#### Method.java - Represent a Method scope in the Sjava file.
#### Variable.java - implement a variable in the Sjava file.
#### VariablesFactory.java - factory of variable, create variable according given type.
#### ValueValidator.java - interface used to differentiate the value validating between the type.
#### BooleanValueValidator.java - implements the ValueValidator interface for validate value of type boolean.
#### CharValueValidator.java - implements the ValueValidator interface for validate value of type char.
#### DoubleValueValidator.java - implements the ValueValidator interface for validate value of type double.
#### IntValueValidator.java - implements the ValueValidator interface for validate value of type int.
#### StringValueValidator.java - implements the ValueValidator interface for validate value of type String.
#### NameTypeException.java - implements the ValueValidator interface for validate value of type boolean.
#### StringValueValidator.java - implements the ValueValidator interface for validate value of type boolean.
#### UsageException.java - Exception thrown with error message to declare usage error, error handled by printing 2 at the end.
#### IllegalCodeException.java - Super class for all exception that should end with printing 1 at the end of the program.
#### ValueOfVariableException.java - thrown when trying to create new variable failed abd the value can be other variable.
#### MisMatchOpenCloseBracketsException.java - thrown when there is mismatch with the number of open and closed bracket.
#### SyntaxException.java - thrown when there is there is line with invalid syntax.
#### DuplicateMethodNameException.java - thrown when detected that more than one method declared with the same name.
#### DuplicateVariableDeclarationException.java - thrown when detected that more than one variable in the same scope declared with the same name.
#### InvalidVariableForConditionException.java - thrown when variable in condition is invalid to appear in condition line.
#### MethodCallInGlobalException.java - thrown if method called in the global scope.
#### ReturnInGlobalException.java - thrown if return line appeared in the global scope.
#### UnknownVariableNameException.java - thrown when trying to get a undeclared variable.
#### ChangeFinalVariableException.java - thrown when trying to assign new value to variable that declared final.
#### NameTypeException.java - thrown when declaring a variable with invalid type name.
#### ValueTypeException.java - thrown when trying to create new variable failed because the value didn't match to the type
#### VariableNameFormatException.java - thrown when trying to create new variable failed because the name if the variable is invalid variable name.

## Design


#### According to the given API:
We created the class Sjavac in the package oop.ex6.main with the main program method.

### Our implementation:
package parser:
    We created the parser package and inside it the classes make the file process.
    FileProcessor - abstract class with static methods that make thr process on the file.

package scopes:
    We created the Scope abstract class that both Global, Condition and Method inherit from, because all are
    type of Scope.
    Under the package we created the scope factory (ScopeFactory) abstract class that create the scopes and
    also contain only one copy of the global scope, in accordance with the singleton principle.

package variables:
    We chose to use strategy design pattern and factory design pattern in this part of program.
    We created the Variable class that represent a variable in the Sjava file.
    We created an interface called ValueValidator and also a class for each type of variables that implements
    ValueValidator interface.
    each object of Variable class compose a ValueValidator object (represent the type of the variable) as data
    member and delegate methods on it. A Variable object is created by the VariablesFactory with its
    appropriate ValueValidator object (get the object in the constructor).

## Implementation details

In the FileProcessor first processed the global scope to allow the inner scope know all its variable while
processing them. the global process and the inner method process separated to 2 method.
While processing the global the inner Method objects created and saved in the global scope, each method with
its scope lines. the methods saved in the order they appear in the global, and after the global processed the
methods process start according to their order.
While processing the global the open close brackets number validating.
Each created scope save its previous scope to get access for outer variables.
The scope save the variables created in its scope in a local variables hashMap to allow O(1) access to the
variables and to avoid duplication in easily by variable name as key.

* As alternative to this process at the start we tried to first process the lines in the order they appear -
 open and close the scopes in the file, in this process we required to save unknown variables founded in
 assigment or condition or value that can be a variable without known declaration, to the end of the process
 in some container to validate at the end that they actually declared and have the needed type, this process
 was inefficient with memory and time complexity, so we decided to change the process to the process described
 above.

We created several Exceptions to allow in the future add more concrete message if sone exception that shoukd
end with 1 printing thrown.

## Answers to questions

Question - How would you modify your code to add new types of variables (e.g., float)?

Answer - we have to create new class that implements a valueValidator of float type variable, add the float
         type to the variable factory, and add the needed regex for the float variable in the regexTool.

Question - Below are three features your program currently does not support. Please select two of
           them, and describe which modifications/extensions you would have to make in your code
           in order to support them?

Answer - 1) Classes: to support classes, we would add a scope class calls Class, the process of the global
                     will remain the same and will processed first, after the global processed the methods
                     and the classes in the global will be process according to the methods process now, their
                     will be only a difference with Class and method, the class will be processed before its
                     inner method according to the current global process.
         2) Using methods of standard java: a method call for a standard java method will be processed the
            same like a call to method proceed now, the difference is before the process start the program
            will add the relevant standard java method to the known declared methods, and than compare the
            call to the method params.

Question - How you handled s-Java code errors in this exercise, and why you chose to do so.

Answer - We created a Super class for the exceptions of Illegal code exceptions (prints 1 at the end), and
         each exception inherit from it, in that way if some exception thrown while the file processed it will
         be caught at the main and the code 1 will printed.
         Also we used the exceptions mechanism to avoid if else in value validating (no need to use boolean
         return value in CreateVariable method), when a value is invalid for the variable type but the value
         can be other variable a specific (ValueOfVariableException) to catch it after the value validating
         and check if the value match to other declared variable.

Question - Describe two of the main regular expressions you used in your code.
Answer - we created regex for the legal lines in the file for example SEMI_COLON_SUFFIX_REGEX that
         filtering all the lines in the file that end with semicolon (variable assigment, return and call
         function),
         SEMI_COLON_SUFFIX_REGEX : ".*;\\s*" - filtering any line starting with any zero or more some
                                               character after it semicolon, and than can be zero or more
                                               White-Space.
         Another main regex is OPEN_BRACKET_SUFFIX_REGEX that filtering all the lines that open
         scope.
         OPEN_BRACKET_SUFFIX_REGEX : ".*[{]\\s*" - filtering any line starting with any zero or more some
                                                   character, after it open brackets, and than can be zero or
                                                   more White-Space.
