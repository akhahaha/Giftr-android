package com.akhahaha.giftr.android.model;

/**
 * UserStatus model
 * Created by Alan on 4/29/2016.
 */
public class UserStatus {
    public static final UserStatus ACTIVE = new UserStatus(1, "Active");
    public static final UserStatus INACTIVE = new UserStatus(2, "Inactive");
    public static final UserStatus DELETED = new UserStatus(3, "Deleted");
    public static final UserStatus FLAGGED = new UserStatus(4, "Flagged");

    private Integer id;
    private String name;

    public UserStatus() {
    }

    public UserStatus(Integer id) {
        this.id = id;
        switch (id) {
            case 1:
                this.name = "Active";
                break;
            case 2:
                this.name = "Inactive";
                break;
            case 3:
                this.name = "Deleted";
                break;
            case 4:
                this.name = "Flagged";
                break;
            default:
                this.name = "Active";
                break;
        }
    }

    public UserStatus(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
