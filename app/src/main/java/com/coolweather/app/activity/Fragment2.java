package com.coolweather.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.coolweather.app.R;
import com.coolweather.app.model.NewsPhotoBean;
import com.coolweather.app.util.MulRecyclerViewAdapter;
import com.coolweather.app.util.RecyclerViewDivider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {
    private  String strJson="{\"pages\":1,\"data\":[{\"type\":0,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":1,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":0,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":1,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":0,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":1,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":0,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":1,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":0,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\"]},{\"type\":1,\"title\":\"澳大利亚向非洲捐赠1000万美元\",\"time\":\"1小时前\",\"author\":\"卡尔网\",\"imgs\":[\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\",\"http://52sjw.com/images/jasdhisah.img\"]}]}";
    private RecyclerView recyclerView;
    private PtrClassicFrameLayout ptrClassicFrameLayout;
    private Handler handler=new Handler();
    private int page = 0;
    private List<NewsPhotoBean> list = new ArrayList<NewsPhotoBean>();
    private MulRecyclerViewAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment2, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ptrClassicFrameLayout = (PtrClassicFrameLayout) view.findViewById(R.id.test_list_view_frame);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        LinearLayoutManager llm =new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL));
        adapter = new MulRecyclerViewAdapter(getActivity().getApplicationContext(),list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setAdapter(mAdapter);
        ptrClassicFrameLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                ptrClassicFrameLayout.autoRefresh(true);
            }
        }, 150);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page= 0;
                        list.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(strJson);
                            JSONArray jsonArray  = jsonObject.getJSONArray("data");
                            for(int i = 0;i<10;i++){
                                NewsPhotoBean newsPhotoBean = new NewsPhotoBean();
                                newsPhotoBean.setType(jsonArray.getJSONObject(i).getInt("type"));
                                newsPhotoBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                                newsPhotoBean.setF_time(jsonArray.getJSONObject(i).getString("time"));
                                newsPhotoBean.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                                int len = jsonArray.getJSONObject(i).getJSONArray("imgs").length();
                                List<String> ls = new ArrayList<>();
                                for(int j = 0;j<len;j++){
                                    String s = jsonArray.getJSONObject(i).getJSONArray("imgs").getString(j);
                                    ls.add(s);
                                }
                                newsPhotoBean.setList(ls);
                                list.add(newsPhotoBean);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.refreshComplete();
                        ptrClassicFrameLayout.setLoadMoreEnable(true);
                        Log.d("TAG","正在刷新...");
                    }
                }, 1500);
            }
        });
        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(strJson);
                            JSONArray jsonArray  = jsonObject.getJSONArray("data");
                            for(int i = 0;i<10;i++){
                                NewsPhotoBean newsPhotoBean = new NewsPhotoBean();
                                newsPhotoBean.setType(jsonArray.getJSONObject(i).getInt("type"));
                                newsPhotoBean.setTitle(jsonArray.getJSONObject(i).getString("title"));
                                newsPhotoBean.setF_time(jsonArray.getJSONObject(i).getString("time"));
                                newsPhotoBean.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                                int len = jsonArray.getJSONObject(i).getJSONArray("imgs").length();
                                List<String> ls = new ArrayList<>();
                                for(int j = 0;j<len;j++){
                                    String s = jsonArray.getJSONObject(i).getJSONArray("imgs").getString(j);
                                    ls.add(s);
                                }
                                newsPhotoBean.setList(ls);
                                list.add(newsPhotoBean);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter.notifyDataSetChanged();
                        ptrClassicFrameLayout.loadMoreComplete(true);
                        page++;
                        Log.e("TAG",page+"页");
                        Toast.makeText(getActivity().getApplicationContext(), "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }

}
