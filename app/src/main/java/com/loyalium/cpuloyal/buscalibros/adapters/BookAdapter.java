package com.loyalium.cpuloyal.buscalibros.adapters;

/**
 * Created by blamagroth on 7/10/16.
 */

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import com.loyalium.cpuloyal.buscalibros.R;
import com.loyalium.cpuloyal.buscalibros.models.Book;

public class BookAdapter extends ArrayAdapter<Book> {
    // Clase auxiliar para almacenar los datos recuperados del libro
    private static class ViewHolder {
        public ImageView ivCover;
        public TextView tvTitle;
        public TextView tvAuthor;
    }

    public BookAdapter(Context context, ArrayList<Book> aBooks) {
        super(context, 0, aBooks);
    }

    // Vaciar el contenido del libro en la
    // fila correspondiente del AdapterView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener los datos de la búsqueda por su posición
        final Book book = getItem(position);
        // Validar si una vista es reutilizada, en caso contrario crearla
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_book, parent, false);
            viewHolder.ivCover = (ImageView)convertView.findViewById(R.id.bookCover);
            viewHolder.tvTitle = (TextView)convertView.findViewById(R.id.title);
            viewHolder.tvAuthor = (TextView)convertView.findViewById(R.id.author);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Asignar los valores a la vista usando el objeto contenedor
        viewHolder.tvTitle.setText(book.getTitle());
        viewHolder.tvAuthor.setText(book.getAuthor());
        Picasso.with(getContext()).load(Uri.parse(book.getCoverUrl())).error(R.drawable.ic_nocover).into(viewHolder.ivCover);
        // Devolver la vista generada a la pantalla
        return convertView;
    }
}
