package fun.mizhuo.hrserver.service.impl.common;

import fun.mizhuo.hrserver.mapper.MenuRoleMapper;
import fun.mizhuo.hrserver.model.MenuRole;
import fun.mizhuo.hrserver.service.common.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/11 8:17 下午
 * @description: 菜单权限服务实现类
 */
@Service
public class MenuRoleServiceImpl implements MenuRoleService {
    @Autowired
    MenuRoleMapper menuRoleMapper;

    @Override
    public Integer[] getMenuIdsByRoleId(Integer id) {
        List<Integer> mids = menuRoleMapper.getMenuIdsByRoleId(id);
        return mids.toArray(new Integer[mids.size()]);
    }
}
