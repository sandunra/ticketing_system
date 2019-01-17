package hms.ts.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="task")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(targetEntity = Project.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="project_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Project project;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne(targetEntity = Employee.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="employee_id", referencedColumnName = "id", insertable =  false, updatable = false)
    private Employee employee;

    @Column(name="assigned_hours")
    private int assignedHours;

    @Column(name="spent_hours")
    private int spentHours;

    @Column(name="status")
    private int status;

    @Column(name="comment")
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getAssignedHours() {
        return assignedHours;
    }

    public void setAssignedHours(int assignedHours) {
        this.assignedHours = assignedHours;
    }

    public int getSpentHours() {
        return spentHours;
    }

    public void setSpentHours(int spentHours) {
        this.spentHours = spentHours;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
