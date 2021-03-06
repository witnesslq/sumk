/**
 * Copyright (C) 2016 - 2017 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.http.handler;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.yx.http.HttpUtil;
import org.yx.http.Web;
import org.yx.log.Log;

public class ReqBodyHandler implements HttpHandler {

	@Override
	public boolean accept(Web web) {
		return true;
	}

	@Override
	public boolean handle(WebContext ctx) throws Exception {
		if (ctx.getData() != null) {
			Log.get(ReqBodyHandler.class).debug("data is not null");
			return false;
		}
		if (ctx.getInfo().getArgClz() == null) {
			return false;
		}
		HttpServletRequest req = ctx.getHttpRequest();
		InputStream in = req.getInputStream();
		ctx.setData(HttpUtil.extractData(in));
		return false;
	}

}
