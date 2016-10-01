/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pt.adrz.appengine.arena.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pt.adrz.appengine.arena.utils.Utils;

// [START example]
@SuppressWarnings("serial")
public class ArenaServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	  
		String ace = request.getParameter("ace");
		String sop = request.getParameter("sop");

		PrintWriter out = response.getWriter();

		if (ace != null) {
			out.println(Utils.getLink(ace));
			return;
		}

		if (sop != null) {
			out.println(Utils.getLink(sop));
			return;
		}

		String html = Utils.table();
		request.setAttribute("html", html);
		RequestDispatcher dispatcher = request.getRequestDispatcher("arenavision.jsp");
		dispatcher.forward(request, response);
  }
}
// [END example]
