package com.example.huqiang.course.data.music;

/**
 * Created by 49988 on 2018/6/7.
 */

public class Subject {
    private RatingBean rating;
    private String image;
    //    private String author;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static class RatingBean {
        private int max;
        private double average;
        private String numRaters;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
        public String getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(String numRaters) {
            this.numRaters = numRaters;
        }
    }

}
