package com.rest.rest_server.pub.util;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
public class POIUtil {

	/**
	 * 获取Excel工作薄
	 * @author daipengjie
	 * @Description:
	 * @date 2019年6月12日上午10:09:54
	 * @param realPath
	 * @return
	 * @throws Exception
	 */
    public static XSSFWorkbook getXSSFWorkbook(String realPath) throws Exception {
        XSSFWorkbook xwb = null;
        File infile = new File(realPath);
        try {
            if (infile.exists()) {
                xwb = new XSSFWorkbook(new FileInputStream(realPath));
                //InputStream in = new FileInputStream(infile);
                //xwb = new XSSFWorkbook(in);
                //in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" %%%%%%获取getXSSFWorkbook error:{}",e.getMessage(),e);
            e.printStackTrace();
            throw new IllegalArgumentException("你的excel版本目前poi解析不了");
//            throw e;
        }
        return xwb;
    }

    /**
     * 获取 XSSFSheet对象
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:10:04
     * @param xwb
     * @param sheetName
     * @return
     * @throws Exception
     */
    public static XSSFSheet getXSSFSheet(XSSFWorkbook xwb, String sheetName)throws Exception{
        try {
            return xwb.getSheet(sheetName);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error(" %%%%%%获取getXSSFSheet页 error:{}",e.getMessage(),e);
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 解析获取产品规格成员属性CharacterId数组几个集
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:10:14
     * @param sheet
     * @return
     * @throws Exception
     */
    public static String[] getProdordSkuMemberCharacterNum(XSSFSheet sheet) throws Exception {
        XSSFRow row = POIUtil.getXSSFRow(sheet, 0);
        int cells = row.getPhysicalNumberOfCells();
        String[] characterIds = new String[cells];
        for (int i = 0; i < cells; i++) {
            characterIds[i] = POIUtil.getXSSFCellStringCellValue(row.getCell(i),1,i).trim();
        }
        return characterIds;
    }

    /**
     * 获取XSSFRow对象
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:10:24
     * @param sheet
     * @param index
     * @return
     * @throws Exception
     */
    public static XSSFRow getXSSFRow(XSSFSheet sheet, int index)throws Exception{
        try {
            return sheet.getRow(index);
        } catch (RuntimeException e) {
            log.error("DCMPUtil getXSSFRow:{}",e.getMessage(),e);
            throw new Exception("获取XSSFRow对象错误！");
        }
    }

    /**
     * 获取Sheet中的总行数
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:10:33
     * @param sheet
     * @return
     * @throws Exception
     */
    public static int getLastRowNum(XSSFSheet sheet)throws Exception{
        return sheet.getLastRowNum();
    }

    /**
     * 获取XSSFRow中的总列数
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:10:44
     * @param row
     * @return
     * @throws Exception
     */
    public static int getPhysicalNumberOfCells(XSSFRow row)throws Exception{
        return row.getPhysicalNumberOfCells();
    }

    /**
     * 返回当前行数组
     * 以后可以用来校验列表是否制定模板
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:10:55
     * @param xssfSheet
     * @return
     * @throws Exception
     */
    public static List<List<String>> getRowCharacterValue(XSSFSheet xssfSheet) throws Exception {
        List list = new ArrayList();

        try {
            int rowFirstNum = xssfSheet.getFirstRowNum();
            int rowCount = xssfSheet.getPhysicalNumberOfRows();
            log.info(" -->第一行FirstRowNum：" + rowFirstNum + ",总行数PhysicalNumberOfRows：" + rowCount);
            //从第二行开始读
            for (int rowIndex = rowFirstNum + 1; rowIndex < rowCount; rowIndex++) {
                List<String> listString = new ArrayList<String>();
                XSSFRow row = xssfSheet.getRow(rowIndex);
                int cells = row.getPhysicalNumberOfCells();
                for (int cellIndex = 0; cellIndex < cells; cellIndex++) {
                    String value = getXSSFCellStringCellValue(row.getCell(cellIndex), rowIndex, cellIndex).trim();
                    listString.add(value);
                }
                list.add(listString);
                log.info(" -->获取到成员条数：" + list.size());
            }
        } catch (Exception e) {
            log.error(" %%%%%getRowCharacterValue error:{}",e.getMessage(),e);
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    /**
     * 获取到指定单元格的值
     * @author daipengjie
     * @Description:
     * @date 2019年6月12日上午10:11:05
     * @param cell
     * @param intRow
     * @param intCell
     * @return
     * @throws Exception
     */
    public static String getXSSFCellStringCellValue(XSSFCell cell, int intRow, int intCell) throws Exception {
        String strCell = "";
        if(cell==null){
            return "";
        }
        try {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_FORMULA: //公式型	2
                    try{
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                strCell = new SimpleDateFormat("yyyy-MM-dd").format(date);
                                break;
                            }
                            strCell = null;
                            break;
                        }
                        DecimalFormat df = new DecimalFormat("0");
                        strCell = df.format(cell.getNumericCellValue());
                        if(strCell.endsWith(".0")) {
                            strCell = strCell.replace(".0", "");
                        }
                    } catch (IllegalStateException e) {
                        strCell = String.valueOf(cell.getRichStringCellValue());
                    }
                    break;
                case XSSFCell.CELL_TYPE_STRING://字符串型	1
                    strCell = cell.getStringCellValue();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC://数值型	0
                    //System.out.println("XSSFCell.CELL_TYPE_NUMERIC");
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        if (date != null) {
                            strCell = new SimpleDateFormat("yyyy-MM-dd") .format(date);
                            break;
                        }
                        strCell = null;
                        break;
                    }
                    DecimalFormat df = new DecimalFormat("0");
                    strCell = df.format(cell.getNumericCellValue());
                    if(strCell.endsWith(".0")) {
                        strCell = strCell.replace(".0", "");
                    }
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN://布尔型	4
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case XSSFCell.CELL_TYPE_BLANK://空值	3
                    break;
                default:
                    break;
            }
            if (StringUtils.isEmpty(strCell)) {
                log.info("获取到strCell值：" + strCell);
                return strCell;
            }
        } catch (Exception e) {
            log.error("单元格数据内容转换错误:第:{}行!",intRow,e.getMessage(),e);
            e.printStackTrace();
            throw e;
        }
        return strCell;
    }
}
