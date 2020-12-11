package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.utilitarios.ConversorDeHoraEDia;
import ponto.model.projetos.DiaSemana;
import view.abstract_factory.FabricaDeTelasSwing;

import view.abstract_factory.InterfaceTelaCadastroHorarioPrevisto;
import view.controller.ControllerCadastroHorarioPrevisto;

public class TelaCadastroHorarioPrevistoSwing extends JFrame implements InterfaceTelaCadastroHorarioPrevisto {

	private ControllerCadastroHorarioPrevisto controller = new ControllerCadastroHorarioPrevisto();
	private JTextField dataPrevistaInicio;
	private JTextField dataPrevistaTermino;
	private JTextField horaPrevistaInicio;
	private JTextField horaPrevistaTermino;
	private JTextField matricularCoordenador;
	private JTextField matriculaMembro;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraTermino;
	private JComboBox<Object> op = new JComboBox<Object>();
	private JComboBox<Object> dia = new JComboBox<Object>();

	private JTextField tolerancia;

	public TelaCadastroHorarioPrevistoSwing() {
		setLayout(null);
		setSize(450, 500);
		getContentPane().setBackground(new Color(213, 213, 213));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Horario Previsto");
		adcionarLabels();
		adcionarBotao();
		adicionarJTextField();
		adcionarJCombobox();
		setVisible(true);
	}

	private void adicionarJTextField() {
		dataPrevistaInicio = new JTextField();
		MaskFormatter macaraDeData = null;
		try {
			macaraDeData = new MaskFormatter("##/##/####");
			dataPrevistaInicio = new JFormattedTextField(macaraDeData);
		} catch (ParseException e) {
		}
		dataPrevistaInicio.setToolTipText("dd/MM/YYYY");
		dataPrevistaInicio.setBounds(50, 150, 70, 20);
		this.add(dataPrevistaInicio);

		dataPrevistaTermino = new JTextField();
		dataPrevistaTermino = new JFormattedTextField(macaraDeData);
		dataPrevistaTermino.setToolTipText("dd/MM/YYYY");
		dataPrevistaTermino.setBounds(210, 150, 70, 20);
		this.add(dataPrevistaTermino);

		horaPrevistaInicio = new JTextField();
		MaskFormatter macaraDeHora = null;
		try {
			macaraDeHora = new MaskFormatter("##:##");
			horaPrevistaInicio = new JFormattedTextField(macaraDeHora);
		} catch (ParseException e) {
		}
		horaPrevistaInicio.setToolTipText("hh:MM");
		horaPrevistaInicio.setBounds(130, 150, 70, 20);
		this.add(horaPrevistaInicio);

		horaPrevistaTermino = new JTextField();
		horaPrevistaTermino = new JFormattedTextField(macaraDeHora);
		horaPrevistaTermino.setToolTipText("hh:MM");
		horaPrevistaTermino.setBounds(300, 150, 79, 20);
		this.add(horaPrevistaTermino);

		matricularCoordenador = new JTextField();
		matricularCoordenador.setBounds(50, 205, 100, 20);
		this.add(matricularCoordenador);

		matriculaMembro = new JTextField();
		matriculaMembro.setBounds(220, 205, 100, 20);
		this.add(matriculaMembro);

		tolerancia = new JTextField();
		tolerancia.setBounds(220, 255, 80, 20);
		this.add(tolerancia);

	}

	private void adcionarJCombobox() {
		Object[] projetos = mostrarProjetosDoUsuarioLogado();
		String[] nome = { "-Nenhum Projeto cadastrado-" };
		if (projetos == null || projetos.length == 0) {
			op.removeAllItems();
			op.addItem(nome[0].toString());
		} else {
			op.removeAllItems();
			for (Object object : projetos) {
				op.addItem(object.toString());
			}
		}
		op.setBounds(50, 255, 150, 20);
		add(op);

		Object[] dias = DiaSemana.values();
		for (int i = 0; i < dias.length; i++) {
			dia.addItem(dias[i].toString());
		}
		dia.setBounds(50, 300, 170, 20);
		add(dia);

	}

	private Object[] mostrarProjetosDoUsuarioLogado() {
		try {
			return controller.mostrarProjetosDoUsuarioLogado().toArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void fecharTela() {
		this.dispose();
	}

	private void adcionarBotao() {
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(50, 350, 150, 20);
		cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataHoraInicio = LocalDateTime.of(
							LocalDate.of(Integer.parseInt(dataPrevistaInicio.getText().substring(6, 10)),
									Integer.parseInt(dataPrevistaInicio.getText().substring(3, 5)),
									Integer.parseInt(dataPrevistaInicio.getText().substring(0, 2))),
							LocalTime.of(Integer.parseInt(horaPrevistaInicio.getText().substring(0, 2)),
									Integer.parseInt(horaPrevistaInicio.getText().substring(3, 5))));
					dataHoraTermino = LocalDateTime.of(
							LocalDate.of(Integer.parseInt(dataPrevistaTermino.getText().substring(6, 10)),
									Integer.parseInt(dataPrevistaTermino.getText().substring(3, 5)),
									Integer.parseInt(dataPrevistaTermino.getText().substring(0, 2))),
							LocalTime.of(Integer.parseInt(horaPrevistaTermino.getText().substring(0, 2)),
									Integer.parseInt(horaPrevistaTermino.getText().substring(3, 5))));

					adcionarHorarioTrabalhado(Long.parseLong(matricularCoordenador.getText()),
							Long.parseLong(matriculaMembro.getText()), op.getSelectedItem().toString(),
							ConversorDeHoraEDia.diaString(dia.getSelectedItem().toString()), dataHoraInicio,
							dataHoraTermino, Long.parseLong(tolerancia.getText()));
				} catch (Exception e2) {

					JOptionPane.showMessageDialog(null,
							dataHoraInicio.toString() + "    " + dataHoraTermino.toString());
				}

			}
		});
		this.add(cadastrar);

		JButton voltar = new JButton("Voltar");
		voltar.setBounds(230, 350, 150, 20);
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharTela();
				FabricaDeTelasSwing.getFabrica().fabricarTelaPrincipal();
			}
		});
		this.add(voltar);
	}

	public void adcionarHorarioTrabalhado(long matriculaCoordenador, long matriculaMembro, String nomeDoProjeto,
			DiaSemana dia, LocalDateTime dataInicio, LocalDateTime dataTermino, long toleranciaMinutos)
			throws Exception {

	}

	private void adcionarLabels() {
		JLabel novoHorarioPrevisto = new JLabel("Horario previsto");
		novoHorarioPrevisto.setBounds(100, 30, 250, 30);
		novoHorarioPrevisto.setFont(new Font("Arial", Font.BOLD, 30));
		novoHorarioPrevisto.setForeground(Color.DARK_GRAY);
		this.add(novoHorarioPrevisto);

		JLabel dataPrevistaInicio = new JLabel("Data inicio");
		dataPrevistaInicio.setBounds(50, 120, 350, 20);
		dataPrevistaInicio.setFont(new Font("Arial", Font.BOLD, 12));
		dataPrevistaInicio.setForeground(Color.DARK_GRAY);
		this.add(dataPrevistaInicio);

		JLabel horaPrevistaInicio = new JLabel("Hora inicio");
		horaPrevistaInicio.setBounds(130, 120, 350, 20);
		horaPrevistaInicio.setFont(new Font("Arial", Font.BOLD, 12));
		horaPrevistaInicio.setForeground(Color.DARK_GRAY);
		this.add(horaPrevistaInicio);

		JLabel dataPrevistaTermino = new JLabel("Data termino");
		dataPrevistaTermino.setBounds(210, 120, 350, 20);
		dataPrevistaTermino.setFont(new Font("Arial", Font.BOLD, 12));
		dataPrevistaTermino.setForeground(Color.DARK_GRAY);
		this.add(dataPrevistaTermino);

		JLabel horaPrevistaTermino = new JLabel("Hora Termino");
		horaPrevistaTermino.setBounds(300, 120, 350, 20);
		horaPrevistaTermino.setFont(new Font("Arial", Font.BOLD, 12));
		horaPrevistaTermino.setForeground(Color.DARK_GRAY);
		this.add(horaPrevistaTermino);

		JLabel matriculaCoordenadorHorario = new JLabel("Matricula Coordenador");
		matriculaCoordenadorHorario.setBounds(50, 180, 350, 20);
		matriculaCoordenadorHorario.setFont(new Font("Arial", Font.BOLD, 12));
		matriculaCoordenadorHorario.setForeground(Color.DARK_GRAY);
		this.add(matriculaCoordenadorHorario);

		JLabel matriculaMmembroAdicionarHorario = new JLabel("Matricula Membro");
		matriculaMmembroAdicionarHorario.setBounds(220, 180, 350, 20);
		matriculaMmembroAdicionarHorario.setFont(new Font("Arial", Font.BOLD, 12));
		matriculaMmembroAdicionarHorario.setForeground(Color.DARK_GRAY);
		this.add(matriculaMmembroAdicionarHorario);

		JLabel projeto = new JLabel("Selecione o projeto");
		projeto.setBounds(50, 230, 170, 20);
		projeto.setFont(new Font("Arial", Font.BOLD, 12));
		projeto.setForeground(Color.DARK_GRAY);
		this.add(projeto);

		JLabel tolerancia = new JLabel("Tolerancia em minutos");
		tolerancia.setBounds(220, 230, 170, 20);
		tolerancia.setFont(new Font("Arial", Font.BOLD, 12));
		tolerancia.setForeground(Color.DARK_GRAY);
		this.add(tolerancia);

		JLabel diaSemana = new JLabel("Dia semana");
		diaSemana.setBounds(50, 280, 170, 20);
		diaSemana.setFont(new Font("Arial", Font.BOLD, 12));
		diaSemana.setForeground(Color.DARK_GRAY);
		this.add(diaSemana);

	}

	public static void main(String[] args) {
		new TelaCadastroHorarioPrevistoSwing();
	}
}
