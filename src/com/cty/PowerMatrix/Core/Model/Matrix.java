package com.cty.PowerMatrix.Core.Model;

import java.util.Arrays;

public class Matrix {
    int row;
    int column;
    MatrixItem[][] matrix;

    public Matrix(int row,int column){
        this.row = row;
        this.column = column;
        matrix = new MatrixItem[row][column];
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public MatrixItem[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(MatrixItem[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < row;i++){
            for (int j = 0; j<column;j++){
                sb.append(getMatrix()[i][j]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
