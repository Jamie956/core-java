package com.jamie.design.pattern.adapter;

/**
 * 播放媒介子类：mp4
 */
public class Mp4Player implements AdvanceMediaPlayer {
    /**
     * 播放 vlc
     */
    @Override
    public void playVlc(String fileName) {

    }
    /**
     * 播放 mp4
     */
    @Override
    public void playMp4(String fileName) {
        System.out.println("play mp4 " + fileName);
    }
}
