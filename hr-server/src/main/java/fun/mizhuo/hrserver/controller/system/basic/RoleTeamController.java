package fun.mizhuo.hrserver.controller.system.basic;

import fun.mizhuo.hrserver.model.Menu;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.model.Role;
import fun.mizhuo.hrserver.service.common.MenuRoleService;
import fun.mizhuo.hrserver.service.common.MenuService;
import fun.mizhuo.hrserver.service.system.basic.RoleTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/11 7:04 下午
 * @description: 权限组Controller
 */
@RestController
@RequestMapping("system/basic/roleTeam")
public class RoleTeamController {

    @Autowired
    RoleTeamService roleTeamService;

    @Autowired
    MenuService menuService;

    @Autowired
    MenuRoleService menuRoleService;

    @GetMapping("/")
    public ResponseVo getAllRoles(){
        List<Role> roles = roleTeamService.getAllRoles();
        return ResponseVo.ok("",roles);
    }

    @GetMapping("/menuTree")
    public ResponseVo getMenuTree(){
        List<Menu> menus = menuService.getMenuTree();
        return ResponseVo.ok("",menus);
    }

    @GetMapping("/menuTree/checked/{id}")
    public ResponseVo getMenuTreeCheckedByRoleId(@PathVariable Integer id){
        Integer[] menuRoles = menuRoleService.getMenuIdsByRoleId(id);
        return ResponseVo.ok("",menuRoles);
    }
}
