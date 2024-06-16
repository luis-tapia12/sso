package com.jcontrerast.sso.service.impl;

import com.jcontrerast.sso.dto.PageDTO;
import com.jcontrerast.sso.model.Group;
import com.jcontrerast.sso.model.GroupUser;
import com.jcontrerast.sso.model.User;
import com.jcontrerast.sso.repository.GroupRepository;
import com.jcontrerast.sso.repository.GroupUserRepository;
import com.jcontrerast.sso.service.GroupService;
import com.jcontrerast.sso.utils.Utils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
    private final GroupUserRepository groupRepository;

    public GroupServiceImpl(
            GroupRepository repository,
            GroupUserRepository groupRepository) {
        this.repository = repository;
        this.groupRepository = groupRepository;
    }

    @Override
    public Page<Group> getGroups(PageDTO pageDTO) {
        return repository.findAll(Utils.getPageable(pageDTO));
    }

    @Override
    public Group getGroup(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The group with id " + id + " does not exist."));
    }

    @Override
    public Group saveGroup(Group group) {
        group.setId(UUID.randomUUID().toString());
        return repository.save(group);
    }

    @Override
    public Group updateGroup(Group group) {
        Group currentGroup = getGroup(group.getId());
        Utils.copyNonNull(group, currentGroup);
        return repository.save(currentGroup);
    }

    @Override
    public void deleteGroup(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("The group with id " + id + " does not exist");
        }
    }

    @Override
    public Page<User> getUsersByGroupId(PageDTO pageDTO, String id) {
        return groupRepository.findByGroupId(id, Utils.getPageable(pageDTO));
    }

    @Override
    public GroupUser saveGroupUser(GroupUser groupUser) {
        groupUser.setId(UUID.randomUUID().toString());
        return groupRepository.save(groupUser);
    }

    @Override
    public void deleteGroupUser(GroupUser groupUser) {
        if (groupRepository.existsByUserIdAndGroupId(groupUser.getUserId(), groupUser.getGroupId())) {
            groupRepository.deleteByUserIdAndGroupId(groupUser.getUserId(), groupUser.getGroupId());
        } else {
            throw new RuntimeException("The relationship between group and user does not exist");
        }
    }
}
