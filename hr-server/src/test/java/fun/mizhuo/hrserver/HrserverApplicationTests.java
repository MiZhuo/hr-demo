package fun.mizhuo.hrserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class HrserverApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date begin=dfs.parse("2022-01-02");

        java.util.Date end = dfs.parse("2022-12-26");

        long between=(end.getTime()-begin.getTime())/ (1000 * 60 * 60 *24);//除以1000是为了转换成秒

        System.out.println(between * 1.00 / 365);
    }

}
