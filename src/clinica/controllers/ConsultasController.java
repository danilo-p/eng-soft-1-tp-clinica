package clinica.controllers;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import clinica.dao.ConsultaDAO;
import clinica.models.Cliente;
import clinica.models.Consulta;
import clinica.models.Medico;

/**
 * Regras de negócio para operações relacionadas a consultas.
 */
public class ConsultasController {

	private final ConsultaDAO consultaDAO;

	public ConsultasController() {
		this.consultaDAO = new ConsultaDAO();
	}

//	Cria uma nova consulta para o médico e o cliente cadastrados
	public Consulta criarConsulta(String dataHoraConsulta, Medico medico, Cliente cliente) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp data = new Timestamp(sdf.parse(dataHoraConsulta).getTime());
			Consulta novaConsulta = new Consulta(data, medico, cliente);
			this.consultaDAO.salvar(novaConsulta);
			return novaConsulta;
		} catch (ParseException ex) {
			System.out.println("ConsultasController: Falha ao converter data.");
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println("ConsultasController: Falha ao salvar consulta.");
			System.out.println(ex);
		}

		return null;
	}

//	Pesquisa consultas para o médico e o intevalo de tempo dado
	public List<Consulta> getByMedico(Medico medico, Timestamp inicio, Timestamp fim) {
		try {
			return this.consultaDAO.findByMedico(medico, inicio, fim);
		} catch (SQLException ex) {
			System.out.println("MedicosController: Falha ao recuperar medicos.");
			System.out.println(ex);
			return new ArrayList<>();
		}
	}

}
