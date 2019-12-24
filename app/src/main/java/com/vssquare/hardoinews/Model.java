package com.vssquare.hardoinews;

class Model {

    static final int IMAGE_TYPE =1;
    public String title, subtitle, Image;
    private int type;

    Model(int mtype, String mtitle, String msubtitle, String image){
        this.title = mtitle;
        this.subtitle = msubtitle;
        this.type = mtype;
        this.Image = image;
    }
}
