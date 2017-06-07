/**
 * 	@file
 * 
 *
 * 
 *	@date 
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Jesse
 *
 */
public class UserDatFile
{
	private HashMap<String, String> users = new HashMap<String, String>();
	
	/**
	 * Write our users dat file to disk
	 */
	public void write()
	{
		try
		{
			FileOutputStream 	f = new FileOutputStream("users.dat");
			ObjectOutputStream 	oos = new ObjectOutputStream(f);
			oos.writeObject(users);
			oos.close();
			f.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Read the users dat file from disk
	 */
	public void read()
	{
		File file;
		
		// Read in the user text file and map each user
		try
		{
			file = new File("users.dat");
			
			if(!file.exists())
			{
				users = new HashMap<String, String>();
				users.put("admin", "admin");
				write();
			}
			else
			{
				FileInputStream 	f = new FileInputStream("users.dat");
				ObjectInputStream 	ois = new ObjectInputStream(f);
				users = (HashMap<String, String>)ois.readObject();
				ois.close();
				f.close();
			}

		}
		catch(IOException ex)
		{
			
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Check if a user exists in our hashmap
	 * @param username
	 * @return 
	 */
	public boolean userExists(String username)
	{
		return users.containsKey(username);
	}
	
	/** 
	 * adds a username/password combo to our list
	 * @param uname
	 * @param pw
	 */
	public void add(String uname, String pw)
	{
		users.put(uname, pw);
	}
	
	/**
	 * Removes a user from our list
	 */
	public void remove(String uname)
	{
		users.remove(uname);
	}
	
	/**
	 * Number of users in our list
	 * @return
	 */
	public int numUsers()
	{
		return users.size();
	}
	
	/**
	 * Get the password from a specific user
	 * @param username
	 * @return
	 */
	public String getPassword(String username)
	{
		return users.get(username);
	}
	
	/**
	 * Return all usernames in our list
	 * @return
	 */
	public Set<String> getUsernames()
	{
		return users.keySet();
	}
}
