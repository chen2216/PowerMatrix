package com.cty.PowerMatrix.Core.Util;

import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;

public class MatrixParser {
    public Matrix toMatrix(String str){
        String data = str.substring(str.indexOf("[")+1,str.indexOf("]"));
        String[] rows = data.split(";");
        int nRow = rows.length;
        int nCol = rows[0].split(" ").length;
        Fraction[][] f = new Fraction[nRow][nCol];
        for (int i = 0;i < rows.length;i++){
            String[] cells = rows[i].split(" ");
            for (int j = 0;j < cells.length;j++){
                f[i][j] = DoubleToFraction.toFraction(cells[j]);
            }
        }
        return new Matrix(f);
    }
}
