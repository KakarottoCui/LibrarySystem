package tgc.edu.mcy.custom;

public class EasyuiResult {
	private Boolean success;
	private String msg;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public EasyuiResult() {
		super();
	}
	public EasyuiResult(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	public EasyuiResult(Boolean success) {
		super();
		this.success = success;
		this.msg="操作成功";
	}
	public EasyuiResult(String msg) {
		super();
		this.msg = msg;
		this.success=true;   //操作成功返回true
	}
	
	

	
}
