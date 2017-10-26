package com.ljs.core;

import java.io.Serializable;

/**
 * Created by 暮雨而歌 on 2017/10/16.
 */

public class IObject implements Serializable{
    public int id;
    public String name;
    public int    icon;

    public IObject(int id){
        this.id = id;
    }
}
