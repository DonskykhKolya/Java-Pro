package com.gmail.ndonskih63;

import com.gmail.ndonskih63.details.AddUsers;
import com.gmail.ndonskih63.details.PostToFile;
import com.gmail.ndonskih63.details.ToPost;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EnterServlet extends HttpServlet {

    private final PostToFile postToFile;
    private final ToPost toPost;
    private final AddUsers users;

    {
        postToFile = new PostToFile();
        toPost = postToFile.getPost();
        users = postToFile.getUsers();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("login");
        String password = req.getParameter("password");
        if (users.getUser(username) != null && users.verify(username, password)) {
            session.setAttribute("posts", toPost.getPost());
            session.setAttribute("login", username);
            session.setAttribute("users",users);
            req.getRequestDispatcher("enter.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("<html><body><center>Sorry, but entered information is incorrect. " +
                    "Please, try yet.</center></body></html>");
        }
    }
}
