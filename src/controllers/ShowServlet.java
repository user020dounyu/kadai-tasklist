//データを一件格納
package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager(); //EntityManagerのオブジェクトを生成

        Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id"))); // 該当のIDのタスク1件のみをデータベースから取得

        em.close(); //閉じる

        request.setAttribute("task", t); //リクエストスコープに格納

        //indexにフォワード
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/show.jsp");
        rd.forward(request, response);
    }

}
