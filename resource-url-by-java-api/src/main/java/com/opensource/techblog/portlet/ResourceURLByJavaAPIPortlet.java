package com.opensource.techblog.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class ResourceURLByJavaAPIPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(ResourceURLByJavaAPIPortlet.class);
	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		
		
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);/*themeDisplay.getPortletDisplay().getId()*/
		PortletURL resourceUrl =  PortletURLFactoryUtil.create(request, PortalUtil.getPortletId(request), themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);
		resourceUrl.setParameter("sampleParam", "This parameter comes from resource URL generated with Java API");
		request.setAttribute("resourceUrlByJavaAPI", resourceUrl.toString());
		
		super.render(request, response);
	}

	
	@Override
	public void serveResource(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException,
			PortletException {
		
		String sampleParam = ParamUtil.getString(resourceRequest, "sampleParam");
		_log.info("parameter is : "+sampleParam);
		
		PrintWriter out = resourceResponse.getWriter();
		out.println("Parameter that you pass is :- "+sampleParam);
		out.flush();
		super.serveResource(resourceRequest, resourceResponse);
	}

	
	
}
