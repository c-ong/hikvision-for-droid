package org.ong.android.examples.hikvision.ui.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LiveSurface extends SurfaceView /*implements SurfaceHolder.Callback*/ {
	
	private SurfaceHolder holder;

	public LiveSurface(Context context, AttributeSet attrs) {
		super( context, attrs );
		
		init();
	}
	
	private void init() {
//		getHolder().addCallback( this );
	}
	
	public SurfaceHolder obtainHolder() {
		return getHolder();
//		return holder;
	}

//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		this.holder = holder;
//	}
//
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//		
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		
//	}
}