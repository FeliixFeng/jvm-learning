package com.learning.jvm.gc;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo2 {
    public static void main(String[] args) {

        byte[] bytes = new byte[1024*1024*100];
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(bytes);
        bytes = null;

        System.out.println(softReference.get());


        byte[] bytes2 = new byte[1024*1024*100];
        System.out.println(softReference.get());


    }
}
