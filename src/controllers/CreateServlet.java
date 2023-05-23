package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import models.validators.TaskValidator;
import utils.DBUtil;
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token"); //_tokenを宣言
        //CSRF対策
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager(); //EntityManagerのオブジェクトを生成
            em.getTransaction().begin(); //トランザクション処理開始

            Task t = new Task();

            //titleを格納
            String title = request.getParameter("title");
            t.setTitle(title);

            //contentを格納
            String content = request.getParameter("content");
            t.setContent(content);

            //時刻を格納
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            //リデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = TaskValidator.validate(t);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                //newにフォワード
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
                rd.forward(request, response);
            } else {
                // データベースに保存
                em.persist(t); //データ保存
                em.getTransaction().commit(); //トランザクション処理終了
                request.getSession().setAttribute("flush", "登録が完了しました。"); //flushの格納
                em.close(); //閉じる

                response.sendRedirect(request.getContextPath() + "/index"); //パス指定
            }
        }
    }
}
