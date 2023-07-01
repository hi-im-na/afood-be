package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.NgayCong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface NgayCongRepository extends JpaRepository<NgayCong, Integer> {
    List<NgayCong> findByStaffId(Integer staffId);
    void deleteByStaffId(Integer staffId);

    @Query("SELECT nc from NgayCong nc where month(nc.workedDate) = ?1 and year(nc.workedDate) = ?2")
    List<NgayCong> findByMonthAndYear(Integer month, Integer year);

    @Query("SELECT sum(nc.timeWorked) from NgayCong nc where nc.staff.id = ?1")
    BigDecimal sumTimeWorkedByStaffId(Integer staffId);

    @Query("SELECT sum(nc.timeWorked) from NgayCong nc where nc.staff.id = ?1 and month(nc.workedDate) = ?2")
    BigDecimal sumTimeWorkedByStaffIdAndMonth(Integer staffId, Integer month);

    @Query("SELECT sum(nc.timeWorked) from NgayCong nc where nc.staff.id = ?1 and year(nc.workedDate) = ?2")
    BigDecimal sumTimeWorkedByYear(Integer staffId, Integer year);

    @Query("SELECT sum(nc.timeWorked) from NgayCong nc where nc.staff.id = ?1 and month(nc.workedDate) = ?2 and year(nc.workedDate) = ?3")
    BigDecimal sumTimeWorkedByMonthAndYear(Integer staffId, Integer month, Integer year);
}
