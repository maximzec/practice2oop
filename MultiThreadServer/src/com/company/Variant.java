
package com.company;


import java.util.ArrayList;

public class Variant implements Result  {
    private Double []x;
    @Override
    public String getResult() {
        String answer;
        double k,b;
        k = (x[3] - x[1]) / (x[2] - x[0]);
        b = x[1] - k * x[0];
        answer = "y = " + k + "x+" + b;
        return answer;
    }
    public Variant(String s){
        String [] s1 = s.split(",");
        x = new Double[s1.length];
        for(int i=0;i<s1.length;i++){
            x[i] =(Double.parseDouble(s1[i]));
        }
    };
    public Variant(Double[]a){
        x=a;
    }
    public Variant(ArrayList<Double> L){
        //L.toArray(x);
        x = new Double[L.size()];
        L.toArray(x);

    }
}