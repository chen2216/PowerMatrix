package com.cty.PowerMatrix.PC;

import com.cty.PowerMatrix.Core.Exception.MatrixNotSquareException;
import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;
import com.cty.PowerMatrix.Core.Util.Euclidian;
import com.cty.PowerMatrix.Core.Util.MatrixParser;

public class Main {
    public static void main(String[] args) throws MatrixNotSquareException{
        MatrixParser mp = new MatrixParser();
        Matrix matrix = mp.toMatrix("[3 2 1 2;1 4 1 5;5 3 1 9;4 5 3 7;]");
        System.out.println(matrix.toString());
        //matrix.swapRow(1,2);
        //matrix = matrix.deleteRow(1);
        //matrix = matrix.deleteColumn(0);
        //System.out.println(matrix);
        System.out.println(matrix.getDeterminant());
    }
}
