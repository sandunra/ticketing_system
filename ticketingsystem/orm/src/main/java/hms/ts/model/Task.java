package hms.ts.model;

import javax.persistence.*;

@Entity
@Table(name="task")
public class Task {

    private int id;
    private Project project;
    private String title;
    private String description;
    private Employee employee;
    private int assignedHours;
    private int spentHours;
    private int status;
    private String comment;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = Project.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = Employee.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="employee_id")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Column(name="assigned_hours")
    public int getAssignedHours() {
        return assignedHours;
    }

    public void setAssignedHours(int assignedHours) {
        this.assignedHours = assignedHours;
    }

    @Column(name="spent_hours")
    public int getSpentHours() {
        return spentHours;
    }

    public void setSpentHours(int spentHours) {
        this.spentHours = spentHours;
    }

    @Column(name="status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name="comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
