package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateNgayCongDto;
import bkdn.afoodbe.dto.NgayCongDto;
import bkdn.afoodbe.entity.NgayCong;
import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.repository.NgayCongRepository;
import bkdn.afoodbe.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NgayCongService {
    private final NgayCongRepository ngayCongRepository;
    private final StaffRepository staffRepository;

    public List<NgayCongDto> findAllNgayCong() {
        List<NgayCong> ngayCongList = ngayCongRepository.findAll();
        if (ngayCongList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timekeeping list is empty");
        }
        return ngayCongList.stream().map(NgayCongDto::toNgayCongDto).collect(Collectors.toList());
    }

    public List<NgayCongDto> findByStaffId(Integer staffId) {
        List<NgayCong> ngayCongList = ngayCongRepository.findByStaffId(staffId);
        if (ngayCongList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timekeeping list is empty");
        }
        return ngayCongList.stream().map(NgayCongDto::toNgayCongDto).collect(Collectors.toList());
    }

    public List<NgayCongDto> findByMonthAndYear(Integer month, Integer year) {
        List<NgayCong> ngayCongList = ngayCongRepository.findByMonthAndYear(month, year);
        if (ngayCongList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timekeeping list is empty");
        }
        return ngayCongList.stream().map(NgayCongDto::toNgayCongDto).collect(Collectors.toList());
    }

    public BigDecimal tinhGioCong(Integer staffId) {
        List<NgayCong> ngayCongList = ngayCongRepository.findByStaffId(staffId);
        if (ngayCongList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timekeeping list is empty");
        }
        BigDecimal gioCong = new BigDecimal(0);
        for (NgayCong ngayCong : ngayCongList) {
            gioCong = gioCong.add(ngayCong.getTimeWorked());
        }
        return gioCong;
    }

    public NgayCongDto createNgayCong(CreateNgayCongDto ngayCongDto) {
        Optional<Staff> staffOptional = staffRepository.findById(ngayCongDto.staffId());
        if (staffOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }
        Staff staff = staffOptional.get();
        NgayCong ngayCong = NgayCong.builder()
                .staff(staff)
                .workedDate(ngayCongDto.workedDate())
                .timeWorked(ngayCongDto.timeWorked())
                .build();
        return NgayCongDto.toNgayCongDto(ngayCongRepository.save(ngayCong));
    }

    public void deleteNgayCong(Integer id) {
        Optional<NgayCong> ngayCongOptional = ngayCongRepository.findById(id);
        if (ngayCongOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Timekeeping not found");
        }
        ngayCongRepository.deleteById(id);
    }

    public BigDecimal sumTimeWorkedByStaffId(Integer id) {
        Staff staff = staffRepository.findById(id.intValue());
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }
        return ngayCongRepository.sumTimeWorkedByStaffId(id);
    }

    public BigDecimal sumTimeWorkedByStaffIdAndMonth(Integer id, Integer month) {
        Staff staff = staffRepository.findById(id.intValue());
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff not found");
        }
        return ngayCongRepository.sumTimeWorkedByStaffIdAndMonth(id, month);
    }

}
