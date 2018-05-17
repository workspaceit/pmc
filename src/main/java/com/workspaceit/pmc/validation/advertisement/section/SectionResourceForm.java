package com.workspaceit.pmc.validation.advertisement.section;

public class SectionResourceForm {


    protected Integer token;
    protected String url;
    protected Boolean selectedStatic;

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
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
}
