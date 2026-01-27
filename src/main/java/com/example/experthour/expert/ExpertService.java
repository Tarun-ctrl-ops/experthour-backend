package com.example.experthour.expert;

import com.example.experthour.dto.expert.AvailabilityDto;
import com.example.experthour.dto.expert.ExpertResponseDto;
import com.example.experthour.mapper.ExpertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpertService {

    private final ExpertRepository expertRepository;
    private final ExpertMapper expertMapper;

    public List<ExpertResponseDto> getAll() {
        return expertRepository.findAll()
                .stream()
                .map(expertMapper::toDto)
                .toList();
    }

    public ExpertResponseDto create(Expert expert) {
        expert.setStatus(ExpertStatus.APPROVED);
        return expertMapper.toDto(expertRepository.save(expert));
    }

    public void saveAvailability(Long id, AvailabilityDto dto) {
        Expert expert = expertRepository.findById(id).orElseThrow();
        expert.setAvailableFrom(String.valueOf(dto.from()));
        expert.setAvailableTo(String.valueOf(dto.to()));
        expertRepository.save(expert);
    }

    public Map<String, Boolean> getAvailability(Long id) {
        Expert expert = expertRepository.findById(id).orElseThrow();
        return Map.of(
                "available", expert.getAvailableFrom() != null && expert.getAvailableTo() != null
        );
    }
    public void approveExpert(Long id) {
        Expert expert = expertRepository.findById(id).orElseThrow();
        expert.setStatus(ExpertStatus.APPROVED);
        expertRepository.save(expert);
    }

}
