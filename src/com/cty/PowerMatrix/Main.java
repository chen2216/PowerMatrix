package com.cty.PowerMatrix;

import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;
import com.cty.PowerMatrix.Core.Util.Euclidian;

public class Main {
    public static void main(String[] args) {
        System.out.println(Euclidian.calcGCD(695,1112));
        Fraction f = new Fraction(33,3);
        f.simplify();
        System.out.println(f);
    }
}
