package com.jixianxueyuan.service.account;

import org.omg.CORBA.PUBLIC_MEMBER;

public class StaticParams {
	
	public static class USERROLE {
		public static final String ROLE_ADMIN ="admin";
		public static final String ROLE_USER = "user";
	}
	
	public static class PATHREGX {
		
		private final static String getPathRex(String path){
			return "/"+path+"/**";
		}
		
		public static final String VIEW = getPathRex(PATH.VIEW);
		public static final String ADMIN = getPathRex(PATH.ADMIN);
		public static final String API = getPathRex(PATH.API);
		public static final String API_SECURE = getPathRex(PATH.API_SECURE);
		public static final String RESOURCE = getPathRex(PATH.RESOURCE);
		public static final String STATIC = getPathRex(PATH.STATIC);
		public static final String JS = getPathRex(PATH.JS);
		public static final String CSS = getPathRex(PATH.CSS);
		public static final String IMG = getPathRex(PATH.IMG);

		public static final String SKATEBOARD = "/skateboard/**";
        public static final String SEMANTIC = "/semantic/**";
        public static final String FAVICON = "/favicon.ico";
        public static final String LOGIN = "/login/**";
    }
	
	public static class PATH {
		public static final String VIEW = "view";
		public static final String ADMIN = "admin";
		public static final String API = "api/v1";
		public static final String API_SECURE = "api/secure";
		public static final String RESOURCE = "resource";
		public static final String STATIC = "static";
		public static final String JS = "js";
		public static final String CSS = "css";
		public static final String IMG = "img";
	}

}
