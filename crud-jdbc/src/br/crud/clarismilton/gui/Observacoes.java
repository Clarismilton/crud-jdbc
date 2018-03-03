package br.crud.clarismilton.gui;

public @interface Observacoes {

	/* Necessita ter o MySQL Instalado, ter criado um banco de dados com o nome "sign"
	 * Uma tabela com o nome "pessoas"
	 * 
	 * Com os seguintes atributos:
	 * 
	 * 
	 * private int id;
	 * private String nome;
	 * private String profissao;
	 * 
	 * Necessita baixar o drive de conex�o com o banco de dados: "mysql-connector-java-5.1.44.jar"
	 * 
	 * Instalar ele no eclipse:
	 * Bot�o direito no projeto ==> Build Path ==> Configure Build Path ==> Libraries ==> Add External JARs.
	 * 
	 * Script para cria��o da tabela no banco de dados:
	 * 
	 * CREATE TABLE `pessoa` (PRIMARY KEY (`id`), `id` INT NOT NULL AUTO_INCREMENT,`nome` VARCHAR(50) NOT NULL,`profissao` VARCHAR(30) NOT NULL);
	 *  
	 * Necessita do plug in "WindowBuilder" instalado no Eclipse.
	 * 
	*/

}
