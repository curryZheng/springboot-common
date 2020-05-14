package com.ygxc.aqjy.framework.core.poi.data;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataCellHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowArrHandle;

/**
 * excel导入行数据处理 - 封装一行数据集合，并进行处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ReadDataRowArrHandler implements IReadDataCellHandle {
	
	/** 列数据处理器 */
	protected IReadDataRowArrHandle readDataRowArrHandle;
	
	/** 当前处理的行 */
	protected int curRowIndex = -1;
	
	/** 当前行的列数据 */
	protected List<String> curRowCellList = new ArrayList<>();
	
	public ReadDataRowArrHandler() {
		
	}
	
	public ReadDataRowArrHandler(IReadDataRowArrHandle readDataRowCellListHandle) {
		this.readDataRowArrHandle = readDataRowCellListHandle;
	}
	

	/**
	 * 列数据处理
	 * @param rowIndex 行下标
	 * @param cellIndex 列下标
	 * @param val 值
	 */
	@Override
	public void handleCell(int rowIndex, int cellIndex, String val, Cell cell) {
		//将当前列数据，加入到当前行的列数据列表中
		curRowCellList.add(val);
	}
	
	/**
	 * 当前行的所有列处理结束，既将移到下一行，或全部处理结束
	 */
	@Override
	public void handleRowOver(int rowIndex, Row row) {
		//行数据处理
		readDataRowArrHandle.handleRow(rowIndex, curRowCellList, row);
		//重置当前行的列数据
		curRowCellList = new ArrayList<>();
	}
	
	

	public int getCurRowIndex() {
		return curRowIndex;
	}

	public void setCurRowIndex(int curRowIndex) {
		this.curRowIndex = curRowIndex;
	}

	public List<String> getCurRowCellList() {
		return curRowCellList;
	}

	public void setCurRowCellList(List<String> curRowCellList) {
		this.curRowCellList = curRowCellList;
	}
	
	public void setReadDataRowArrHandle(IReadDataRowArrHandle readDataRowArrHandle) {
		this.readDataRowArrHandle = readDataRowArrHandle;
	}
	
	public IReadDataRowArrHandle getReadDataRowArrHandle() {
		return readDataRowArrHandle;
	}
}
