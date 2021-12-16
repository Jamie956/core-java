package com.jamie.design.pattern.adapter;

/**
 * 播放器媒体类型接口
 */
interface AdvanceMediaPlayer {
    /**
     * 播放 vlc 格式
     */
    void playVlc(String fileName);
    /**
     * 播放 mp4 格式
     */
    void playMp4(String fileName);
}