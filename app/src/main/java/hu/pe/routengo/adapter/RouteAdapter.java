package hu.pe.routengo.adapter;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import hu.pe.routengo.R;
import hu.pe.routengo.entity.Route;
import hu.pe.routengo.presenter.MapsActivity;

/**
 * Created by anton on 18.02.2017.
 */
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {

    private List<Route> mData;
    public static String username;
    private TextView time;
    private TextView distance;

    public RouteAdapter(List<Route> routes) {
        mData = routes;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_routes_main, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Route route = mData.get(position);
        if (route.getType().equals("bar")) {
            Glide.with(holder.view.getContext())
                    .load(R.drawable.bar_black)
                    .centerCrop()
                    .into(holder.type);
        } else {
            Glide.with(holder.view.getContext())
                    .load(R.drawable.history_black)
                    .centerCrop()
                    .into(holder.type);
        }
        holder.time.setText(route.getName());

        holder.view.setOnClickListener(v -> {
            AlertDialog.Builder ad = new AlertDialog.Builder(holder.view.getContext());

            ad.setTitle("Start this route?");  // заголовок
            ad.setMessage("You will vizit historical places in range 7 km"); // сообщение
            ad.setPositiveButton("Yes", (dialog, arg1) -> {
                Intent intent = new Intent(holder.view.getContext(), MapsActivity.class);
                intent.putExtra("route", route);
                holder.view.getContext().startActivity(intent);
            });
            ad.setNegativeButton("Cancel", (dialog, arg1) -> {
                dialog.dismiss();
            });
            ad.setCancelable(true);
            AlertDialog alert = ad.create();
            ad.show();
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public TextView time;
        public TextView distance;
        public TextView places;
        public ImageView type;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            time = (TextView) itemView.findViewById(R.id.time_card_main);
            distance = (TextView) itemView.findViewById(R.id.distance_card_main);
            type = (ImageView) itemView.findViewById(R.id.type_card_main);
            //places =(TextView) itemView.findViewById(R.id.places_card_main);
        }
    }

}