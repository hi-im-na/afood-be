package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}