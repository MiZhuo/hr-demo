package fun.mizhuo.hrserver.service.system.hr;

import fun.mizhuo.hrserver.model.Hr;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/13 11:07 上午
 * @description: Hr服务接口类
 */
public interface HrService {
    List<Hr> getAllHrs();
}
