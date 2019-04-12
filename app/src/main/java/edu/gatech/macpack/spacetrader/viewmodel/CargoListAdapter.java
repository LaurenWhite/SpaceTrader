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
import edu.gatech.macpack.spacetrader.entity.CargoItem;
import edu.gatech.macpack.spacetrader.entity.TradeGood;

/**
 * Creates list adapter for cargo list
 */
public class CargoListAdapter extends ArrayAdapter<CargoItem> {
    private final Context mContext;
    private final int mResource;

    /**
     * Creates cargo list adapter object
     *
     * @param context  the context of the adapter
     * @param resource the resource of the adapter
     * @param objects  the given objects
     */
    public CargoListAdapter(Context context, int resource, List<CargoItem> objects) {
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

        // create cargoItem object with information
        CargoItem item = new CargoItem(good, quantity, price);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (convertView == null) { convertView = inflater.inflate(mResource, parent, false); }

        TextView tvGoodName = convertView.findViewById(R.id.tvGoodName);
        TextView tvGoodQuantity = convertView.findViewById(R.id.tvGoodQuantity);
        TextView tvGoodPrice = convertView.findViewById(R.id.tvGoodPrice);

        tvGoodName.setText(item.getGood().toString());
        tvGoodQuantity.setText(mContext.getString(R.string.quantity, item.getQuantity()));
        tvGoodPrice.setText(mContext.getString(R.string.price, item.getPrice()));

        return convertView;
    }
}
