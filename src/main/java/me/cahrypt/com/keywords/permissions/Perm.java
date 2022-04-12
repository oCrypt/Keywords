package me.cahrypt.com.keywords.permissions;

public enum Perm {

    USER("keywords.user"),
    ADMIN("keywords.admin");

    private String perm;

    Perm(String perm) {
        this.perm = perm;
    }

    public String getPerm() {
        return perm;
    }
}
