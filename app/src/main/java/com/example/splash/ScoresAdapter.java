package com.example.splash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the sports data.
 */
class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<score> mScoresData;
    private Context mContext;

    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param sportsData ArrayList containing the sports data.
     * @param context Context of the application.
     */
    ScoresAdapter(Context context, ArrayList<score> sportsData) {
        this.mScoresData = sportsData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public ScoresAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(ScoresAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        score currentScore = mScoresData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentScore);
    }


    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mScoresData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder
            {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.score);
            mInfoText = itemView.findViewById(R.id.time);
            mSportsImage = itemView.findViewById(R.id.gameImage);

            // Set the OnClickListener to the entire view.
//            itemView.setOnClickListener(this);
        }

        void bindTo(score currentScore){
            // Populate the textviews with data.
            mTitleText.setText(currentScore.getTitle());
            mInfoText.setText(currentScore.getInfo());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentScore.getImageResource()).into(mSportsImage);

        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
//        @Override
//        public void onClick(View view) {
//            Sport currentScore = mScoresData.get(getAdapterPosition());
//            Intent detailIntent = new Intent(mContext, DetailActivity.class);
//            detailIntent.putExtra("title", currentScore.getTitle());
//            detailIntent.putExtra("image_resource",
//                    currentScore.getImageResource());
//            mContext.startActivity(detailIntent);
//        }
    }
}
