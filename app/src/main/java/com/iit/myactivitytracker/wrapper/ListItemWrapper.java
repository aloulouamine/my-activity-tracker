package com.iit.myactivitytracker.wrapper;

/**
 * Created by slouma on 21/02/2015.
 */
public class ListItemWrapper {


    private int mItemImage;
    private String mTitle;
    private String mTime;
    private String mState;

    public ListItemWrapper() {

    }


    public ListItemWrapper(int image, String title, String time, String state) {
        mItemImage = image;
        mTitle = title;
        mTime = time;
        mState = state;

    }


    public int getItemImage() {
        return mItemImage;
    }

    public void setItemImage(int mItemImage) {
        this.mItemImage = mItemImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }
}
