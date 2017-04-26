package com.takeahike.takeahike;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by wesle on 4/17/2017.
 */

public class TrailList extends ArrayAdapter<Trail> {

    private Activity context;
    private List<Trail> trails;

    public TrailList(Activity context, List<Trail> trails){
        super(context, R.layout.cust_trail_layout, trails);
        this.context = context;
        this.trails = trails;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.cust_trail_layout, null, true);

        TextView textName = (TextView) ListViewItem.findViewById(R.id.TrailNameView);
        TextView textDiff = (TextView) ListViewItem.findViewById(R.id.TrailDiffView);
        TextView textDe = (TextView) ListViewItem.findViewById(R.id.TrailDeView);
        TextView milage = (TextView) ListViewItem.findViewById(R.id.TrailMilage);

        Trail t = trails.get(position);

        textName.setText(t.getName());
        textDiff.setText("Difficulty: " + t.getDifficulty());
        milage.setText("Mileage: " + t.getDistance());
        textDe.setText("Description: " + t.getDescription());

        return ListViewItem;
    }
}
