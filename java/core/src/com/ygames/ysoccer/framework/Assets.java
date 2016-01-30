package com.ygames.ysoccer.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Assets {

    public static I18NBundle strings;
    public static List<String> locales;
    public static Font font14;
    public static FileHandle dataFolder;
    public static List<String> monthNames;

    public static void load(Settings settings) {
        loadLocales();
        loadStrings(settings);
        font14 = new Font(14);
        font14.load();
        dataFolder = Gdx.files.local("data/");
        setMonthNames();
    }

    private static void loadLocales() {
        locales = new ArrayList<String>();
        FileHandle[] files = Gdx.files.local("i18n/").list(".properties");
        for (FileHandle file : files) {
            String[] parts = file.nameWithoutExtension().split("_");
            if (parts.length > 1) {
                locales.add(parts[1]);
            } else {
                locales.add("en");
            }
        }
    }

    public static void loadStrings(Settings settings) {
        strings = I18NBundle.createBundle(Gdx.files.internal("i18n/strings"), new Locale(settings.locale));
    }

    private static void setMonthNames() {
        monthNames = new ArrayList<String>();
        monthNames.add(Calendar.JANUARY, Assets.strings.get("JANUARY"));
        monthNames.add(Calendar.FEBRUARY, Assets.strings.get("FEBRUARY"));
        monthNames.add(Calendar.MARCH, Assets.strings.get("MARCH"));
        monthNames.add(Calendar.APRIL, Assets.strings.get("APRIL"));
        monthNames.add(Calendar.MAY, Assets.strings.get("MAY"));
        monthNames.add(Calendar.JUNE, Assets.strings.get("JUNE"));
        monthNames.add(Calendar.JULY, Assets.strings.get("JULY"));
        monthNames.add(Calendar.AUGUST, Assets.strings.get("AUGUST"));
        monthNames.add(Calendar.SEPTEMBER, Assets.strings.get("SEPTEMBER"));
        monthNames.add(Calendar.OCTOBER, Assets.strings.get("OCTOBER"));
        monthNames.add(Calendar.NOVEMBER, Assets.strings.get("NOVEMBER"));
        monthNames.add(Calendar.DECEMBER, Assets.strings.get("DECEMBER"));
    }
}
