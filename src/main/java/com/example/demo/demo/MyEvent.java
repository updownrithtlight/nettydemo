package com.example.demo.demo;

import java.util.EventObject;

public class MyEvent extends EventObject {



    public MyEvent(AppConfig source) {
        super(source);
    }
}
