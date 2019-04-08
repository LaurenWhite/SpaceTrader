package edu.gatech.macpack.spacetrader.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.TradeGood;

public class MarketListAdapter extends ArrayAdapter<MarketItem> {
    private static final String TAG = "MarketListAdapter";
    private Context mContext;
    private int mResource;

    public MarketListAdapter(Context context, int resource, List<MarketItem> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get good information
        TradeGood good = getItem(position).getGood();
        int quantity = getItem(position).getQuantity();
        int price = getItem(position).getPrice();

        // create marketItem object with information
        MarketItem item = new MarketItem(good, quantity, price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvGoodName = convertView.findViewById(R.id.tvGoodName);
        TextView tvGoodQuantity = convertView.findViewById(R.id.tvGoodQuantity);
        TextView tvGoodPrice = convertView.findViewById(R.id.tvGoodPrice);

        tvGoodName.setText(item.getGood().toString());
        tvGoodQuantity.setText("" + item.getQuantity());
        tvGoodPrice.setText("" + item.getPrice());

        return convertView;
    }
}
