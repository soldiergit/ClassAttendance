package com.classAttendance.models.pojo;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author soldier
 * @title: SysMenuEntity
 * @projectName class_attendance
 * @date 19-7-5下午9:49
 * @Email： 583403411@qq.com
 * @description:
 */
@Entity
@Table(name = "sys_menu", schema = "class_attendance", catalog = "")
public class SysMenuEntity {
    private int menuId;
    private String href;
    private String icon;
    private Byte spread;
    private String title;
    private Integer userType;
    private Set<SysMenuChildrenEntity> children;//子菜单

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", nullable = false)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
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

    @Basic
    @Column(name = "user_type", nullable = false, length = 255)
    public Integer getUserType() {
        return userType;
    }
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysMenuEntity that = (SysMenuEntity) o;
        return menuId == that.menuId &&
//                roleType == that.roleType &&
                Objects.equals(href, that.href) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(spread, that.spread) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, href, icon, spread, title);
    }

    /**
     * FetchType.LAZY：懒加载，加载一个实体时，定义懒加载的属性不会马上从数据库中加载
     * FetchType.EAGER：急加载，加载一个实体时，定义急加载的属性会立即从数据库中加载
     */
    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
    public Set<SysMenuChildrenEntity> getChildren() {
        return children;
    }
    public void setChildren(Set<SysMenuChildrenEntity> children) {
        this.children = children;
    }
}
