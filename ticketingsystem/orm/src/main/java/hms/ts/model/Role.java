package hms.ts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="title" )
    private String title;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Employee> employeeList;

    public Role() {

    }

    public Role(String title, List<Employee> employeeList) {
        this.title = title;
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
