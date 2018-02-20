package com.workspaceit.pmc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by anik on 12/18/17.
 */
@Component
public class Environment {

    @Value("${jdbc.user}")
    private String jdbcUser;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;
    //File Path
    @Value("${file.tmp.path}")
    private String tmpFilePath;

    @Value("${file.common.path}")
    private String commonFilePath;

    @Value("${file.watermark.preview.sample.uri}")
    private String watermarkSamplePreviewImgUri;

    @Value("${file.photographer.profile.path}")
    private String photographerProfilePath;

    public String getHbm2ddl() {
        return hbm2ddl;
    }

    public String getJdbcUser() {
        return jdbcUser;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public String getJdbcDriverClassName() {
        return jdbcDriverClassName;
    }

    public String getUrl() {
        return url;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

	public String getTmpFilePath() {
		return tmpFilePath;
	}

    public String getPhotographerProfilePath() {
        return photographerProfilePath;
    }

    public String getCommonFilePath() {
        return commonFilePath;
    }

    public void setCommonFilePath(String commonFilePath) {
        this.commonFilePath = commonFilePath;
    }

    public String getWatermarkSamplePreviewImgUri() {
        return watermarkSamplePreviewImgUri;
    }

    public void setWatermarkSamplePreviewImgUri(String watermarkSamplePreviewImgUri) {
        this.watermarkSamplePreviewImgUri = watermarkSamplePreviewImgUri;
    }
}