package src;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SoundManager {
    private static final Map<String, byte[]> CACHE = new ConcurrentHashMap<>();

    private SoundManager() {}

    public static void play(String resourcePath) {
        byte[] data = load(resourcePath);
        if (data == null) return;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Clip loop(String resourcePath) {
        byte[] data = load(resourcePath);
        if (data == null) return null;
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void stop(Clip clip) {
        if (clip != null) {
            try {
                clip.stop();
            } catch (Exception ignored) {}
            try {
                clip.close();
            } catch (Exception ignored) {}
        }
    }

    private static byte[] load(String resourcePath) {
        try {
            byte[] cached = CACHE.get(resourcePath);
            if (cached != null) return cached;
            URL url = SoundManager.class.getResource(resourcePath);
            if (url == null) return null;
            try (InputStream is = url.openStream(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[4096];
                int n;
                while ((n = is.read(buffer)) > 0) {
                    bos.write(buffer, 0, n);
                }
                byte[] data = bos.toByteArray();
                CACHE.put(resourcePath, data);
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
