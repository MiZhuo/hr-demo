package fun.mizhuo.hrserver.model;

import java.io.Serializable;

/**
 * @author mizhuo
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -5054400856348777098L;

    private Integer id;

    private String name;

    private String namezh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNamezh() {
        return namezh;
    }

    public void setNamezh(String namezh) {
        this.namezh = namezh == null ? null : namezh.trim();
    }

}