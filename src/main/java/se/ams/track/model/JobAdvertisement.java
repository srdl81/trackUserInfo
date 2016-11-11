package se.ams.track.model;


public class JobAdvertisement {

    private String id;
    private String branch;
    int views;

    public JobAdvertisement(String id) {
        this.id = id;
    }

    public JobAdvertisement(String id, String branch) {
        this.id = id;
        this.branch = branch;
    }

    public JobAdvertisement() {
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "JobAdvertisement{" +
                "id='" + id + '\'' +
                ", branch='" + branch + '\'' +
                ", views=" + views +
                '}';
    }
}
