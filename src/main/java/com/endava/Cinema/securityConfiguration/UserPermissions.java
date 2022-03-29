package com.endava.Cinema.securityConfiguration;

public enum UserPermissions {
    MOVIE_READ("movie:read"),// module:permiso es una manera estandar dedefinir como se dan los permisos
    MOVIE_WRITE("movie:write"),// read solo podes leer no modificar,write para modificar y leer
    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write"),
    SHOWTIME_READ("showTime:read"),
    SHOWTIME_WRITE("showTime:write");

    private final String permission;

    UserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
