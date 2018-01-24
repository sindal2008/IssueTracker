package by.test.sindalouski.issuetracker.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="build")
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String buildNumb;

    @OneToMany(mappedBy = "build")
    private List<Project> projects;

    @OneToMany(mappedBy = "build")
    private List<Issue> issues;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuildNumb() {
        return buildNumb;
    }

    public void setBuildNumb(String buildNumb) {
        this.buildNumb = buildNumb;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
