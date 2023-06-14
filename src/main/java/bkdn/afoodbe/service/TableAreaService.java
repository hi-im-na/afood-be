package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.TableAreaDto;
import bkdn.afoodbe.entity.TableArea;
import bkdn.afoodbe.repository.TableAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TableAreaService {
    private final TableAreaRepository tableAreaRepository;

    public List<TableAreaDto> getAllTableArea() {
        List<TableArea> tableAreas = tableAreaRepository.findAll();
        if (tableAreas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table area list is empty");
        }
        return tableAreas.stream().map(TableAreaDto::toTableAreaDto).collect(java.util.stream.Collectors.toList());
    }


}
