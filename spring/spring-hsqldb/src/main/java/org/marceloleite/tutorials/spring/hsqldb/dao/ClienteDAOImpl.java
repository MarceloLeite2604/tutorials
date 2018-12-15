package org.marceloleite.tutorials.spring.hsqldb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.marceloleite.tutorials.spring.hsqldb.dao.rowmapper.ClienteRowMapper;
import org.marceloleite.tutorials.spring.hsqldb.model.Cliente;
import org.marceloleite.tutorials.spring.hsqldb.model.Script;
import org.marceloleite.tutorials.spring.hsqldb.util.BancoDadosUtil;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@Inject
	private ClienteRowMapper clienteRowMapper;

	@Named("dataSources")
	@Inject
	private List<DataSource> dataSources;

	@Inject
	private BancoDadosUtil bancoDadosUtil;

	private int dataSourceAtual = -1;

	@Override
	public Cliente obterPorId(UUID id) {

		int bancosAnalisados = 0;
		Cliente resultado = null;
		while (resultado == null && bancosAnalisados++ < BancoDadosUtil.TOTAL_BANCO_DADOS) {
			resultado = abrirConexaoBuscarClientePorId(id);
		}
		return resultado;
	}

	private Cliente abrirConexaoBuscarClientePorId(UUID id) {
		Cliente resultado = null;
		String sql = bancoDadosUtil.obterScript(Script.SELECT_CLIENTE);
		try (Connection connection = obterDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, id.toString());
			resultado = executarQueryBuscarClientePorId(preparedStatement);
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}
		return resultado;
	}

	private Cliente executarQueryBuscarClientePorId(PreparedStatement preparedStatement) {
		Cliente resultado = null;
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			int count = 0;
			while (resultSet.next()) {
				resultado = clienteRowMapper.mapRow(resultSet, ++count);
			}
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}
		return resultado;
	}

	@Override
	public long contarClientes() {
		long resultado = 0L;
		int bancosAnalisados = 0;
		while (bancosAnalisados++ < BancoDadosUtil.TOTAL_BANCO_DADOS) {
			resultado += abrirContexaoContarClientes();
		}
		return resultado;
	}

	private long abrirContexaoContarClientes() {
		long resultado = 0L;
		String sql = bancoDadosUtil.obterScript(Script.COUNT_CLIENTES);
		try (Connection connection = obterDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			resultado = executarQueryContarClientes(preparedStatement);

		} catch (SQLException excecao) {
			throw new RuntimeException("Erro na abertura da conexão para contagem de clientes.", excecao);
		}
		return resultado;
	}

	private long executarQueryContarClientes(PreparedStatement preparedStatement) throws SQLException {
		long resultado = 0;
		try (ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				resultado += resultSet.getLong("total");
			}
		}
		return resultado;
	}

	@Override
	public int inserir(Cliente cliente) {
		String sql = bancoDadosUtil.obterScript(Script.INSERT_CLIENTE);
		try (Connection connection = obterDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, cliente.getId()
					.toString());
			preparedStatement.setLong(2, cliente.getValor());
			return preparedStatement.executeUpdate();
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}
	}

	@Override
	public int inserir(List<Cliente> clientes) {
		int resultado = 0;
		int batches = 0;
		String sql = bancoDadosUtil.obterScript(Script.INSERT_CLIENTE);
		try (Connection connection = obterDataSource().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			for (Cliente cliente : clientes) {
				preparedStatement.setString(1, cliente.getId()
						.toString());
				preparedStatement.setLong(2, cliente.getValor());
				preparedStatement.addBatch();

				if (++batches != 0 && batches >= BancoDadosUtil.MAXIMO_BATCHES) {
					int[] resultadosParciais = preparedStatement.executeBatch();
					for (int contador = 0; contador < resultadosParciais.length; contador++) {
						resultado += resultadosParciais[contador];
					}
					batches = 0;
				}

			}

			return resultado;
		} catch (SQLException excecao) {
			throw new RuntimeException(excecao);
		}
	}

	private DataSource obterDataSource() {
		dataSourceAtual = ((dataSourceAtual + 1) % BancoDadosUtil.TOTAL_BANCO_DADOS);
		return dataSources.get(dataSourceAtual);
	}

}
