package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {

  private LinkedStack<String> operators = new LinkedStack<String>();
  private LinkedStack<Integer> operands  = new LinkedStack<Integer>();

  /** return stack object (for testing purpose). */
  public LinkedStack<String> getOperatorStack() { 
    return operators; 
  }
  
  public LinkedStack<Integer> getOperandStack() { 
    return operands;
  }
  
  public void process() throws Exception {
    
    String opString = operators.pop();
    if (opString.equals("!")) {
      Integer A = operands.pop();
      if (A == null)
          throw new Exception("too few operands");
      operands.push(-A);
  } else {
      Integer B = operands.pop();
      Integer A = operands.pop();
      if (A == null || B == null) {
          throw new Exception("too few operands");
      }
      if (opString.equals("+")) {
          operands.push(A + B);
      } else  if (opString.equals("-")) {
          operands.push(A - B);
      } else if (opString.equals("*")) {
          operands.push(A * B);
      } else  if (opString.equals("/")) {
          if (B.equals(0))
            throw new Exception("division by zero");
            operands.push(A / B);
      } else {
          throw new Exception("invalid operator");
      }
  }
    
  }


  /** This method performs one step of evaluation of a infix expression.
   *  The input is a token. Follow the infix evaluation algorithm
   *  to implement this method. If the expression is invalid, throw an
   *  exception with the corresponding exception message.
   */
  public void evaluate_step(String token) throws Exception {
    if (isOperand(token)) {
      // TODO: What do we do if the token is an operand?
      operands.push(Integer.parseInt(token));
    } else {
      /* TODO: What do we do if the token is an operator?
               If the expression is invalid, make sure you throw
               an exception with the correct message.

               You can call precedence(token) to get the precedence
               value of an operator. It's already defined in 
               the Evaluator class.
       */ 
      if (token.equals("(")) {
        operators.push(token);
      } else if(token.equals(")")) {
        while (!operators.isEmpty() && !operators.top().equals("(")) {
          process();  
        }
        if (operators.isEmpty()) {
          throw new Exception("missing (");
        } else {
        operators.pop();
        }
      
      } else if (operators.isEmpty() || (precedence(token) > precedence(operators.top()))) { 
        operators.push(token);
      } else {
        while (!operators.isEmpty()  && (precedence(token) <= precedence(operators.top()))) {
          process();
      }
      operators.push(token);
     }
   }
}

  
  /** This method evaluates an infix expression (defined by expr)
   *  and returns the evaluation result. It throws an Exception object
   *  if the infix expression is invalid.
   */
  public Integer evaluate(String expr) throws Exception {

    for (String token : ArithParser.parse(expr)) {
      evaluate_step(token);
    }

    /* TODO: what do we do after all tokens have been processed? */
    while (!operators.isEmpty()) {
      process();
    }
    // The operand stack should have exactly one operand after the evaluation is done
    if (operands.size() > 1) {
      throw new Exception("too many operands");
    } else if (operands.size() < 1) {
      throw new Exception("too few operands");
    }

    return operands.pop();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
  }
}
