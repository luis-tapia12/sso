package com.jcontrerast.sso.service;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.Group;
import com.jcontrerast.sso.model.GroupUser;
import com.jcontrerast.sso.model.User;
import org.springframework.data.domain.Page;

public interface GroupService {
    Page<Group> getGroups(PageDTO pageDTO);

    Group getGroup(String id);

    Group saveGroup(Group group);

    Group updateGroup(Group group);

    void deleteGroup(String id);

    Page<User> getUsersByGroupId(PageDTO pageDTO, String id);

    GroupUser saveGroupUser(GroupUser groupUser);

    void deleteGroupUser(GroupUser groupUser);
}
