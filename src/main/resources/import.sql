insert into tbl_especie (especieid, descricao) values (1, 'AVES');
insert into tbl_especie (especieid, descricao) values (2, 'MAMÍFEROS');
insert into tbl_especie (especieid, descricao) values (3, 'PEIXES');
insert into tbl_especie (especieid, descricao) values (4, 'RÉPTEIS');

insert into tbl_raca (racaid, descricao, especieid) values (1, ' Arara-azul-grande', 1);
insert into tbl_raca (racaid, descricao, especieid) values (2, ' Tuiuiús', 1);
insert into tbl_raca (racaid, descricao, especieid) values (3, ' Garça-branca', 1);
insert into tbl_raca (racaid, descricao, especieid) values (4, ' Tucano', 1);

insert into tbl_raca (racaid, descricao, especieid) values (5, ' Capivara', 2);
insert into tbl_raca (racaid, descricao, especieid) values (6, ' Lobinho', 2);
insert into tbl_raca (racaid, descricao, especieid) values (7, ' Macaco-prego', 2);
insert into tbl_raca (racaid, descricao, especieid) values (8, ' Tamanduá', 2);

insert into tbl_raca (racaid, descricao, especieid) values (9, ' Piranha', 3);
insert into tbl_raca (racaid, descricao, especieid) values (10, ' Pacu', 3);
insert into tbl_raca (racaid, descricao, especieid) values (11, ' Dourado', 3);
insert into tbl_raca (racaid, descricao, especieid) values (12, ' Curimbatã', 3);

insert into tbl_raca (racaid, descricao, especieid) values (13, ' Jacaré-do-pantanal', 4);
insert into tbl_raca (racaid, descricao, especieid) values (14, ' Sucuri', 4);
insert into tbl_raca (racaid, descricao, especieid) values (15, ' Iguana', 4);
insert into tbl_raca (racaid, descricao, especieid) values (16, ' Cágado', 4);

insert into tbl_pelagem (pelagemid, descricao) values (1, 'Pêlo Longo');
insert into tbl_pelagem (pelagemid, descricao) values (2, 'Alazão');
insert into tbl_pelagem (pelagemid, descricao) values (3, 'Branco albino');
insert into tbl_pelagem (pelagemid, descricao) values (4, 'Gateada');
insert into tbl_pelagem (pelagemid, descricao) values (5, 'Moura');
insert into tbl_pelagem (pelagemid, descricao) values (6, 'Rosilha');

insert into tbl_patologia (patologiaid, nome, ativo, descricao) values (1, 'Alergia alimentar', true, 'O que é: uma resposta imunológica exagerada do organismo a determinada substância presente em alimentos.');
insert into tbl_patologia (patologiaid, nome, ativo, descricao) values (2, 'Depressão', true, 'O que é: ainda faltam trabalhos que expliquem exatamente o que acontece no cérebro dos animais melancólicos, mas alguns apresentam um distúrbio muito parecido com a depressão dos seres humanos.');
insert into tbl_patologia (patologiaid, nome, ativo, descricao) values (3, 'Erlichiose (doença do carrapato)', true, 'O que é: uma infecção gravíssima transmitida por carrapatos portadores de bactérias do gênero erlichia.');
insert into tbl_patologia (patologiaid, nome, ativo, descricao) values (4, 'Insuficiência renal', true, 'O que é: alteração na capacidade de filtragem dos rins, o que acarreta a retenção de ureia e creatinina');

insert into tbl_tipo_atendimento (tipodeatendimentoid, custo, duracao, frequencia, modeloatendimento, nome, status) 
	values (1, 10.00, 3600000000000, 'P1M', 'Nossa equipe está sempre de prontidão para atender as situações mais críticas que possam surgir com os pets.', 'Atendimento Emergencial', true);
insert into tbl_tipo_atendimento (tipodeatendimentoid, custo, duracao, frequencia, modeloatendimento, nome, status) 
	values (2, 150.00, 18000000000000, 'P1D', 'Aqui no VetPet você e seu animalzinho poderão ficar juntos em um ambiente próprio e confortável enquanto ele recebe as medicações necessárias e também toma soro (fluidoterapia) quando necessário.', 'Fluidoterapia', true);
insert into tbl_tipo_atendimento (tipodeatendimentoid, custo, duracao, frequencia, modeloatendimento, nome, status) 
	values (3, 95.00, 1800000000000, 'P14D', 'Aqui no VetPet seu animalzinho ainda conta com leitos de internação intensiva, que são destinados à hospitalização daqueles pacientes em estado mais grave, que precisam de monitorização, acompanhamento e cuidados intensivos, que muitas vezes necessitam de medicações mais fortes, oxigenioterapia, entre outras condições mais intensivas.', 'Internação Intensiva', true);
insert into tbl_tipo_atendimento (tipodeatendimentoid, custo, duracao, frequencia, modeloatendimento, nome, status) 
	values (4, 295.00, 18000000000000, 'P15D', 'Nosso setor de internação é capacitado para receber todos os pacientes que precisam de cuidados mais intensivos. ', 'Internação Semi-Intensiva', true);
	
insert into tbl_vacina (vacinaid, grupo, laboratorio, nome, preco, status) values (1, 'Cães', 'Vencofarma', 'Hepatite', 110.00, true);
insert into tbl_vacina (vacinaid, grupo, laboratorio, nome, preco, status) values (2, 'Pecuária', 'Vaxxinova', 'Leptospirose', 40.00, true);
insert into tbl_vacina (vacinaid, grupo, laboratorio, nome, preco, status) values (3, 'Suinocultura', 'Biovet', 'Carbun-Vet Polivalente', 95.00, true);

insert into tbl_pessoa (pessoaid, celular, email, telefone, cpf, bairro, cep, cidade, complemento, estado, numero, rua, inclusao, nacionalidade, nascimento, nome, rg, sexo, tipopessoa)
	values (1, '(11)9436-15412', 'renan.rodrigues@metasix.com.br', '(11)4642-9480', '365.898.178-47', 'Jardim Miray', '08574-400', 'Itaquaquecetuba', 'Casa 2', 'SP', 6, 'Rua das Uniões', '2018-07-28', 'Brasil', '1997-06-18', 'Renan Fernandes Rodrigues', '55.856.174-3', 'M', 'FISICA');
	
insert into tbl_cliente (aceitanotificacoes, ativo, comonosconheceu, observacoes, profissao, pessoaid) values (true, true, 'Realizando testes', 'Nada a declarar', 'Aeronauta', 1);

insert into tbl_animal (animalid, dtnascimento, esteril, imagem, nome, peso, status, pelagemid, proprietarioid, racaid) 
	values (1, '2018-07-28', true, 'http://localhost:9444/s3/vetweb/ninja.jpg?noAuth=true', 'Roni', 59, false, 1, 1, 8);

insert into tbl_prontuario (prontuarioid, animalid) values (1, 1);