package jvm.monitoring.service;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StressService {

	public static final Logger logger = LoggerFactory.getLogger(StressService.class);

	public static AtomicInteger atomicInteger = new AtomicInteger(1);
	public ExecutorService executorService;
	private int sleepTime;
	private int threadCount;

	public void goStress(int sleepTime, int threadCount) throws Exception {

		this.sleepTime = sleepTime;
		this.threadCount = threadCount;

		createThreadPool();
		shootStressToThreads();

	}

	public void stopStress() {
		logger.info("stop");
		executorService.shutdownNow();
	}

	private void createThreadPool() throws Exception {

		executorService = Executors.newFixedThreadPool(threadCount);

	}

	private void shootStressToThreads() {

		for (int i = 0; i < threadCount; i++) {

			executorService.submit(() -> {

				logger.debug("hello! ");

				if (sleepTime > 0) {
					
					long start = System.currentTimeMillis();
					int a;
					
					while(true) {
						
						a = atomicInteger.incrementAndGet();
						if(a % 10 == 0) logger.info("yeah ~ " + a);
						if(System.currentTimeMillis() - start > sleepTime) {
							break;
						}
					}
					
					
				} else if (sleepTime == -1) {
					while(true) {
						logger.info("a");
					}
				}

				logger.debug("end! ");

			});

		}
		
		executorService.shutdown();
	}
	
	public static void main(String[] args) throws Exception {
		StressService service = new StressService();
		
		service.goStress(-1, 10);
		service.stopStress();
	}
}
