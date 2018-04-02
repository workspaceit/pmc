package com.workspaceit.pmc.constant.watermark;

/**
 * Created by shawon on 1/8/18.
 */


public enum Placement {
    tl("Top Left"),
    tc("Top Center"),
    tr("Top Right"),
    cl("Center left"),
    cc("Center"),
    cr("Center right"),
    bl("Bottom Left"),
    bc("Bottom Center"),
    br("Bottom Right"),
    tile("Tile");
    private String displayName;

    Placement(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "Placement{" +
                "displayName='" + displayName + '\'' +
                '}';
    }
}
