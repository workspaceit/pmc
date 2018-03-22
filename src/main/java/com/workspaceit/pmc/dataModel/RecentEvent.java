package com.workspaceit.pmc.dataModel;

import java.util.Date;

public class RecentEvent {
    private int id;
    private String name;
    private Date createAt;
    private int noOfImages;
    private int noOfPhotographers;
    private Boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getNoOfImages() {
        return noOfImages;
    }

    public void setNoOfImages(int noOfImages) {
        this.noOfImages = noOfImages;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getNoOfPhotographers() {
        return noOfPhotographers;
    }

    public void setNoOfPhotographers(int noOfPhotographers) {
        this.noOfPhotographers = noOfPhotographers;
    }

    @Override
    public String toString() {
        return "RecentEvent{" +
                "name='" + name + '\'' +
                ", createAt=" + createAt +
                ", noOfImages=" + noOfImages +
                ", status=" + status +
                '}';
    }
}
