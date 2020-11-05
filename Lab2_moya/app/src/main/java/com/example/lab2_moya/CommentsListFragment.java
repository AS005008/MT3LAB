package com.example.lab2_moya;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsListFragment extends ListFragment {
    Comment[] comments;
    MyListAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NetworkService.getInstance()
                .getJSONApi()
                .getAllComments()
                .enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        List<Comment> d = response.body();
                        comments = new Comment[d.size()];
                        for(int i = 0; i<d.size(); i++)
                            comments[i] = d.get(i);
                        adapter = new MyListAdapter(getActivity(),android.R.layout.simple_list_item_1, comments);
                        setListAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle b = new Bundle();
        b.putString("name", comments[position].getName());
        b.putString("email", comments[position].getEmail());
        b.putString("body", comments[position].getBody());

        detailsFragment.setArguments(b);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.listFragment, detailsFragment)
                .addToBackStack(null)
                .commit();
    }

    public class MyListAdapter extends ArrayAdapter {
        private Context context;

        public MyListAdapter(Context _context, int textViewResourceId, Comment[] items){
            super(_context, textViewResourceId, items);
            context = _context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.comment, parent,
                    false);
            TextView title = (TextView) row.findViewById(R.id.commentName);
            TextView body = (TextView) row.findViewById(R.id.commentEmail);
            title.setText(comments[position].getName());
            body.setText(comments[position].getEmail());
            return row;
        }
    }
}
