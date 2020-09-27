package fun.mizhuo.hrserver.util;

import fun.mizhuo.hrserver.model.Employee;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/27 10:23 下午
 * @description: POI工具类
 */
public class PoiUtils {
    /**
     * List转Excel导出
     * @param list
     * @return
     */
    public static ResponseEntity<byte[]> list2Excel(List<?> list) {
        //1:创建文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2:创建文档摘要
        workbook.createInformationProperties();
        //3:获取并配置文档信息
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
        //文档类别
        information.setCategory("员工信息");
        //文档管理员
        information.setManager("mizhuo");
        //公司名称
        information.setCompany("www.mizhuo.fun");
        //4:获取摘要信息
        SummaryInformation sumInfo = workbook.getSummaryInformation();
        //文档标题
        sumInfo.setTitle("员工信息表");
        //文档作者
        sumInfo.setAuthor("mizhuo");
        //文档备注
        sumInfo.setComments("mizhuo专用");
        //5:创建Sheet页
        HSSFSheet sheet = workbook.createSheet("员工信息表");

        return null;
    }
}
