package com.takeahike.takeahike;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by wesle on 4/15/2017.
 */

public class TrailList extends ArrayAdapter<TrailInfo> {

    private Activity context;
    private List<TrailInfo> trails;

    public TrailList(Activity context, List<TrailInfo> trails){
        super(context, R.layout.cust_trail_layout, trails);
        this.context = context;
        this.trails = trails;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.cust_trail_layout, null, true);

        TextView trailName = (TextView)listViewItem.findViewById(R.id.TrailNameView);
        TextView trailDiff = (TextView)listViewItem.findViewById(R.id.TrailDiffView);
        TextView trailDe = (TextView)listViewItem.findViewById(R.id.TrailDeView);

        TrailInfo trail = trails.get(position);

        trailName.setText(TrailInfo.getName());
        trailDiff.setText((TrailInfo.getDifficulty()));
        trailDe.setText(TrailInfo.getDescription());

        return listViewItem;

    }
}
