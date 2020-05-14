package com.ygxc.aqjy.common.structure;

import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.enumeration.MsgEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class R<T> {

    @ApiModelProperty(value = "返回代码", example = "0", required = true)
    private int code;

    @ApiModelProperty(value = "返回代码描述", example = "success", required = true)
    private String info;

    @ApiModelProperty(value = "其他信息")
    private T data;

    /**
     * 构建默认成功结果集
     *
     * @author leiZheng
     * @date 2019年6月18日
     */
    public R() {

    }

    public static<T>  R <T> error(int code, String info) {
        R<T> r = new R<T>();
        r.code = code;
        r.info = info;
        return r;
    }

    public static <T> R<T> error(MsgEnum msg) {
        R<T> r = new R<T>();
        r.code = msg.getVal();
        r.info = msg.getDescription();
        return r;
    }
    public static <T> R<T> error(String msg) {
        return error(RConst.ERROR_CODE, msg);
    }

    public static <T> R <T> error() {
        return error("未知异常");
    }

    public static <T> R <T> error(Throwable e) {
        return R.error(e.getMessage());
    }

    public static <T> R <T> validError(String msg) {
        return error(RConst.BAD_REQUEST_ERROR_CODE, msg);
    }

    public void judgeDataService() {
        if (RConst.SUCCESS_CODE != code) {
            //操作异常
         //   throw new HvacBusinessException(info);
        }
    }

    /**
	 * 是否操作成功，即code = SUCCESS
	 * 
	 * @return
	 */
	public boolean judgeSuccess() {
		return RConst.SUCCESS_CODE.equals(code);
	}
    
	/**
	 * 是否操作成功，即code = SUCCESS
	 * @return true：1；false：0；
	 */
	public int judgeSuccessInt() {
		return judgeSuccess() ? BConst.ONE : BConst.ZERO;
	}
	
	
    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.code = RConst.SUCCESS_CODE;
        r.info = RConst.SUCCESS_INFO;
        return r;
    }

    public R<T> data(T data) {
        this.data = data;
        return this;
    }

}
