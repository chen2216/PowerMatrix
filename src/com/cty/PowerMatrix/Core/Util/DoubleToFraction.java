package com.cty.PowerMatrix.Core.Util;

import com.cty.PowerMatrix.Core.Model.Fraction;

public class DoubleToFraction {
    public static Fraction toFraction(String str){
        String[] array = str.split("\\.");
        int a = Integer.parseInt(array[0]);//获取整数部分
        int b = (array.length == 2)?Integer.parseInt(array[1]):0;//获取小数部分
        int length =(array.length == 2)?array[1].length():0;
        int numerator = (int) (a * Math.pow(10, length) + b);
        int denominator = (int) Math.pow(10, length);
        Fraction temp = new Fraction(numerator,denominator);
        temp.simplify();
        return temp;
    }

    public static void main(String[] args) {
        Fraction f = toFraction("22.1");
        System.out.println(f);
    }
}
