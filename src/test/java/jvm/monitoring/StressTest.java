package jvm.monitoring;

import org.junit.Test;

import jvm.monitoring.service.StressService;

public class StressTest {

	@Test
	public void stressTest() throws Exception {
		
		StressService service = new StressService();
		
		int limitTime = 100;
		int threadCount = 10;
		
		service.goStress(limitTime, threadCount);
	}
}
