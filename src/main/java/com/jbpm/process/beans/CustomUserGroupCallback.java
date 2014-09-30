package com.jbpm.process.beans;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.task.UserGroupCallback;

public class CustomUserGroupCallback implements UserGroupCallback {

	public boolean existsGroup(String groupId) {
		return groupId.equals("PM") || groupId.equals("HR");
	}

	public boolean existsUser(String userId) {
		return userId.equals("jiri") || userId.equals("mary") || userId.equals("Administrator");
	}

	public List<String> getGroupsForUser(String userId, List<String> groupIds,
			List<String> allExistingGroupIds) {
		List<String> groups = new ArrayList<String>();
        if (userId.equals("jiri"))
            groups.add("PM");
        else if (userId.equals("mary"))
            groups.add("HR");
        return groups;
	}

}
