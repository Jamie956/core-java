package com.cat.design.pattern.adapter;

/**
 * 播放器接口
 */
interface MediaPlayer {
    /**
     * 播放器根据格式和文件名执行播放，由实现类实现
     * @param audioType 文件格式
     * @param fileName 文件名
     */
    void play(String audioType, String fileName);
}
