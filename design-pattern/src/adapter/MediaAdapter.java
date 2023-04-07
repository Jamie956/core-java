package adapter;

public class MediaAdapter implements MediaPlayer {
    AdvanceMediaPlayer play;

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
                play.playByVlc(fileName);
                break;
            case "mp4":
                play.playByMp4(fileName);
                break;
            default:
                break;
        }
    }
}
