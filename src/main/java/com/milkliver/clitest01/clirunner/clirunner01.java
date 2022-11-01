package com.milkliver.clitest01.clirunner;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.milkliver.clitest01.utils.CommandTools;

@Component
public class clirunner01 {

	private static final Logger log = LoggerFactory.getLogger(clirunner01.class);

	@Autowired
	CommandTools commandTools;

	@Value("${command:whoami}")
	String command;

	@PostConstruct
	public void commandLineRunner() {
		log.info(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + " ...");
		try {
			commandTools.executeCommandLine(command, true, true, 1000000);
		} catch (Exception e) {
			log.error(e.getMessage());
			for (StackTraceElement elem : e.getStackTrace()) {
				log.error(elem.toString());
			}
		}
		log.info(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName()
				+ " finish");
	}
}
