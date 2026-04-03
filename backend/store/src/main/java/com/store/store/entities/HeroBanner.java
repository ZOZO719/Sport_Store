package com.store.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "hero_banners")
@Data
@Builder
public class HeroBanner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(name = "imageUrl")
     private String imageUrl;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitles")
    private String subtitle;

    @Builder.Default
    @Column(name = "isActive")
    private boolean isActive =false;
}
