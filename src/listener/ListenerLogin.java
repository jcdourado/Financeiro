package listener;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import mb.UsuarioMB;

public class ListenerLogin implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent e) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String page = ctx.getViewRoot().getViewId();
		if(!"/login.xhtml".equals(page) && !"/index.xhtml".equals(page) && !"/esqueci.xhtml".equals(page) && !"/register.xhtml".equals(page)){
			Application app = ctx.getApplication();
			UsuarioMB user = app.evaluateExpressionGet(ctx, "#{usuarioMB}", UsuarioMB.class);
			if(!user.getUsuario().isLogado()){
				NavigationHandler handler = app.getNavigationHandler();
				handler.handleNavigation(ctx, "", "login?faces-redirect=true");
				ctx.renderResponse();
			}
		}
		if ("/logout.xhtml".equals(page)) {
			Application app = ctx.getApplication();
			UsuarioMB user = app.evaluateExpressionGet(ctx, "#{usuarioMB}", UsuarioMB.class);
			user.logout();
		}
	}

	@Override
	public void beforePhase(PhaseEvent e) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
