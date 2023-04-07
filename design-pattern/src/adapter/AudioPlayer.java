package adapter;

public class AudioPlayer implements MediaPlayer {
    // 适配器路由
    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "mp3":
                System.out.println("mp3 play " + fileName);
                break;
            case "vlc":
            case "mp4":
                new MediaAdapter(audioType).play(audioType, fileName);
                break;
            default:
                System.out.println("Not support " + audioType);
        }
    }
}
