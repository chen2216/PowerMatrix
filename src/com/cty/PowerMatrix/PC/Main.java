package com.cty.PowerMatrix.PC;

import com.cty.PowerMatrix.Core.Exception.MatrixNotSquareException;
import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;
import com.cty.PowerMatrix.Core.Util.Euclidian;
import com.cty.PowerMatrix.Core.Util.MatrixParser;

public class Main {
    public static void main(String[] args) throws MatrixNotSquareException{
        MatrixParser mp = new MatrixParser();
        Matrix matrix = mp.toMatrix("[0 1 2 1;0 2 0 4;0 3 5 3]");
        //Matrix m1 = mp.toMatrix("[1 0 0 0;0 0 1 0;0 0 0 1]");
        matrix.transpose();
        //Matrix matrix = mp.toMatrix("[3 2 1;1 4 1;5 3 1;]");
        //System.out.println(matrix.toString());
        //matrix.swapRow(1,2);
        //matrix = matrix.deleteRow(1);
        //matrix = matrix.deleteColumn(0);
        //System.out.println(matrix);
        //matrix.rowOperation(1,2,3);
        //System.out.println(matrix.getTranspose());
        //System.out.println(matrix);
        System.out.println(matrix);
        matrix.toREchelon();
        //m1.toEchelon();
        //System.out.println(m1);
        System.out.println(matrix);
    }
}
