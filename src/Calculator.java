import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by bearg on 4/30/2016.
 * We're going to make a calculator that does addition and
 * subtraction by repeatedly popping the top
 * three tokens off the stack, calculating the result, and pushing
 * the result back onto the stack. When we have one element on the stack
 * at the end, we can return it.
 * Because of order of operations,
 * this would also work with multiplication and division, because the
 * precedence of those operations is the same -- we just do them left
 * to right, or top to bottom in the case of a stack. we cannot, however,
 * mix addition/subtraction with multiplication/division and use a stack.
 */
public class Calculator {

    /*
    1
    -
    3
    +
    2
    +
    4
     */

    protected static final String TEST_INPUT =
            "1 - 3 + 4 - 6 + 3";

    public int evaluate(final String input) {

        final Deque<String> stack = new ArrayDeque<>();
        final String[] tokens = input.split(" "); // use space as delimiter
        int result = 0;

        // add tokens to stack
        for (String token: tokens) {
            stack.add(token);
        }

        while (stack.size() > 1) {
            int num1 = Integer.parseInt(stack.pop()); // pop removes + returns top element
            String operator = stack.pop();
            int num2 = Integer.parseInt(stack.pop());


            switch(operator) {
                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                default: // entered the wrong character in the string. shut it down.
                    throw new IllegalArgumentException("Only + and - operators can be used");


            }
            // convert the result to a String and push it to the stack
            stack.push(String.valueOf(result));

        }

        return result;
    }
}
