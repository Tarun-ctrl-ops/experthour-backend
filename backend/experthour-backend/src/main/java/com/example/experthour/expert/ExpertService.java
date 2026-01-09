package com.example.experthour.expert;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpertService {

    private final ExpertRepository expertRepository;

    public ExpertService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }

    public Expert create(Expert expert) {
        return expertRepository.save(expert);
    }

    public List<Expert> getAll() {
        return expertRepository.findAll();
    }

    public Expert updateAvailability(Long expertId, String from, String to) {
        Expert expert = expertRepository.findById(expertId).orElseThrow();
        expert.setAvailableFrom(from);
        expert.setAvailableTo(to);
        return expertRepository.save(expert);
    }
}
