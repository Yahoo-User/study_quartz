package org.zerock.myapp.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

// Quartz Scheduler 에 등록시킬 작업(Task)는 반드시
// Quartz 가 제공하는 Job 이란 인터페이스를 구현해야 합니다.
public class Task_A implements Job {
	
	@Override
	// 일정에 따라 실행될 로직을 이 메소드 안에서 구현하시면 됩니다.
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
		log.trace("execute({}) invoked.", context);
		
		try {
			log.info(">>> I'm a Task_A.");
		} catch(Exception orignal) {
			throw new JobExecutionException(orignal);	// caused-by 가 나오게 된다
		} // try-catch
	}	// POJO
	

} // end class
