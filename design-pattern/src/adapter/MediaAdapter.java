package adapter;

/**
 * 适配器，实现共同的接口，行为保持一致
 */
public class MediaAdapter implements MediaPlayer {
    AdvanceMediaPlayer play;

    /**
     * 在实例化时确定 play
     */
    public MediaAdapter(String audioType) {
        switch (audioType) {
            case "vlc":
                play = new VlcPlayer();
                break;
            case "mp4":
                play = new Mp4Player();
                break;
            default:
                break;
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "vlc":
                play.playVlc(fileName);
                break;
            case "mp4":
                play.playMp4(fileName);
                break;
            default:
                break;
        }
    }
}
