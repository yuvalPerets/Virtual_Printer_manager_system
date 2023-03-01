package com.example.printerApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrinterApplicationTests {

	@Test
	void Test_Liveness() {
        Printer p = new Printer(Long.valueOf(100));


	}


}
