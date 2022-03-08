package com.cat.pattern.adapter;

/**
 * 播放器接口实现类
 */
public class AudioPlayer implements MediaPlayer {
    /**
     * 播放器播放按不同格式选择 不同的适配器 或者 直接执行播放操作
     * @param audioType 播放格式
     * @param fileName 文件名
     */
    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "mp3":
                //默认支持播放map3
                System.out.println("play mp3 " + fileName);
                break;
            case "vlc":
            case "mp4":
                //交给适配器去执行，只要vlc和mp4格式都交给适配器执行就可以了
                MediaAdapter mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
                break;
            default:
                System.out.println("Not support " + audioType);
        }
    }
}
