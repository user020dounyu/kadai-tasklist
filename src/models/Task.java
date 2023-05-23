//必要なデータの格納

package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity //テーブル構造
//JPQL
@NamedQueries({
    @NamedQuery(
        name = "getAllTasks", //引数の指定
        query = "SELECT t FROM Task AS t ORDER BY t.id DESC" //一覧表示するデータを取得する
        ),
    @NamedQuery(
        name = "getTasksCount",//引数の指定
        query = "SELECT COUNT(t) FROM Task AS t" //データベースにデータが何件入っているか
        )
})
@Table(name = "tasks") //tasksテーブルを指定
public class Task {


    @Id //主キー
    @Column(name = "id") //idを格納
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主キー値を自動採番
    private Integer id; //数値型

    //name=カラム名,length=長さ,nullable=無しを許容するか)
    @Column(name = "title", length = 255, nullable = false) //titleを格納
    private String title; //文字列型

    @Column(name = "content", length = 255, nullable = false) //contentを格納
    private String content; //文字列型

    @Column(name = "created_at", nullable = false) //created_atを格納
    private Timestamp created_at; //日時型

    @Column(name = "updated_at", nullable = false) //updated_at格納
    private Timestamp updated_at; //日時型

  //getter,setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
}
