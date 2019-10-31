package com.bugsButchery.demo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Extra {

	@Test
	public void test() {
		int num1=10;
		int num2=3;
		int num3=num1/num2;
		System.out.println(num3);
	}

}
