/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinica.views;

import clinica.models.Cliente;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.sql.Time;

/**
 * View para a agenda de um cliente.
 */
public class AgendaClienteView extends JPanel {
	private static final long serialVersionUID = 1L;

	AgendaClienteView(Cliente cliente) {
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(new BorderLayout(15, 15));

		JPanel tituloPanel = new JPanel();
		tituloPanel.setLayout(new GridLayout(1, 2, 0, 0));

		JLabel titulo = new JLabel("Agenda " + cliente.getNome());
		titulo.setAlignmentX(CENTER_ALIGNMENT);
		tituloPanel.add(titulo);

		this.add(tituloPanel, BorderLayout.NORTH);

		String aux = "Horário,";
		aux = aux.concat(cliente.getAgenda().getCargaHoraria());
		String[] titulos = aux.split(",");

		int intervalo = cliente.getAgenda().getTempoIntervalo();
		int n_linhas = (60 / intervalo) * 13;
		Object[][] dados = new Object[n_linhas][titulos.length];
		dados[0][0] = cliente.getAgenda().getHoraInicio();
		for (int j = 1; j < n_linhas; j++) {
			dados[j][0] = new Time(cliente.getAgenda().getHoraInicio().getTime() + intervalo * j * 60 * 1000);
		}
		JTable clientesTable = new JTable(dados, titulos);
		clientesTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(clientesTable);
		clientesTable.setFillsViewportHeight(true);
		this.add(scrollPane, BorderLayout.CENTER);

		// rodape com botao voltar
		JPanel rodapePanel = new JPanel();
		rodapePanel.setLayout(new GridLayout(1, 2, 0, 0));
		JButton voltarButton = new JButton("Voltar");
		voltarButton.addActionListener((ActionEvent e) -> {
			Router.getInstance().goToView(new ClientesView());
		});
		rodapePanel.add(voltarButton);
		this.add(rodapePanel, BorderLayout.SOUTH);
	}
}
