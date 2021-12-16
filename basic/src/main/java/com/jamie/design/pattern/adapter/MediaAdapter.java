package com.jamie.design.pattern.adapter;

/**
 * 播放器实现类
 */
public class MediaAdapter implements MediaPlayer {
    AdvanceMediaPlayer advanceMediaPlayer;

    /**
     * 按不同格式，适配不同的播放格式类型
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
     * 按播放格式，使用播放媒介播放文件
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
