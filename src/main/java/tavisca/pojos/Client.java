package tavisca.pojos;

public class Client {

    private String name;

    private String location;

    private String projectName;

    public Client() {

    }

    public Client(String name, String location, String projectName) {
        this.name = name;
        this.location = location;
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }



    public String getLocation() {
        return location;
    }



    public String getProjectName() {
        return projectName;
    }


}
