package com.workspaceit.pmc.util;

import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;
import com.workspaceit.pmc.validation.advertisement.section.SectionResourceForm;

public class FileToken {
    private boolean multiFile;
    private FILE_TYPE fileType;
    private SectionResourceForm[] secResources;
    private Integer singleToken;
    private String url;

    public FileToken(SectionResourceForm[] secResources, FILE_TYPE fileType) {
        this.multiFile = true;
        this.fileType = fileType;
        this.secResources = secResources;
    }

    public FileToken(Integer token, FILE_TYPE fileType,String url) {
        this.multiFile = false;
        this.fileType = fileType;
        this.singleToken = token;
        this.url = url;
    }

    public boolean hasMultiFile() {
        return multiFile;
    }

    public FILE_TYPE getFileType() {
        return fileType;
    }

    public SectionResourceForm[] getSecResources() {
        return secResources;
    }

    public Integer getSingleToken() {
        return singleToken;
    }

    public String getUrl() {
        return url;
    }
}
