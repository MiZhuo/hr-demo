package fun.mizhuo.hrserver.service.impl.system.hr;

import fun.mizhuo.hrserver.mapper.HrMapper;
import fun.mizhuo.hrserver.model.Hr;
import fun.mizhuo.hrserver.service.system.hr.HrService;
import fun.mizhuo.hrserver.util.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/13 11:08 上午
 * @description: Hr服务实现类
 */
@Service
public class HrServiceImpl implements HrService {

    @Autowired
    HrMapper hrMapper;

    @Override
    public List<Hr> getAllHrs() {
        return hrMapper.getAllHrsWithOutCurrentHr(HrUtils.getCurrentHr().getId());
    }
}
