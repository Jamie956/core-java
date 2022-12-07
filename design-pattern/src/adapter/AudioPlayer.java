package adapter;

//入口，调用者不需要关心适配谁
public class AudioPlayer implements MediaPlayer {

    @Override
    public void play(String audioType, String fileName) {
        switch (audioType) {
            case "mp3":
                //默认
                System.out.println("play mp3 " + fileName);
                break;
            case "vlc":
            case "mp4":
                //由适配器适配执行
                new MediaAdapter(audioType).play(audioType, fileName);
                break;
            default:
                System.out.println("Not support " + audioType);
        }
    }
}
