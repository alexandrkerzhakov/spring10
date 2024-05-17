package ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "data")
public class VotesData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    private String identifier;

    private String type;

    @OneToOne(cascade = CascadeType.PERSIST)
    private VotesDataLinks links;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Attributes attributes;
}
