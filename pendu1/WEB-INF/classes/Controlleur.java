

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Dictionnaire;
import modele.PenduBean;
import businessLogic.Loader;

/**
 * Servlet implementation class Controlleur
 */
@WebServlet("/Proposition")
public class Controlleur extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Création du bean Dictionnaire : Scope d'application
		Dictionnaire beanDico = (Dictionnaire)getServletContext().getAttribute("Dico");
		if(beanDico == null) 
		{
			beanDico = new Dictionnaire();
			beanDico.setDico(Loader.loadDico(getServletContext().getRealPath("dico/dico.txt")));
			getServletContext().setAttribute("Dico", beanDico);
		}
		// Création du bean PenduBean : Scope Session
		PenduBean bean = (PenduBean)request.getSession().getAttribute("PenduBean");
		if(bean == null) bean = new PenduBean();
		// S'il existe déjà, je dois tout de même regénérer le mot : cas de restart
		bean.setMot(Loader.randomMot((ArrayList<String>)beanDico.getDico()));
		bean.setLettresJouees(new ArrayList<Character>());
		boolean[] lettreTrouvees = new boolean[beanDico.getDico().size()]; // false par défaut en Java
		bean.setLettresTrouvees(lettreTrouvees);
		request.getSession().setAttribute("PenduBean", bean);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String urlVue = "index.jsp";
		String lettre = request.getParameter("lettre");
		if(lettre != null && lettre.trim() != "" && lettre.length() < 2)
		{
			char c = lettre.toLowerCase().charAt(0);
			if(c >= 'a' && c <= 'z')
			{
				PenduBean bean = (PenduBean)request.getSession().getAttribute("PenduBean");
				bean.getLettresJouees().add(c);
				bean.setLettresTrouvees(Loader.lettreTrouvees(bean.getMot(), (ArrayList<Character>)bean.getLettresJouees()));
				if(Loader.aGagne(bean.getLettresTrouvees())) urlVue += "?gagne=true";
				request.getSession().setAttribute("PenduBean", bean);
			}
			else
			{
				urlVue += "?error=Vous ne pouvez pas jouer cette lettre, choisissez entre a et z";
			}
		}
		else
		{
			urlVue += "?error=Lettre incorrecte";
		}
		request.getRequestDispatcher(urlVue).forward(request, response);
	}

}
