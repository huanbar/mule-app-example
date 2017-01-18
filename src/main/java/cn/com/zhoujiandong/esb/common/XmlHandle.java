package cn.com.zhoujiandong.esb.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

public class XmlHandle implements Callable {
	static String ROOT = "root";
	static String ITEMS = "items";

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		MuleMessage message = eventContext.getMessage();
		Object payload = message.getPayload();

		/*
		 * 赋初始值
		 */
		int code = message.getInvocationProperty("errcode");
		String msg = message.getInvocationProperty("errmsg");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", payload);

		return mapToXML(result, ROOT);
	}

	public static String mapToXML(Map<String, Object> map, String tab) {
		StringBuilder sb = new StringBuilder("<");
		sb.append(tab);
		sb.append(">");

		for (Map.Entry<String, Object> e : map.entrySet()) {
			sb.append("<");
			sb.append(e.getKey());
			sb.append(">");

			if (e.getValue() instanceof List) {
				sb.append(listToXML((List<Map<String, Object>>) e.getValue()));
			} else if (e.getValue() instanceof String) {
				sb.append(StringEscapeUtils.escapeXml(e.getValue().toString()));
			} else {
				sb.append(e.getValue());
			}
			sb.append("</");
			sb.append(e.getKey());
			sb.append(">");
		}

		sb.append("</");
		sb.append(tab);
		sb.append(">");

		return sb.toString();
	}

	public static String listToXML(List<Map<String, Object>> list) {
		StringBuilder sb = new StringBuilder("<");
		sb.append(ITEMS);
		sb.append(">");

		for (Map<String, Object> map : list) {
			sb.append(mapToXML(map, "item"));
		}

		sb.append("</");
		sb.append(ITEMS);
		sb.append(">");

		return sb.toString();
	}

}
