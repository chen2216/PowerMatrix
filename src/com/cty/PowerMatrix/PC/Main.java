package com.cty.PowerMatrix.PC;

import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;
import com.cty.PowerMatrix.Core.Util.Euclidian;
import com.cty.PowerMatrix.Core.Util.MatrixParser;

public class Main {
    public static void main(String[] args) {
        MatrixParser mp = new MatrixParser();
        Matrix matrix = mp.toMatrix("[3 2;1 4;5 3]");
        System.out.println(matrix.toString());
        matrix.swapRow(1,2);
        System.out.println(matrix);
    }
}
