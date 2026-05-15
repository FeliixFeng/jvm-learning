package com.learning.jvm.heap;


import java.io.IOException;
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) throws IOException {


        ArrayList<Object> objects = new ArrayList<>();

        System.in.read();
        while (true){
//            System.out.println("添加一次");
            objects.add(new byte[1024*1024*100]);
//            Thread.sleep(10);

        }
    }
}