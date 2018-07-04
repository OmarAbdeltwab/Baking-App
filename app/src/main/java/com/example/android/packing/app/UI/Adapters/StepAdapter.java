package com.example.android.packing.app.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.packing.app.Data.Step;
import com.example.android.packing.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Omar on 21-May-18.
 */


public class StepAdapter extends RecyclerView.Adapter<StepAdapter.StepViewHolder> {

    private final List<Step> steps;
    public StepAdapter(List<Step> steps , StepOnclickListner stepOnclickListner) {
        this.steps=steps;
        this.stepOnclickListner=stepOnclickListner;
    }

    public  interface StepOnclickListner{ void StepOnClick(int pos);}
    private final StepOnclickListner stepOnclickListner;
    @Override
    public StepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StepViewHolder( LayoutInflater.from( parent.getContext()).inflate( R.layout.step,parent,false ) );
    }


    @Override
    public void onBindViewHolder(StepViewHolder holder, int position) {

        holder.StepSD_TV.setText(String.valueOf( position +1)+": "+ steps.get(position).getShortDescription() );
        String imagepath=steps.get(position).getThumbnailURL();

      if (imagepath.length()!=0&&imagepath!=null)
       Picasso.with(holder.StepThumb_IV.getContext())
              .load(imagepath);

        else holder.StepThumb_IV.setImageResource( R.drawable.no_image_available );
    }

    @Override
    public int getItemCount() {
        if (steps!=null)
            return steps.size();
        else return 0;
    }

    class StepViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        final TextView StepSD_TV ; final ImageView StepThumb_IV;
        public StepViewHolder(View itemView) {
            super( itemView );
itemView.setOnClickListener( this );
            StepSD_TV = itemView.findViewById( R.id.tv_step_sd );
            StepThumb_IV =itemView.findViewById( R.id.iv_step_thumb );
        }


        @Override
        public void onClick(View v) {
            stepOnclickListner.StepOnClick(getAdapterPosition());
        }
    }
}
