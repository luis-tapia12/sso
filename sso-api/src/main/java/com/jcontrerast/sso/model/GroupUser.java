package com.jcontrerast.sso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SSO_GROUP_USERS")
public class GroupUser {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "GROUP_ID")
    private String groupId;

    public GroupUser(String groupId, String userId) {
        this.groupId = groupId;
        this.userId = userId;
    }
}
