package com.example.demo.al;


public class LSD {
    public static void sort(String[] a ,int W){
        int N = a.length;
        int R=256;
        String[] aux = new String[N];

        for (int d=W-1;d >=0;d--){
            int[] count=new int[R+1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d)+1]++;
            }
            for (int r = 0; r < R; r++) {
                count[r+1]+=count[r];
            }
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++]=a[i];
            }
            for (int i = 0; i < N; i++) {
                a[i]=aux[i];
            }

        }
    }

    public static void main(String[] args) {
        String[] a={"abcd","bcde","cded","affe","ddaf","zefa","wzde","ddae","adfe","123d","111a"};
        LSD.sort(a,4);
        for (int i = 0; i < a.length; i++) {

            System.out.println(a[i]);
        }
    }

}
