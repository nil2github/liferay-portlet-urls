<%@page import="javax.portlet.ResourceURL"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.PortletURL"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<portlet:defineObjects />

<a href="#" onclick="callResourceUrlByJavaAPI()">Resource Url created by Java API in Portlet Class</a>
<br>
<br>
<%
    ResourceURL resourceUrlFromJSP = renderResponse.createResourceURL();
	resourceUrlFromJSP.setParameter("sampleParam", "This portletULR is created with API in JSP");
%>

<a href="#" onclick="callResourceUrlByFromJSP()">Resource Url created by JAVA API in JSP</a>

<script type="text/javascript">
function callResourceUrlByJavaAPI(){
	processAjax('${resourceUrlByJavaAPI}');
}

function callResourceUrlByFromJSP(){
	processAjax('<%=resourceUrlFromJSP%>');
}

function processAjax(resourceUrl){
    AUI().use('aui-io-request', function(A){
        A.io.request(resourceUrl, {
               method: 'post',
               data: {
            	   <portlet:namespace />param2: 'value2',
               },
               on: {
                   success: function() {
                    alert(this.get('responseData'));
                   }
              }
        });
     });
}
</script>
