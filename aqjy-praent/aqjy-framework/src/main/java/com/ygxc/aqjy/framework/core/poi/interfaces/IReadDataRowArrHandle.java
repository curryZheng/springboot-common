package com.ygxc.aqjy.framework.core.poi.interfaces;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;

/**
 * excel导入行数据处理 - 封装一行数据集合，并进行处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IReadDataRowArrHandle {

	/**
	 * 行数据处理
	 * @param rowIndex 行下标
	 * @param cellList 列数据列表
	 */
	void handleRow(int rowIndex, List<String> cellList, Row row);
}
