package adapter;

public class Mp4Player implements AdvanceMediaPlayer {
    @Override
    public void playByVlc(String fileName) {
    }

    @Override
    public void playByMp4(String fileName) {
        System.out.println("mp4 play " + fileName);
    }
}
