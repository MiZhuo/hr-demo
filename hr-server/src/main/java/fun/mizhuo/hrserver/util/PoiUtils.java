package fun.mizhuo.hrserver.util;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author: Mizhuo
 * @time: 2020/9/27 10:23 下午
 * @description: POI工具类
 */
public class PoiUtils {

    private static HSSFWorkbook workbook = new HSSFWorkbook();

    /**
     * 初始化POI工具
     * @return
     */
    private static void initPOI(){
        //1:创建文档
        workbook = new HSSFWorkbook();
        //2:创建文档摘要
        workbook.createInformationProperties();
        //3:获取并配置文档信息
        DocumentSummaryInformation information = workbook.getDocumentSummaryInformation();
        //文档类别
        information.setCategory("机密");
        //文档管理员
        information.setManager("mizhuo");
        //公司名称
        information.setCompany("www.mizhuo.fun");
        //4:获取摘要信息
        SummaryInformation sumInfo = workbook.getSummaryInformation();
        //文档标题
        sumInfo.setTitle("机密表");
        //文档作者
        sumInfo.setAuthor("mizhuo");
        //文档备注
        sumInfo.setComments("mizhuo专用");
    }

    /**
     * 初始化表头
     * @param clazz
     * @param sheet
     * @throws IllegalAccessException
     */
    private static void initSheetHead(Class clazz,HSSFSheet sheet) {
        Field[] fields = clazz.getDeclaredFields();
        // 设置列头宽度
        for (int i = 0; i < fields.length; i++) {
            sheet.setColumnWidth(i, 4000);
        }
        // 设置默认行高
        sheet.setDefaultRowHeight((short) 400);
        // 构建头单元格样式
        CellStyle style = workbook.createCellStyle();
        //对齐方式设置
        style.setAlignment(HorizontalAlignment.CENTER);
        //边框颜色和宽度设置
        // 下边框
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        // 左边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        // 右边框
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        // 上边框
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        //设置背景颜色
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //粗体字设置
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        // 写入第一行各列的数据
        Row head = sheet.createRow(0);
        for (int i = 0; i < fields.length; i++) {
            if( Modifier.isStatic(fields[i].getModifiers())) {
                continue;
            }
            fun.mizhuo.hrserver.anno.Field annotation = fields[i].getAnnotation(fun.mizhuo.hrserver.anno.Field.class);
            Cell cell = head.createCell(i);
            cell.setCellValue(annotation.value());
            cell.setCellStyle(style);
        }
    }
    /**
     * 输出流
     * @return
     */
    private static ResponseEntity<byte[]> outExcelStream(String fileName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName + ".xls" );
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        workbook.write(outByteStream);
        return new ResponseEntity<byte[]>(outByteStream.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * List转Excel导出
     * @param list
     * @return
     */
    public static ResponseEntity<byte[]> list2Excel(List<?> list,Class clazz,String fileName) throws IOException, IllegalAccessException {
        initPOI();
        //创建Sheet页
        HSSFSheet sheet = workbook.createSheet(fileName);
        //初始化sheet表头
        initSheetHead(clazz,sheet);
        Field[] fields = clazz.getDeclaredFields();
        //写数据
        for (int i = 0; i < list.size(); i++) {
            //表头占一行,从第二行开始创建
            HSSFRow row = sheet.createRow(i + 1);
            for(int j = 0;j < fields.length;j++){
                HSSFCell cell = row.createCell(j);
                fields[i].setAccessible(true);
                cell.setCellValue(String.valueOf(fields[j].get(list.get(i))));
            }
        }
        return outExcelStream(new String(fileName.getBytes("GBK"),"iso-8859-1"));
    }

}
