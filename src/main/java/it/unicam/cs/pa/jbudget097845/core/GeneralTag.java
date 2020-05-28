package it.unicam.cs.pa.jbudget097845.core;

import java.util.concurrent.atomic.AtomicInteger;

public class GeneralTag implements Tag {

    private static AtomicInteger ids = new AtomicInteger(0);
    private final int id;
    private final TagType type;
    private String name;
    private String description;

    public GeneralTag(String name, String description, TagType type) {
        this.id = ids.incrementAndGet();
        this.type = type;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public TagType getType() {
        return this.type;
    }
}
