package kg.cs_soft.sklad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomArrayAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List list;
    private List list2;
    private List list3;
    private List list4;
    private List list5;
    private List list6;
    public CustomArrayAdapter(Context context,List list,List list2,List list3,List list4,List list5,List list6) {
        this.list = list;
        this.list2 = list2;
        this.list3 = list3;
        this.list4 = list4;
        this.list5 = list5;
        this.list6 = list6;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View v = convertView;
        if ( v == null){
            holder = new ViewHolder();
            v = inflater.inflate(R.layout.custom_list, parent, false);

            holder.txt1 = (TextView) v.findViewById(R.id.model);
            holder.txt2 = (TextView) v.findViewById(R.id.tip);
            holder.txt3 = (TextView) v.findViewById(R.id.kolichestvo);
            holder.txt4 = (TextView) v.findViewById(R.id.rozn_cena);
            holder.txt5 = (TextView) v.findViewById(R.id.optovaya_cena);
            holder.txt6 = (TextView) v.findViewById(R.id.summa_op_cena);


            v.setTag(holder);
        }
        holder = (ViewHolder) v.getTag();
        holder.txt1.setText(""+list.get(position));
        holder.txt2.setText(""+list2.get(position));
        holder.txt3.setText(""+list3.get(position));
        holder.txt4.setText(""+list4.get(position));
        holder.txt5.setText(""+list5.get(position));
        holder.txt6.setText(""+list6.get(position));

        return v;

    }
    private static class ViewHolder {
        private TextView txt1;
        private TextView txt2;
        private TextView txt3;
        private TextView txt4;
        private TextView txt5;
        private TextView txt6;
    }
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
}