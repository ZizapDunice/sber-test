package org.example.sbertest.mapper;

import org.example.sbertest.modules.RoleEntity;
import org.example.sbertest.modules.RoleEnum;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public Set<RoleEnum> toRoleEnums(Set<RoleEntity> roles) {
        if (roles == null || roles.isEmpty()) {
            return Collections.emptySet();
        }
        return roles.stream()
                .map(RoleEntity::getName)
                .map(RoleEnum::valueOf)
                .collect(Collectors.toSet());
    }

    public Set<RoleEntity> toRoleEntities(Set<RoleEnum> roleEnums) {
        if (roleEnums == null || roleEnums.isEmpty()) {
            return Collections.emptySet();
        }
        return roleEnums.stream()
                .map(roleEnum -> new RoleEntity(roleEnum.name()))
                .collect(Collectors.toSet());
    }
}
