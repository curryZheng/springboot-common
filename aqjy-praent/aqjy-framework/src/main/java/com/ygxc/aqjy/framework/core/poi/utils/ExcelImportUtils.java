package com.ygxc.aqjy.framework.core.poi.utils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.common.utils.FileUtils;
import com.ygxc.aqjy.framework.core.poi.client.ExcelImportClient;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataAllDtoHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataCellHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowArrHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowDtoHandle;


/**
 * excel导入工具类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ExcelImportUtils {

	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 */
	public static void read(InputStream inputStream, int startRow, int startCell, IReadDataCellHandle handle) {
		ExcelImportClient.create()
			.setInputStream(inputStream)
			.setStartRow(startRow)
			.setStartCell(startCell)
			.setReadDataCellHandle(handle)
			.read();
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param handle 数据处理
	 */
	public static void read(InputStream inputStream, IReadDataCellHandle handle) {
		read(inputStream, 0, 0, handle);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 */
	public static void read(File file, int startRow, int startCell, IReadDataCellHandle handle) {
		read(FileUtils.createFileInputStream(file), startRow, startCell, handle);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param handle 数据处理
	 */
	public static void read(File file, IReadDataCellHandle handle) {
		read(file, 0, 0, handle);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 */
	public static void read(String filePath, int startRow, int startCell, IReadDataCellHandle handle) {
		read(new File(filePath), startRow, startCell, handle);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param handle 数据处理
	 */
	public static void read(String filePath, IReadDataCellHandle handle) {
		read(filePath, 0, 0, handle);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 */
	public static void readRowArr(InputStream inputStream, int startRow, int startCell, IReadDataRowArrHandle handle) {
		ExcelImportClient.create()
			.setInputStream(inputStream)
			.setStartRow(startRow)
			.setStartCell(startCell)
			.setReadDataRowCellListHandle(handle)
			.read();
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param handle 数据处理
	 */
	public static void read(InputStream inputStream, IReadDataRowArrHandle handle) {
		readRowArr(inputStream, 0, 0, handle);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 */
	public static void read(File file, int startRow, int startCell, IReadDataRowArrHandle handle) {
		readRowArr(FileUtils.createFileInputStream(file), startRow, startCell, handle);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param handle 数据处理
	 */
	public static void readRowArr(File file, IReadDataRowArrHandle handle) {
		read(file, 0, 0, handle);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 */
	public static void readRowArr(String filePath, int startRow, int startCell, IReadDataRowArrHandle handle) {
		read(new File(filePath), startRow, startCell, handle);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param handle 数据处理
	 */
	public static void readRowArr(String filePath, IReadDataRowArrHandle handle) {
		readRowArr(filePath, 0, 0, handle);
	}

	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readRowDto(InputStream inputStream, int startRow, int startCell, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		ExcelImportClient.create()
			.setInputStream(inputStream)
			.setStartRow(startRow)
			.setStartCell(startCell)
			.setReadDataRowDtoHandle(handle, dtoClass, fieldList)
			.read();
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readRowDto(InputStream inputStream, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readRowDto(inputStream, 0, 0, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readRowDto(File file, int startRow, int startCell, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readRowDto(FileUtils.createFileInputStream(file), startRow, startCell, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readRowDto(File file, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readRowDto(file, 0, 0, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readRowDto(String filePath, int startRow, int startCell, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readRowDto(new File(filePath), startRow, startCell, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readRowDto(String filePath, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readRowDto(filePath, 0, 0, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readRowDto(InputStream inputStream, int startRow, int startCell, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		List<String> fieldList = Assist.toList(fields.split(BConst.COMMA));
		readRowDto(inputStream, startRow, startCell, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readRowDto(InputStream inputStream, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readRowDto(inputStream, 0, 0, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readRowDto(File file, int startRow, int startCell, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readRowDto(FileUtils.createFileInputStream(file), startRow, startCell, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readRowDto(File file, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readRowDto(file, 0, 0, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readRowDto(String filePath, int startRow, int startCell, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readRowDto(new File(filePath), startRow, startCell, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void read(String filePath, IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readRowDto(filePath, 0, 0, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readAllDto(InputStream inputStream, int startRow, int startCell, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		ExcelImportClient.create()
			.setInputStream(inputStream)
			.setStartRow(startRow)
			.setStartCell(startCell)
			.setReadDataRowDtoListHandle(handle, dtoClass, fieldList)
			.read();
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readAllDto(InputStream inputStream, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readAllDto(inputStream, 0, 0, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readAllDto(File file, int startRow, int startCell, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readAllDto(FileUtils.createFileInputStream(file), startRow, startCell, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readAllDto(File file, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readAllDto(file, 0, 0, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readAllDto(String filePath, int startRow, int startCell, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readAllDto(new File(filePath), startRow, startCell, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param handle 数据处理
	 * @param fieldList 字段列表
	 */
	public static <T> void readAllDto(String filePath, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		readAllDto(filePath, 0, 0, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readAllDto(InputStream inputStream, int startRow, int startCell, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		List<String> fieldList = Assist.toList(fields.split(BConst.COMMA));
		readAllDto(inputStream, startRow, startCell, handle, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readAllDto(InputStream inputStream, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readAllDto(inputStream, 0, 0, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readAllDto(File file, int startRow, int startCell, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readAllDto(FileUtils.createFileInputStream(file), startRow, startCell, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readAllDto(File file, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readAllDto(file, 0, 0, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readAllDto(String filePath, int startRow, int startCell, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readAllDto(new File(filePath), startRow, startCell, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param handle 数据处理
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> void readAllDto(String filePath, IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		readAllDto(filePath, 0, 0, handle, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param fieldList 字段列表
	 */
	public static <T> List<T> readFnAllDto(InputStream inputStream, int startRow, int startCell, Class<T> dtoClass, List<String> fieldList) {
		List<T> list = new ArrayList<>();
		
		IReadDataAllDtoHandle<T> handle = new IReadDataAllDtoHandle<T>() {
			@Override
			public void handleRow(List<T> dtoList) {
				Assist.forEach(dtoList, list::add);
			}
		};
		
		ExcelImportClient.create()
			.setInputStream(inputStream)
			.setStartRow(startRow)
			.setStartCell(startCell)
			.setReadDataRowDtoListHandle(handle, dtoClass, fieldList)
			.read();
		
		return list;
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param fieldList 字段列表
	 */
	public static <T> List<T> readFnAllDto(InputStream inputStream, Class<T> dtoClass, List<String> fieldList) {
		return readFnAllDto(inputStream, 0, 0, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param fieldList 字段列表
	 */
	public static <T> List<T> readFnAllDto(File file, int startRow, int startCell, Class<T> dtoClass, List<String> fieldList) {
		return readFnAllDto(FileUtils.createFileInputStream(file), startRow, startCell, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param fieldList 字段列表
	 */
	public static <T> List<T> readFnAllDto(File file, Class<T> dtoClass, List<String> fieldList) {
		return readFnAllDto(file, 0, 0, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param fieldList 字段列表
	 */
	public static <T> List<T> readFnAllDto(String filePath, int startRow, int startCell, Class<T> dtoClass, List<String> fieldList) {
		return readFnAllDto(new File(filePath), startRow, startCell, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param fieldList 字段列表
	 */
	public static <T> List<T> readFnAllDto(String filePath, Class<T> dtoClass, List<String> fieldList) {
		return readFnAllDto(filePath, 0, 0, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> List<T> readFnAllDto(InputStream inputStream, int startRow, int startCell, Class<T> dtoClass, String fields) {
		List<String> fieldList = Assist.toList(fields.split(BConst.COMMA));
		return readFnAllDto(inputStream, startRow, startCell, dtoClass, fieldList);
	}
	
	/**
	 * excel导入
	 * @param inputStream 文件输入流
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> List<T> readFnAllDto(InputStream inputStream, Class<T> dtoClass, String fields) {
		return readFnAllDto(inputStream, 0, 0, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> List<T> readFnAllDto(File file, int startRow, int startCell, Class<T> dtoClass, String fields) {
		return readFnAllDto(FileUtils.createFileInputStream(file), startRow, startCell, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param file 文件
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> List<T> readFnAllDto(File file, Class<T> dtoClass, String fields) {
		return readFnAllDto(file, 0, 0, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param startRow 起始行，以0开始
	 * @param startCell 起始列，以0开始
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> List<T> readFnAllDto(String filePath, int startRow, int startCell, Class<T> dtoClass, String fields) {
		return readFnAllDto(new File(filePath), startRow, startCell, dtoClass, fields);
	}
	
	/**
	 * excel导入
	 * @param filePath 文件地址
	 * @param fields 字段列表，以,号隔开
	 */
	public static <T> List<T> readFnAllDto(String filePath, Class<T> dtoClass, String fields) {
		return readFnAllDto(filePath, 0, 0, dtoClass, fields);
	}
}
