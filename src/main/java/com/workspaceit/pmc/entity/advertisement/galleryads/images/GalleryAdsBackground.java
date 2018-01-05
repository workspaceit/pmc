package com.workspaceit.pmc.entity.advertisement.galleryads.images;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by mi_rafi on 1/5/18.
 */
@Entity
@DiscriminatorValue("BACKGRAOUND")
public class GalleryAdsBackground extends GalleryAdsImage {
}
