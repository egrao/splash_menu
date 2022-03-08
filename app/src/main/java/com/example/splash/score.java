package com.example.splash;

class score {

    // Member variables representing the score and timermation about the sport.
    private String score;
    private String time;
    private final int imageResource;

    /**
     * Constructor for the Sport data model.
     *
     * @param score The name if the sport.
     * @param time timermation about the sport.
     */
    public score(String score, String time, int imageResource) {
        this.score = score;
        this.time = time;
        this.imageResource = imageResource;
    }

    /**
     * Gets the score of the sport.
     *
     * @return The score of the sport.
     */
    String getScore() {
        return score;
    }

    /**
     * Gets the time about the sport.
     *
     * @return The time about the sport.
     */
    String getTime() {
        return time;
    }

    public int getImageResource() {
        return imageResource;
    }

}
