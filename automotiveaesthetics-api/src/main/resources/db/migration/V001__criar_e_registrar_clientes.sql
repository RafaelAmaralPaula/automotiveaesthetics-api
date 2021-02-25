CREATE TABLE tb_cliente(
	codigo BIGINT(20) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	data_nascimento DATE NOT NULL 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO tb_cliente(nome , email , data_nascimento) VALUES('José Da Silva' , 'jose@jose.com'  ,   '1992-02-15' );
INSERT INTO tb_cliente(nome , email , data_nascimento) VALUES('Maria Santos'  , 'maria@maria.com',   '1972-11-01');
INSERT INTO tb_cliente(nome , email , data_nascimento) VALUES('Eduarda Oliveira','eduarda@eduarda.com'  , '1995-01-05');
INSERT INTO tb_cliente(nome , email , data_nascimento) VALUES('João Pedro Nunes' , 'joao@joao.com'  ,   '1782-04-25' );
INSERT INTO tb_cliente(nome , email , data_nascimento) VALUES('Benedita Miranda' , 'beneditae@benedita.com'  , '1888-05-14');
