package org.kie.spring.factorybeans;


import org.kie.internal.runtime.manager.RuntimeManagerRegistry;
import org.kie.spring.factorybeans.RuntimeManagerFactoryBean;

/**
 * Workaround for BZ1121764
 * 	Spring/JBPM: IllegalStateException: RuntimeManager with id spring-rmr is already active	
 * 	https://bugzilla.redhat.com/show_bug.cgi?id=1121764 
 * 		
 */
public class FixedRuntimeManagerFactoryBean extends RuntimeManagerFactoryBean {

    @Override
    public Object getObject() throws Exception {
    	if(RuntimeManagerRegistry.get().isRegistered(getIdentifier())) {
    		// get the runtime manager from the registry
    		return RuntimeManagerRegistry.get().getManager(getIdentifier());
    	}
    	else {
    		// Create the runtime manager
    		return super.getObject();
    	} 
    }	
	
    @Override
    public boolean isSingleton() {
        return true;
    } 
}