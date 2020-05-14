package com.ygxc.aqjy.framework.core.poi.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import com.alibaba.fastjson.JSONObject;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowArrHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowDtoHandle;

/**
 * excel导入行数据处理 - 封装一行数据对象，并进行处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ReadDataRowDtoHandler<T> extends ReadDataRowArrHandler {
	
	/** 字段列表 */
	protected List<String> fieldList = new ArrayList<>();
	
	/** 实体class */
	protected Class<T> dtoClass;
	
	/** 行数据处理器 */
	protected IReadDataRowDtoHandle<T> readDataRowDtoHandle;

	public ReadDataRowDtoHandler() {
		this.readDataRowArrHandle = createReadDataRowCellListHandle();
	}

	public ReadDataRowDtoHandler(IReadDataRowDtoHandle<T> readDataRowDtoHandle, Class<T> dtoClass, List<String> fieldList) {
		this();
		this.readDataRowDtoHandle = readDataRowDtoHandle;
		this.fieldList = fieldList;
		this.dtoClass = dtoClass;
		
	}
	
	/**
	 * 将当前行的列数据转为实体
	 * @param cellList
	 * @return
	 */
	protected T convertDto(List<String> cellList) {
		//当前行的列数据列表
		Map<String, Object> curRowCellMap = new HashMap<>();
		
		for (int i = 0; i < fieldList.size(); i++) {
			if (i > cellList.size()) continue;
			
			//将当前列数据，加入到当前行的列数据列表中
			curRowCellMap.put(fieldList.get(i), cellList.get(i));
		}
			
		return new JSONObject(curRowCellMap).toJavaObject(dtoClass);
	}
	
	/**
	 * 创建excel导入行数据处理 - 封装一行数据集合，并进行处理
	 * @return
	 */
	protected IReadDataRowArrHandle createReadDataRowCellListHandle() {
		return new IReadDataRowArrHandle() {
			
			@Override
			public void handleRow(int rowIndex, List<String> cellList, Row row) {
				//转为数据实体
				T dto = convertDto(cellList);
				//行数据处理
				readDataRowDtoHandle.handleRow(rowIndex, dto, row);
			}
		};
	}
	
	
	
	
	

	public List<String> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public Class<T> getDtoClass() {
		return dtoClass;
	}

	public void setDtoClass(Class<T> dtoClass) {
		this.dtoClass = dtoClass;
	}

	public int getCurRowIndex() {
		return curRowIndex;
	}

	public void setCurRowIndex(int curRowIndex) {
		this.curRowIndex = curRowIndex;
	}

	public IReadDataRowDtoHandle<T> getReadDataRowDtoHandle() {
		return readDataRowDtoHandle;
	}

	public void setReadDataRowDtoHandle(IReadDataRowDtoHandle<T> readDataRowDtoHandle) {
		this.readDataRowDtoHandle = readDataRowDtoHandle;
	}
}
