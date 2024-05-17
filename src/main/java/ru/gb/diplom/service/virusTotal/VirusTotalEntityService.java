package ru.gb.diplom.service.virusTotal;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity.Votes;
import ru.gb.diplom.controller.domain.virusTotal.ipVotesEntity.VotesData;
import ru.gb.diplom.repository.*;

import java.util.List;

@Service
@Data
public class VirusTotalEntityService {

    @Autowired
    private VotesRepository votesRepository;

    @Autowired
    private VotesDataRepository votesDataRepository;

    @Autowired
    private VotesDataLinksRepository votesDataLinksRepository;

    @Autowired
    private VotesLinksRepository votesLinksRepository;

    @Autowired
    private VotesMetaRepository votesMetaRepository;

    @Autowired
    private VirusTotalEntityRepository virusTotalEntityRepository;

    public void save(Votes votes) {
        System.out.println(votes);
        votesMetaRepository.save(votes.getMeta());
        votesLinksRepository.save(votes.getLinks());
        for (VotesData votesData : votes.getData()) {
            votesDataLinksRepository.save(votesData.getLinks());
            votesDataRepository.save(votesData);
        }
        virusTotalEntityRepository.save(votes);
    }

    public List<Votes> findAll() {
        return virusTotalEntityRepository.findAll();
    }
}
