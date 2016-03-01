package com.tianque.plugin.example.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.tianque.plugin.AbstractJobPlugin;

@Component("testJob")
public class ExampleJob extends AbstractJobPlugin {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
	}

	@Override
	public String getName() {
		return "testJob";
	}

	@Override
	public String getCronExpression() {
		return "0 0/30 * * * ?";
	}

}
