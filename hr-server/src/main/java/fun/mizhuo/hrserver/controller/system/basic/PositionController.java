package fun.mizhuo.hrserver.controller.system.basic;

import fun.mizhuo.hrserver.model.JobLevel;
import fun.mizhuo.hrserver.model.Position;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.service.system.basic.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/6 3:09 下午
 * @description: 职位Controller
 */
@RestController
@RequestMapping("/system/basic/position")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/")
    public ResponseVo getAllPosition(){
        List<Position> positions = positionService.getAllPosition();
        return ResponseVo.ok("",positions);
    }

    @PostMapping("/")
    public ResponseVo addPosition(@RequestBody Position position){
        if(positionService.addPosition(position) == 1){
            return ResponseVo.ok("添加成功!");
        }
        return ResponseVo.error("添加失败!");
    }

    @PutMapping("/")
    public ResponseVo updatePosition(@RequestBody Position position){
        if(positionService.updatePosition(position) == 1){
            return ResponseVo.ok("更新成功!");
        }
        return ResponseVo.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public ResponseVo deletePosition(@PathVariable Integer id){
        if(positionService.deletePosition(id) == 1){
            return ResponseVo.ok("删除成功!");
        }
        return ResponseVo.error("删除失败!");
    }

    @DeleteMapping("/deleteMore")
    public ResponseVo deleteMorePosition(@RequestBody List<Position> positions){
        if(positionService.deleteMorePosition(positions) > 0){
            return ResponseVo.ok("删除成功!");
        }
        return ResponseVo.error("删除失败!");
    }

    @PutMapping("/{id}/{enabled}")
    public ResponseVo updateJobTitle(@PathVariable Integer id, @PathVariable Boolean enabled){
        Position position = new Position();
        position.setId(id);
        position.setEnabled(enabled);
        if(positionService.updatePosition(position) == 1){
            if(enabled){
                return ResponseVo.ok("启用成功!");
            }else{
                return ResponseVo.ok("禁用成功!");
            }
        }
        return ResponseVo.error("更新失败!");
    }
}
