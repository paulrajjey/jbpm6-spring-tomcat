package com.jbpm.process.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.task.api.InternalTaskService;
import org.springframework.stereotype.Component;

import com.jbpm.process.bo.impl.TaskController;

@Component
@ManagedBean
@SessionScoped
public class ProcessTask {
	
	private String comment;
    private Map<String,Object> content;
    private Task task;
    private long taskId;
    private String user;
    private List<TaskSummary> tasks;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Map<String, Object> getContent() {
		return content;
	}
	public void setContent(Map<String, Object> content) {
		this.content = content;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<TaskSummary> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskSummary> tasks) {
		this.tasks = tasks;
	}
    
	public void retrieveTasks () {
        String message;
        try {
        	TaskController taskController = new TaskController();
        	TaskService taskService = taskController.processTask();
        	List<TaskSummary> list;
        	list = taskService.getTasksAssignedAsPotentialOwner(user, "en-UK");
        	       
        	this.tasks =    list;
       
        	
        	
            //tasks = taskService.retrieveTaskList(user);
            message = "Retrieved " + tasks.size() + " task(s) for user " + user + ".";
            //logger.info(message);
            
            System.out.println(message);
        } catch (Exception e) {
            message = "Cannot retrieve task list for user " + user + ".";
           // logger.log(Level, message, e);
            //logger.error(message,e); 
            System.out.println(message);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().getFlash()
                    .put("msg", message);
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash()
                .put("msg", message);
         
    }

	public String approveTask () {
		String message;
        try {
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("out_comment", comment);
            TaskController taskController = new TaskController();
        	TaskService taskService = taskController.processTask();
           // taskBean.approveTask(user, taskId, result);
        	taskService.start(taskId,user);
        	taskService.complete(taskId, user, content);
            message = "The task " + taskId + " has been successfully approved.";
            //logger.info(message);
            System.out.println(message);
        } catch (Exception e) {
            message = "Unable to approve the task " + taskId + ".";
            e.printStackTrace();
            System.out.println(message);
           // logger.error(message,e); 
            //logger.log(Level.SEVERE, message, e);
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash()
                .put("msg", message);
        return "index.xhtml?faces-redirect=true";
	}
	public void queryTask() {
		String message = null;
		
		try {
            task = getTask(taskId);
            content = getContent();
            message = "Loaded task " + taskId + ".";
            //logger.info(message);
            System.out.println(message);
        } catch (Exception e) {
            message = "Unable to query for task with id = " + taskId;
            //logger.error(message,e); 
            e.printStackTrace();
            System.out.println(message);
            //log(Level.SEVERE, message, e);
          
        }
		FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getFlash()
                .put("msg", message);
	}
	
	 public Task getTask(long taskId) throws Exception {
	       
	        Task task = null;
	        TaskController taskController = new TaskController();
        	TaskService taskService = taskController.processTask();
	        
	            task = taskService.getTaskById(taskId);
	            content = ((InternalTaskService) taskService).getTaskContent(taskId);
	           
	        
	        return task;
	    }

}
