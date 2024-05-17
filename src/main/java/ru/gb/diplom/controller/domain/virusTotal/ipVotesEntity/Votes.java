package ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "votes")
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<VotesData> data;

    @OneToOne(cascade = CascadeType.PERSIST)
    private VotesMeta meta;

    @OneToOne(cascade = CascadeType.PERSIST)
    private VotesLinks links;
}
