package com.example.quickcashgroup5.EmployeeSelection;

/**
 * The class for the Data Model select employee
 */
public class DataModelSelectEmployee {
    String name;
    String key;

    /**
     * The method to get key
     *
     * @return
     */
    public String getKey() {
        return key;
    }

    /**
     * The method to set key
     *
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * The method to get name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * The method to set name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor for this class
     *
     * @param key
     * @param name
     */
    public DataModelSelectEmployee(String key, String name) {
        this.key = key;
        this.name = name;
    }
}
