package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.dto.CreateMenuDto;
import bkdn.afoodbe.dto.MenuDto;
import bkdn.afoodbe.entity.Menu;
import bkdn.afoodbe.repository.MenuRepository;
import bkdn.afoodbe.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MenuServiceImpl implements IMenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuDto> getAllMenu() {
        List<Menu> menuList = menuRepository.findAll();
        if (menuList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu list is empty");
        }
        return menuList.stream().map(MenuDto::toMenuDto).collect(Collectors.toList());
    }

    @Override
    public MenuDto findMenuById(int id) {
        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id " + id + " not found");
        }
        return MenuDto.toMenuDto(menu);
    }

    @Override
    public List<MenuDto> findAllMenuByName(String name) {
        List<Menu> menu = menuRepository.findAllByNameContainsIgnoreCase(name);
        if (menu.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with name " + name + " not found");
        }
        return menu.stream().map(MenuDto::toMenuDto).collect(Collectors.toList());
    }

    @Override
    public MenuDto createMenu(CreateMenuDto createMenuDto) {
        if (menuRepository.existsByName(createMenuDto.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Menu with name " + createMenuDto.name() + " already exists");
        }
        Menu menu = Menu.builder()
                .name(createMenuDto.name())
                .description(createMenuDto.description())
                .build();
        Menu newMenu = menuRepository.saveAndFlush(menu);
        return MenuDto.toMenuDto(newMenu);
    }

    @Override
    public void deleteMenu(int id) {
        if (!menuRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id " + id + " not found");
        }
        menuRepository.deleteById(id);
    }

    @Override
    public MenuDto updateMenuInfo(int id, CreateMenuDto createMenuDto) {
        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id " + id + " not found");
        }
        menu.setName(createMenuDto.name());
        menu.setDescription(createMenuDto.description());
        Menu newMenu = menuRepository.saveAndFlush(menu);
        return MenuDto.toMenuDto(newMenu);
    }
}
