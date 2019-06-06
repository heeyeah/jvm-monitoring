package jvm.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stress")
public class CommandController {

	public static final Logger logger = LoggerFactory.getLogger(CommandController.class);

	@GetMapping(value = "/go")
	public String goStressForJVM() throws Throwable {

		Runtime runtime = Runtime.getRuntime();

		logger.info("==================== heap memory ====================");

		logger.info("totalMemory : {}MB", runtime.totalMemory());
		logger.info("maxMemory   : {}MB", runtime.maxMemory());
		logger.info("freeMemory  : {}MB", runtime.freeMemory());

		logger.info("========================================================");

		return "go stress";
	}

}
