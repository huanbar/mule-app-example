package cn.com.zhoujiandong.esb.common;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class ParameterHandle implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		String payload = eventContext.getMessageAsString("UTF-8");
		/*
		 * 校验输入
		 */

		/*
		 * 赋初始值
		 */
		message.setInvocationProperty("errcode", 100);
		message.setInvocationProperty("errmsg", "成功");
		message.setInvocationProperty("dataType", "json");
		return null;
	}

}
