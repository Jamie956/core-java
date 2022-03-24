package adapter;

/**
 * 定义接口，支持多种行为
 */
interface AdvanceMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}