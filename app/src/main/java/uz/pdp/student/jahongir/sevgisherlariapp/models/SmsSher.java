package uz.pdp.student.jahongir.sevgisherlariapp.models;

public class SmsSher {
    private String category;
    private Boolean isnew;
    private String name;
    private String description;
    private Boolean isliked;


    public SmsSher(String category, Boolean isnew, String name, String description, Boolean isliked) {
        this.category = category;
        this.isnew = isnew;
        this.name = name;
        this.description = description;
        this.isliked = isliked;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsnew() {
        return isnew;
    }

    public void setIsnew(Boolean isnew) {
        this.isnew = isnew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsliked() {
        return isliked;
    }

    public void setIsliked(Boolean isliked) {
        this.isliked = isliked;
    }

    @Override
    public String toString() {
        return "SmsSher{" +
                "category='" + category + '\'' +
                ", isnew='" + isnew + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isliked=" + isliked +
                '}';
    }
}
