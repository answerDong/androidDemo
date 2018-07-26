package com.appsino.bingluo.databingtest.Utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.location.LocationManager;
import android.media.AudioFormat;
import android.media.MediaRecorder.AudioSource;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("SimpleDateFormat")
public class Utils {
	public static final String TAG = "PushDemoActivity";
	public static final String RESPONSE_METHOD = "method";
	public static final String RESPONSE_CONTENT = "content";
	public static final String RESPONSE_ERRCODE = "errcode";
	protected static final String ACTION_LOGIN = "com.baidu.pushdemo.action.LOGIN";
	public static final String ACTION_MESSAGE = "com.baiud.pushdemo.action.MESSAGE";
	public static final String ACTION_RESPONSE = "bccsclient.action.RESPONSE";
	public static final String ACTION_SHOW_MESSAGE = "bccsclient.action.SHOW_MESSAGE";
	protected static final String EXTRA_ACCESS_TOKEN = "access_token";
	public static final String EXTRA_MESSAGE = "message";
	public static int luyinqudao = AudioFormat.CHANNEL_CONFIGURATION_DEFAULT;
	public static int luyinlaiyuan = AudioSource.DEFAULT;
	public static String called_num;
	public static Long xxxx;
	public static int x = 0;
	public static boolean app_living=true;
	public static boolean caller_login=false;
    public static String Num;
    public static boolean CommentsSign=false;
    public static Boolean PicOrMp4=false;
    private static long lastClickTime;
    private static boolean resetText;
    private static String inputAfterText;
	public static boolean isupload =false;
	public static boolean showthreeyear = true;
    private static int cursorPos;
    public static String Ca_path=null;
	
	// 获取AppKey
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String apiKey = null;
        if (context == null || metaKey == null) {
        	return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
            	apiKey = metaData.getString(metaKey);
            }
        } catch (NameNotFoundException e) {

        }
        return apiKey;
    }
    //判断是否为中文名字
    public static boolean checkNameChese(String name) {

    	boolean res = true;

    	char[] cTemp = name.toCharArray();

    	for (int i = 0; i < name.length(); i++) {

    	if (!isChinese(cTemp[i])) {

    	res = false;

    	break;

    	}

    	}

    	return res;

    	}
    	public static boolean isChinese(char c) {

    	Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

    	if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
    	return true;
    	}
    	return false;
    	}
    	public static void ToastSign(Context context, String msg){
    		Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    	}
    	public static boolean checkInfoNumber(String msg){
    		 boolean b=true;
    		 
    		 if(msg.length()==18){
    			 try {
    				 xxxx= Long.parseLong(msg.substring(0,17));
    			} catch (Exception e) {
    				System.out.println(e);
    				 b=false;
    				 System.out.println(b);
    				 return b;
    				// TODO: handle exception
    			}	
    		    	try {
    		    	     Num=msg.substring(17,18);
    		    	     System.out.println(Num);
    		    	      x= Integer.parseInt(msg.substring(17,18));
    		    	      System.out.println(Num);
    		    	     if(x==0){
    		    	    	 if(Num.equals("0")==true){
    		    	    		 b=true;
    		    	    	 }
    		    	    	 else if(Num.equals("x")==true||Num.equals("X")==true){
    		    	    		 b=true;
    		    	    	 }
    		    	     }
    		    	      System.out.println(x);
    		    	} catch (Exception e) {
    		    		// TODO: handle exception
    		    		System.out.println(e);
    		    	  
    		    		if(x==0){
    		    	    	 if(Num.equals("0")==true){
    		    	    		 b=true;
    		    	    	 }
    		    	    	 else if(Num.equals("x")==true||Num.equals("X")==true){
    		    	    		 b=true;
    		    	    	 }else{
    		    	    		 b=false;
    		    	    	 }
    		    	     }
    		    		System.out.println(x);
    		    	}
    		    	System.out.println(xxxx);
    		    }else{
    		    	
    		    	b=false;
    		    }
    		 System.out.println(b);
    		 return b;
    	}
    	//手机号码正则
    	 public static boolean isPhoneNumberValid(String phoneNumber) {
//    	        boolean isValid = false;
//    	        CharSequence inputStr = phoneNumber;
//    	        //正则表达式
//    	            
//    	        String phone="^1[34578]\\d{9}$" ;
//    	      
//    	      
//    	        Pattern pattern = Pattern.compile(phone);
//    	        Matcher matcher = pattern.matcher(inputStr);
//    	         
//    	       
//    	        if(matcher.matches()) {
//    	            isValid = true;
//    	        }
//    	        return isValid;
    		 		if(phoneNumber.length()>=10&&phoneNumber.length()<=11){
    		 		}else{
    		 			return false;
    		 		}
    			  Pattern pattern = Pattern.compile("[0-9]*");
    			  return pattern.matcher(phoneNumber).matches();
    			 
    	    }
    	/**
    	 * 验证身份证是否正确
    	 */
    	public static boolean isCardNo(String card) {
    		String pat = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9Xx])";
    		Pattern pattern = Pattern.compile(pat);
    		Matcher mat = pattern.matcher(card);
    		return mat.matches();
    	}
    	  public static String ToSBC(String input) {
  	        char c[] = input.toCharArray(); 
  	        for (int i = 0; i < c.length; i++) { 
  	            if (c[i] == ' ') { 
  	                c[i] = '\u3000'; 
  	            } else if (c[i] < '\177') { 
  	                c[i] = (char) (c[i] + 65248); 
  	            } 
  	        } 
  	        return new String(c);
  	    } 
    	  public static boolean isAppOnForeground(Context context) {
    	         // Returns a list of application processes that are running on the  
    	         // device  
    	            
    	         ActivityManager activityManager = (ActivityManager)context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
    	         String packageName = context.getApplicationContext().getPackageName();

    	         List<RunningAppProcessInfo> appProcesses = activityManager
    	                         .getRunningAppProcesses();  
    	         if (appProcesses == null)  
    	                 return false;  

    	         for (RunningAppProcessInfo appProcess : appProcesses) {
    	                 // The name of the process that this object is associated with.  
    	                 if (appProcess.processName.equals(packageName)  
    	                                 && appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
    	                         return true;  
    	                 }  
    	         } 
    	         return false;  
    	 }
//    	  public static void goGesture(Context context){
//    			 if (!Utils.app_living) {
//				  //app 从后台唤醒，进入前台
//				  //在这里进行退出app操作
//				  if(Utils.caller_login==true){
//					  return;
//				  }
////					 SharedPreferences mySharedPreferences1=context.getSharedPreferences("gesture",Activity.MODE_PRIVATE);
////					 String y=mySharedPreferences1.getString("gesture_on","");
//				  SharedPreferences mySharedPreferences1= PreferenceManager.getDefaultSharedPreferences(context);
//				  String y=mySharedPreferences1.getString(AppContext.getCurrentUser(context).getMobileNo(),"");
//				  SharedPreferences mySharedPreferences= context.getSharedPreferences("gesture_open", Activity.MODE_PRIVATE);
//				  String x=mySharedPreferences.getString("gesture_opensign","");
//				  if(!x.equals("")&&!y.equals("")){
//					  Utils.app_living = true;
//					  Intent intent=new Intent(context, ActivityGongUnlock.class);
//					  intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//					  intent.putExtra("value","1");
//					  context.startActivity(intent);
//				  }
//			  }
//    	  }
    	  /** 
    	   * make true current connect service is wifi 
    	   * @param mContext 
    	   * @return 
    	   */
    	  public static boolean isWifi(Context mContext){
    		  ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    		  NetworkInfo activieNetInfo = connectivityManager.getActiveNetworkInfo();
    		  if(activieNetInfo != null && activieNetInfo.getType() == ConnectivityManager.TYPE_WIFI){
    			  return true;
    		  }
    		  return false;
    	  }
    	  public static void returnFW(Context context){
//    		  	if(PicOrMp4==true){//当进入到图片或者视频后回来还会打开手势解锁
//    		  		PicOrMp4=false;
//    			  return;
//    		  	}
    			if (!Utils.isAppOnForeground(context)) {  
    	            //app 进入后台  
    				Utils.app_living=false;
    	            //全局变量isActive = false 记录当前已经进入后台  
    	    } 
    	  }
//    	@TargetApi(Build.VERSION_CODES.KITKAT)
//		@SuppressLint("InlinedApi")
//    	//沉浸栏暂时保留
//		public static void changerActionBar(Activity context){
//    			if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
//    	            //透明状态栏
//    				context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    	            //透明导航栏
//    				context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//    	          //判断当前SDK版本号，如果是4.4以上，就是支持沉浸式状态栏的
//    	   			SystemBarTintManager tintManager = new SystemBarTintManager(context);
//    	   		    // enable status bar tint
//    	   		    tintManager.setStatusBarTintEnabled(true);
//    	   		    // enable navigation bar tint
//    	   		    tintManager.setNavigationBarTintEnabled(true);
//    	   		    tintManager.setTintColor(Color.parseColor("#436EEE"));
////    	   		    tintManager.setTintColor(Color.RED);
////    	   		    tintManager.setNavigationBarTintResource(R.drawable.action_bar);
//    			}
//    	  }
    	  //存值 如果点击到了消息里的按钮
    	  public static void setInfoValue(Context context, String value){
    	    SharedPreferences mySharedPreferences1= context.getSharedPreferences("infoValue", Activity.MODE_PRIVATE);
  			SharedPreferences.Editor sp1 = mySharedPreferences1.edit();
//  			sp1.putString(AppContext.user1.mobileNo,value);
  			sp1.commit();
    	  }
//    	  //取值判断消息里按钮是否点击
//    	  public static String getInfoValue(Context context){
//    		SharedPreferences mySharedPreferences1= context.getSharedPreferences("infoValue", Activity.MODE_PRIVATE);
//  			String value=mySharedPreferences1.getString(AppContext.user1.mobileNo,"");
//			return value;
//    	  }
//    	  public static void setInfoluckydraw(Context context, String value){
//    	    SharedPreferences mySharedPreferences1= context.getSharedPreferences("luckydraw", Activity.MODE_PRIVATE);
//  			SharedPreferences.Editor sp1 = mySharedPreferences1.edit();
//  			sp1.putString(AppContext.user1.mobileNo,value);
//  			sp1.commit();
//    	  }
//    	  //判断是否点击活动
//    	  public static String getInfoluckydraw(Context context){
//    		SharedPreferences mySharedPreferences1= context.getSharedPreferences("luckydraw", Activity.MODE_PRIVATE);
//  			String value=mySharedPreferences1.getString(AppContext.user1.mobileNo,"");
//			return value;
//    	  }
    	  //判断是否存在友盟消息
    	  public static void setUmenginformation(Context context, String title, String value){
      	    SharedPreferences mySharedPreferences1= context.getSharedPreferences("umenginfo", Activity.MODE_PRIVATE);
    			SharedPreferences.Editor sp1 = mySharedPreferences1.edit();
    			String umtitle=mySharedPreferences1.getString("umengtitle", "").toString();
    			String umvalue=mySharedPreferences1.getString("umengvalue", "");
    			if(!umvalue.equals("")){
    				sp1.putString("umengtitle",umtitle+","+title);
        			sp1.putString("umengvalue",umvalue+","+value);
        			sp1.commit();
    			}else{
    			sp1.putString("umengtitle",title);
    			sp1.putString("umengvalue",value);
    			sp1.commit();
    			}
      	  }
    	  public static String getUmenginformation(Context context){
    			SharedPreferences mySharedPreferences1= context.getSharedPreferences("umenginfo", Activity.MODE_PRIVATE);
      			String value=mySharedPreferences1.getString("umengvalue","");
      			return value;
    	  }
    	  public synchronized static boolean isFastClick() {

    	        long time = System.currentTimeMillis();
    	        if ( time - lastClickTime < 2000) {
    	            return true;
    	        }
    	        lastClickTime = time;
    	        return false;
    	    }
    	  //对事件的判定方法
    	  public static String realTime(String startTime){
    		int YTime= Integer.parseInt(startTime.substring(0,4));
  			int MTime= Integer.parseInt(startTime.substring(6,8));
  			int DTime= Integer.parseInt(startTime.substring(10,12));
    		return startTime;
    	  }
			public static String formatTimer(int time) {
    			time = time / 1000;
    			// time = (time+500)/1000;
    			if (time < 60) {
    				return time < 10 ? "00:0" + time : "00:" + time;
    			}
    			if (time < 60 * 60) {
    				int mt = time / 60;
    				String m = mt < 10 ? "0" + mt : mt + "";
    				int st = time % 60;
    				String s = st < 10 ? "0" + st : st + "";
    				return m + ":" + s;
    			}
    			if (time < 60 * 60 * 24) {
    				int ht = time / (60 * 60);
    				String h = ht < 10 ? "0" + ht : ht + "";
    				int mt = time % (60 * 60) / 60;
    				String m = mt < 10 ? "0" + mt : mt + "";
    				int st = time % (60 * 60 * ht)%60;
    				String s = st < 10 ? "0" + st : st + "";
    				return h + ":" + m + ":" + s;
    			}
    			return "";
    		}
			/**
			   * 获取application中指定的meta-data
			   * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
			   */
			  public static String getAppMetaData(Context ctx, String key) {
				  key="518cca0b56240bb20d00197a";
			      if (ctx == null || TextUtils.isEmpty(key)) {
			          return null;
			      }
			      String resultData = null;
			      try {
			          PackageManager packageManager = ctx.getPackageManager();
			          if (packageManager != null) {
			              ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
			              if (applicationInfo != null) {
			                  if (applicationInfo.metaData != null) {
			                      resultData = applicationInfo.metaData.getString(key);
			                  }
			              }

			          }
			      } catch (NameNotFoundException e) {
			          e.printStackTrace();
			      }

			      return resultData;
			  }
			  //邮箱格式验证
			  public static boolean isValidEmail(String mail) {
				  Pattern pattern = Pattern
				    .compile("^[A-Za-z0-9][\\w\\._]*[a-zA-Z0-9]+@[A-Za-z0-9-_]+\\.([A-Za-z]{2,4})");
				  Matcher mc = pattern.matcher(mail);
				  return mc.matches();
				 }
			  //字母数字正则
			  public static boolean isZiorNum(String text) {
				  Pattern pattern = Pattern
				    .compile("^[0-9a-zA-Z]{15,18}$");
				  Matcher mc = pattern.matcher(text);
				  return mc.matches();
				 }
			  /**
			     * 单位换算
			     *
			     * @param size 单位为B
			     * @param isInteger 是否返回取整的单位
			     * @return 转换后的单位
			     */
			    private static DecimalFormat fileIntegerFormat = new DecimalFormat("#0");
			    private static DecimalFormat fileDecimalFormat = new DecimalFormat("#0.#");
			    public static Boolean formatFileSize() {
			    	 File path = Environment.getDataDirectory();
			         StatFs stat = new StatFs(path.getPath());
			         long blockSize = stat.getBlockSize();
			         long availableBlocks = stat.getAvailableBlocks();
			         long size=blockSize*availableBlocks;
			          Boolean isInteger=true;
			        DecimalFormat df = isInteger ? fileIntegerFormat : fileDecimalFormat;
			        String fileSizeString = "0M";
			        if (size < 1024 && size > 0) {
			            fileSizeString = df.format((double) size) + "B";
			        } else if (size < 1024 * 1024) {
			            fileSizeString = df.format((double) size / 1024) + "K";
			        } else if (size < 1024 * 1024 * 1024) {
			            fileSizeString = df.format((double) size / (1024 * 1024))+"M";
			        } else {
			            fileSizeString = df.format((double) size / (1024 * 1024 * 1024)) + "G";
			        }
			        System.out.println("========================存储空间"+fileSizeString);
			        String bitfileSizeString=fileSizeString.substring(fileSizeString.length()-1,fileSizeString.length()).toString();
			        if(!bitfileSizeString.equals("G")){
			        	if(bitfileSizeString.equals("M")){
			        		String remmainspace=fileSizeString.substring(0, fileSizeString.length()-1);
			        		int remainspace= Integer.parseInt(remmainspace);
			        		if(remainspace<80){
			        			return false;
			        		}else{
			        			return true;
			        		}
			        	}else{
			        		return false;
			        	}
			        }else{
			        	return true;
			        }
			    }
			    //判断文件的大小
				public static boolean fileIsNull(String path) {
					File file = new File(path);
					return file.length() <1024? true:false;
				}
				//对时间进行计算
				public static String formatDateTime(String startTime, int second1, int minute1){
//					startTime="2016-12-31 23:59:54";
					String endTime=null;
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date nowDate = null;
					 try {
				            nowDate = df.parse(startTime);
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
					long snumber=minute1 * 60 * 1000+ second1*1000;
					long newTime=nowDate.getTime()+snumber;
					Date newDate2 = new Date(newTime);
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        endTime = simpleDateFormat.format(newDate2);
					return endTime;
				}
				//处理计算的时间
				private static String formatTime(int num){
					String n = String.valueOf(num);
					if(n.length()==1){
						n = "0"+n;
					}
					return n;
				}
				public static void TextUtils(final EditText view, final int num){
					view.addTextChangedListener(new TextWatcher() {

						@Override
						public void onTextChanged(CharSequence s, int start, int before, int count) {
							// TODO Auto-generated method stub
							if (!resetText) {
								if(before != 0){

									return;

									}
								if (count >= 2) {// 表情符号的字符长度最小为2
									CharSequence input = s.subSequence(cursorPos, cursorPos + count);
									if (containsEmoji(input.toString())) {
										resetText = true;
										// 是表情符号就将文本还原为输入表情符号之前的内容
										view.setText(inputAfterText);
										CharSequence text = view.getText();
										if (text instanceof Spannable) {
											Spannable spanText = (Spannable) text;
											Selection.setSelection(spanText, text.length());
										}
									}
								}
							} else {
								resetText = false;
							}
							Editable editable = view.getText();
					        int len = editable.length();
					        if(len > num)
					        {
					            int selEndIndex = Selection.getSelectionEnd(editable);
					            String str = editable.toString();
					            //截取新字符串
					            String newStr = str.substring(0,num);
					            view.setText(newStr);
					            editable = view.getText();
					            //新字符串的长度
					            int newLen = editable.length();
					            //旧光标位置超过字符串长度
					            if(selEndIndex > newLen)
					            {
					                selEndIndex = editable.length();
					            }
					            //设置新光标所在的位置
					            Selection.setSelection(editable, selEndIndex);
					        }
						}
						@Override
						public void beforeTextChanged(CharSequence s, int start, int count,
                                                      int after) {
							// TODO Auto-generated method stub
							if (!resetText) {
								cursorPos = view.getSelectionEnd();
								// 这里用s.toString()而不直接用s是因为如果用s，
								// 那么，inputAfterText和s在内存中指向的是同一个地址，s改变了，
								// inputAfterText也就改变了，那么表情过滤就失败了
								inputAfterText = s.toString();
							}

						}

						@Override
						public void afterTextChanged(Editable s) {
							// TODO Auto-generated method stub

						}
					});

				}
				/**
				 * 检测是否有emoji表情
				 *
				 * @param source
				 * @return
				 */
				public static boolean containsEmoji(String source) {
					int len = source.length();
					for (int i = 0; i < len; i++) {
						char codePoint = source.charAt(i);
						if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
							return true;
						}
					}
					return false;
				}
				/**
				 * 判断是否是Emoji
				 *
				 * @param codePoint
				 *            比较的单个字符
				 * @return
				 */
				private static boolean isEmojiCharacter(char codePoint) {
					return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
							|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
							|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
				}

//				public final class MIUIUtils {
//
//					private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
//					private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
//					private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
//
//					public boolean isMIUI() {
//						try {
//							final BuildProperties prop = BuildProperties.newInstance();
//							return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
//									|| prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
//									|| prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
//						} catch (final Exception e) {
//							return false;
//						}
//					}
//				}
				public static void deleteFile(File file) {
					if (file.exists()) { // 判断文件是否存在
					if (file.isFile()) { // 判断是否是文件
					file.delete(); // delete()方法 你应该知道 是删除的意思;
					} else if (file.isDirectory()) { // 否则如果它是一个目录
					File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
					for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
					}
					}
					file.delete();
					} else {
//					Constants.Logdada("文件不存在！"+"\n");
					}
					}
//				//友盟的插图
//				public static void UmengPic(Activity context, String tag){
//					InAppMessageManager.getInstance(context).showCardMessage(context, tag,
//						       new IUmengInAppMsgCloseCallback() {
//						             //插屏消息关闭时，会回调该方法
//						             @Override
//						             public void onColse() {
//						                  UmLog.i(TAG, "card message close");
//						        }
//						});
//				}
				/**
			     * 检测当的网络（WLAN、3G/2G）状态
			     * @param context Context
			     * @return true 表示网络可用
			     */
			    public static boolean isNetworkAvailable(Context context) {
			        ConnectivityManager connectivity = (ConnectivityManager) context
			                .getSystemService(Context.CONNECTIVITY_SERVICE);
			        if (connectivity != null) {
			            NetworkInfo info = connectivity.getActiveNetworkInfo();
			            if (info != null && info.isConnected())
			            {
			                // 当前网络是连接的
			                if (info.getState() == NetworkInfo.State.CONNECTED)
			                {
			                    // 当前所连接的网络可用
			                    return true;
			                }
			            }
			        }
			        return false;
			    }
			    /**
			     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
			     * @param context
			     * @return true 表示开启
			     */
			    public static final boolean isOPen(final Context context) {
			        LocationManager locationManager
			                                 = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
			        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
			        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
//			        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			        if (gps) {
			            return true;
			        }

			        return false;
			    }
			    public static boolean zhengjuTime(Context context){
			    	SharedPreferences sp = context.getSharedPreferences("find_time", Context.MODE_PRIVATE);
			    	String recordTime = MessageFormat.format("{0,date,yyyyMMddHHmmss}",new Object[] { new Date(System.currentTimeMillis()) });
			    	long agetime = Long.parseLong(recordTime);
			    	long age = sp.getLong("findtime", 0);
			    	if(age == 0){
			    		SharedPreferences sp1 = context.getSharedPreferences("find_time", Context.MODE_PRIVATE);
			    		sp1.edit().putLong("findtime",agetime).commit();
			    		return false;
			    	}else{

			    	if(agetime - age >= 86400){
//			    		if(agetime - age >= 50){
			    		//当大于1天  显示并且更新值
			    		SharedPreferences sp2 = context.getSharedPreferences("find_time", Context.MODE_PRIVATE);
			    		sp2.edit().putLong("findtime",agetime).commit();
			    		return true;
			    	}else{

			    		return false;
			    	}
			    	}

			    }
			    public static  int getStatusBarHeight(Context context) {
			        int result = 0;
			        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
			        if (resId > 0) {
			            result = context.getResources().getDimensionPixelSize(resId);
			        }
			        return result;
			    }
	//沉浸式状态栏
	public static void setTransparentStatusBar(Activity activity) {
		//5.0及以上
		if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
			Utils.setStatusBarUpperAPI21(activity);
			//4.4到5.0
		} else if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
		}
	}
	/**
	 * 思路:直接设置状态栏的颜色
	 */
	@SuppressWarnings("deprecation")
	@TargetApi(VERSION_CODES.LOLLIPOP)
	private static void setStatusBarUpperAPI21(Activity activity) {
		View decorView = activity.getWindow().getDecorView();
		int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
		decorView.setSystemUiVisibility(option);
		activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
	}
	public static  void backgroundAlpha(float bgAlpha,Activity activity)
	{
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = bgAlpha; //0.0-1.0
		activity.getWindow().setAttributes(lp);
	}
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 判断qq是否可用
	 *
	 * @param context
	 * @return
	 */
	public static boolean isQQClientAvailable(Context context) {
		final PackageManager packageManager = context.getPackageManager();
		List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
		if (pinfo != null) {
			for (int i = 0; i < pinfo.size(); i++) {
				String pn = pinfo.get(i).packageName;
				if (pn.equals("com.tencent.mobileqq")) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isMobileNo(String mobiles) {
		Pattern p = Pattern.compile("^0{0,1}1[2|3|4|5|6|7|8|9][0-9]{9}$|^0[0-9]{11}$|^0[0-9]{9}$|^0[0-9]{10}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
}
