package com.example.printerApplication;

import com.sun.jdi.LongValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class longObjectTests {
    @Test
    public void CheckInitValue() {
        longObject newObject = new longObject();
        assertEquals(Long.valueOf(0), newObject.getValue());
    }

    @Test
    public void CheckConstructorValue() {
        longObject newObject = new longObject(5);
        assertEquals(Long.valueOf(5), newObject.getValue());
    }
     @Test
     public void CheckSetValue(){
         longObject newObject = new longObject();
         newObject.setValue(Long.valueOf(5));
         assertEquals(Long.valueOf(5), newObject.getValue());
     }

}