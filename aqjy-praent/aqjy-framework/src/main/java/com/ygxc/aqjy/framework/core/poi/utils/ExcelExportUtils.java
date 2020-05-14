package com.ygxc.aqjy.framework.core.poi.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.framework.core.poi.client.ExcelExportClient;
import com.ygxc.aqjy.framework.core.poi.constant.TableTheme;
import com.ygxc.aqjy.framework.core.poi.data.WriteDataCellPropertyHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IWriteDataCellHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IWriteDataFetch;

/**
 * excel导出工具类
 *
 * @author Qiaoxin.Hong
 */
public class ExcelExportUtils {

    public static void main(String[] args) throws Exception {
        List<Dto> dtos = new LinkedList<>();
        for (int i = 1; i <= 10; i++) {
            Dto e = new Dto("永利皇宫鉅星 <戶口存款> V005V5 彭岳熙 存款人：彭岳熙存入金額：25927萬 港幣備註：存C存款結餘  港幣：26878.5309萬  人民幣：202萬  澳元：133萬  盧布：5萬跟單員工：1000時間：2020-03-29 20:50:42(如有錯漏，以賬房數據為準，查詢電話: +853 2875 7955)。<11>" + i, "a" + i);
            e.setAac("AAC" + i);
            e.setMsr("MSR" + i);
            e.setAcr("ACR" + i);
            e.setTech("TECH" + i);
            e.setP90("CRI" + i);
            e.setM200("MT2" + i);
            e.setSmg("SMG" + i);
            e.setMk(("MK" + i));
            dtos.add(e);
        }
        Dto e = new Dto("张三", "a");
        e.setAac("AAC");
        e.setMsr("MSR");
        e.setAcr("ACR");
        e.setTech("TECH");
        e.setP90("CRI");
        e.setM200("MTH");
        e.setSmg("SMG");
        e.setMk(("MK"));
        dtos.add(4, e);
        FileOutputStream os = new FileOutputStream(new File("E://aa.xlsx"));
        write(os, dtos, "姓名, name, 编号, no, D1,aac,D2,msr,D3,acr,D4,tech,D5,p90,A1,m200,A2,smg,A3,mk", TableTheme.STYLE_THREE);
    }

    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream        输出流
     * @param dataList            数据列表
     * @param colNames            列标题
     * @param writeDataCellHandle 列数据处理器
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String[] colNames
            , IWriteDataCellHandle<T> writeDataCellHandle, TableTheme tableTheme) {
        new ExcelExportClient<T>().setOutputStream(outputStream).setColNames(colNames)
                .setWriteDataCellHandle(writeDataCellHandle).setTableTheme(tableTheme)
                .write(dataList);
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream        输出流
     * @param dataList            数据列表
     * @param colNames            列标题
     * @param writeDataCellHandle 列数据处理器
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String[] colNames
            , IWriteDataCellHandle<T> writeDataCellHandle) {
    	write(outputStream, dataList, colNames, writeDataCellHandle, null);
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream 输出流
     * @param dataList     数据列表
     * @param colNames     列标题
     * @param fields       属性
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String[] colNames
            , String[] fields) {
    	write(outputStream, dataList, colNames, fields, null);
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream 输出流
     * @param dataList     数据列表
     * @param colNames     列标题
     * @param fields       属性
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, String[] colNames
            , String[] fields) {
    	write(outputStream, writeDataFetch, colNames, fields, null);
    }

    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream 输出流
     * @param dataList     数据列表
     * @param colNames     列标题
     * @param fields       属性
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String[] colNames
            , String[] fields, TableTheme tableTheme) {
    	write(outputStream, ExcelExportClient.dataListToFetch(dataList), colNames, fields, tableTheme);
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream 输出流
     * @param dataList     数据列表
     * @param colNames     列标题
     * @param fields       属性
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, String[] colNames
            , String[] fields, TableTheme tableTheme) {
    	new ExcelExportClient<T>().setOutputStream(outputStream).setColNames(colNames)
    		.setWriteDataFetch(writeDataFetch)
	        .setWriteDataCellHandle(new WriteDataCellPropertyHandle<T>(fields)).setTableTheme(tableTheme)
	        .write();
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream
     * @param dataList
     * @param colParams    参数列表，"，"隔开，例：姓名,name,年龄,age
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String colParams) {
    	write(outputStream, dataList, colParams, null);
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream
     * @param dataList
     * @param colParams    参数列表，"，"隔开，例：姓名,name,年龄,age
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, String colParams) {
    	write(outputStream, writeDataFetch, colParams, null);
    }

    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream
     * @param dataList
     * @param colParams    参数列表，"，"隔开，例：姓名,name,年龄,age
     */
    public static <T> void write(OutputStream outputStream, List<T> dataList, String colParams, TableTheme tableTheme) {
    	write(outputStream, ExcelExportClient.dataListToFetch(dataList), colParams, tableTheme);
    }
    
    /**
     * excel导出
     *
     * @param <T>
     * @param outputStream
     * @param dataList
     * @param colParams    参数列表，"，"隔开，例：姓名,name,年龄,age
     */
    public static <T> void write(OutputStream outputStream, IWriteDataFetch<T> writeDataFetch, String colParams
    		, TableTheme tableTheme) {
    	Assist.notBlank(colParams, "colParams cannot be blank");

        String[] colParamArr = colParams.split(BConst.COMMA);
        int length = colParamArr.length;
        Assist.mustTrue(length % 2 == 0, "col count error");

        String[] colNames = new String[length / 2];
        String[] fields = new String[length / 2];

        for (int i = 0; i < colParamArr.length; i++) {
            String val = colParamArr[i].trim();
            if (i % 2 == 0) {
                colNames[i / 2] = val;
            } else {
                fields[(i - 1) / 2] = val;
            }
        }

        write(outputStream, writeDataFetch, colNames, fields, tableTheme);
    }
}
