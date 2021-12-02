package com.example.quickcashgroup5;

import android.widget.Button;

public class DataModelSelectEmployee {
    String name;
    String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataModelSelectEmployee(String key, String name) {
        this.key=key;
        this.name = name;
    }
}
