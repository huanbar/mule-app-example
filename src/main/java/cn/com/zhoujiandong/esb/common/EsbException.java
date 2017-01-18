package cn.com.zhoujiandong.esb.common;

public class EsbException extends Exception {

	Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public EsbException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}

}
