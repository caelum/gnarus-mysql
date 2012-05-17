package infra

object BaseSql {
  def apply() = """CREATE TABLE IF NOT EXISTS COMPRAS (
		  ID int NOT NULL AUTO_INCREMENT,
		  VALOR decimal(10,2),
		  DATA datetime,
		  OBSERVACOES text,
		  RECEBIDO tinyint(1),
		  PRIMARY KEY (ID)
		);
		create table CONTAS (ID int not null auto_increment primary key, OBSERVACOES text);		    
		insert into compras(valor,data,observacoes,recebido) values(300,'2010-05-12','observacoes',1); 
		insert into compras(valor,data,observacoes,recebido) values(400,'2010-05-14','observacoes',1);
        insert into compras(valor,data,observacoes,recebido) values(100,'2010-05-13','observacoes',1);
		insert into compras(valor,data,observacoes,recebido) values(600,'2010-05-11','observacoes',1);"""
}