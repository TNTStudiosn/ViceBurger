package com.TNTStudios.viceburger.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.util.Identifier;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.*;

public class ToyConfig {

    private static final File CONFIG_FILE = new File("config/viceburger/toys_config.json");
    private static final Gson GSON = new Gson();
    private static final Type TYPE = new TypeToken<Map<String, Double>>(){}.getType();

    private static Map<String, Double> toyChances = new HashMap<>();
    private static List<ToyEntry> weightedList = new ArrayList<>();

    private static class ToyEntry {
        String id;
        double weight;

        ToyEntry(String id, double weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    public static void load() {
        try {
            if (!CONFIG_FILE.exists()) {
                saveDefault();
            }

            FileReader reader = new FileReader(CONFIG_FILE);
            toyChances = GSON.fromJson(reader, TYPE);
            reader.close();

            rebuildWeightedList();
        } catch (Exception e) {
            System.err.println("[ViceBurger] Error loading toy config: " + e.getMessage());
        }
    }

    private static void saveDefault() {
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
    }

    private static void rebuildWeightedList() {
        weightedList.clear();
        toyChances.forEach((id, weight) -> {
            if (weight > 0) {
                weightedList.add(new ToyEntry(id, weight));
            }
        });
    }

    public static Identifier getRandomToy(Random random) {
        double totalWeight = weightedList.stream().mapToDouble(e -> e.weight).sum();
        double roll = random.nextDouble() * totalWeight;

        for (ToyEntry entry : weightedList) {
            roll -= entry.weight;
            if (roll <= 0) {
                return new Identifier(entry.id);
            }
        }

        return new Identifier("viceburger:abeja");
    }
}
