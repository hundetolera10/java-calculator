package calculator.operations;

public abstract class AbstractOperation implements Operation {
    protected String symbol;
    public String getSymbol(){
        return symbol;
    }

}
class Addition extends AbstractOperation {
    public Addition(){
        this.symbol= "+";
    }
    public double calculate(double a, double b){
        return a + b;
    }
}
class Substraction extends AbstractOperation {
    public Substraction(){
        this.symbol= "-";
    }
    public double calculate(double a, double b){
        return a - b;
    }
}

class Multiplication extends AbstractOperation {
    public Multiplication() {
        this.symbol = "*";
    }

    public double calculate(double a, double b) {
        return a * b;
    }
}
class Division extends AbstractOperation{
    public Division(){
        this.symbol= "/";
    }
    public double calculate(double a, double b){
        return a / b;
    }
}
