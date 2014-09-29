package com.jbpm.process.bo.impl;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.task.TaskService;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

public class TaskController {
	
	
	public TaskService processTask(){
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("local-em-spring.xml");
	AbstractPlatformTransactionManager aptm = (AbstractPlatformTransactionManager) context.getBean( "jbpmTxManager" );
    RuntimeManager manager = (RuntimeManager) context.getBean("runtimeManager");

    RuntimeEngine engine = manager.getRuntimeEngine(ProcessInstanceIdContext.get());
    KieSession ksession = engine.getKieSession();
    TaskService taskService = engine.getTaskService();
    
    return taskService;
	}
	
	

}
