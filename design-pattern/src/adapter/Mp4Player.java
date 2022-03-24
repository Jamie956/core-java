package adapter;

/**
 * 子类支持多种行为
 */
public class Mp4Player implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String fileName) {
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("play mp4 " + fileName);
    }
}
