package com.jcontrerast.sso.model.id;

import lombok.*;

import java.io.Serial;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID userId;

    private UUID roleId;
}