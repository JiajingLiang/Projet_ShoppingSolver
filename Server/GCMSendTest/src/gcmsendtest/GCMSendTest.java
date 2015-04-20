/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcmsendtest;
import com.google.android.gcm.server.Message;  
import com.google.android.gcm.server.Result;  
import com.google.android.gcm.server.Sender;  

/**
 *
 * @author Jiajing
 */
public class GCMSendTest {

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GCMSendTest send = new GCMSendTest();
		send.sendMessage("Hello world");
	}

	public void sendMessage(String msg){
		try{
			String serverKey = "AIzaSyAoC-eRbxY9uJyEbCaj3xeVqA7wERAxphk";		//serverkey 就是你在API Project中API Access创建server key之后得到的API Key
			String regId = "APA91bFDZmR7LCYAgtwPhdfAJyE6rvV3fGQ8AnJkQEQo4RwIZl7yuF8epfsKBXqvx_gLrc91irMf8Xv17XsOgHXiVWKLppulX2VmXQrD28WvQ6m3SBC3qto_EYkSBGjI-y9hEsf5PO9KOI4LgWUBtdGjLzFTb6qEvw";	// 你的设备中的app向GCM注册得到的值,  即 GCMRegistrar.getRegistrationId(this);
			Sender sender = new Sender(serverKey);
			Message message = new Message.Builder().addData("shopAddress", "3820 Boul de la Cote Vertu,Saint-Laurent,H4R 1P8,Canada")
                                .addData("productDescription", "Lactantia skim milk 1L")
                                .addData("newPrice", "2.19").build();
			Result result = sender.send(message, regId, 5);
			String status = "Sent message to one device: " + result;
			System.out.println(status);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
}
