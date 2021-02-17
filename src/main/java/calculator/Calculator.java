package calculator;

import java.util.Scanner;
import java.util.Stack;

/**
 * <h1>Basic Calculator</h1>
 * This class takes equation as input from the user and
 * prints out the result after solving it.
 * This class implements applyOp and precedence methods.
 *
 * @author Akash Gupta
 */
public class Calculator {
    /**
     * This Method is used to do different operation and then
     * return the operation result.
     * This method takes two numbers and operation to be applied.
     *
     * @param num1 This is the first number
     * @param num2 This is the second number
     * @param op   This is the operation to be applied on the number.
     * @return int This return operation result.
     */
    static int applyOp(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    /**
     * This method is used to decide precedence order of operator.
     *
     * @param op This is the operation
     * @return int This returns the value of operator.
     */
    static int precedence(char op) {
        if (op == '+' || op == '-')
            return 1;
        if (op == '*' || op == '/')

            return 2;
        return 0;
    }

    /**
     * This is the main method which takes input as equation and
     * makes use of applyOp and precedence methods.
     *
     * @param args Unused
     * @return Nothing
     */
    public static void main(String []args) {
        String expr;
        System.out.println("Enter exp: ");
        Scanner sc = new Scanner(System.in);
        expr = sc.next();
        int flag = 0;
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            if ((expr.charAt(i) >= 'A' && expr.charAt(i) <= 'Z') ||
                    (expr.charAt(i) >= 'a' && expr.charAt(i) <= 'z'))
            {
                flag = 1;
                break;
            }
            else if (expr.charAt(i) == ' ')
                continue;
            else if (expr.charAt(i) == '(') {
                ops.push(expr.charAt(i));
            }
// Current char is a number, push
// it to number stack
            else if (Character.isDigit(expr.charAt(i))) {
                int val = 0;
// There may be more than one
// digits in number.
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    val = (val * 10) + (expr.charAt(i) - '0');
                    i++;
                }
                values.push(val);
                i--;
            } else if (expr.charAt(i) == ')') {
                while (!ops.empty() && ops.peek() != '(') {
                    int val2 = values.peek();
                    values.pop();
                    int val1 = values.peek();
                    values.pop();
                    char op = ops.peek();

                    ops.pop();
                    values.push(applyOp(val1, val2, op));
                }
// pop opening brace.
                if (!ops.empty())
                    ops.pop();
            } else {
// While top of 'ops' has same or greater
// precedence to current operator. Apply
// operator on top of 'ops' to top two
// elements in values stack.
                while (!ops.empty() && precedence(ops.peek())
                        >= precedence(expr.charAt(i))) {
                    int val2 = values.peek();
                    values.pop();
                    int val1 = values.peek();
                    values.pop();
                    char op = ops.peek();
                    ops.pop();
                    values.push(applyOp(val1, val2, op));
                }
// Push current operation to 'ops'.
                ops.push(expr.charAt(i));
            }
        }
        while (!ops.empty()) {
            int val2 = values.peek();
            values.pop();
            int val1 = values.peek();
            values.pop();
            char op = ops.peek();
            ops.pop();
            values.push(applyOp(val1, val2, op));
        }
        if (flag == 1)
            System.out.println("Error : Input is not correct");
// Top of 'values' contains result, return it.
        else
            System.out.println(values.peek());
    }
}