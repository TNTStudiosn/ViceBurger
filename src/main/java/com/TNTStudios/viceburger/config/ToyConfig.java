package com.TNTStudios.viceburger.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;  // ← Usamos el Random de Minecraft

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToyConfig {
    private static final File CONFIG_FILE = new File("config/viceburger/toys_config.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type TYPE = new TypeToken<Map<String, Double>>(){}.getType();

    private static Map<String, Double> toyChances = new HashMap<>();
    private static List<ToyEntry> weightedList = new ArrayList<>();

    private static class ToyEntry {
        String id;
        double weight;
        ToyEntry(String id, double weight) { this.id = id; this.weight = weight; }
    }

    public static void load() {
        try {
            // Asegura que exista la carpeta
            File parent = CONFIG_FILE.getParentFile();
            if (!parent.exists()) parent.mkdirs();

            // Si no existe, crea el JSON default
            if (!CONFIG_FILE.exists()) saveDefault();

            // Lee el JSON
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                toyChances = GSON.fromJson(reader, TYPE);
            }

            rebuildWeightedList();
        } catch (Exception e) {
            System.err.println("[ViceBurger] Error loading toy config: " + e.getMessage());
        }
    }

    private static void saveDefault() {
        toyChances.clear();
        toyChances.put("viceburger:abeja", 0.2);
        toyChances.put("viceburger:chaneke", 0.3);
        toyChances.put("viceburger:huevo", 0.15);
        toyChances.put("viceburger:rana", 0.2);
        toyChances.put("viceburger:tani", 0.15);

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(toyChances, writer);
        } catch (Exception e) {
            System.err.println("[ViceBurger] Error saving default config: " + e.getMessage());
        }

        rebuildWeightedList();
    }

    private static void rebuildWeightedList() {
        weightedList.clear();
        toyChances.forEach((id, weight) -> {
            if (weight > 0) weightedList.add(new ToyEntry(id, weight));
        });
    }

    /** Ahora acepta el Random de Minecraft directamente */
    public static Identifier getRandomToy(Random random) {
        double total = weightedList.stream().mapToDouble(e -> e.weight).sum();
        double roll = random.nextDouble() * total;
        for (ToyEntry e : weightedList) {
            roll -= e.weight;
            if (roll <= 0) return new Identifier(e.id);
        }
        return new Identifier("viceburger:abeja");
    }
}
