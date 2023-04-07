package adapter;

public class VlcPlayer implements AdvanceMediaPlayer {
    @Override
    public void playByVlc(String fileName) {
        System.out.println("vlc play " + fileName);
    }

    @Override
    public void playByMp4(String fileName) {
    }
}
