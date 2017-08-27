package com.cty.PowerMatrix.PC;

import com.cty.PowerMatrix.Core.Exception.MatrixNotSquareException;
import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;
import com.cty.PowerMatrix.Core.Util.Euclidian;
import com.cty.PowerMatrix.Core.Util.MatrixParser;

public class Main {
    public static void main(String[] args) throws Exception{
        MatrixParser mp = new MatrixParser();
        Matrix m1 = mp.toMatrix("[1 5 3;2 3 8]");
        Matrix m2 = mp.toMatrix("[3 2;5 6;8 4]");
        System.out.println(m1);
        System.out.println(m2);
        Fraction f1 = new Fraction(0,1);
        Fraction f2 = new Fraction(2,1);
        System.out.println(f1.getAdd(f2));
        m1.multiply(m2);
        System.out.println(m1);
    }
}
