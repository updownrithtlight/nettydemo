package com.example.demo.al;

public class Insertion {
    public static void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j=i;j>0&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }

    public static void sort(String[] a,int lo,int hi,int d){
        for (int i=lo;i<=hi;i++){
            for (int j=i;j>lo&&less(a[j],a[j-1],d);j--){
                exch(a,j,j-1);
            }
        }
    }
    private static boolean less(String a,String b,int d){
        return a.substring(d).compareTo(b.substring(d))<0;
    }
    private static boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable t=a[i];
        a[i]=a[j];
        a[j]=t;

    }
}
