package ru.gb.diplom.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity.Votes;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Long> {
}
