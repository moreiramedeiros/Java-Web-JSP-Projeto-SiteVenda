package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import bean.BeanProduto;
import bean.BeanUsuario;
import dao.DaoProduto;

@WebServlet("/ServletProduto")
@MultipartConfig
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletProduto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			DaoProduto daoProduto = new DaoProduto();
			String acao = request.getParameter("acao");
			String id = request.getParameter("id");
			if (acao != null) {
				if (acao.equalsIgnoreCase("editar")) {
					BeanProduto beanProduto = daoProduto.consultar(Long.parseLong(id));

					List<BeanProduto> lista = daoProduto.listar();
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroProduto.jsp");
					request.setAttribute("produto", beanProduto);
					request.setAttribute("produtos", lista);
					rd.forward(request, response);

				} else if (acao.equalsIgnoreCase("excluir")) {
					daoProduto.deletar(Long.parseLong(id));
					List<BeanProduto> lista;
					lista = daoProduto.listar();
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroProduto.jsp");
					request.setAttribute("produtos", lista);
					rd.forward(request, response);
				} else if (acao.equalsIgnoreCase("download")) {

					BeanProduto beanProduto = daoProduto.consultar(Long.parseLong(id));
					String imagem = beanProduto.getImagem();

					if (beanProduto != null) {

						/* Inicio Download */

						// base64data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA/YAAAP2CAYAAABNGAZUAAAAC
                        String [] aux = imagem.split(",");
						String fotobase46 = aux[1];						 
						String contentType = aux[0].replace(";base64", "");
						
						byte[] filebytes = new Base64().decodeBase64(fotobase46);

						response.setHeader("Content-Disposition",
								"attachment;filename=arquivo." + contentType.split("\\/")[1]);

						// Coloca bytes em um objeto para processar
						InputStream is = new ByteArrayInputStream(filebytes);

						// Inicio da resposta ao navegador
						int read = 0;
						byte[] bytes = new byte[1024];
						OutputStream os = response.getOutputStream();

						while ((read = is.read(bytes)) != -1) {
							os.write(bytes, 0, read);
						}

						os.flush();
						os.close();
					}

				}

			} else {

				List<BeanProduto> lista;
				lista = daoProduto.listar();
				RequestDispatcher rd = request.getRequestDispatcher("adminCadastroProduto.jsp");
				request.setAttribute("produtos", lista);
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			String preco = request.getParameter("preco");
			String descricao = request.getParameter("descricao");
			String quantidade = request.getParameter("quantidade");
			String comprimento = request.getParameter("comprimento");
			String largura = request.getParameter("largura");
			String altura = request.getParameter("altura");
			String peso = request.getParameter("peso");
			String imagem = null;

			/* Obtendo arquivo do formulário e salvar */

			if (ServletFileUpload.isMultipartContent(request)) {

				/* Recupera imagem do formulário */
				Part imagemFoto = request.getPart("foto");

				if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
					// Converte a entrada de fluxo de dados para bytes
					byte[] bytesImagem = convertStreamParaByte(imagemFoto.getInputStream());

					// Converte o arquivo byte para base64
					String fotobase64 = new Base64().encodeBase64String(bytesImagem);

					imagem = "data:" + imagemFoto.getContentType() + ";base64," + fotobase64;
				} else {

					if (id != null && !id.isEmpty()) {
						DaoProduto daoProduto = new DaoProduto();
						// Caso não exista arquivo selecionado no formulário, recuperar do banco de
						// dados
						BeanProduto produtoVelho = daoProduto.consultar(Long.parseLong(id));
						imagem = produtoVelho.getImagem();
					}
				}

			}

			BeanProduto beanProduto = new BeanProduto();

			beanProduto.setId((id == null || id.isEmpty()) ? null : Long.parseLong(id));
			beanProduto.setNome(nome);
			beanProduto.setCategoria(categoria);
			beanProduto.setDescricao(descricao);
			beanProduto.setPreco((preco == null || preco.isEmpty()) ? 0 : Double.parseDouble(preco));
			beanProduto.setQuantidade((quantidade == null || quantidade.isEmpty()) ? 0 : Integer.parseInt(quantidade));
			beanProduto.setLargura((largura == null || largura.isEmpty()) ? 0 : Double.parseDouble(largura));
			beanProduto.setAltura((altura == null || altura.isEmpty()) ? 0 : Double.parseDouble(altura));
			beanProduto.setComprimento(
					(comprimento == null || comprimento.isEmpty()) ? 0 : Double.parseDouble(comprimento));
			beanProduto.setPeso((peso == null || peso.isEmpty()) ? 0 : Double.parseDouble(peso));
			beanProduto.setImagem(imagem);
			DaoProduto daoProduto = new DaoProduto();

			/* Salvar Produto */
			if (id == null || id.isEmpty()) {
				daoProduto.salvar(beanProduto);
				List<BeanProduto> lista = daoProduto.listar();
				RequestDispatcher rd = request.getRequestDispatcher("adminCadastroProduto.jsp");
				request.setAttribute("produtos", lista);
				request.setAttribute("msgSucesso", "Produto cadastrado com sucesso");
				rd.forward(request, response);
			} else {
				/* Editar Produto */
				daoProduto.atualizar(beanProduto);
				List<BeanProduto> lista = daoProduto.listar();
				RequestDispatcher rd = request.getRequestDispatcher("adminCadastroProduto.jsp");
				request.setAttribute("produtos", lista);
				request.setAttribute("msgSucesso", "Editado com sucesso");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Converte a entrada de fluxo de dados da imagem em um array de bytes[] */

	private byte[] convertStreamParaByte(InputStream imagem) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();

		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		return baos.toByteArray();
	}

}
