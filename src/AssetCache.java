package src;

import java.awt.Image;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

public final class AssetCache {
    private static final Map<String, Image> IMAGES = new ConcurrentHashMap<>();

    private AssetCache() {}

    public static Image get(String path) {
        if (path == null) return null;
        return IMAGES.computeIfAbsent(path, p -> {
            URL u = AssetCache.class.getResource(p);
            if (u == null) return null;
            return new ImageIcon(u).getImage();
        });
    }
}
