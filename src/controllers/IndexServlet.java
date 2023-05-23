//データをリスト化し格納
package controllers;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager(); //EntityManagerのオブジェクトを生成

        int page = 1; //開くページ数を取得（デフォルトは1ページ目）
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {}

        //最大件数と開始位置を指定してデータをリスト形式で取得
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class)
                            .setFirstResult(15 * (page - 1))
                            .setMaxResults(15)
                            .getResultList();

        //全件数を取得
        long tasks_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                      .getSingleResult();

        em.close(); //閉じる

        request.setAttribute("tasks", tasks); //リクエストスコープに格納
        request.setAttribute("tasks_count", tasks_count); // 全件数
        request.setAttribute("page", page); // ページ数

        //フラッシュメッセージがセッションスコープにセットされていたら
        //リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        //indexにフォワード
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/index.jsp");
        rd.forward(request, response);
    }

}
