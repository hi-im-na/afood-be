package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateMenuDto;
import bkdn.afoodbe.dto.MenuDto;

import java.util.List;

public interface IMenuService {
    List<MenuDto> getAllMenu();

    MenuDto findMenuById(int id);

    List<MenuDto> findAllMenuByName(String name);

    MenuDto createMenu(CreateMenuDto menuDto);

    void deleteMenu(int id);

    MenuDto updateMenuInfo(int id, CreateMenuDto menuDto);
}