package adapter;

/**
 * 子类支持多种行为
 */
public class VlcPlayer implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("play vlc " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
    }
}
