package fun.mizhuo.hrserver.controller;

import fun.mizhuo.hrserver.model.Menu;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/8/23 11:03 上午
 * @description:
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public ResponseVo initMenuByUserId(){
        List<Menu> menuList = menuService.initMenuByUserId();
        return ResponseVo.ok("获取菜单列表成功",menuList);
    }
}
