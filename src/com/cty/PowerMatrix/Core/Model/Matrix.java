package com.cty.PowerMatrix.Core.Model;

import java.util.Arrays;

public class Matrix {
    private int row;
    private int column;
    private Fraction[][] matrix;

    public Matrix(int row,int column){
        this.row = row;
        this.column = column;
        matrix = new Fraction[row][column];
    }

    public Matrix(Fraction[][] matrix) {
        this.matrix = matrix;
        this.row = matrix.length;
        this.column = matrix[0].length;
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

    public Fraction[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Fraction[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < row;i++){
            for (int j = 0; j<column;j++){
                sb.append(getMatrix()[i][j].toString()+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void swapRow(int row1,int row2){
        Fraction[] temp = new Fraction[this.getColumn()];
        System.arraycopy(this.getMatrix()[row1],0,temp,0,this.getColumn());
        System.arraycopy(this.getMatrix()[row2],0,this.getMatrix()[row1],0,this.getColumn());
        System.arraycopy(temp,0,this.getMatrix()[row2],0,this.getColumn());
    }
}
