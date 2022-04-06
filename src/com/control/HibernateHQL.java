package com.control;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.Player;

public class HibernateHQL {

	public static void main(String[] args) {
		try {
			Configuration config = new Configuration().configure();
			
			config.addAnnotatedClass(com.Player.class);
			StandardServiceRegistryBuilder builder = 
					new StandardServiceRegistryBuilder().applySettings(config.getProperties());
			SessionFactory factory = config.buildSessionFactory(builder.build());
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			//Ex:1 retriev all data from table
			Query query = session.createQuery("from Player");
			List<Player> playerList=query.list();
			
			System.out.println("Player details:");
			for(Player p:playerList) {
				System.out.println(p);
			}
			
			// EX2: retrieve specific data
			Query query1 = session.createQuery("from Player where playerName=:name");
			query1.setString("name", "Botir");
			
			List<Player> playerList1=query1.list();
			
			System.out.println("Single Player details:");
			for(Player p:playerList1) {
				System.out.println(p);
			}
			//EX:3 search data with specific name
			Query query2 = session.createQuery("from Player where teamName=:name");
			query2.setString("name", "Regar");
				
			List<Player> playerList2=query2.list();
				
			System.out.println("Team Player details:");
			for(Player p:playerList2) {
				System.out.println(p);
			}
			//Ex:4 search specific data from table
			Query query3 = session.createQuery("from Player where age=:age order by playerName desc");
			query3.setInteger("age", 24);
				
			List<Player> playerList3=query3.list();
				
			System.out.println("Team Player age details:");
			for(Player p:playerList3) {
				System.out.println(p);
			}
			
			//Ex:5 search specific data between range from table
			Query query4 = session.createQuery("from Player where age between :age1 and :age2 order by playerName desc");
			query4.setInteger("age1", 22);
			query4.setInteger("age2", 24);
			
			List<Player> playerList4=query4.list();
				
			System.out.println("Team Player age details:");
			for(Player p:playerList4) {
				System.out.println(p);
			}
			
			
			
			
			
			transaction.commit();
			session.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}

	}

}
