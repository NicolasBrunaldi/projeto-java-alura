<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<tags:pageTemplate
	titulo="Livros de Java, Android, iPhone, Ruby, PHP e muito mais ....">

	<section id="index-section" class="container middle">
		<h1>Cadastro de Usuário</h1>
		<form:form action="${s:mvcUrl('UC#cadastraUsuario').build()}"
			method="post" commandName="usuario">
			<div class="form-group">
				<label>Nome:</label>
				<form:input path="nome" cssClass="form-control" />
				<form:errors path="nome" />
			</div>
			<div class="form-group">
				<label>Email:</label>
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
			<div class="form-group">
				<label>senha:</label>
				<form:input path="senha" cssClass="form-control" />
				<form:errors path="senha" />
			</div>
			<button type="submit">Cadastrar</button>
		</form:form>
	</section>

	<footer id="layout-footer">
		<div class="clearfix container">
			<div id="collections-footer">
				<!-- cdc-footer -->
				<p class="footer-title">Coleções de Programação</p>
				<ul class="footer-text-links">
					<li><a href="/collections/livros-de-java">Java</a></li>
					<li><a href="/collections/livros-desenvolvimento-web">Desenvolvimento
							Web</a></li>
					<li><a href="/collections/livros-de-mobile">Mobile</a></li>
					<li><a href="/collections/games">Games</a></li>
					<li><a href="/collections/livros-de-front-end">Front End</a></li>
				</ul>
				<p class="footer-title">Outros Assuntos</p>
				<ul class="footer-text-links">
					<li><a href="/collections/livros-de-agile">Agile</a></li>
					<li><a href="/collections/outros">e outros...</a></li>
				</ul>
			</div>
			<div id="social-footer">
				<!-- books-footer -->
				<p class="footer-title">Links da Casa do Código</p>
				<ul class="footer-text-links">
					<li><a href="http://livros.casadocodigo.com.br" rel="nofollow">Meus
							E-books</a></li>
					<li><a href="/pages/sobre-a-casa-do-codigo">Sobre a Casa
							do Código</a></li>
					<li><a href="/pages/perguntas-frequentes">Perguntas
							Frequentes</a></li>
					<li><a href="https://www.caelum.com.br">Caelum - Ensino e
							Inovação</a></li>
					<li><a href="http://www.codecrushing.com/" rel="nofollow">Code
							Crushing</a></li>
					<li><a
						href="http://www.casadocodigo.com.br/pages/politica-de-privacidade"
						rel="nofollow">Política de Privacidade</a></li>
				</ul>
				<p class="footer-title">Redes Sociais</p>
				<ul>
					<li class="social-links"><a
						href="http://www.twitter.com/casadocodigo" target="_blank"
						id="twitter" rel="nofollow">Facebook</a> <a
						href="http://www.facebook.com/casadocodigo" target="_blank"
						id="facebook" rel="nofollow">Twitter</a></li>
				</ul>
			</div>
			<div id="newsletter-footer">
				<!-- social-footer -->
				<p class="footer-title">Receba as Novidades e Lançamentos</p>
				<div id="form-newsletter">
					<form action="" method="POST" id="ss-form" class="form-newsletter">
						<ul>
							<li><input type="hidden" name="pageNumber" value="0" /><input
								type="hidden" name="backupCache" value="" /><input type="email"
								name="entry.0.single" value="" class="ss-q-short" id="entry_0"
								placeholder="seu@email.com" /></li>
							<li><input type="submit" name="submit"
								value="Quero Receber!" id="submit-newsletter" /></li>
						</ul>
					</form>
					<ul>
						<li class="ie8"><a href="" rel="nofollow">Receba as
								Novidades e Lançamentos</a></li>
					</ul>
				</div>
				<ul class="footer-payments">
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
	</footer>

</tags:pageTemplate>
