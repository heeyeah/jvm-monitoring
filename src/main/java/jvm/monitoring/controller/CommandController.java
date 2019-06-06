package jvm.monitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jvm.monitoring.dto.CommandField;
import jvm.monitoring.service.StressService;

@RestController("/stress")
public class CommandController {

	public static final Logger logger = LoggerFactory.getLogger(CommandController.class);

	@Autowired
	public StressService stressService;

	@GetMapping(value = "/go")
	public String goStressForJVM(@RequestBody CommandField command) throws Throwable {

		logger.info("==================== goStressForJVM ====================");
		logger.info(command.toString());
		logger.info("========================================================");

		stressService.goStress(command.getLimitTime(), command.getThreadCount());

		return "go stress";
	}

	@GetMapping(value = "/stop")
	public String stopStressForJVM() throws Throwable {

		logger.info("=================== stopStressForJVM ===================");
		logger.info("========================================================");

		stressService.stopStress();
		
		return "stop stress";
	}
}
