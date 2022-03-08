package com.cat.pattern.adapter;

/**
 * 播放媒介实现类：vlc
 */
public class VlcPlayer implements AdvanceMediaPlayer {
    /**
     * 播放vlc
     */
    @Override
    public void playVlc(String fileName) {
        System.out.println("play vlc " + fileName);
    }
    /**
     * 播放mp4
     */
    @Override
    public void playMp4(String fileName) {

    }
}
