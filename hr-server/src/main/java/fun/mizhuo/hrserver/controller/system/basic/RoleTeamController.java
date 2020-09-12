package fun.mizhuo.hrserver.controller.system.basic;

import fun.mizhuo.hrserver.model.Menu;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.model.Role;
import fun.mizhuo.hrserver.service.common.MenuRoleService;
import fun.mizhuo.hrserver.service.common.MenuService;
import fun.mizhuo.hrserver.service.system.basic.RoleTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseVo addRole(@RequestBody Role role){
        if(roleTeamService.addRole(role) == 1){
            return ResponseVo.ok("添加角色成功!");
        }
        return ResponseVo.error("添加角色失败!");
    }

    @DeleteMapping("/{roleId}")
    public ResponseVo deleteRoleByRoleId(@PathVariable Integer roleId){
        if(roleTeamService.deleteRoleByRoleId(roleId) == 1){
            return ResponseVo.ok("删除成功!");
        }
        return ResponseVo.error("");
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

    @PutMapping("/modify/menu/{roleId}")
    public ResponseVo modifyMenuByRoleId(@PathVariable int roleId, @RequestBody Integer[] menuIds){
        if(menuRoleService.modifyMenuByRoleId(roleId,menuIds) >= 0){
            return ResponseVo.ok("更新成功!");
        }
        return ResponseVo.error("更新失败!");
    }
}
