package me.cahrypt.com.keywords.permissions;

public enum Permissions {

    USER("keywords.user"),
    ADMIN("keywords.admin");

    private final String perm;

    Permissions(String perm) {
        this.perm = perm;
    }

    public String getPerm() {
        return perm;
    }
}
