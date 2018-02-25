package listing.trademe.test.com.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Letícia on 23/02/2018.
 */
public class Item {

    @SerializedName("List")
    private ArrayList<ItemDetail> items;

    public Item(ArrayList<ItemDetail> items) {
        this.items = items;
    }

    public ArrayList<ItemDetail> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetail> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Item{" +
                "items=" + items +
                '}';
    }

    public class ItemDetail {
        @SerializedName("ListingId")
        private String listingId;
        @SerializedName("Title")
        private String title;
        @SerializedName("Subtitle")
        private String subtitle;
        @SerializedName("PriceDisplay")
        private String priceDisplay;
        @SerializedName("PictureHref")
        private String pictureHref;
        @SerializedName("Category")
        private String categoryNumber;

        public ItemDetail(String listingId, String title, String subtitle, String priceDisplay, String pictureHref, String categoryNumber) {
            this.listingId = listingId;
            this.title = title;
            this.subtitle = subtitle;
            this.priceDisplay = priceDisplay;
            this.pictureHref = pictureHref;
            this.categoryNumber = categoryNumber;
        }

        public String getListingId() {
            return listingId;
        }

        public void setListingId(String listingId) {
            this.listingId = listingId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getPriceDisplay() {
            return priceDisplay;
        }

        public void setPriceDisplay(String priceDisplay) {
            this.priceDisplay = priceDisplay;
        }

        public String getPictureHref() {
            return pictureHref;
        }

        public void setPictureHref(String pictureHref) {
            this.pictureHref = pictureHref;
        }

        public String getCategoryNumber() {
            return categoryNumber;
        }

        public void setCategoryNumber(String categoryNumber) {
            this.categoryNumber = categoryNumber;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "listingId='" + listingId + '\'' +
                    ", title='" + title + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", priceDisplay='" + priceDisplay + '\'' +
                    ", pictureHref=" + pictureHref + '\'' +
                    ", categoryNumber=" + categoryNumber +
                    '}';
        }
    }
}
