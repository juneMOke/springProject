package org.o7planning.thymeleaf.model;

import org.o7planning.thymeleaf.controller.State;

import java.math.BigInteger;

public class NumberResult {

    private int numberValue;
    private State state;
    private int result;
    private BigInteger resultLong;


    public NumberResult() {

    }


    public NumberResult(int numberValue) {
        this.numberValue = numberValue;
        this.state = State.NATURAL;
    }

    public int getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(int numberValue) {
        this.numberValue = numberValue;
    }

    public State getState() {
        return state;
    }


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public BigInteger getResultLong() {
        return resultLong;
    }

    public void setResultLong(BigInteger resultLong) {
        this.resultLong = resultLong;
    }
    public  void result() {
        if (numberValue < 0 )
            this.state = State.NEGATIVE;
        if ( numberValue  % 2 != 0)
            this.resultLong = fibonacci(BigInteger.valueOf(numberValue));
        else
            this.resultLong = (numberValue < 51) ? factoriel (numberValue) : BigInteger.valueOf(numberValue);
    }

    private BigInteger factoriel(int value) {
        this.state = State.FACTORIAL;
        return (value == 0) ? BigInteger.ONE : BigInteger.valueOf(value).multiply(factoriel(value-1));
    }


    private  BigInteger fibonacci(BigInteger value) {
        this.state = State.FIBONACCI;
        return value.compareTo(BigInteger.TWO) == -1 ? value : fibonacci(value.subtract(BigInteger.ONE) ).add(fibonacci(value.subtract(BigInteger.TWO)));


    }



}