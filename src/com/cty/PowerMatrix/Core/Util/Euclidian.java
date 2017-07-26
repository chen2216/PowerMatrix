package com.cty.PowerMatrix.Core.Util;

public class Euclidian {
    public static int calcGCD(int i1,int i2){
        int mod;
        do {
            mod = i1 % i2;
            i1 = i2;
            i2 = mod;
        }while (mod != 0);
        return i1;
    }
}
