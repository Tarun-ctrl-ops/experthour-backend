package com.example.experthour.availability;

import com.example.experthour.expert.Expert;
import com.example.experthour.expert.ExpertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository repo;
    private final ExpertRepository expertRepo;

    public Availability create(Long expertId, Availability a) {
        Expert expert = expertRepo.findById(expertId).orElseThrow();
        a.setExpert(expert);
        return repo.save(a);
    }

    public List<Availability> getForExpert(Long expertId) {
        return repo.findByExpertId(expertId);
    }
}

