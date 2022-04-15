package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;


public interface Dao<T> {
	public Connection connection = BddConnection.getInstance().getConnection();
	public void create(T obj);		//ajout d'une nouvelle occurence en base
	public T read(int id);			//renvoie un objet correspondant à l'id en base
	public boolean update(T obj); 	//met à jour l'objet ien base, renvoi vrai si c'est fait
	public boolean delete(int id); 	// supprime l'objet ien base, renvoi vrai si c'est fait
	public ArrayList<T> readAll();	//rencoi tous les objet de la table correspondante
	
	
}
