package com.cty.PowerMatrix.Core.Model;

import com.cty.PowerMatrix.Core.Exception.MatrixNotSquareException;
import com.cty.PowerMatrix.Core.Util.DoubleToFraction;

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
                sb.append(getMatrix()[i][j].toString() + " ");
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

    public void swapColumn(int column1,int column2){
        Fraction[] temp = new Fraction[this.getRow()];
        for (int i = 0;i < this.getRow();i++){
            temp[i] = this.getMatrix()[i][column1];
            this.getMatrix()[i][column1] = this.getMatrix()[i][column2];
            this.getMatrix()[i][column2] = temp[i];
        }
    }

    public Matrix deleteRow(int nRow){
        int count = 0;
        Fraction[][] f = new Fraction[this.getRow()-1][this.getColumn()];
        for (int i = 0;i < this.getRow();i++){
            if (i!=nRow){
                System.arraycopy(this.getMatrix()[i],0,f[count],0,this.getColumn());
                count++;
            }
        }
        return new Matrix(f);
    }

    public Matrix deleteColumn(int nCol){
        int count = 0;
        Fraction[][] f = new Fraction[this.getRow()][this.getColumn()-1];
        for (int i = 0;i < this.getRow();i++) {
            System.arraycopy(this.getMatrix()[i],0,f[i],0,nCol);
            count = nCol;
            System.arraycopy(this.getMatrix()[i],nCol+1,f[i],count,this.getColumn() - nCol - 1);
        }
        return new Matrix(f);
    }

    public Fraction getDeterminant() throws MatrixNotSquareException {
        if(this.getRow() != this.getColumn())throw new MatrixNotSquareException();
        if (this.getRow() == 1)return this.getMatrix()[0][0];
        if (this.getRow() == 2)return this.getMatrix()[0][0].
                getMultiply(this.getMatrix()[1][1]).
                getSubtract(this.getMatrix()[0][1].
                        getMultiply(this.getMatrix()[1][0]));
        Fraction determinant = new Fraction(0,1);
        for (int j = 0;j < this.getColumn();j++){
            determinant.add(DoubleToFraction.toFraction(String.valueOf(Math.pow(-1,j))).
                    getMultiply(
                    getMatrix()[0][j].getMultiply(this.deleteRow(0).deleteColumn(j).getDeterminant()))
            );
        }
        return determinant;
    }
}
