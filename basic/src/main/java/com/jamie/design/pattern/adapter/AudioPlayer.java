package com.jamie.design.pattern.adapter;

/**
 * 播放器接口实现类
 */
public class AudioPlayer implements MediaPlayer {
    /**
     * 播放器播放按不同格式选择不同的适配器
     * @param audioType 播放格式
     * @param fileName 文件名
     */
    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "mp3":
                System.out.println("play mp3 " + fileName);
                break;
            case "vlc":
            case "mp4":
                MediaAdapter mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
                break;
            default:
                System.out.println("Not support " + audioType);
        }
    }
}
