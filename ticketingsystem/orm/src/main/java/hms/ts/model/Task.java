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
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne(targetEntity = Employee.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="employee_id", nullable = false)
    private Employee employee;

    @Column(name="assigned_hours")
    private int assignedHours;

    @Column(name="spent_hours")
    private int spentHours;

    @Column(name="status")
    private int status;

    @Column(name="comment")
    private String comment;

    public Task() {

    }

    public Task( String title, String description,  int assignedHours, int spentHours, String comment, int status) {
        this.title = title;
        this.description = description;
        this.assignedHours = assignedHours;
        this.spentHours = spentHours;
        this.comment = comment;
        this.status = status;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Task))
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
