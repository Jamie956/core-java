package com.cat.pattern.adapter;

/**
 * 适配器，实现播放器接口，把自己也当作播放器
 * 播放器选择一个或多个指定格式的文件交给适配器去具体执行
 */
public class MediaAdapter implements MediaPlayer {
    AdvanceMediaPlayer advanceMediaPlayer;

    /**
     * 按格式实例化格式播放器
     */
    public MediaAdapter(String audioType) {
        switch (audioType) {
            case "vlc":
                advanceMediaPlayer = new VlcPlayer();
                break;
            case "mp4":
                advanceMediaPlayer = new Mp4Player();
                break;
            default:
                break;
        }
    }

    /**
     * 按格式播放文件
     */
    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "vlc":
                advanceMediaPlayer.playVlc(fileName);
                break;
            case "mp4":
                advanceMediaPlayer.playMp4(fileName);
                break;
            default:
                break;
        }
    }
}
