package com.gmail.ndonskih63;

import com.gmail.ndonskih63.details.ThisPost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String title = req.getParameter("title");
        ArrayList<ThisPost> posts = (ArrayList) session.getAttribute("posts");
        ThisPost thisPost = null;
        for (ThisPost tempPost : posts) {
            if (tempPost.getId().equals(title)) {
                thisPost = tempPost;
            }
        }
        posts.remove(thisPost);
        req.getRequestDispatcher("enter.jsp").forward(req, resp);
    }
}

