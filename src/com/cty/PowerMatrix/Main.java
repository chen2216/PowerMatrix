package com.cty.PowerMatrix;

import com.cty.PowerMatrix.Core.Model.Fraction;

public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(3,5);
        Fraction f2 = new Fraction(9,4);
        System.out.println(f1.multiply(f2).getMagnitude());
    }
}
