package com.cty.PowerMatrix.PC;

import com.cty.PowerMatrix.Core.Exception.MatrixNotSquareException;
import com.cty.PowerMatrix.Core.Model.Fraction;
import com.cty.PowerMatrix.Core.Model.Matrix;
import com.cty.PowerMatrix.Core.Util.MatrixParser;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    HashMap<String,Matrix> map = new HashMap<>();

    public void getCommand(String command) throws MatrixNotSquareException {
        String name;
        String op;
        if (command.contains("=")){
            name = command.substring(0,command.indexOf("=")).trim();
            op = command.substring(command.indexOf("=")+1).trim();
        }else{
            name = "ans";
            op = command;
        }

        if (op.contains("[") && op.contains("]")){
            Matrix temp = new MatrixParser().toMatrix(op);
            map.put(name,temp);
            System.out.println(name + ": \n" + temp.toString());
        }else {
            String keyword = op.substring(0,op.indexOf("(")).trim();
            String param = op.substring(op.indexOf("(")+1,op.indexOf(")")).trim();
            switch (keyword){
                case "det":
                    Fraction det;
                    if (map.containsKey(param)){
                        det = map.get(param).getDeterminant();
                        System.out.println(op +" = " + det);
                    }else {

                    }
                    break;
                case "ref":
                    if (map.containsKey(param)){
                        map.get(param).toEchelon();
                        System.out.println(op +" = \n" + map.get(param));
                    }
                    break;
                case "rref":
                    if (map.containsKey(param)){
                        map.get(param).toREchelon();
                        System.out.println(op +" = \n" + map.get(param));
                    }
                case "inv":
                    if (map.containsKey(param)){
                        Matrix temp = map.get(param).inv();
                        System.out.println(op +" = \n" + temp);
                    }
                    break;
                case "add":
                    break;
                case "sbt":
                    break;
                case "mul":
                    break;

            }
        }

    }

    public static void main(String[] args){
        Console c = new Console();
        Scanner sc = new Scanner(System.in);
        String input = "";
        try {
            while (!input.equals("exit")) {
                input = sc.nextLine();
                c.getCommand(input);
            }
        }catch (MatrixNotSquareException e){
            e.printStackTrace();
        }
    }
}
