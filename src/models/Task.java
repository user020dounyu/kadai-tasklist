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
//一覧表示するデータを取得するためのJPQL
@NamedQueries({
    @NamedQuery(
        name = "getAllTasks", //引数の指定
        query = "SELECT t FROM Task AS t ORDER BY t.id DESC"
    )
})
@Table(name = "tasks") //tasksテーブルを指定
public class Task {


    @Id //主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主キー値を自動採番
    @Column(name = "id") //idを格納
    private Integer id; //数値型

    //name=カラム名,length=長さ,nullable=無しを許容するか)
    @Column(name = "title" , length = 255 , nullable = false) //titleを格納
    private String title; //文字列型

    @Column(name = "content" , length = 255 , nullable = false) //contentを格納
    private String content; //文字列型

    @Column(name = "create_at" , nullable = false) //create_atを格納
    private Timestamp create_at; //日時型

    @Column(name = "update_at" , nullable = false) //update_at格納
    private Timestamp update_at; //日時型

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

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }
}
