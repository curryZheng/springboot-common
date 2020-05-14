package com.ygxc.aqjy.common.structure;

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

    public static R error(int code, String info) {
        R r = new R();
        r.code = code;
        r.info = info;
        return r;
    }

    public static R error(MsgEnum msg) {
        R r = new R();
        r.code = msg.getVal();
        r.info = msg.getDescription();
        return r;
    }
    public static R error(String msg) {
        return error(RConst.ERROR_CODE, msg);
    }

    public static R error() {
        return error("未知异常");
    }

    public static R error(Throwable e) {
        return R.error(e.getMessage());
    }

    public static R validError(String msg) {
        return error(RConst.BAD_REQUEST_ERROR_CODE, msg);
    }

    public void judgeDataService() {
        if (RConst.SUCCESS_CODE != code) {
            //操作异常
         //   throw new HvacBusinessException(info);
        }
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
