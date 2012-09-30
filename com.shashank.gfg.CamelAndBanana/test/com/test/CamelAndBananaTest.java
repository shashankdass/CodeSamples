package com.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import com.shashank.gfg.CamelAndBananaPuzzle;

public class CamelAndBananaTest {
	CamelAndBananaPuzzle tester;
	@Before
	 public void setUp(){
		tester = new CamelAndBananaPuzzle();
		
   }
	@Test
	public void test() {
	    assertEquals("Result", 533, tester.findNumberOfBananas(3000,1000,1000));
	}

}
