package org.zerock.myapp;

import java.util.Arrays;
import java.util.Objects;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.zerock.myapp.task.SimpleTask;

import lombok.extern.slf4j.Slf4j;


//@Log4j2
@Slf4j
public class QuartzExample {	// Quartz 프레임워크를 사용하는 예제
	
	
    public static void main( String[] args ) throws SchedulerException {
        log.trace("main({}) invoked.", Arrays.toString(args));
        
        // ========================
        // Step1. Quartz 의 Job Scheduler 인스턴스 생성.
        // ========================
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        log.info("\t+ Step1 scheduler: {}, type: {}", scheduler, scheduler.getClass().getName());

        
        // ========================
        // Step2. Step1 에서 생성한 Scheduler 에 미리 선언한 Job(Task) 클래스를 등록.
        // ========================
        Objects.requireNonNull(scheduler);
        
        JobDetail simpleJob = 
	        JobBuilder.newJob(SimpleTask.class)
	        	// 작업의 이름으로 등록
	//        	.withIdentity("SimpleTask")			
	        	// 작업의 이름과 작업이 소속그룹명을 등록
	        	.withIdentity("SimpleTask", "GROUP1")
	        	.build();
        
        log.info("\t+ simpleJob: {}", simpleJob);

        
        // ========================
        // Step3. Step2 에서 생성한 JobDetail 에 대한 
        // 일정(의미: 언제/어떻게 구동시킬지) 생성
        // ========================
        Trigger simpleJobTrigger =
        	TriggerBuilder.newTrigger()
        		.withIdentity("simpleJobTrigger", "GROUP1")
        		.startNow()
        		.withSchedule(
        			SimpleScheduleBuilder.simpleSchedule()
//        				.withIntervalInSeconds(1)					// 1초 간격
        				.withIntervalInMilliseconds(1000L * 3)		// 3초 간격
        				.repeatForever()
        		)
        		.build();
        
        
                
        // ========================
        // Step4. Step2에서 생성한 JobDetail과 Step3에서 생성한 Trigger(일정)으로, 
        // 		  Quartz Scheduler 에 최종등록
        // ========================
        scheduler.scheduleJob(simpleJob, simpleJobTrigger);
        
        
        // ========================
        // Step3. Step1. Quartz 의 Job Scheduler 구동.
        // ========================
        scheduler.start();
        
    } // main
    
} // end class



