package com.example.kamustigabahasa_2023606601031.models;

public class Word {
    private int id;
    private String indonesian;
    private String english;
    private String korean;

    public Word(int id, String indonesian, String english, String korean) {
        this.id = id;
        this.indonesian = indonesian;
        this.english = english;
        this.korean = korean;
    }

    public int getId() { return id; }

    public String getIndonesian() { return indonesian; }

    public String getEnglish() { return english; }

    public String getKorean() { return korean; }
}
