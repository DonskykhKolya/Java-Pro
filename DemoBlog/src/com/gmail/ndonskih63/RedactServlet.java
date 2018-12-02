package com.gmail.ndonskih63;

import com.gmail.ndonskih63.details.ThisPost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedactServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String title = req.getParameter("title");
        List<ThisPost> thisPosts = (ArrayList) session.getAttribute("posts");
        ThisPost thisPost = null;
        for (ThisPost tempPost : thisPosts) {
            if (tempPost.getId().equals(title)) {
                thisPost = tempPost;
            }
        }
        session.setAttribute("post", thisPost);
        req.getRequestDispatcher("redact.jsp").forward(req, resp);
    }
}

