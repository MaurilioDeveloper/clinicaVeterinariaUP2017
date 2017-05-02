package com.up.clinicaveterinaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.up.clinicaveterinaria.jdbc.ConnectionFactory;
import com.up.clinicaveterinaria.model.Animal;

public class AnimalDAO implements IGenericDAO<Animal, Long>{

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	
	@Override
	public List<Animal> listar() throws Exception {
		List<Animal> retorno = new ArrayList<Animal>();
		Connection con=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Exception exp = null;
		try{
			con = connectionFactory.getConnection();
			String sql = "SELECT id,nome,nascimento FROM ANIMAL";
			statement = con.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				Animal a = new Animal();
				
				a.setId(resultSet.getLong("id"));
				a.setNome(resultSet.getString("nome"));
				a.setNascimento(resultSet.getDate("nascimento"));
				
				retorno.add(a);
			}
			return retorno;
		}catch(Exception e){
			exp = e;
		}finally{
			try{
				if(resultSet != null)
					resultSet.close();
			}catch(Exception e){
				exp = e;
				//logar exceção
			}
			try{
				if(statement != null)
					statement.close();
			}catch(Exception e){
				exp = e;
				//logar exceção
			}
			try{
				if(con != null)
					con.close();
			}catch(Exception e){
				exp = e;
				//logar exceção
			}
		}
		throw exp;//lançando somente a última exceção gerada para simplificar!
	}

	@Override
	public Animal find(Long id) throws Exception {
		Animal retorno = null;
		Connection con=null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Exception exp = null;
		try{
			con = connectionFactory.getConnection();
			String sql = "SELECT nome,nascimento FROM ANIMAL where id=?";
			statement = con.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				retorno = new Animal();
				
				retorno.setId(id);
				retorno.setNome(resultSet.getString("nome"));
				retorno.setNascimento(resultSet.getDate("nascimento"));
			}
			return retorno;
		}catch(Exception e){
			exp = e;
		}finally{
			try{
				if(resultSet != null)
					resultSet.close();
			}catch(Exception e){
				exp = e;
				//logar exceção
			}
			try{
				if(statement != null)
					statement.close();
			}catch(Exception e){
				exp = e;
				//logar exceção
			}
			try{
				if(con != null)
					con.close();
			}catch(Exception e){
				exp = e;
				//logar exceção
			}
		}
		throw exp;//lançando somente a última exceção gerada para simplificar!
	}

}
