package listing.trademe.test.com.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Letícia on 20/02/2018.
 */

public class Category {

    @SerializedName("Name")
    private String name;
    @SerializedName("Number")
    private String number;
    @SerializedName("Path")
    private String path;
    @SerializedName("Subcategories")
    private List<Category> subCategories;
    @SerializedName("HasClassifieds")
    private String hasClassifieds;
    @SerializedName("IsLeaf")
    private String isLeaf;

    public Category(String name, String number, String path, List<Category> subCategories, String hasClassifieds, String isLeaf) {
        this.name = name;
        this.number = number;
        this.path = path;
        this.subCategories = subCategories;
        this.hasClassifieds = hasClassifieds;
        this.isLeaf = isLeaf;
    }

    public String getNumber() {
        if(number == null || number.isEmpty())
            number = "0";
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasClassifieds() {
        return Boolean.valueOf(hasClassifieds);
    }

    public void setHasClassifieds(String hasClassifieds) {
        this.hasClassifieds = hasClassifieds;
    }

    public boolean isNotLeaf() { return !Boolean.valueOf(isLeaf); }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", path='" + path + '\'' +
                ", subCategories=" + subCategories +
                ", hasClassifieds='" + hasClassifieds + '\'' +
                ", isLeaf='" + isLeaf + '\'' +
                '}';
    }
}
