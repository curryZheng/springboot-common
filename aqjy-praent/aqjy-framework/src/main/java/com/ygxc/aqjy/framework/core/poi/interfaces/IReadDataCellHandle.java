package com.ygxc.aqjy.framework.core.poi.interfaces;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * excel导入列数据处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IReadDataCellHandle {

	/**
	 * 列数据处理
	 * @param rowIndex 行下标
	 * @param cellIndex 列下标
	 * @param val 值
	 */
	void handleCell(int rowIndex, int cellIndex, String val, Cell cell);
	
	/**
	 * 当前行的所有列处理结束，既将移到下一行，或全部处理结束
	 * @param rowIndex 行下标
	 */
	default void handleRowOver(int rowIndex, Row row) {
		
	}
	
	/**
	 * 所有行处理结束，既全部处理结束
	 */
	default void handleOver() {
		
	}
}
