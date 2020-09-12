package fun.mizhuo.hrserver.exception;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import fun.mizhuo.hrserver.model.ResponseVo;
import fun.mizhuo.hrserver.util.ErrMessage;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * @author: Mizhuo
 * @time: 2020/9/6 5:11 下午
 * @description:
 */
@RestControllerAdvice
public class HrGlobalExceptionHandler{

//    @ExceptionHandler(SQLException.class)
//    public ResponseVo sqlException(SQLException e){
//        if(e instanceof MySQLIntegrityConstraintViolationException){
//            return ResponseVo.error(ErrMessage.POSITION_ERROR_MESSAGE1);
//        }
//        return ResponseVo.error(ErrMessage.SQL_ERROR_MESSAGE1);
//    }
}
