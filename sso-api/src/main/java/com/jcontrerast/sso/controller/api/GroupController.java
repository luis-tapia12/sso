package com.jcontrerast.sso.controller.api;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.Group;
import com.jcontrerast.sso.model.GroupUser;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.service.GroupService;
import com.jcontrerast.sso.validation.Create;
import com.jcontrerast.sso.validation.Update;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Group> getGroups(PageDTO pageDTO) {
        return service.getGroups(pageDTO);
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable String id) {
        return service.getGroup(id);
    }

    @PostMapping
    public Group saveGroup(@Validated(Create.class) @RequestBody Group group) {
        return service.saveGroup(group);
    }

    @PatchMapping("/{id}")
    public Group updateGroup(@PathVariable String id, @Validated(Update.class) @RequestBody Group group) {
        group.setId(id);
        return service.updateGroup(group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable String id) {
        service.deleteGroup(id);
    }

    @GetMapping("/{id}/users")
    public Page<User> getUsersByGroupId(PageDTO pageDTO, @PathVariable String id) {
        return service.getUsersByGroupId(pageDTO, id);
    }

    @PostMapping("/{id}/users/{userId}")
    public GroupUser saveGroupUser(@PathVariable String id, @PathVariable String userId) {
        return service.saveGroupUser(new GroupUser(id, userId));
    }

    @DeleteMapping("/{id}/users/{userId}")
    public void deleteGroupUser(@PathVariable String id, @PathVariable String userId) {
        service.deleteGroupUser(new GroupUser(id, userId));
    }
}
