package com.jbpm.process.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jbpm.process.ProcessBaseImpl;
import org.kie.api.runtime.process.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jbpm.process.bo.ProcessBo;
import com.jbpm.process.bo.impl.ProcessBoImpl;


@Component
@ManagedBean
@SessionScoped
public class ProcessUserBean {
	
	// This is going to be injected by Spring framework
	@Autowired
	ProcessBo processBo;
	
	private String recipient;
	private int reward;
	
	/**
	 * Method registers user
	 */
	public String startProcess() {
		// Output some info
		System.out.println("process UserBean:: startproess for " + recipient + " " + reward );
		
		// Call the business object to register the user
		if(processBo == null){
			processBo =(ProcessBo) new ProcessBoImpl();
		}
		
		ProcessInstance ins = processBo.startProcess(recipient, reward);
		
		// Set the message here
		FacesContext fcontect = FacesContext.getCurrentInstance();
		String message = "Process instance " + ins.getId() + " has been successfully started." ;
		fcontect.getExternalContext().getFlash().put("msg", message);
		return "index.xhtml?faces-redirect=true";
		//FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration success", "success");  
       // FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	// Set the registrationBo attribute used by Spring
	public void setRegistrationBo(ProcessBo processBo) {
		this.processBo = processBo;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public ProcessBo getProcessBo() {
		return processBo;
	}

	public void setProcessBo(ProcessBo processBo) {
		this.processBo = processBo;
	}

	
	
}
