package us.coreis.smartedu.startedu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.Cards> {
    ArrayList<String> WhoInvites;
    ArrayList<String> WhoInvited;
    Context context;

    public FeedAdapter(ArrayList<String> WhoInvites , ArrayList<String> WhoInvited , Context context) {
        this.context = context;
        this.WhoInvites = WhoInvites;
        this.WhoInvited = WhoInvited ;
    }

    @Override
    public Cards onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.start_feed_card, parent , false);
        return new Cards(view);
    }

    @Override
    public void onBindViewHolder(Cards holder, int position) {
        holder.whoInvites.setText(Html.fromHtml(WhoInvites.get(position)));
        holder.whomInvited.setText(Html.fromHtml(WhoInvited.get(position)));
    }

    @Override
    public int getItemCount() {
        return WhoInvited.size() ;
    }

    public class Cards extends RecyclerView.ViewHolder {
        TextView whoInvites ,whomInvited ;
        public Cards(View itemView) {
            super(itemView);
                whoInvites = (TextView) itemView.findViewById(R.id.whoInvites);
                whomInvited = (TextView) itemView.findViewById(R.id.InvitedWhom);
        }
    }
}
