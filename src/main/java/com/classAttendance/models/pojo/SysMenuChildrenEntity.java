package com.classAttendance.models.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author soldier
 * @title: SysMenuChildrenEntity
 * @projectName class_attendance
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "sys_menu_children", schema = "class_attendance", catalog = "")
public class SysMenuChildrenEntity {
    private int id;
    private String href;
    private String icon;
    private Byte spread;
    private String title;
    private SysMenuEntity menu;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "href", nullable = false, length = 255)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Basic
    @Column(name = "icon", nullable = false, length = 255)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "spread", nullable = true)
    public Byte getSpread() {
        return spread;
    }

    public void setSpread(Byte spread) {
        this.spread = spread;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenuChildrenEntity that = (SysMenuChildrenEntity) o;
        return id == that.id &&
                Objects.equals(href, that.href) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(spread, that.spread) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, href, icon, spread, title);
    }

    @ManyToOne(cascade=CascadeType.ALL)
    public SysMenuEntity getMenu() {
        return menu;
    }

    public void setMenu(SysMenuEntity menu) {
        this.menu = menu;
    }
}
