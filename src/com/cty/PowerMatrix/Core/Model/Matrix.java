package com.cty.PowerMatrix.Core.Model;

import com.cty.PowerMatrix.Core.Exception.MatrixFormatNotMatchException;
import com.cty.PowerMatrix.Core.Exception.MatrixNotSquareException;
import com.cty.PowerMatrix.Core.Util.DoubleToFraction;

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
       this.setMatrix(matrix);
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
        this.row = matrix.length;
        this.column = matrix[0].length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < row;i++){
            for (int j = 0; j<column;j++){
                sb.append((getMatrix()[i][j]!= null)?getMatrix()[i][j].toString() + " ":"null");
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

    private Matrix deleteRow(int nRow){
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

    private Matrix deleteColumn(int nCol){
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
        Fraction determinant = new Fraction(0,1);
        for (int j = 0;j < this.getColumn();j++){
            determinant.add(DoubleToFraction.toFraction(String.valueOf(Math.pow(-1,j))).
                    getMultiply(
                    getMatrix()[0][j].getMultiply(this.deleteRow(0).deleteColumn(j).getDeterminant()))
            );
        }
        return determinant;
    }

    public void transpose(){
        Fraction[][] f = new Fraction[this.getColumn()][this.getRow()];
        for (int i = 0;i < this.getRow();i++){
            for (int j = 0;j < this.getColumn();j++){
                f[j][i] = this.getMatrix()[i][j];
            }
        }
        this.setMatrix(f);
    }

    public Matrix getTranspose(){
        Fraction[][] f = new Fraction[this.getColumn()][this.getRow()];
        for (int i = 0;i < this.getRow();i++){
            for (int j = 0;j < this.getColumn();j++){
                f[j][i] = this.getMatrix()[i][j];
            }
        }
        return new Matrix(f);
    }

    public void rowOperation(int r1,int r2,Fraction f){
        Fraction[] tempRow = new Fraction[this.getColumn()];
        for(int j = 0;j < getColumn();j++){
            tempRow[j] = this.getMatrix()[r2][j].getMultiply(f).getAdd(this.getMatrix()[r1][j]);
        }
        System.arraycopy(tempRow,0,this.getMatrix()[r1],0,this.getColumn());
    }

    public void simplify(){
        for (Fraction[] fRow:getMatrix()) {
            for (Fraction fCell:fRow) {
                fCell.simplify();
            }
        }
    }

    public void toEchelon(){
        int c = 0,h = 0;
        while(h < getRow() && c < getColumn()){
            if (getMatrix()[h][c].getMagnitude() == 0){
                for (int i = h+1;i < getRow();i++){
                    if (getMatrix()[i][c].getMagnitude() == 0){
                        continue;
                    }
                    swapRow(i,h);
                    toEchelon();
                    return;
                }
            }else{
                rowOperation(h, getMatrix()[h][c].getInverse());
                for (int i = h+1;i<getRow();i++){
                    rowOperation(i, h, this.getMatrix()[i][c].getDivide(this.getMatrix()[h][c]).getNegative());
                }
                h++;
            }
            c++;
        }
    }


    public void rowOperation(int row,Fraction f){
        for (int i = 0;i < this.getColumn();i++){
            this.getMatrix()[row][i].multiply(f);
        }
    }

    public void toREchelon(){
        toEchelon();
        for (int i = getRow() - 1;i >= 0;i--){
            for (int j = 0;j < getColumn();j++){
                if (getMatrix()[i][j].getMagnitude() == 1){
                    for (int k = i - 1;k >= 0;k--){
                        rowOperation(k,i,this.getMatrix()[k][j].getNegative());
                    }
                }
            }
        }
    }

    public void add(Matrix that) throws MatrixFormatNotMatchException{
        if (this.getRow() != that.getRow() || this.getColumn() != that.getColumn())throw new MatrixFormatNotMatchException("Addition");
        for (int i = 0;i < getRow();i++){
            for (int j = 0;j < getColumn();j++){
                getMatrix()[i][j].add(that.getMatrix()[i][j]);
            }
        }
    }

    public Matrix getAdd(Matrix that) throws MatrixFormatNotMatchException{
        if (this.getRow() != that.getRow() || this.getColumn() != that.getColumn())throw new MatrixFormatNotMatchException("Addition");
        Matrix temp = new Matrix(getRow(),getColumn());
        for (int i = 0;i < temp.getRow();i++){
            for (int j = 0;j < temp.getColumn();j++){
                temp.getMatrix()[i][j] = this.getMatrix()[i][j].getAdd(that.getMatrix()[i][j]);
            }
        }
        return temp;
    }

    public void subtract(Matrix that) throws MatrixFormatNotMatchException{
        if (this.getRow() != that.getRow() || this.getColumn() != that.getColumn())throw new MatrixFormatNotMatchException("Subtraction");
        for (int i = 0;i < getRow();i++){
            for (int j = 0;j < getColumn();j++){
                getMatrix()[i][j].subtract(that.getMatrix()[i][j]);
            }
        }
    }

    public Matrix getSubtract(Matrix that) throws MatrixFormatNotMatchException{
        if (this.getRow() != that.getRow() || this.getColumn() != that.getColumn())throw new MatrixFormatNotMatchException("Subtraction");
        Matrix temp = new Matrix(getRow(),getColumn());
        for (int i = 0;i < temp.getRow();i++){
            for (int j = 0;j < temp.getColumn();j++){
                temp.getMatrix()[i][j] = this.getMatrix()[i][j].getSubtract(that.getMatrix()[i][j]);
            }
        }
        return temp;
    }

    public Matrix getMultiply(Matrix that) throws MatrixFormatNotMatchException{
        if (this.getColumn() != that.getRow())throw new MatrixFormatNotMatchException("Multiplication");
        int bound = this.getColumn();
        Matrix temp = new Matrix(this.getRow(),that.getColumn());
        for (int i = 0;i < this.getRow();i++){
            for (int j = 0;j < that.getColumn();j++){
                Fraction ft = new Fraction(0,1);
                for (int k = 0;k < bound;k++){
                    System.out.println(this.getMatrix()[i][k]+" * " + that.getMatrix()[k][j]+" = " +
                            this.getMatrix()[i][k].getMultiply(that.getMatrix()[k][j]));
                    ft.add(this.getMatrix()[i][k].getMultiply(that.getMatrix()[k][j]));
                }
                temp.getMatrix()[i][j] = ft;
            }
        }
        return temp;
    }

    public void multiply(Matrix that) throws MatrixFormatNotMatchException {
        Matrix temp = this.getMultiply(that);
        this.setRow(temp.getRow());
        this.setColumn(temp.getColumn());
        this.setMatrix(temp.getMatrix());
    }

    public Matrix inv() throws MatrixNotSquareException {
        if (getDeterminant().getMagnitude() == 0){ return null;}
        Matrix adj = new Matrix(this.getRow(),this.getColumn());
        for (int i = 0;i < getRow();i++){
            for (int j = 0;j < getColumn();j++){
                adj.getMatrix()[i][j] = this.deleteRow(i).deleteColumn(j).getDeterminant().getMultiply(
                        DoubleToFraction.toFraction(String.valueOf(Math.pow(-1,j+i))));
                adj.getMatrix()[i][j].divide(this.getDeterminant());
            }
        }
        adj.transpose();
        return adj;
    }
}
