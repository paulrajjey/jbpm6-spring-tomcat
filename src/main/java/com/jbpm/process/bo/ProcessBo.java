package com.jbpm.process.bo;

import org.kie.api.runtime.process.ProcessInstance;

/**
 * 
 *
 */
public interface ProcessBo {
	
	public ProcessInstance startProcess(String recip, int reward); 
}
