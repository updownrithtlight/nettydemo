package com.example.demo.al;

public class Quick3String {
    private static int chartAt(String s,int d){
        if(d<s.length()){
            return s.charAt(d);
        }else {
            return -1;
        }
    }
    public static void sort(String[] a){

        sort(a,0,a.length-1,0);
    }
    private static void sort(String[] a,int lo,int hi,int d){
        if(hi<=lo){
            return;
        }
        int lt=lo,gt=hi;

        int v=chartAt(a[lo],d);

        int i=lo+1;

        while (i<=gt){
            int t=chartAt(a[i],d);
            if(t<v){
                exch(a,lt++,i++);
            }else if(t>v){
                exch(a,i,gt--);
            }else {
                i++;
            }
            sort(a,lo,lt-1,d);
            if(v>=0) {
                sort(a,lt,gt,d+1);
            }
            sort(a,gt+1,hi,d);
        }
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;

    }

    public static void main(String[] args) {
        String[] a={"abcd","bcde","cded","afe","daf","zefa","de","ae","ade","123d","11a"};
        Quick3String.sort(a);
        for (int i = 0; i < a.length; i++) {

            System.out.println(a[i]);
        }
    }
}
