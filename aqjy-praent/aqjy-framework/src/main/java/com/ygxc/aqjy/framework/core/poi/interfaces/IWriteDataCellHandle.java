package com.ygxc.aqjy.framework.core.poi.interfaces;

/**
 * excel导出列数据处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IWriteDataCellHandle<T> {

	/**
	 * 列数据处理
	 * @param obj
	 * @param rowIndex
	 * @param cellIndex
	 */
	Object handleCell(T obj, int rowIndex, int cellIndex);
}
