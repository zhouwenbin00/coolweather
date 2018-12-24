package com.coolweather.app.util;

/**
 * Created by root on 2018/12/24.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coolweather.app.R;
import com.coolweather.app.model.NewsPhotoBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.R.attr.author;
import static android.R.attr.type;
import static android.R.id.list;
import static android.media.CamcorderProfile.get;

/**
 * Created by chenlei on 2017/10/11.
 */

public class MulRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NEW_SIMPLE_TYPE = 0;//单图文模式
    private static final int NEW_MUL_TYPE = 1;//多图文模式
    private static final int NEW_OTHER_TYPE = 2;//多图文模式
    private Context context;
    private List<NewsPhotoBean> list;

    public MulRecyclerViewAdapter(Context context, List<NewsPhotoBean> list) {
        this.context = context;
        this.list = list;
    }

    //重写getItemViewType方法,通过此方法来判断应该加载是哪种类型布局
    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        switch (type) {
            case 0:
                return NEW_SIMPLE_TYPE;
            case 1:
                return NEW_MUL_TYPE;
        }
        return NEW_OTHER_TYPE;
    }

    //根据不同的item类型来加载不同的viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case NEW_SIMPLE_TYPE:
                return new NewsPhotoViewHolder(inflater.inflate(R.layout.recyclerview_item_type_02, parent, false));
            case NEW_MUL_TYPE:
                return new NewsPhotosViewHolder(inflater.inflate(R.layout.recyclerview_item_type_01, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //把对应位置的数据得到
        String title = list.get(position).getTitle();
        String time = list.get(position).getF_time();
        String author = list.get(position).getAuthor();
        List<String> ls = list.get(position).getList();//这里是json数据中的图片集合，也就是封面。不同类型item的封面图片数量是不一样的
        //  //无论是否单图文，标题和更新时间以及作者不变

        //如果单图文
        if (holder instanceof NewsPhotoViewHolder) {

            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_title.setText(title);
            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_time.setText(time);
            ((NewsPhotoViewHolder) holder).tx_news_simple_photos_author.setText(author);
//            ((NewsPhotoViewHolder) holder).img_news_simple_photos_01.setImageBitmap(btm_01);//单图文不用遍历直接将图片转换bitmap对象设置到ImageView上
            return;
        }
        //如果多图文
        if (holder instanceof NewsPhotosViewHolder) {
            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_title.setText(title);
            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_time.setText(time);
            ((NewsPhotosViewHolder) holder).tx_news_mul_photos_author.setText(author);
//            ((NewsPhotosViewHolder) holder).img_news_mul_photos_01.setImageBitmap(btm_01);//多图文需要遍历list将每个图片链接转换成Bitmap对象设置到ImageView上
//            ((NewsPhotosViewHolder) holder).img_news_mul_photos_02.setImageBitmap(btm_02);
//            ((NewsPhotosViewHolder) holder).img_news_mul_photos_03.setImageBitmap(btm_03);
            return;
        }
    }
    //具体item数据等于pages*10，每页10条
    @Override
    public int getItemCount() {

        return list.size();
    }

    /**
     * NewsPhotoViewHolder为单图文模式
     */
    class NewsPhotoViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_news_simple_photos_title;//标题
        private ImageView img_news_simple_photos_01;//单图文模式的唯一一张图
        private TextView tx_news_simple_photos_time;//单图文模式的更新时间
        private TextView tx_news_simple_photos_author;//单图文模式的新闻作者

        public NewsPhotoViewHolder(View itemView) {
            super(itemView);
            tx_news_simple_photos_title = (TextView) itemView.findViewById(R.id.tx_news_simple_photos_title);//标题
//            img_news_simple_photos_01 = (ImageView) itemView.findViewById(R.id.tx_news_simple_photos_01);//单图文模式的唯一一张图
            tx_news_simple_photos_time = (TextView) itemView.findViewById(R.id.tx_news_simple_photos_time);//单图文模式的更新时间
            tx_news_simple_photos_author = (TextView) itemView.findViewById(R.id.img_news_simple_photos_author);//单图文模式的新闻作者

        }
    }

    /**
     * NewsPhotosViewHolder为多图模式
     */
    class NewsPhotosViewHolder extends RecyclerView.ViewHolder {
        private TextView tx_news_mul_photos_title;//标题
        //        private ImageView img_news_mul_photos_01;//多图文模式的第一张图
//        private ImageView img_news_mul_photos_02;//多图文模式的第二张图
//        private ImageView img_news_mul_photos_03;//多图文模式的第三张图
        private TextView tx_news_mul_photos_time;//多图文模式的更新时间
        private TextView tx_news_mul_photos_author;//多图文模式的新闻作者

        public NewsPhotosViewHolder(View itemView) {
            super(itemView);
            tx_news_mul_photos_title = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_title);
//            img_news_mul_photos_01 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_01);
//            img_news_mul_photos_02 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_02);
//            img_news_mul_photos_03 = (ImageView) itemView.findViewById(R.id.img_news_mul_photos_03);
            tx_news_mul_photos_time = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_time);
            tx_news_mul_photos_author = (TextView) itemView.findViewById(R.id.tx_news_mul_photos_author);
        }
    }
}
