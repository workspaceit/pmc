package com.workspaceit.pmc.validation.advertisement.section;

public class SectionResourceUpdateForm {
    private Integer id;
    private String url;
    private Boolean selectedStatic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getSelectedStatic() {
        return selectedStatic;
    }

    public void setSelectedStatic(Boolean selectedStatic) {
        this.selectedStatic = selectedStatic;
    }

    @Override
    public String toString() {
        return "SectionResourceUrlUpdateForm{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", selectedStatic=" + selectedStatic +
                '}';
    }
}