package com.endava.Cinema.securityConfiguration;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.endava.Cinema.securityConfiguration.UserPermissions.*;


public enum UserRol {
    CLIENT(Sets.newHashSet(MOVIE_READ,RESERVATION_READ,RESERVATION_WRITE,SHOWTIME_READ)),

    ADMIN(Sets.newHashSet(MOVIE_READ,MOVIE_WRITE,RESERVATION_READ,SHOWTIME_READ,SHOWTIME_WRITE));

    private final Set<UserPermissions> permissions;// es un set porque los permisos tienen que ser unicos

    UserRol(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {//Preguntar esto,cual es la diferencia de usar directamente los roles
        final Set<SimpleGrantedAuthority> authorities =
                getPermissions().stream().map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
