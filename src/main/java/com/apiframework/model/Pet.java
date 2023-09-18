package com.apiframework.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pet {
    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tags> tags;
    private PetStatus status;

    @Data
    @Builder
    static class Category {
        private String id;
        private String name;
    }

    @Data
    @Builder
    static class Tags {
        private String id;
        private String name;
    }

    public enum PetStatus {
        AVAILABLE,
        PENDING,
        SOLD;
    }

    public Pet(String uniqueid) {
        this.id = uniqueid;
        this.category = Category.builder().id("1").name("animal").build();
        this.name = "doggie";
        this.photoUrls = new ArrayList<>();
        this.photoUrls.add("url1");
        this.photoUrls.add("url2");
        this.tags = new ArrayList<>();
        this.tags.add(Tags.builder().id("1").name("petdog").build());
        this.tags.add(Tags.builder().id("2").name("labrador").build());
        this.status = PetStatus.AVAILABLE;
    }
}
