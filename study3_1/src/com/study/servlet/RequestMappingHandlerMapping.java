package com.study.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

//요청에 해당하는 핸들러를 FrontController에 return 
//핸들러들을 가지고 있어야합니다 => Map형태로 갖고있겠다.
//프로퍼티파일에 있는 매핑과 핸들러들을 가지고있겠다.   서버가 켜질때...
public class RequestMappingHandlerMapping {
	private Map<String,Handler> handlerMap=new HashMap<String,Handler>();
	
	public RequestMappingHandlerMapping
	(ServletContext app, String contextConfigLocation) throws Exception{
		//properties파일을 읽어서 handlerMap에 저장하기 
		Properties prop=new Properties();
		String configPath=app.getRealPath(contextConfigLocation);
		prop.load(new FileReader(configPath)); //프로퍼티파일 읽을 준비
		Iterator<Object> keyItr=prop.keySet().iterator();
		while(keyItr.hasNext()) {
			String uri=(String) keyItr.next();  // /free/freeList.wow
			String handlerClassName=prop.getProperty(uri); //com.study.free.web.FreeList
			Class<?> handlerClass=Class.forName(handlerClassName); 
			// 제네릭, 어떤타입의 클래스든 받을 수 있도록 ..? 
			Handler handler=(Handler)handlerClass.newInstance();
			handlerMap.put(uri, handler);  
			// 프로퍼티의 /free/freeList.wow를 key값으로, com.study.free.web.FreeList라는 객체를
			// value로 맵에 저장 
		}
	}
	
	public Handler getHandler(String uri) {
		return handlerMap.get(uri);
	}
	
	
	
}
