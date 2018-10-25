package com.application.nest.canvasejemplo;

import android.graphics.Path;

/**
 * Created by Nest on 14/4/2018.
 */

public class StorePathPojo {
    private Path path;
    private Integer color;

    public StorePathPojo(Path path, Integer color) {
        this.path = path;
        this.color = color;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }
}
