package com.hd.ele.Bean;

import java.io.Serializable;
/**
 * Created by Administrator on 2018/12/19 0019.
 */

public class Kind {
    private static final long serialVersionUID = 1L;
    private String name;
    private int url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public Kind(String name, int url) {
        super();
        this.name = name;
        this.url = url;
    }
}
