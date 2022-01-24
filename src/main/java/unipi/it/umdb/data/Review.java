package unipi.it.umdb.data;

public class Review {
    private String review_id;
    private String reviewer;
    private String movie;
    private String rating;
    private String reviewSummary;
    private String reviewDate;
    private int spoilerTag;
    private String reviewDetail;
    private String[] helpful;

    public Review(String rId, String userId, String title, String rating, String summary, String date, String detail){
        this.review_id = rId;
        this.reviewer = userId;
        this.movie = title;
        this.rating = rating;
        this.reviewSummary = summary;
        this.reviewDate = date;
        this.spoilerTag = 0;
        this.reviewDetail = detail;
        this.helpful = new String[]{"0","0"};

    }

    public void setRating(String rating){
        this.rating = rating;
    }

    public String getRating(){
        return this.rating;
    }

    public void setReviewSummary (String summary){
        this.reviewSummary = summary;
    }

    public void setReviewDetail (String detail){
        this.reviewDetail = detail;
    }

    public void setHelpful () {
        this.helpful[0] = Integer.toString(Integer.parseInt(this.helpful[0]) + 1);
    }

    public void setUnhelpful (){
        this.helpful[1] = Integer.toString(Integer.parseInt(this.helpful[1]) + 1);
    }

}
