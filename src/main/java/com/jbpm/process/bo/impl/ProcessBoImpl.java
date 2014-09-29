package com.jbpm.process.bo.impl;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

import com.jbpm.process.bo.ProcessBo;


@Service
public class ProcessBoImpl implements ProcessBo {
	
	public ProcessInstance startProcess(String recipient, int reward) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("local-em-spring.xml");
		AbstractPlatformTransactionManager aptm = (AbstractPlatformTransactionManager) context.getBean( "jbpmTxManager" );
        RuntimeManager manager = (RuntimeManager) context.getBean("runtimeManager");

        RuntimeEngine engine = manager.getRuntimeEngine(ProcessInstanceIdContext.get());
        KieSession ksession = engine.getKieSession();
        TaskService taskService = engine.getTaskService();
        int ksessionId = ksession.getId();

        ProcessInstance processInstance = ksession.startProcess("org.jbpm.examples.rewards");

		// Output some info
		System.out.println("" + recipient + " " + reward  + "id " + processInstance.getId());
		return processInstance;
		//processEngine.getKsession();
		
		// TODO: Contact your database here
		// ...
	}

		
}
