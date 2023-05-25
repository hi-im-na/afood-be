package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Menu findById(int id);

    List<Menu> findAllByNameContainsIgnoreCase(String name);

    boolean existsByName(String name);
}