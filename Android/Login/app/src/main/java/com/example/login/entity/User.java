package com.example.login.entity;

public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String currentVersion;
    private String latestVersion;
    private String updateDescription;
    private String headPortrait;
    private String nickName;
    private String vipTime;
    private String userCategory;
    private String registerDate;

    public User(Integer userId, String userName, String userPassword, String currentVersion, String latestVersion, String updateDescription, String headPortrait, String nickName, String vipTime, String userCategory, String registerDate) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.currentVersion = currentVersion;
        this.latestVersion = latestVersion;
        this.updateDescription = updateDescription;
        this.headPortrait = headPortrait;
        this.nickName = nickName;
        this.vipTime = vipTime;
        this.userCategory = userCategory;
        this.registerDate = registerDate;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVipTime() {
        return vipTime;
    }

    public void setVipTime(String vipTime) {
        this.vipTime = vipTime;
    }

    public String getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}
