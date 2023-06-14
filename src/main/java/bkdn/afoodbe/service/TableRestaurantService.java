package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.TableRestaurantDto;
import bkdn.afoodbe.entity.TableRestaurant;
import bkdn.afoodbe.repository.TableRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TableRestaurantService {
    private final TableRestaurantRepository tableRestaurantRepository;

    public List<TableRestaurantDto> getAllTableRestaurant() {
        List<TableRestaurant> tableRestaurants = tableRestaurantRepository.findAll();
        if (tableRestaurants.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table restaurant list is empty");
        }
        return tableRestaurants.stream().map(TableRestaurantDto::toTableRestaurantDto).collect(java.util.stream.Collectors.toList());
    }

    public List<TableRestaurant> getTablesByAreaId(int areaId) {
        List<TableRestaurant> tableRestaurants = tableRestaurantRepository.findByTableAreaId(areaId);
        if (tableRestaurants.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table restaurant list is empty. No table in this area");
        }
        return tableRestaurants;
    }
}
