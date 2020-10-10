package com.test.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
	private int code;
	private String msg;
	private T data;
	  
	public CommonResult() {
		
	}
	
    public CommonResult(int code, String msg) {
	    this.code = code;
	    this.msg = msg;
    }
    
    public CommonResult(int code, String msg,Object o) {
	    this.code = code;
	    this.msg = msg;
	    this.data = (T)o;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
}
