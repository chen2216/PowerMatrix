package com.cty.PowerMatrix.Core.Model;
import com.cty.PowerMatrix.Core.Util.Euclidian;

public class Fraction {
    private int numerator; //分子
    private int denominator;//分母
    private double magnitude; //实际

    public Fraction(){
        this.denominator = 1;
        this.numerator = 0;
        this.generateMagnitude();
    }

    public void simplify(){
        if (numerator < 0 && denominator < 0){
            this.setNumerator(-1*this.getNumerator());
            this.setDenominator(-1*this.getDenominator());
        }
        int GCD;
        while ((GCD = Euclidian.calcGCD(numerator,denominator)) != 1){
            this.setNumerator(this.getNumerator()/GCD);
            this.setDenominator(this.getDenominator()/GCD);
        }
    }

    public void negative(){
        this.setNumerator(this.getNumerator() * -1);
        this.generateMagnitude();
    }

    public Fraction getNegative(){
        return new Fraction(-1 * this.getNumerator(),this.getDenominator());
    }

    public void inverse(){
        int temp = this.getDenominator();
        this.setDenominator(this.getNumerator());
        this.setNumerator(temp);
        this.generateMagnitude();
    }

    public Fraction getInverse(){
        return new Fraction(this.getDenominator(),this.getNumerator());
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.generateMagnitude();
        this.simplify();
    }

    public void generateMagnitude(){
        this.setMagnitude((double)numerator/denominator);
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double add(double d){
        return this.magnitude+d;
    }

    public void add(Fraction that){
        if (numerator == 0){
            this.numerator = that.numerator;
            this.denominator = that.denominator;
            this.getMagnitude();
        }else {
            if (this.getDenominator() == that.getDenominator()) {
                this.setNumerator(this.getNumerator() + that.getNumerator());
            } else {
                this.setDenominator(this.getDenominator() * that.getDenominator());
                this.setNumerator(this.getNumerator() * that.getDenominator() + this.getDenominator() * that.getNumerator());
            }
            this.generateMagnitude();
            this.simplify();
        }
    }

    public Fraction getAdd(Fraction that){
        if (this.numerator == 0){
            return that;
        }else {
            Fraction result;
            if (this.getDenominator() == that.getDenominator()) {
                result = new Fraction(this.numerator + that.numerator, this.denominator);
            } else {
                result = new Fraction(this.numerator * that.denominator + this.denominator * that.numerator,
                        this.denominator * that.denominator);
            }
            result.generateMagnitude();
            result.simplify();
            return result;
        }
    }

    public void subtract(Fraction that){
        if (this.getDenominator() == that.getDenominator()) {
            this.setNumerator(this.getNumerator() - that.getNumerator());
        }else{
            this.setDenominator(this.getDenominator()*that.getDenominator());
            this.setNumerator(this.getNumerator()*that.getDenominator() - this.getDenominator()*that.getNumerator());
        }
        this.generateMagnitude();
        this.simplify();
    }

    public Fraction getSubtract(Fraction that){
        return this.getAdd(that.getNegative());
    }

    public void multiply(Fraction that){
        this.setNumerator(this.getNumerator() * that.getNumerator());
        this.setDenominator(this.getDenominator()*that.getDenominator());
        this.generateMagnitude();
        this.simplify();
    }

    public Fraction getMultiply(Fraction that){
        Fraction result;
        result = new Fraction(this.getNumerator()*that.getNumerator(),
                this.getDenominator()*that.getDenominator());
        result.generateMagnitude();
        result.simplify();
        return result;
    }

    public void divide(Fraction that){
        this.setNumerator(this.getNumerator() * that.getDenominator());
        this.setDenominator(this.getDenominator() * that.getNumerator());
        generateMagnitude();
        this.simplify();
    }

    public Fraction getDivide(Fraction that){
        return this.getMultiply(that.getInverse());
    }

    @Override
    public String toString() {
        return (getDenominator() == 1)?String.valueOf(numerator):numerator + "/"+denominator;
    }
}
