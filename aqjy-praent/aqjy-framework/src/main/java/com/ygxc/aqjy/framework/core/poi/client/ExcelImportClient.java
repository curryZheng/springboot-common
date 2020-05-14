package com.ygxc.aqjy.framework.core.poi.client;

import java.io.InputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.common.utils.StringUtil;
import com.ygxc.aqjy.framework.core.poi.constant.PoiType;
import com.ygxc.aqjy.framework.core.poi.data.ReadDataRowAllDtoHandler;
import com.ygxc.aqjy.framework.core.poi.data.ReadDataRowArrHandler;
import com.ygxc.aqjy.framework.core.poi.data.ReadDataRowDtoHandler;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataAllDtoHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataCellHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowArrHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowDtoHandle;


/**
 * excel导入管理类
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ExcelImportClient {
	
	/** 文件输入流 */
	protected InputStream inputStream;
	
	/** poi类型 */
	protected PoiType poiType = PoiType.XSSF;
	
	/** sheet下标 */
	protected int sheetAt = 0;
	
	/** 起始行，以0开始 */
	protected int startRow = 0;
	
	/** 起始列，以0开始 */
	protected int startCell = 0;
	
	/** excel导入列数据处理 */
	protected IReadDataCellHandle readDataCellHandle;
	
	/**
	 * 导入excel
	 */
	public void read() {
		//创建workbook
		Workbook workbook = createWorkbook();
		//取得sheet
		Sheet sheet = workbook.getSheetAt(sheetAt);
		//最后一行下标
		int lastRowNum = sheet.getLastRowNum();
		
		for (int i = startRow; i < lastRowNum; i++) {
			//行
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			
			//最后一列下标
			int lastCellNum = row.getLastCellNum();
			
			for (int j = startCell; j < lastCellNum; j++) {
				//列
				Cell cell = row.getCell(j);
				if (cell == null) {
					continue;
				}
				
				//值
				Object valObj = null;
				if (CellType.NUMERIC.equals(cell.getCellType())) {
					double valDouble = cell.getNumericCellValue();
					//整数
					if (Math.floor(valDouble) == valDouble) {
						valObj = (long) valDouble;
					} else {  //浮点数
						valObj = valDouble;
					}
				} else {
					valObj = cell.getStringCellValue();
				}
				String val = StringUtil.defaultString(valObj);
				
				//数据处理
				readDataCellHandle.handleCell(i, j, val, cell);
			}
			
			//当前行处理结束
			readDataCellHandle.handleRowOver(i, row);
		}
		
		//全部处理结束
		readDataCellHandle.handleOver();
	}
	
	/**
	 * 根据poi类型创建Workbook
	 * @return
	 */
	protected Workbook createWorkbook() {
		try {
			Assist.notNull(inputStream, "inputStream cannot be null");
			return PoiType.HSSF.equals(poiType) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
		} catch (Exception e) {
			throw new YgxcBusinessException("create workbook error", e);
		}
	}

	
	
	
	
	
	/**
	 * excel导入行数据处理 - 封装所有数据对象，并进行处理
	 * @param <T>
	 * @param handle
	 * @param fields 字段列表，以,号隔开
	 * @return
	 */
	public <T> ExcelImportClient setReadDataRowDtoListHandle(IReadDataAllDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		List<String> fieldList = Assist.toList(fields.split(BConst.COMMA));
		setReadDataRowDtoListHandle(handle, dtoClass, fieldList);
		return this;
	}
	
	/**
	 * excel导入行数据处理 - 封装所有数据对象，并进行处理
	 * @param <T>
	 * @param handle
	 * @param fieldList
	 * @return
	 */
	public <T> ExcelImportClient setReadDataRowDtoListHandle(IReadDataAllDtoHandle<T> handle, Class<T> dtoClass
			, List<String> fieldList) {
		fieldList = Assist.trimToList(fieldList);
		ReadDataRowAllDtoHandler<T> handler = new ReadDataRowAllDtoHandler<>(handle, dtoClass, fieldList);
		setReadDataCellHandle(handler);
		return this;
	}
	
	/**
	 * excel导入行数据处理 - 封装一行数据对象，并进行处理
	 * @param <T>
	 * @param handle
	 * @param fields 字段列表，以,号隔开
	 * @return
	 */
	public <T> ExcelImportClient setReadDataRowDtoHandle(IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, String fields) {
		List<String> fieldList = Assist.toList(fields.split(BConst.COMMA));
		setReadDataRowDtoHandle(handle, dtoClass, fieldList);
		return this;
	}
	
	/**
	 * excel导入行数据处理 - 封装一行数据对象，并进行处理
	 * @param <T>
	 * @param handle
	 * @param fieldList
	 * @return
	 */
	public <T> ExcelImportClient setReadDataRowDtoHandle(IReadDataRowDtoHandle<T> handle, Class<T> dtoClass, List<String> fieldList) {
		fieldList = Assist.trimToList(fieldList);
		ReadDataRowDtoHandler<T> handler = new ReadDataRowDtoHandler<>(handle, dtoClass, fieldList);
		setReadDataCellHandle(handler);
		return this;
	}
	
	/**
	 * 设置excel导入行数据处理 - 封装一行数据集合，并进行处理
	 * @param handle
	 * @return
	 */
	public ExcelImportClient setReadDataRowCellListHandle(IReadDataRowArrHandle handle) {
		ReadDataRowArrHandler handler = new ReadDataRowArrHandler(handle);
		setReadDataCellHandle(handler);
		return this;
	}
	
	/**
	 * 创建ExcelImportClient
	 * @return
	 */
	public static ExcelImportClient create() {
		return new ExcelImportClient();
	}
	
	/**
	 * 取得文件输入流
	 * @return
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 设置文件输入流
	 * @return
	 */
	public ExcelImportClient setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
		return this;
	}

	/**
	 * 取得poi类型
	 * @return
	 */
	public PoiType getPoiType() {
		return poiType;
	}

	/**
	 * 设置poi类型
	 * @return
	 */
	public ExcelImportClient setPoiType(PoiType poiType) {
		this.poiType = poiType;
		return this;
	}

	/**
	 * 取得sheet下标
	 * @return
	 */
	public int getSheetAt() {
		return sheetAt;
	}

	/**
	 * 设置sheet下标
	 * @return
	 */
	public ExcelImportClient setSheetAt(int sheetAt) {
		this.sheetAt = sheetAt;
		return this;
	}

	/**
	 * 取得起始行
	 * @return
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * 设置起始行
	 * @return
	 */
	public ExcelImportClient setStartRow(int startRow) {
		this.startRow = startRow;
		return this;
	}

	/**
	 * 取得起始列
	 * @return
	 */
	public int getStartCell() {
		return startCell;
	}

	/**
	 * 设置起始列
	 * @return
	 */
	public ExcelImportClient setStartCell(int startCell) {
		this.startCell = startCell;
		return this;
	}
	
	/**
	 * 取得excel导入列数据处理
	 * @return
	 */
	public IReadDataCellHandle getReadDataCellHandle() {
		return readDataCellHandle;
	}
	
	/**
	 * 设置excel导入列数据处理
	 * @return
	 */
	public ExcelImportClient setReadDataCellHandle(IReadDataCellHandle readDataCellHandle) {
		this.readDataCellHandle = readDataCellHandle;
		return this;
	}
}
