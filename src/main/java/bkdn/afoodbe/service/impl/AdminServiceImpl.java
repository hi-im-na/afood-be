package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.dto.CreateStaffDTO;
import bkdn.afoodbe.dto.StaffDTO;
import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.model.Role;
import bkdn.afoodbe.repository.NgayCongRepository;
import bkdn.afoodbe.repository.OrderRepository;
import bkdn.afoodbe.repository.StaffRepository;
import bkdn.afoodbe.service.IAdminService;
import bkdn.afoodbe.service.NgayCongService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AdminServiceImpl implements IAdminService {

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final NgayCongRepository ngayCongRepository;
    private final OrderRepository orderRepository;
    private final NgayCongService ngayCongService;

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> staffList = staffRepository.findAll();
        if (staffList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff list is empty");
        }
        return staffList.stream().map(StaffDTO::toStaffDTO).collect(Collectors.toList());
    }

    @Override
    public StaffDTO createStaff(CreateStaffDTO dto) {
        String username = dto.username();
        Staff existStaff = staffRepository.findByUsername(username);
        if (existStaff != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username" + username + " is already taken");
        }
        System.out.println(dto.role());
        BigDecimal salary = switch (dto.role()) {
            case "ROLE_ADMIN" -> BigDecimal.valueOf(10);
            case "ROLE_MANAGER" -> BigDecimal.valueOf(7);
            case "ROLE_STAFF" -> BigDecimal.valueOf(6);
            default -> throw new IllegalStateException("Unexpected role value: " + dto.role());
        };

        Staff newStaff = Staff.builder()
                .username(username)
                .password(passwordEncoder.encode(dto.password()))
                .role(Role.valueOf(dto.role()))
                .fullName(dto.fullName())
                .phoneNumber(dto.phoneNumber())
                .salary(salary)
                .build();
        staffRepository.saveAndFlush(newStaff);
        return StaffDTO.toStaffDTO(newStaff);
    }

    @Override
    public StaffDTO updateStaff(StaffDTO dto) {
        Staff staff = staffRepository.findById(dto.id().intValue());
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + dto.id() + "not found");
        }
        staff.setFullName(dto.fullName());
        staff.setPhoneNumber(dto.phoneNumber());
        staff.setRole(Role.valueOf(String.valueOf(dto.role())));
        return StaffDTO.toStaffDTO(staffRepository.saveAndFlush(staff));
    }


    @Override
    public void updateStaffRoleByUsername(String username, String role) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + username + "not found");
        }
        staff.setRole(Role.valueOf(role));
        staffRepository.saveAndFlush(staff);
    }

    @Override
    public void updateStaffRoleById(int id, String role) {
        Staff staff = staffRepository.findById(id);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        staff.setRole(Role.valueOf(role));
        staffRepository.saveAndFlush(staff);
    }

    @Override
    public void deleteStaff(String username) {
        Staff staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + username + "not found");
        }
        staffRepository.delete(staff);
    }

    @Override
    @Transactional
    public void deleteStaffById(int id) {
        Staff staff = staffRepository.findById(id);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        ngayCongRepository.deleteByStaffId(id);
        orderRepository.deleteByStaffId(id);
        staffRepository.delete(staff);
    }

    @Override
    public StaffDTO updateSalary(int id, BigDecimal salary) {
        Staff staff = staffRepository.findById(id);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        try {
            staff.setSalary(salary);
//            salary = salary.setScale(2);
        } catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary is invalid");
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Scs cai j dod");
        }
        return StaffDTO.toStaffDTO(staffRepository.saveAndFlush(staff));
    }

    @Override
    public BigDecimal totalSalaryByStaffIdAndMonthWorked(Integer id, Integer month) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        BigDecimal timeWorkedInMonth = ngayCongService.sumTimeWorkedByStaffIdAndMonth(id, month);
        if (timeWorkedInMonth == null) {
            return BigDecimal.valueOf(0);
        }
        BigDecimal salaryPerHour = staff.getSalary();
        return timeWorkedInMonth.multiply(salaryPerHour);
    }

    @Override
    public BigDecimal totalHoursWorkedInMonth(Integer id, Integer month) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        return ngayCongRepository.sumTimeWorkedByMonthAndYear(id, month, Calendar.getInstance().get(Calendar.YEAR));
    }

    @Override
    public BigDecimal totalHoursWorkedInYear(Integer id, Integer year) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        return ngayCongRepository.sumTimeWorkedByYear(id, year);
    }

    @Override
    public BigDecimal totalHoursWorkedInMonthAndYear(Integer id, Integer month, Integer year) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        return ngayCongRepository.sumTimeWorkedByMonthAndYear(id, month, year);
    }

    @Override
    public BigDecimal totalHoursWorkedAllTimeByStaffId(Integer id) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }
        return ngayCongRepository.sumTimeWorkedByStaffId(id);
    }

    @Override
    public Map<Integer, BigDecimal> totalHoursWorkedAllTimeOfAllStaff() {
        Map<Integer, BigDecimal> mapTotalHoursWorked;
        try {
            mapTotalHoursWorked = new HashMap<>();
            for (Staff staff : staffRepository.findAll()) {
                mapTotalHoursWorked.put(staff.getId(),
                        ngayCongRepository.sumTimeWorkedByStaffId(staff.getId()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
        return mapTotalHoursWorked;
    }

    @Override
    public BigDecimal totalMoneyMadeForRestaurantAllTime(Integer id) {
        Staff staff = staffRepository.findById(id).orElse(null);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff" + id + "not found");
        }

        //TODO: implement this
        return null;
    }
}
