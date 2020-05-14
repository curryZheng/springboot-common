package com.ygxc.aqjy.framework.core.poi.interfaces;

import org.apache.poi.ss.usermodel.Row;

/**
 * excel导入行数据处理 - 封装一行数据对象，并进行处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IReadDataRowDtoHandle<T> {

	/**
	 * 行数据处理
	 * @param rowIndex 行下标
	 * @param cellList 列数据列表
	 */
	void handleRow(int rowIndex, T dto, Row row);
}
