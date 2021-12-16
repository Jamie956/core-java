package com.jamie.design.pattern.adapter;

/**
 * 播放器接口
 */
interface MediaPlayer {
    /**
     * 执行播放，有实现类实现
     * @param audioType 播放格式
     * @param fileName 文件名
     */
    void play(String audioType, String fileName);
}
