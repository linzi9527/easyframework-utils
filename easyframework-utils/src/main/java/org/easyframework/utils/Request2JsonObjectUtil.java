package org.easyframework.utils;


import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import net.sf.json.JSONObject;

/**
 * 通过HttpServletRequest（ajax）请求上来的json字符串数据参数，后台接收转换为JSONObject返回使用或转换为已知对应的实体bean
 * @author 蓝眼泪
 * 2017-9-5下午6:53:05
 */
public class Request2JsonObjectUtil {

	private static final Logger log = Logger.getLogger(Request2JsonObjectUtil.class);

	//在求情的网络流中的缓冲区读取数据
	 private static String readJSONString(HttpServletRequest request){
			   StringBuffer json = new StringBuffer();
			   String line = null;
			   try {
				   BufferedReader reader = request.getReader();
				   while((line = reader.readLine()) != null) {
					   json.append(line);
				   }
			   }
			   catch(Exception e) {
				   	log.error("缓冲区读取数据-异常："+e);
			   }
				log.info("\n缓冲区读取数据："+json.toString());
			   return json.toString();
		   }

	 /**
	  * 接收前端发送到后台的数据（json格式为转为对象对应某个bean，可以保存、更新）
	  * @param request
	  * @param clazz
	  *  com.alibaba.fastjson.JSONObject paramsOjbect = (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.parse(params);
	  * @return
	  */
	 @SuppressWarnings("unchecked")
	public static <T> T beanFromJSONString(HttpServletRequest request,Class<T> clazz){
		String params= readJSONString(request);
		//log.info("\n接收params："+params);
		T t=null;
		if(null!=params&&params.contains("{")&&params.contains("}")){
			 JSONObject jsonObject=JSONObject.fromObject(params.toString());
			 t=(T)JSONObject.toBean(jsonObject, clazz);
		}else{
			log.info("\n================有问题没进去处理==================");
		}
		 return t;
	 }
	 
	 /**
	  * 对于各种查询的请求参数json数据处理，一般带有分页和关键字offset、rows以及同时携带了查询条件id、名称、日期、其他等
	  * @param request
	  * @return
	  */
	 public static JSONObject jsonObjectFromJSONString(HttpServletRequest request){
			String params= readJSONString(request);
		//	log.info("\n接收params："+params);
			 JSONObject jsonObject=null;
			if(null!=params&&params.contains("{")&&params.contains("}")){
				  jsonObject=JSONObject.fromObject(params.toString());
			}else{
				log.info("\n================有问题没进去处理==================");
			}
			 return jsonObject;
		 }
	 
	 
}
