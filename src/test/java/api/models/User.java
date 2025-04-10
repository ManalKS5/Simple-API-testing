package api.models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String job;

    public User() {}

    public User(String firstName, String lastName, String email, String job) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.job = job;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
