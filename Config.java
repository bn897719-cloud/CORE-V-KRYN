package vn.kryn.core;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

public final class Config {
    private static final Path FILE = Path.of("config", "core-v-kryn.properties");
    public static boolean fps = true, ping = true, armor = true, potions = true, inventory = true;
    public static boolean neon = true, particles = true;
    public static int accent = 0xFFB45CFF;

    private Config() {}
    public static void load() {
        Properties p = new Properties();
        if (!Files.exists(FILE)) return;
        try (Reader r = Files.newBufferedReader(FILE)) {
            p.load(r);
            fps = Boolean.parseBoolean(p.getProperty("fps", "true"));
            ping = Boolean.parseBoolean(p.getProperty("ping", "true"));
            armor = Boolean.parseBoolean(p.getProperty("armor", "true"));
            potions = Boolean.parseBoolean(p.getProperty("potions", "true"));
            inventory = Boolean.parseBoolean(p.getProperty("inventory", "true"));
            neon = Boolean.parseBoolean(p.getProperty("neon", "true"));
            particles = Boolean.parseBoolean(p.getProperty("particles", "true"));
            accent = Integer.parseInt(p.getProperty("accent", Integer.toString(0xFFB45CFF)));
        } catch (Exception ignored) {}
    }
    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            Properties p = new Properties();
            p.setProperty("fps", Boolean.toString(fps));
            p.setProperty("ping", Boolean.toString(ping));
            p.setProperty("armor", Boolean.toString(armor));
            p.setProperty("potions", Boolean.toString(potions));
            p.setProperty("inventory", Boolean.toString(inventory));
            p.setProperty("neon", Boolean.toString(neon));
            p.setProperty("particles", Boolean.toString(particles));
            p.setProperty("accent", Integer.toString(accent));
            try (Writer w = Files.newBufferedWriter(FILE)) { p.store(w, "CORE V KRYN settings"); }
        } catch (IOException ignored) {}
    }
}
