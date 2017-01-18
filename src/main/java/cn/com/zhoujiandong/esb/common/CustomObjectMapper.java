package cn.com.zhoujiandong.esb.common;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解决java.util.Date、oracle.sql.BLOB类型返回json格式为自定义格式
 */
public class CustomObjectMapper extends ObjectMapper {
	@SuppressWarnings("unchecked")
	public CustomObjectMapper() {
		CustomSerializerFactory factory = new CustomSerializerFactory();
		// java.util.Date
		factory.addGenericMapping(Date.class, new JsonSerializer<Date>() {

			@Override
			public void serialize(Date value, JsonGenerator jsonGenerator,
					SerializerProvider provider) throws IOException {
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				jsonGenerator.writeString(sdf.format(value));
			}

		});
		// oracle.sql.BLOB
		/*
		 * factory.addGenericMapping(oracle.sql.BLOB.class, new JsonSerializer()
		 * {
		 * 
		 * @Override public void serialize(BLOB value, JsonGenerator jgen,
		 * SerializerProvider provider) throws IOException,
		 * JsonProcessingException { InputStream is = null; byte[] bit = null;
		 * try { is = value.getBinaryStream(); if (is == null) {
		 * jgen.writeString(""); } else { int lenth = (int) value.length(); bit
		 * = new byte[lenth]; is.read(bit); jgen.writeBinary(bit); } } catch
		 * (Exception e) { jgen.writeString("BLOB EXCEPTION"); } finally { if
		 * (is != null) { try { is.close(); } catch (Exception e) { } } }
		 * 
		 * } });
		 */
		this.setSerializerFactory(factory);
		this.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);// 格式化输出

	}
}