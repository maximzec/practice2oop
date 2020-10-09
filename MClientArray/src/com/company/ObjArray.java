package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class ObjArray implements Serializable {
    private Double[] x;
    private String req;
    private ArrayList<Double> req1;
    private String message;
    public ObjArray(Double[] a, String m){
        x=a; message = m;
    }

    public ObjArray(String req1 , String m){
        req = req1 ; message = m;
    }

    public ObjArray(ArrayList<Double> req , String m){
        req1 = req ; message = m;
    }
    public Double[] getArray(){
        return x;
    }
    public String getM(){
        return message;
    }

    public String getReq() {
        return req;
    }

    public ArrayList<Double> getReq1() {
        return req1;
    }
}