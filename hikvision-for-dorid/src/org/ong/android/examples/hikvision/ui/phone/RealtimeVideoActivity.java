package org.ong.android.examples.hikvision.ui.phone;

import java.util.ArrayList;
import java.util.Iterator;

import org.MediaPlayer.PlayM4.Player;
import org.ong.android.examples.hikvision.R;
import org.ong.android.examples.hikvision.debug.DebugTools;

import com.hikvision.netsdk.ExceptionCallBack;
import com.hikvision.netsdk.HCNetSDK;
import com.hikvision.netsdk.NET_DVR_CLIENTINFO;
import com.hikvision.netsdk.NET_DVR_DEVICEINFO_V30;
import com.hikvision.netsdk.NET_DVR_IPCHANINFO;
import com.hikvision.netsdk.NET_DVR_IPDEVINFO_V31;
import com.hikvision.netsdk.NET_DVR_IPPARACFG_V40;
import com.hikvision.netsdk.RealPlayCallBack;
//import com.mcu.iVMS.channelmanager.SelectedItemInfo;
//import com.mcu.iVMS.devicemanager.ChannelInfo;
//import com.mcu.iVMS.devicemanager.DeviceInfo;
//import com.mcu.iVMS.global.GlobalAppManager;
//import com.mcu.iVMS.playback.TimeBarShowInfo;
//import com.mcu.iVMS.realplay.LiveControl;
//import com.mcu.iVMS.sysconfig.CheckUpdates;
//import com.mcu.iVMS.util.Utility;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DebugUtils;
import android.view.SurfaceHolder;

public class RealtimeVideoActivity extends Activity implements SurfaceHolder.Callback {

	private boolean firstRunningApp;
	private LiveSurface surface;
	
	public static final int CHANNEL_TYPE_ANALOG = 1;
	public static final int CHANNEL_TYPE_DIGIT = 0;
	public static final int CHANNEL_TYPE_ZERO = 3;
	
	
	public static final byte CHANNEL_ENABLED = 1;
	public static final byte CHANNEL_DISABLED = 0;	  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState );

		setContentView( R.layout.live );

		init();

		// LiveAction -> LiveControl -> LivePlayActionControl(Live unit UIs)
		// GetChannelNameThread(NET_DVR_PICCFG_V30.sChanName)
		// startLive();

		// 每个 IP Camera 对应一个 SurfaceView
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void init() {
		findViews();
		
//		surface.getHolder().setType( SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS );
		
        // Preparing the surface
        surface.getHolder().addCallback( this );
		
		new LoadingDevicesTask().execute();	
	}
	
	private void findViews() {
		surface = (LiveSurface) findViewById( R.id.surface );
	}
	
	private class LoadingDevicesTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			
			final long begin = System.currentTimeMillis();			
			
			player = Player.getInstance();			

//			GlobalAppManager globalAppManager = GlobalAppManager.getInstance();

			// Step 1:
//			globalAppManager.initDB( RealtimeVideoActivity.this );

			// Step 2: Loading the system Configuration if this is not first
			//         Running.
//			SharedPreferences localSharedPreferences = getSharedPreferences(
//					"system_config", 0);
//
//			int currentVersion = localSharedPreferences.getInt(
//					"current_version_code", 1/*0*/ );
//			int latestVersion = CheckUpdates.getCurVersionCode( RealtimeVideoActivity.this );
//			System.out.println( "Checking for updates#latest: " + latestVersion );
//			if ( currentVersion >= latestVersion ) {
//				System.out.println( "Updates is available." );
//				RealtimeVideoActivity.this.firstRunningApp = true;
//
//				SharedPreferences.Editor localEditor = localSharedPreferences
//						.edit();
//				localEditor.putInt("current_version_code", latestVersion);
//				localEditor.putBoolean("real_play", true);
//				localEditor.putBoolean("check_channel", true);
//				localEditor.putBoolean("start_live", true);
//				localEditor.commit();
//
//				globalAppManager.getDbEngine().getDeviceList(
//						globalAppManager.getDeviceList() );
//				
//				if ( globalAppManager.getDeviceList().size() == 0) {
//					// Adding we are the default Device to device list.
//					DeviceInfo demo = new DeviceInfo( "Hangzhou, China", "", 1,
//							"115.236.50.5", 8800, "guest", "guest4500" );
//					globalAppManager.getDeviceManager().addDevice( demo );
//					
//					System.out.println( "Attempting to login: " + demo.login( false ) );
//				}
//			}

			// Step 4:
//			globalAppManager.configNetSDK( RealtimeVideoActivity.this );
//			globalAppManager.getDeviceManager().getDeviceList();
//			globalAppManager.getFavoriteManager().getAllFavorites();
//			globalAppManager.getSelectedChannelManager().getAllSelected();
			
//			System.out.println( "DeviceList: " + GlobalAppManager.getInstance()
//					.getCloneDeviceList().size() );
//
//			ArrayList list = new ArrayList();
//			Iterator selected = globalAppManager.getSelectedList().iterator();
//			for ( ;; ) {
//				long waitForEnd = 0;
//				if ( ! selected.hasNext() ) {
//					if (list.size() > 0) {
//						globalAppManager.getSelectedList().removeAll( list );
//						globalAppManager.getSelectedChannelManager()
//								.reCreateAllSelected(true);
//					}
//
//					final long spent = System.currentTimeMillis() - begin;
//
//					if ( spent < 1800L )
//						waitForEnd = 1800L - spent;
//				}
//
//				try {
//					Thread.sleep( waitForEnd );
//					TimeBarShowInfo.setDefaultSearchCalendar();
//					// return null;
//
//					SelectedItemInfo localSelectedItemInfo = (SelectedItemInfo) selected.next();
//					Iterator localIterator2 = GlobalAppManager.getInstance()
//							.getCloneDeviceList().iterator();
//
//					label349:
//					{
//						boolean bool1 = localIterator2.hasNext();
			
//						int k = 0;
//						if ( ! bool1 );
//						while (k == 0) {
//							list.add(localSelectedItemInfo);
////							break;
//							DeviceInfo localDeviceInfo1 = (DeviceInfo) localIterator2
//									.next();
//							if (localDeviceInfo1.getID() != localSelectedItemInfo
//									.getDeviceID())
//								break label349;
//							Iterator localIterator3 = localDeviceInfo1
//									.getSourceChannelList().iterator();
//							ChannelInfo localChannelInfo;
//							do {
//								boolean bool2 = localIterator3.hasNext();
//								k = 0;
//								if (!bool2)
//									break;
//								localChannelInfo = (ChannelInfo) localIterator3.next();
//							} while ((localChannelInfo.getChannelType() != localSelectedItemInfo
//									.getChannelType())
//									|| (localChannelInfo.getChannelNo() != localSelectedItemInfo
//											.getChannelNo()));
//							k = 1;
//						}
//					}
//				} catch (InterruptedException localInterruptedException) {
//					while (true)
//						localInterruptedException.printStackTrace();
//				}
//
//				System.out.println("SelectedList.size: "
//						+ globalAppManager.getSelectedList().size());
//
//			}
			
			hcNetSdk = new HCNetSDK();
			
			hcNetSdk.NET_DVR_Init();
			
			hcNetSdk.NET_DVR_SetConnectTime( Integer.MAX_VALUE );
			
			hcNetSdk.NET_DVR_SetExceptionCallBack( exceptionCallback );
			
//			DeviceInfo device = globalAppManager.getDeviceList().get( 0 );
			
	        // ----------------------------------------------------------------
	        
	        // get play port
	        playPort = player.getPort();	        
	        catchErrorIfNecessary();
	        
	        // ----------------------------------------------------------------
			
			NET_DVR_DEVICEINFO_V30 dvr_deviceinfo = new NET_DVR_DEVICEINFO_V30();
			// "Hangzhou, China", "", 1, "115.236.50.5", 8800, "guest", "guest4500"
	        int userId = hcNetSdk.NET_DVR_Login_V30( 
	        		"115.236.50.5", 8800, 
	        		"guest", "guest4500", 
	        		dvr_deviceinfo );
	        
	        DebugTools.dump( dvr_deviceinfo );
	        
//	        System.out.println( "DeviceConfig: " + device.getAddress() + " " + device.getPort() + " " 
//	        		+ device.getUserName() + " " + device.getPassword() );
	        
	        System.out.println( "Attempting to login: userId " + userId );
	        System.out.println( 
	        		String.format( "DeviceInfo: byChanNum=%s, byIPChanNum=%s", 
	        				dvr_deviceinfo.byChanNum, dvr_deviceinfo.byIPChanNum ) );

	        catchErrorIfNecessary();
	        
	        // ----------------------------------------------------------------
	        
	        /* If you renew a device, then be reset the entries of the below:
	         * setAnalogChannelCount(0);
	         * setIPChannelCount(0);
	         * setZeroChannelCount(0);
	         */
	        
	        // Note: Device id is SQLite record id.
//	        System.out.println( "ChannelList: " + device.getChannelList().size() + " SourceChannelList: " + device.getSourceChannelList().size() );
	        
	        // DeviceId, Name, ChannelType, ChannelNo., Enable
//	        ChannelInfo channel = new ChannelInfo( /*3*/device.getID(), "Test", CHANNEL_TYPE_DIGIT, 34/*33 + 1*/, true );
//	        ArrayList<ChannelInfo> channels = new ArrayList<ChannelInfo>( 1 );
//	        channels.add( channel );
	        // setting the Source channels......
//	        device.setSourceChannelList( channels );
	        
//	        System.out.println( "ChannelList: " + device.getChannelList().size() + " SourceChannelList: " + device.getSourceChannelList().size() );
	                
	        
	        //ChannelInfo channel = new ChannelInfo();
	        //globalAppManager.getDeviceManager().getCurrentChannel( )
	        // byStartChan + 1
	        // Structure of IP device resource and IP channel resource configuration.
	        NET_DVR_IPPARACFG_V40 ipParaCfg = new NET_DVR_IPPARACFG_V40();
	        
	        // UserId, Command, ChannelNo., Out
	        hcNetSdk.NET_DVR_GetDVRConfig( userId, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, 0, ipParaCfg );
//	        System.out.println( 
//	        		String.format( "NET_DVR_IPPARACFG_V40{ dwAChanNum : %s, dwDChanNum : %s, dwGroupNum : %s, dwStartDChan : %s }", 
//	        				ipParaCfg.dwAChanNum, ipParaCfg.dwDChanNum, 
//	        				ipParaCfg.dwGroupNum, ipParaCfg.dwStartDChan ) );
	        
	        int counter = 0;
//	        for ( byte byt : ipParaCfg.byAnalogChanEnable ) {
//	        	if ( CHANNEL_ENABLED == byt ) counter++;
//	        }
//	        System.out.println( "AnalogChanEnabledSize: " + counter );
	        
	        System.out.println( "-------------------------------------" );
	        
	        for ( NET_DVR_IPCHANINFO entry : ipParaCfg.struIPChanInfo ) {
	        	if ( CHANNEL_ENABLED == entry.byEnable ) {
	        		DebugTools.dump( entry );
	        	}
	        }
	        
//	        System.out.println( "-------------------------------------" );
//	        
//	        for ( NET_DVR_IPDEVINFO_V31 entry : ipParaCfg.struIPDevInfo ) {
//	        	
//	        	if ( 1 == entry.byEnable ) {
//	        		// byProType
//	        		// Protocol type: 0- private (default), 1- Panasonic, 2- sony, get more NET_DVR_GetIPCProtoList。 
//
//	        		//DebugTools.dump( entry );
//	        		
//	        		System.out.println( "{" );
//		        	System.out.println( "  byDomain -> " + new String( Utility.getValidByte( entry.byDomain ) ) );
//		        	System.out.println( "  struIP.sIpV4 -> " + new String( Utility.getValidByte( entry.struIP.sIpV4 ) ) );
//		        	System.out.println( "  sUserName -> " + new String( Utility.getValidByte( entry.sUserName ) ) );
//		        	System.out.println( "  sPassword -> " + new String( Utility.getValidByte( entry.sPassword ) ) );
//		        	System.out.println( "  byProType -> " + entry.byProType );
//		        	System.out.println( "  wDVRPort -> " + entry.wDVRPort );		        	
//		        	System.out.println( "}" );
//	        	}
//	        }
//	        System.out.println( "-------------------------------------" );	        
	        
	        
	        DebugTools.dump( ipParaCfg );
	        catchErrorIfNecessary();      

	        
	        // ----------------------------------------------------------------
	        
	        NET_DVR_CLIENTINFO clientInfo = new NET_DVR_CLIENTINFO();
//	        ArrayList<ChannelInfo> channelList = device.getChannelList();
//	        System.out.println( "ChannelList: " + channelList.size() );
//	        DebugTools.dump( channelList.get( 0 ) );
	        
//	        clientInfo.lChannel = 4;
	        clientInfo.lChannel = 34;
	        
//	        clientInfo.lLinkMode = 1;
	        clientInfo.lLinkMode = 0;
//	        clientInfo.lLinkMode = 0x80000000;
	        clientInfo.sMultiCastIP = null;
	        
	        // UserId, ClientInfo, RealplayCallback, Blocked
	        final int returned = hcNetSdk.NET_DVR_RealPlay_V30( userId, clientInfo, realplayCallback, true );
	        System.out.println( "Living: " + returned );
	        catchErrorIfNecessary();	        
	        
			return null;

			// LiveControl liveCtrl = new LiveControl( this );

		}	
	}
	
	private static final int PLAYING_BUFFER_SIZE = 1024 * 1024 * 4;
	
	private Player player;
	private int playPort = -1;
	
	private HCNetSDK hcNetSdk;
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
//		if ( -1 == playPort ) {
		System.out.println( "surfaceCreated: " );
		if ( holder.getSurface().isValid() ) {
			if ( ! Player.getInstance().setVideoWindow( playPort, 0, holder.getSurface() ) ) {
				System.out.println( "player set video window failed!" );
			}
		}
//	
//		this.holder = holder;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		System.out.println( "surfaceChanged: " );
		if ( holder.getSurface().isValid() ) {
			if ( ! Player.getInstance().setVideoWindow( playPort, 0, null ) ) {
				System.out.println( "player release video window failed!" );
			}			
		}		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
	private ExceptionCallBack exceptionCallback = new ExceptionCallBack() {
		
		@Override
		public void fExceptionCallBack(int code, int userId, int handle) {
			System.out.println( 
					String.format( 
							"ExceptionCallBack::fExceptionCallBack( 0x%h, %s, %s )", code, userId, handle ) );
		}
	};
	
	private RealPlayCallBack realplayCallback = new RealPlayCallBack() {
		
		@Override
		public void fRealDataCallBack(int handle, int dataType, byte[] buffer, int bufferSize) {
			System.out.println( String.format( "fRealDataCallBack{ handle : %s, dataType : %s, bufferSize : %s }", 
					handle, dataType, bufferSize ) );
			
			int i = 0;
			
			switch ( dataType ) {
			case HCNetSDK.NET_DVR_SYSHEAD:
				
				if ( -1 == (playPort = Player.getInstance().getPort() ) ) {
					System.out.println( "Can't get play port!" );
					
					return;
				}
							
				if ( 0 < bufferSize ) {
					if ( openPlayer( buffer, bufferSize ) ) {					
						System.out.println( "Open player successfully." );
					} else {
						System.out.println( "Open player failed." );
					}
				}	
			
				break;
				
			case HCNetSDK.NET_DVR_STREAMDATA:
			case HCNetSDK.NET_DVR_STD_VIDEODATA:
			case HCNetSDK.NET_DVR_STD_AUDIODATA:
				
				if ( 0 < bufferSize && -1 != playPort ) {
					try {
						for ( i = 0; i < 400; i++) {
							if ( Player.getInstance().inputData( playPort, buffer,
									bufferSize ) ) {
								System.out.println( "Played successfully." );
								break;
							} 
							
							System.out.println( "Playing failed." );
							
							Thread.sleep( 10 );
						}
					} catch (Exception e) {
						
					}
					
					if ( i == 400 ) {
						System.out.println( "inputData failed" );
					}
					
//					if ( Player.getInstance().inputData( playPort, buffer, bufferSize ) ) {
//						System.out.println( "Played successfully." );
//					} else {
//						System.out.println( "Playing failed." );
//					}					
				}				
				
//				if ( -1 != playPort ) {
//					// closing the player
//				} 
//				
//				if ( openPlayer( buffer, bufferSize ) ) {
//					
//				}
			}
			
			//if ( -1 == playPort ) return;
			
			//Player.getInstance().inputData( playPort, buffer, bufferSize );
		}
	};	
	
	private boolean openPlayer(byte[] buffer, int bufferSize) {
		
//		do {
//			playPort = Player.getInstance().getPort();
//		} while ( -1 == playPort );
		
		if ( ! Player.getInstance().setStreamOpenMode(playPort, Player.STREAM_FILE ) ) {
			System.out.println( "The player set stream mode failed!" );
			return false;
		}
		    
	    if ( ! Player.getInstance().openStream( playPort, buffer, bufferSize, PLAYING_BUFFER_SIZE ) ) {
	      Player.getInstance().freePort( playPort );
	      playPort = -1;
	      
	      return false;
	    }
		    
		Player.getInstance().setStreamOpenMode( playPort, Player.STREAM_FILE );
		
	    System.out.println( "We are using " + surface.getHolder() + " as a Displayer." );
	    
	    if ( ! Player.getInstance().play( playPort, surface.getHolder().getSurface() ) ) {
	    	Player.getInstance().closeStream( playPort );
	    	Player.getInstance().freePort( playPort );
	      
	    	playPort = -1;
	      
	    	return false;
	    }
		
		return true;
	}
	
	public void catchErrorIfNecessary() {
		int code = hcNetSdk.NET_DVR_GetLastError();
		if ( 0 != code ) System.out.println( "Error: " + code );
	}
}

//public void run()
//{
//  if (DeviceInfo.DEVICE_STATUS_ENUM.FAIL == DeviceInfo.this.login(false))
//    return;
//  Iterator localIterator = DeviceInfo.this.getSourceChannelList().iterator();
//  while (true)
//  {
//    if (!localIterator.hasNext())
//    {
//      if (DeviceInfo.this.mOnUpdateChannelNameListener != null)
//        DeviceInfo.this.mOnUpdateChannelNameListener.onUpdateChannelName();
//      DeviceInfo.this.logout();
//      return;
//    }
//    ChannelInfo localChannelInfo = (ChannelInfo)localIterator.next();
//    if (3 == localChannelInfo.getChannelType())
//      continue;
//    NET_DVR_PICCFG_V30 localNET_DVR_PICCFG_V30 = new NET_DVR_PICCFG_V30();
//    if (!HCNetSDK.getInstance().NET_DVR_GetDVRConfig(DeviceInfo.this.getUserID(), 1002, localChannelInfo.getChannelNo(), localNET_DVR_PICCFG_V30))
//      continue;
//    try
//    {
//      localChannelInfo.setName(new String(Utility.getValidByte(localNET_DVR_PICCFG_V30.sChanName), "GB2312"));
//      localChannelInfo.setIsNameUpdated(true);
//      GlobalAppManager.getInstance().getDbEngine().updateChannel(localChannelInfo);
//    }
//    catch (UnsupportedEncodingException localUnsupportedEncodingException)
//    {
//      localUnsupportedEncodingException.printStackTrace();
//    }
//  }
//}

//private DEVICE_STATUS_ENUM loginAction()
//{
//  if (this.mLoginCount == 0)
//  {
//    if (this.mUserID >= 0)
//    {
//      this.mLoginCount = (1 + this.mLoginCount);
//      return DEVICE_STATUS_ENUM.READY;
//    }
//    if (1 == this.mRegMode)
//      this.mParsedIp = this.mIpDomainAddress;
//    while (true)
//    {
//      this.mDeviceInfoV30 = new NET_DVR_DEVICEINFO_V30();
//      this.mUserID = HCNetSDK.getInstance().NET_DVR_Login_V30(this.mParsedIp, this.mDevicePort, this.mUserName, this.mPassword, this.mDeviceInfoV30);
//      if (-1 != this.mUserID)
//        break;
//      setOnline(false);
//      setLastErrorCode(HCNetSDK.getInstance().NET_DVR_GetLastError());
//      ErrorManager.getInstace().setLastError(HCNetSDK.getInstance().NET_DVR_GetLastError());
//      return DEVICE_STATUS_ENUM.FAIL;
//      if (((this.mRegMode != 0) && (2 != this.mRegMode)) || (getIpByDDNS(this.mDDNSAddress)))
//        continue;
//      setOnline(false);
//      return DEVICE_STATUS_ENUM.FAIL;
//    }
//    setOnline(true);
//    this.mAlarmCount = this.mDeviceInfoV30.byAlarmOutPortNum;
//    this.mSerialNo = new String(Utility.getValidByte(this.mDeviceInfoV30.sSerialNumber));
//    int i = this.mSourceChannelList.size();
//    int j = 0;
//    int k = this.mDeviceInfoV30.byChanNum;
//    NET_DVR_IPPARACFG_V40 localNET_DVR_IPPARACFG_V40;
//    int i2;
//    int i4;
//    int i5;
//    boolean bool1;
//    label386: label487: ArrayList localArrayList;
//    Iterator localIterator;
//    if (j >= k)
//    {
//      setAnalogStartChannelNo(this.mDeviceInfoV30.byStartChan);
//      setAnalogChannelCount(this.mDeviceInfoV30.byChanNum);
//      localNET_DVR_IPPARACFG_V40 = new NET_DVR_IPPARACFG_V40();
//      if (HCNetSDK.getInstance().NET_DVR_GetDVRConfig(this.mUserID, 1062, 0, localNET_DVR_IPPARACFG_V40))
//      {
//        i2 = 0;
//        int i3 = this.mDeviceInfoV30.byChanNum;
//        if (i2 < i3)
//          break label739;
//        i4 = this.mSourceChannelList.size();
//        i5 = 0;
//        int i6 = localNET_DVR_IPPARACFG_V40.dwDChanNum;
//        if (i5 < i6)
//          break label786;
//        setIPChannelCount(localNET_DVR_IPPARACFG_V40.dwDChanNum);
//        setIPStartChannelNo(localNET_DVR_IPPARACFG_V40.dwStartDChan);
//      }
//      if (this.mDeviceInfoV30.byZeroChanNum > 0)
//      {
//        bool1 = true;
//        NET_DVR_ZEROCHANCFG localNET_DVR_ZEROCHANCFG = new NET_DVR_ZEROCHANCFG();
//        if (HCNetSDK.getInstance().NET_DVR_GetDVRConfig(getUserID(), 1102, 1, localNET_DVR_ZEROCHANCFG))
//        {
//          if (localNET_DVR_ZEROCHANCFG.byEnable == 0)
//            break label992;
//          bool1 = true;
//        }
//        int n = this.mSourceChannelList.size();
//        int i1 = this.mDeviceInfoV30.byChanNum + getIPChannelCount();
//        if (i1 >= n)
//          break label998;
//        ChannelInfo localChannelInfo5 = (ChannelInfo)this.mSourceChannelList.get(i1);
//        localChannelInfo5.setChannelType(3);
//        localChannelInfo5.setChannelNo(1);
//        localChannelInfo5.setDeviceID(this.mID);
//        localChannelInfo5.setDevice(this);
//        localChannelInfo5.setEnable(bool1);
//        localChannelInfo5.setName(GlobalApplication.getInstance().getResources().getString(2131296320));
//        localChannelInfo5.setStreamType(0);
//        setZeroChannelCount(1);
//      }
//      localArrayList = new ArrayList();
//      localIterator = this.mSourceChannelList.iterator();
//    }
//    while (true)
//    {
//      if (!localIterator.hasNext())
//      {
//        this.mChannelInfoList.clear();
//        if (localArrayList.size() > 0)
//          this.mChannelInfoList.addAll(localArrayList);
//        this.mLoginCount = (1 + this.mLoginCount);
//        Log.i("TEST_DEBUG", "TEST_DEBUG  " + getName() + " 登陆计数： " + this.mLoginCount);
//        return DEVICE_STATUS_ENUM.SUCC;
//        if (j < i)
//        {
//          ChannelInfo localChannelInfo2 = (ChannelInfo)this.mSourceChannelList.get(j);
//          localChannelInfo2.setChannelType(1);
//          localChannelInfo2.setChannelNo(j + this.mDeviceInfoV30.byStartChan);
//          localChannelInfo2.setDeviceID(this.mID);
//          localChannelInfo2.setDevice(this);
//          localChannelInfo2.setEnable(true);
//        }
//        while (true)
//        {
//          j++;
//          break;
//          Object[] arrayOfObject1 = new Object[1];
//          arrayOfObject1[0] = Integer.valueOf(j + 1);
//          String str1 = String.format("%02d", arrayOfObject1);
//          int m = j + this.mDeviceInfoV30.byStartChan;
//          ChannelInfo localChannelInfo1 = new ChannelInfo(this.mID, str1, 1, m, true);
//          localChannelInfo1.setDevice(this);
//          this.mSourceChannelList.add(localChannelInfo1);
//        }
//        label739: ChannelInfo localChannelInfo6 = (ChannelInfo)this.mSourceChannelList.get(i2);
//        if (localNET_DVR_IPPARACFG_V40.byAnalogChanEnable[i2] != 0);
//        for (boolean bool2 = true; ; bool2 = false)
//        {
//          localChannelInfo6.setEnable(bool2);
//          i2++;
//          break;
//        }
//        label786: if (i5 + this.mDeviceInfoV30.byChanNum < i4)
//        {
//          ChannelInfo localChannelInfo8 = (ChannelInfo)this.mSourceChannelList.get(i5 + this.mDeviceInfoV30.byChanNum);
//          localChannelInfo8.setChannelType(0);
//          localChannelInfo8.setChannelNo(i5 + localNET_DVR_IPPARACFG_V40.dwStartDChan);
//          localChannelInfo8.setDeviceID(this.mID);
//          localChannelInfo8.setDevice(this);
//          if (localNET_DVR_IPPARACFG_V40.struIPChanInfo[i5].byEnable != 0);
//          for (boolean bool4 = true; ; bool4 = false)
//          {
//            localChannelInfo8.setEnable(bool4);
//            i5++;
//            break;
//          }
//        }
//        Object[] arrayOfObject2 = new Object[1];
//        arrayOfObject2[0] = Integer.valueOf(i5 + 1);
//        String str3 = String.format("%02d", arrayOfObject2);
//        if (localNET_DVR_IPPARACFG_V40.struIPChanInfo[i5].byEnable != 0);
//        for (boolean bool3 = true; ; bool3 = false)
//        {
//          int i7 = i5 + localNET_DVR_IPPARACFG_V40.dwStartDChan;
//          ChannelInfo localChannelInfo7 = new ChannelInfo(this.mID, str3, 0, i7, bool3);
//          localChannelInfo7.setDevice(this);
//          this.mSourceChannelList.add(localChannelInfo7);
//          break;
//        }
//        label992: bool1 = false;
//        break label386;
//        label998: String str2 = GlobalApplication.getInstance().getResources().getString(2131296320);
//        ChannelInfo localChannelInfo4 = new ChannelInfo(this.mID, str2, 3, 1, bool1);
//        localChannelInfo4.setDevice(this);
//        localChannelInfo4.setStreamType(0);
//        this.mSourceChannelList.add(localChannelInfo4);
//        break label487;
//      }
//      ChannelInfo localChannelInfo3 = (ChannelInfo)localIterator.next();
//      if (!localChannelInfo3.isEnable())
//        continue;
//      localArrayList.add(localChannelInfo3);
//    }
//  }
//  this.mLoginCount = (1 + this.mLoginCount);
//  Log.i("TEST_DEBUG", "TEST_DEBUG  " + getName() + " 登陆计数： " + this.mLoginCount);
//  return DEVICE_STATUS_ENUM.READY;
//}