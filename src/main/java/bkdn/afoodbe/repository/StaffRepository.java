package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
}