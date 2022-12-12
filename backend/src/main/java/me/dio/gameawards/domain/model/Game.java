package me.dio.gameawards.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "GAMES")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 500)
    private String description;
    private String cover;
    private long votes;
}
