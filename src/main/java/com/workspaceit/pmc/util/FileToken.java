package com.workspaceit.pmc.util;

import com.workspaceit.pmc.constant.advertisement.FILE_TYPE;

public class FileToken {
    private boolean multiFile;
    private FILE_TYPE fileType;
    private Integer[] tokens;
    private Integer singleToken;

    public FileToken( Integer[] tokens,FILE_TYPE fileType) {
        this.multiFile = true;
        this.fileType = fileType;
        this.tokens = tokens;
    }
    public FileToken(Integer token,FILE_TYPE fileType) {
        this.multiFile = false;
        this.fileType = fileType;
        this.singleToken = token;
    }

    public boolean hasMultiFile() {
        return multiFile;
    }

    public FILE_TYPE getFileType() {
        return fileType;
    }

    public Integer[] getTokens() {
        return tokens;
    }

    public Integer getSingleToken() {
        return singleToken;
    }
}
