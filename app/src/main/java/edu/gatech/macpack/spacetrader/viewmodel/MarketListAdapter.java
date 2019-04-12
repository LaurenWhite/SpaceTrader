package edu.gatech.macpack.spacetrader.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.Objects;

import edu.gatech.macpack.spacetrader.R;
import edu.gatech.macpack.spacetrader.entity.MarketItem;
import edu.gatech.macpack.spacetrader.entity.TradeGood;

/**
 * Creates list adapter for a market list
 */
public class MarketListAdapter extends ArrayAdapter<MarketItem> {

    private final Context mContext;
    private final int mResource;

    /**
     * Creates new market list adapter
     *
     * @param context  the context of the adapter
     * @param resource the resource of the adapter
     * @param objects  objects in adapter
     */
    public MarketListAdapter(Context context, int resource, List<MarketItem> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // get good information
        TradeGood good = Objects.requireNonNull(Objects.requireNonNull(getItem(position))).getGood();
        int quantity = Objects.requireNonNull(Objects.requireNonNull(getItem(position))).getQuantity();
        int price = Objects.requireNonNull(Objects.requireNonNull(getItem(position))).getPrice();

        // create marketItem object with information
        MarketItem item = new MarketItem(good, quantity, price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) convertView = inflater.inflate(mResource,parent, false);

        TextView tvGoodName = convertView.findViewById(R.id.tvGoodName);
        TextView tvGoodQuantity = convertView.findViewById(R.id.tvGoodQuantity);
        TextView tvGoodPrice = convertView.findViewById(R.id.tvGoodPrice);

        tvGoodName.setText(item.getGood().toString());
        tvGoodQuantity.setText(mContext.getString(R.string.quantity, item.getQuantity()));
        tvGoodPrice.setText(mContext.getString(R.string.price,item.getPrice()));

        return convertView;
    }
}
