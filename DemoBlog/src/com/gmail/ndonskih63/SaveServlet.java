package com.gmail.ndonskih63;

import com.gmail.ndonskih63.details.AddUsers;
import com.gmail.ndonskih63.details.ThisPost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int marker = 0;
        HttpSession session = req.getSession();
        ArrayList<ThisPost> articles = (ArrayList) session.getAttribute("posts");
        AddUsers users = (AddUsers) session.getAttribute("users");
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        for (ThisPost tempArticle : articles) {
            if (tempArticle.getId().equals(title)) {
                tempArticle.setText(text);
                marker = 1;
            }
        }
        if (marker == 0) {
            articles.add(new ThisPost(title, text, users.getUser((String) session.getAttribute("login")),
                    LocalDate.now()));
        }
        req.getRequestDispatcher("enter.jsp").forward(req, resp);
    }
}

