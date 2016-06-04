package br.com.claudio.drogaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.claudio.drogaria.bean.AutenticacaoBean;
import br.com.claudio.drogaria.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		//System.out.println("Depois da fase " + event.getPhaseId());
		
		//pegando a pagina atual 
		String paginaAtual = Faces.getViewId();
		
		boolean ehPaginadeAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		if(!ehPaginadeAutenticacao){
			//pegando os atributos da tela de login(ManagedBean)
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
			
			//verifica se ja entrou na tela de autenticação
			if(autenticacaoBean == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
			
			//verifica se o usuario é nulo(não logou)
			Usuario usuario = autenticacaoBean.getUsuarioLogado();
			if(usuario == null){
				Faces.navigate("/pages/autenticacao.xhtml");
				return;
			}
		}
		
		
		
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		//System.out.println("antes da fase " + event.getPhaseId());
		
	}

	@Override
	//quais fases serão ouvidas
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
}
