package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.StaffInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffInfoRepository extends JpaRepository<StaffInfo, Integer> {
}