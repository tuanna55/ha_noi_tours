package com.example.hanoitours;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PlaceList extends ItemizedOverlay<OverlayItem> {
	ArrayList<Place> mOverlays = new ArrayList<Place>();
	Activity mContext;

	public PlaceList(Drawable defaultMarker, Activity context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		populate();
	}
	
	public ArrayList<GeoPoint> getListGeoPoint(){
		ArrayList<GeoPoint> list = new ArrayList<GeoPoint>();
		for(int i = 0; i < mOverlays.size(); i++){
			list.add(mOverlays.get(i).point);
		}
		return list;
	}
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
		Place item = mOverlays.get(index);
    	Intent intent = new Intent(mContext, PlaceDetail.class);
    	intent.putExtra("TEST", index);
    	mContext.startActivity(intent);
		return true;
	}
	
	public void addOverlay(Place overlay) {
		mOverlays.add(overlay);
		populate();
	}

	public void remove(OverlayItem overlay) {
        mOverlays.remove(overlay);
        populate();
    }
	
	public ArrayList<Place> getPlaceList(){
		return mOverlays;
	}
}
