package br.com.minhaagenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import br.com.minhaagenda.R;

public class UserAdapter<User> extends ArrayAdapter<User> implements View.OnClickListener{

    Context mContext;
    private ArrayList<User> listaUsers;

    public UserAdapter(Context context, ArrayList<User> users ) {
        super(context, R.layout.row_item, users);
        this.listaUsers = users;
        this.mContext=context;
    }


    @Override
    public void onClick(View view) {
        int position  = (Integer) view.getTag();
        Object object =  getItem(position);
        User user = (User) object;


        switch (view.getId()) {
            case R.id.item_info:
                Snackbar.make(view, "Release date " + user.getNome(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }
    private int lastPosition = -1;
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;

        User usuario = listaUsers.get(position);

        final View result;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);

            TextView name = (TextView) listItem.findViewById(R.id.name);
            name.setText(usuario.getNome());

            TextView telefone = (TextView) listItem.findViewById(R.id.txtTelefone);
            name.setText(usuario.getTelefone());

            ImageView image = (ImageView) listItem.findViewById(R.id.item_info);
            image.setImageResource(usuario.getmImageDrawable());

            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.animator.up_from_bottom : R.animator.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.txtName.setText(usuario.getNome());
        viewHolder.txtType.setText(usuario.getType());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;

    }
}
