package com.ltmcp.test;
import java.net.InetAddress;

public class Kkkk { 
 public static void main(String args[]) throws Exception { 
 InetAddress address = InetAddress.getByName("192.168.0.104");// wxh-PC是我的计算机名 
 System.out.println(address); 
 System.out.println("-----"); 
 InetAddress address1 = InetAddress.getLocalHost(); 
 System.out.println(address1); 
   
   
 InetAddress[] addresses = InetAddress 
  .getAllByName("www.91exiu.com"); 
 System.out.println(addresses.length); 
 for (InetAddress addr : addresses) { 
  System.out.println(addr); 
 } 
 } 
} 