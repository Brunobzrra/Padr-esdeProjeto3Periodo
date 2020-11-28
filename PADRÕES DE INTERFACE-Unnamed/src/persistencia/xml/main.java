package persistencia.xml;
import java.time.LocalDateTime;
import java.util.Date;
import model.autenticacao.Membro;
import model.projetos.Participacao;
import model.projetos.Projeto;
import ponto.model.projetos.DiaSemana;
import ponto.model.projetos.HorarioPrevisto;

public class main {
	public static void main(String[] args) {
		Projeto p1= new Projeto("gdhhdfh1", 55, 22, 33, 66);
		Membro m1= new Membro(888, "Antonio", "aaaaaaa@gmail.com", "111111");
		Participacao pa1= new Participacao(new Date(11112222), 555, (short)555,(short) 66, true);
		pa1.adcionarHorarioPrevisto(new HorarioPrevisto(DiaSemana.SEG, LocalDateTime.now(), null, 30));
		DAOXMLMembroConta daoMembro= new DAOXMLMembroConta();
		DAOXMLProjetoParticipacao daoProjeto= new DAOXMLProjetoParticipacao();
		try {
			m1.adicionar(pa1);
			p1.adicionar(pa1);
			daoMembro.criar(m1);
			daoProjeto.criar(p1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
