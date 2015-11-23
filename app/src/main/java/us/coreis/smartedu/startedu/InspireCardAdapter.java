package us.coreis.smartedu.startedu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class InspireCardAdapter extends RecyclerView.Adapter<InspireCardAdapter.Cards> {
    Context context;
    ArrayList<InspireCard> details;
    public InspireCardAdapter(Context context, ArrayList<InspireCard> details) {
        this.context = context;
        this.details = details;
    }
    @Override
    public InspireCardAdapter.Cards onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inspire_card, parent , false );
        return new InspireCardAdapter.Cards(view);
    }

    @Override
    public void onBindViewHolder(InspireCardAdapter.Cards holder, int position) {
        holder.name.setText(Html.fromHtml(details.get(position).name));
        holder.company.setText(details.get(position).company);
        holder.image.setImageResource(details.get(position).image);
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public class Cards extends RecyclerView.ViewHolder {
        TextView name , company ;
        ImageView image;
        public Cards(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.inspire_name);
            company = (TextView) itemView.findViewById(R.id.inspire_company);
            image = (ImageView) itemView.findViewById(R.id.inspire_image);
        }
    }
}
