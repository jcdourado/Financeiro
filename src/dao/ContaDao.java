package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Conta;
import model.Usuario;

public class ContaDao {
	
	public boolean adicionar(Conta c) throws SQLException, ClassNotFoundException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "INSERT INTO CONTA(FREQUENCIA,NOME,DESCRICAO,VALOR,USUARIO,DATA) VALUES (?,?,?,?,?,?)";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, c.getFrequencia());
		pS.setString(2, c.getNome());
		pS.setString(3, c.getDescricao());
		pS.setFloat(4, c.getValor());
		pS.setString(5, c.getUsuario().getUsuario());
		pS.setDate(6, new java.sql.Date(c.getData().getTime()));
		return !pS.execute();
	}
	
	public boolean remover(int c) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "DELETE FROM CONTA WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, c);
		return !pS.execute();
	}
	
	public boolean alterar(Conta c) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "UPDATE CONTA SET FREQUENCIA = ?, NOME = ?, DESCRICAO = ?, VALOR = ?, DATA = ? WHERE ID = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setInt(1, c.getFrequencia());
		pS.setString(2, c.getNome());
		pS.setString(3, c.getDescricao());
		pS.setFloat(4, c.getValor());
		pS.setDate(5, new java.sql.Date(c.getData().getTime()));
		pS.setInt(6, c.getId());
		return !pS.execute();
	}
	
	public List<Conta> consultarPorNome(String nome, Usuario usuario) throws ClassNotFoundException, SQLException{
		Connection con = DBUtil.getDBUtil().getConnection();
		String sql = "SELECT * FROM CONTA WHERE NOME LIKE ? AND USUARIO = ?";
		PreparedStatement pS = con.prepareStatement(sql);
		pS.setString(1, "%"+nome+"%");
		pS.setString(2, usuario.getUsuario());
		ResultSet rS = pS.executeQuery();
		List<Conta> contas = new ArrayList<Conta>();
		while(rS.next()){
			Conta c = new Conta();
			c.setFrequencia(rS.getInt("FREQUENCIA"));
			c.setNome(rS.getString("nome"));
			c.setDescricao(rS.getString("descricao"));
			c.setUsuario(usuario);
			c.setValor(rS.getFloat("valor"));
			c.setId(rS.getInt("ID"));
			c.setData(rS.getDate("DATA"));
			contas.add(c);
		}
		return contas;
	}
	
	public List<Conta> todasContas(Usuario usuario) throws ClassNotFoundException, SQLException{
		return consultarPorNome("", usuario);
	}
}
