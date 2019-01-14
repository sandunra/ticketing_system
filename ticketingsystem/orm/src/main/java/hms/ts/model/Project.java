package hms.ts.model;

import javax.persistence.*;

@Entity
@Table(name="project")
public class Project {

    private int id;
    private String title;
    private String description;
    private String type;
    private String client;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="client")
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
