package ru.gb.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity.Votes;
import ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity.VotesLinks;

@Repository
public interface VotesLinksRepository extends JpaRepository<VotesLinks, Long> {
}
