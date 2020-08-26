package fun.mizhuo.hrserver.service;


import fun.mizhuo.hrserver.model.Menu;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/8/23 11:08 上午
 * @description:
 */
public interface MenuService {
    List<Menu> initMenuByUserId();

    List<Menu> getMenuWithRole();
}
