package com.jamie.pattern;

/**
 * 适配器模式
 */
public class AdapterDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }



    interface AdvanceMediaPlayer {
        void playVlc(String fileName);
        void playMp4(String fileName);
    }

    static class VlcPlayer implements AdvanceMediaPlayer {

        @Override
        public void playVlc(String fileName) {
            System.out.println("play vlc name="+fileName);
        }

        @Override
        public void playMp4(String fileName) {

        }
    }

    static class Mp4Player implements AdvanceMediaPlayer {
        @Override
        public void playVlc(String fileName) {

        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("play mp4 name="+fileName);
        }
    }

    interface MediaPlayer {
        void play(String audioType, String fileName);
    }

    static class MediaAdapter implements MediaPlayer {
        AdvanceMediaPlayer advanceMediaPlayer;

        public MediaAdapter(String audioType) {
            switch (audioType) {
                case "vlc":
                    advanceMediaPlayer = new VlcPlayer();
                    break;
                case "mp4":
                    advanceMediaPlayer = new Mp4Player();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            switch (audioType) {
                case "vlc":
                    advanceMediaPlayer.playVlc(fileName);
                    break;
                case "mp4":
                    advanceMediaPlayer.playMp4(fileName);
                    break;
                default:
                    break;
            }
        }
    }

    static class AudioPlayer implements MediaPlayer {
        MediaAdapter mediaAdapter;

        @Override
        public void play(String audioType, String fileName) {
            switch (audioType) {
                case "mp3":
                    System.out.println("play mp3 name="+fileName);
                    break;
                case "vlc":
                case "mp4":
                    mediaAdapter = new MediaAdapter(audioType);
                    mediaAdapter.play(audioType, fileName);
                    break;
                default:
                    System.out.println("Not support "+audioType);
            }
        }
    }


}
