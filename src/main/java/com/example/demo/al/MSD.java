package com.example.demo.al;

public class MSD {
    private static int R=256;
    private static final int M=15;
    private static String[] aux;
    private static int charAt(String s,int d){
        if(d<s.length()) {
            return s.charAt(d);
        }else {
            return -1;
        }
    }

    public static void sort(String[] a){
        int N = a.length;
        aux=new String[N];
        sort(a,0,N-1,0);
    }

    private static void sort(String[] a,int lo,int hi,int d){
        if(hi<=lo+M){
            Insertion.sort(a,lo,hi,d);
            return;
        }
        int[] count=new int[R+2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i],d)+2]++;
        }
        for (int i = 0; i < R + 1; i++) {
            count[i+1]+=count[i];
        }
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i],d)+1]++]=a[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i]=aux[i-lo];
        }
        for (int i = 0; i < R; i++) {
            sort(a,lo+count[i],lo+count[i+1],d+1);
        }
    }

    public static void main(String[] args) {
        String[] a={"abcd","bcde","cded","afe","daf","zefa","de","ae","ade","123d","11a"};
        MSD.sort(a);
        for (int i = 0; i < a.length; i++) {

            System.out.println(a[i]);
        }
    }
}
