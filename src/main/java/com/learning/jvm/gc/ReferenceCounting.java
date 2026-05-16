package com.learning.jvm.gc;

import java.io.IOException;

public class ReferenceCounting {
    public static A a2 = null;

    public static void main(String[] args) throws IOException {
        A a1 = new A();
        B b1 = new B();
        a1.b = b1;
        b1.a = a1;
        a2 = a1;
        System.in.read();

    }
}


class A{
    B b;
}

class B{
    A a;
}