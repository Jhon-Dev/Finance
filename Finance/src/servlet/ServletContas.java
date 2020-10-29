package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Contas;
import dao.DaoContas;


@WebServlet("/servletContas")
@MultipartConfig
public class ServletContas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private DaoContas daoContas = new DaoContas();
	
    public ServletContas() {
        super();
        
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String contas = request.getParameter("contas");
			
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");

			if (acao.equalsIgnoreCase("delete")) {
				//daoContas.delete(contas);	
				
				request.setAttribute("contas", daoContas.listar());
				
			} else if (acao.equalsIgnoreCase("editar")) {
				//contas beanCursoJsp = daoContas.consultar(contas);				
			//	request.setAttribute("contas", beanCursoJsp);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {
				
				request.setAttribute("contas", daoContas.listar());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("contas", daoContas.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {			
			
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String valor = request.getParameter("valor");
			
			try {
				
				String msg = null;
				boolean podeInserir = true;

				if (nome == null || nome.isEmpty()) {
					msg = "Nome da conta deve ser informada";
					podeInserir = false;

				} else if (valor == null || valor.isEmpty()) {
					msg = "valor deve ser informado";
					podeInserir = false;								
				} 
				
				Contas contas = new Contas();
				
				contas.setNome(nome);
				contas.setId(!id.isEmpty() ? Long.parseLong(id) : null);
				

				if (valor != null && !valor.isEmpty()) {
					String valorParse = valor.replaceAll("\\.", ""); // 10.500,20
					valorParse = valorParse.replaceAll("\\,", "."); // 10500.20
					contas.setValor(Double.parseDouble(valorParse));
				}
				if (msg != null) {
					request.setAttribute("msg", msg);
					
				} else if (id == null || id.isEmpty() && podeInserir) {

					daoContas.salvar(contas);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					//daoContas.atualizar(contas);
				}

				if (!podeInserir) {
					request.setAttribute("contas", contas);
				}

				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("contas", daoContas.listar());
				//request.setAttribute("categorias", daoContas.listaCategorias());
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		
	}

}
