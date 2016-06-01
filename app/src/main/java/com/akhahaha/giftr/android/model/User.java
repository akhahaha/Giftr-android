package com.akhahaha.giftr.android.model;

import java.util.Date;
import java.util.List;

/**
 * User model
 * Created by Alan on 4/29/2016.
 */
public class User {
    private Integer id;
    private String username;
    private String email;
    private UserStatus status;
    private Date joinDate;
    private Date lastActive;
    private Gender gender;
    private String location;
    private GiftType giftType;
    private String interests;
    private Integer priceMin;
    private Integer priceMax;
    private List<Integer> matches;

    /**
     * Creates a new User with default properties.
     */
    public User() {
        status = UserStatus.ACTIVE;
        Date currentDate = new Date();
        joinDate = currentDate;
        lastActive = currentDate;
        gender = Gender.UNKNOWN;
        giftType = GiftType.UNKNOWN;
        priceMin = 0;
        priceMax = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GiftType getGiftType() {
        return giftType;
    }

    public void setGiftType(GiftType giftType) {
        this.giftType = giftType;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public List<Integer> getMatches() {
        return matches;
    }

    public void setMatches(List<Integer> matches) {
        this.matches = matches;
    }
}
