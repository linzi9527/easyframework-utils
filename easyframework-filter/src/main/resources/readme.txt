1、权限访问控制
2、传输内容含有特殊字符的转义
使用方法，在web.xml拷贝以下代码即可

<filter>
		<filter-name>doneFilter</filter-name>
		<filter-class>com.ddxc.utils.SysUrlFilter</filter-class>
		<init-param>
			<param-name>ignoreResourceUrls</param-name>
			<param-value>
			index.jsp,
			login.htm,
			.css,
			.js,
			.jpg,
			.png,
			login.jsp
			</param-value>
		</init-param>
		<init-param>
			<param-name>toUrl</param-name>
			<param-value>/index.jsp</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>doneFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

	<filter-name>StringFilter</filter-name>
	    <filter-class>org.easyframework.filter.StringFilter</filter-class>
	</filter>
	<filter-mapping>
	    <filter-name>StringFilter</filter-name>
	    <url-pattern>/*</url-pattern>
	</filter-mapping>