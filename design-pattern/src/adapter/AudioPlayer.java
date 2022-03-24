package adapter;

public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "mp3":
                //默认支持播放map3
                System.out.println("play mp3 " + fileName);
                break;
            case "vlc":
            case "mp4":
                //交给适配器去执行
                MediaAdapter mediaAdapter = new MediaAdapter(audioType);
                mediaAdapter.play(audioType, fileName);
                break;
            default:
                System.out.println("Not support " + audioType);
        }
    }
}
