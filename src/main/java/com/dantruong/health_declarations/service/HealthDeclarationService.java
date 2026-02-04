package com.dantruong.health_declarations.service;

import com.dantruong.health_declarations.connection.HealthDeclarantRepo;
import com.dantruong.health_declarations.connection.HealthDeclarationRepo;
import com.dantruong.health_declarations.dto.HealthDeclarationDto;
import com.dantruong.health_declarations.entity.HealthDeclarant;
import com.dantruong.health_declarations.entity.HealthDeclaration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthDeclarationService {
    private final HealthDeclarantRepo healthDeclarantRepo;
    private final HealthDeclarationRepo healthDeclarationRepo;

    public HealthDeclarationService(HealthDeclarantRepo healthDeclarantRepo, HealthDeclarationRepo healthDeclarationRepo) {
        this.healthDeclarantRepo = healthDeclarantRepo;
        this.healthDeclarationRepo = healthDeclarationRepo;
    }

    @Transactional
    public void saveAll(HealthDeclarationDto healthDeclarationDto) {
        HealthDeclarant healthDeclarant;

        // XỬ LÝ NGƯỜI KHAI  Có rồi thì update, chưa có thì tạo mới)
        if (healthDeclarationDto.getHealthDeclarantId() == null) {
            healthDeclarant = new HealthDeclarant();
        } else {
            healthDeclarant = healthDeclarantRepo.findById(healthDeclarationDto.getHealthDeclarantId())
                    .orElseThrow(() -> new RuntimeException("Không tim thấy người này"));
        }
        healthDeclarant.setName(healthDeclarationDto.getName());
        healthDeclarant.setYearOfBirth(healthDeclarationDto.getYearOfBirth());
        healthDeclarant.setGender(healthDeclarationDto.getGender());
        healthDeclarant.setNationality(healthDeclarationDto.getNationality());
        healthDeclarant.setCitizenId(healthDeclarationDto.getCitizenId());
        healthDeclarant.setPhoneNumber(healthDeclarationDto.getPhoneNumber());

        // Lưu người khai trước để lấy ID
        healthDeclarant = healthDeclarantRepo.save(healthDeclarant);

        //  XỬ LÝ TỜ KHAI (SỬA LẠI LOGIC QUAN TRỌNG)
        // Sai lầm cũ: Bạn tìm tờ khai cũ để ghi đè -> Mất lịch sử di chuyển.
        // Sửa lại: Luôn tạo tờ khai MỚI (new) để lưu lịch sử.

        HealthDeclaration healthDeclaration = new HealthDeclaration();
        healthDeclaration.setHealthDeclarant(healthDeclarant);
        healthDeclaration.setTravelType(healthDeclarationDto.getTravelType());
        healthDeclaration.setVehicleNumber(healthDeclarationDto.getVehicleNumber());
        healthDeclaration.setSeatNumber(healthDeclarationDto.getSeatNumber());
        healthDeclaration.setDepartureDate(healthDeclarationDto.getDepartureDate());
        healthDeclaration.setArrivalDate(healthDeclarationDto.getArrivalDate());
        healthDeclaration.setVisitedLocations(healthDeclarationDto.getVisitedLocations());
        healthDeclaration.setCity(healthDeclarationDto.getCity());
        healthDeclaration.setDistrict(healthDeclarationDto.getDistrict());
        healthDeclaration.setWard(healthDeclarationDto.getWard());
        healthDeclaration.setDetailAddress(healthDeclarationDto.getDetailAddress());
        healthDeclaration.setSymptoms(healthDeclarationDto.getSymptoms());
        healthDeclaration.setExposureHistory(healthDeclarationDto.getExposureHistory());

        if (healthDeclarationDto.getCreatedAt() == null) {
            healthDeclaration.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        } else {
            healthDeclaration.setCreatedAt(healthDeclarationDto.getCreatedAt());
        }
        healthDeclarationRepo.save(healthDeclaration);
        healthDeclarationDto.setHealthDeclarantId(healthDeclarant.getId());
    }


    public List<HealthDeclarationDto> getHealthDeclarationById(Integer id) {
        HealthDeclarant healthDeclarant = healthDeclarantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không thể tìm thấy người này"));

        List<HealthDeclaration> healthDeclarationList = healthDeclarationRepo.findAllByHealthDeclarantId(id);

        List<HealthDeclarationDto> listToShow = new ArrayList<>();

        for (HealthDeclaration declaration : healthDeclarationList) {
            HealthDeclarationDto dto = new HealthDeclarationDto();

            dto.setHealthDeclarantId(healthDeclarant.getId());
            dto.setName(healthDeclarant.getName());
            dto.setYearOfBirth(healthDeclarant.getYearOfBirth());
            dto.setGender(healthDeclarant.getGender());
            dto.setNationality(healthDeclarant.getNationality());
            dto.setCitizenId(healthDeclarant.getCitizenId());
            dto.setPhoneNumber(healthDeclarant.getPhoneNumber());
            dto.setEmail(healthDeclarant.getEmail());

            dto.setHealthDeclarationID(declaration.getId());
            dto.setTravelType(declaration.getTravelType());
            dto.setVehicleNumber(declaration.getVehicleNumber());
            dto.setSeatNumber(declaration.getSeatNumber());
            dto.setDepartureDate(declaration.getDepartureDate());
            dto.setArrivalDate(declaration.getArrivalDate());
            dto.setVisitedLocations(declaration.getVisitedLocations());
            dto.setCity(declaration.getCity());
            dto.setDistrict(declaration.getDistrict());
            dto.setWard(declaration.getWard());
            dto.setDetailAddress(declaration.getDetailAddress());
            dto.setSymptoms(declaration.getSymptoms());
            dto.setExposureHistory(declaration.getExposureHistory());
            dto.setCreatedAt(declaration.getCreatedAt());

            listToShow.add(dto);
        }
        return listToShow;
    }
}