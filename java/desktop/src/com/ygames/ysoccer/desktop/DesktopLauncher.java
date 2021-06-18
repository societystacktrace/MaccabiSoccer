package com.ygames.ysoccer.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.ygames.ysoccer.YSoccer;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowSizeLimits(1280, 720, 9999, 9999);
        config.setWindowIcon(Files.FileType.Internal, "images/icon_128.png");
        config.setWindowIcon(Files.FileType.Internal, "images/icon_32.png");
        new Lwjgl3Application(new YSoccer(), config);
    }
}
