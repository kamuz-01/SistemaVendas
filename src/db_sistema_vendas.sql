-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 05/04/2025 às 16:56
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_sistema_vendas`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `cnpj_cliente` varchar(255) NOT NULL,
  `razaosocial_cliente` varchar(255) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `cnpj_cliente`, `razaosocial_cliente`, `id_usuario`, `email`) VALUES
(153, '35475475675', 'Materiales Plata LTDA', 1, 'pingopingorocho@gmail.com'),
(154, '453743574', 'Industrias Pedro LTDA', 1, 'pingopingorocho@gmail.com'),
(155, '453743574', 'Industrias Pedro LTDA', 1, 'pingopingorocho@gmail.com'),
(156, '54685685', 'rjrtyjurtyj', 1, 'pingopingorocho@gmail.com'),
(157, '345674567', 'kalwsdfgiko', 1, 'pingopingorocho@gmail.com'),
(158, '34576345734', 'ertertuert', 1, 'pingopingorocho@gmail.com'),
(159, '34576345734', 'ertertuert', 1, 'pingopingorocho@gmail.com'),
(160, '2222222222', 'papeldata', 1, 'pingopingorocho@gmail.com'),
(161, '2346347678', 'PepeLTDA', 1, 'pingopingorocho@gmail.com'),
(162, '5678585468', 'papeldata', 1, 'pingopingorocho@gmail.com'),
(163, '67364578456', 'Papelaria', 1, 'kamuz01@yahoo.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

CREATE TABLE `produto` (
  `id_produto` int(11) NOT NULL,
  `sku_produto` varchar(255) NOT NULL,
  `descricao_produto` varchar(255) NOT NULL,
  `quantidade_produto` int(11) NOT NULL,
  `preco_produto` decimal(7,2) NOT NULL,
  `modo_pagamento` enum('Dinheiro','Cartão de Crédito','Pix') NOT NULL,
  `quantidade_parcelas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `produto`
--

INSERT INTO `produto` (`id_produto`, `sku_produto`, `descricao_produto`, `quantidade_produto`, `preco_produto`, `modo_pagamento`, `quantidade_parcelas`) VALUES
(1, 'MB-01', 'Maçã Boa', 100, 5.00, 'Dinheiro', 12),
(2, 'MB-02', 'Maçã Média', 80, 2.50, 'Dinheiro', 12),
(3, 'MB-03', 'Maçã Barata', 80, 1.50, 'Dinheiro', 12),
(4, 'BN-01', 'Banana Muito boa', 80, 2.90, 'Dinheiro', 12),
(5, 'BN-02', 'Banana Boa', 80, 2.00, 'Dinheiro', 12),
(6, 'BN-05', 'Banana Barata', 80, 6.00, 'Pix', 12),
(8, 'BN-03', 'Banana Delicia', 90, 1.00, 'Dinheiro', 13),
(9, 'BN-04', 'Banana Rich', 780, 0.43, 'Cartão de Crédito', 12),
(10, 'BN-06', 'Banana Brasil', 70, 4.00, 'Cartão de Crédito', 4);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `cpf_usuario` varchar(255) NOT NULL,
  `nome_usuario` varchar(100) NOT NULL,
  `sobrenome_usuario` varchar(100) NOT NULL,
  `data_nascimento` varchar(10) NOT NULL,
  `nivel_acesso` enum('Atendente','Administrador') NOT NULL,
  `email` varchar(255) NOT NULL,
  `celular` varchar(255) NOT NULL,
  `nome_login` varchar(50) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `cpf_usuario`, `nome_usuario`, `sobrenome_usuario`, `data_nascimento`, `nivel_acesso`, `email`, `celular`, `nome_login`, `senha`) VALUES
(1, '12345678900', 'Pepe', 'Trueno', '1990-12-30', 'Administrador', 'pepe@yahoo.com', '23454123256', 'pepe', '$2a$10$nHUb2gG6F.Y19I7rqmIdz.kp91CvAVR538f51leoocgiQfH/mdT52'),
(2, '98712345678', 'João', 'Pessoa', '1940-04-18', 'Atendente', 'joaopessoa@gmail.com', '12567543212', 'joao', '$2a$10$904ieLpA8Y53QMImKYIeuuWRTV5EDDVb9c0tjF.8ylK8mW3pi.EpS');

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

CREATE TABLE `venda` (
  `id_venda` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_produto` int(11) DEFAULT NULL,
  `preco_venda` double NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `data_venda` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `venda`
--

INSERT INTO `venda` (`id_venda`, `id_cliente`, `id_produto`, `preco_venda`, `quantidade`, `data_venda`, `id_usuario`) VALUES
(82, 159, 9, 18, 9, '2025-03-30 23:30:24', 1),
(83, 160, 9, 4, 2, '2025-03-30 23:30:47', 1),
(84, 161, 1, 130, 40, '2025-03-30 23:31:42', 1),
(85, 162, 9, 20, 10, '2025-03-30 23:44:45', 1),
(86, 163, 10, 70, 10, '2025-03-31 21:42:24', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `vendas_itens`
--

CREATE TABLE `vendas_itens` (
  `id_venda_item` int(11) NOT NULL,
  `id_venda` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `quantidade_Itens` int(11) NOT NULL,
  `preco_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `vendas_itens`
--

INSERT INTO `vendas_itens` (`id_venda_item`, `id_venda`, `id_produto`, `quantidade_Itens`, `preco_unitario`, `subtotal`) VALUES
(91, 82, 9, 9, 2.00, 18.00),
(92, 83, 9, 2, 2.00, 4.00),
(93, 84, 1, 10, 5.00, 50.00),
(94, 84, 2, 10, 2.50, 25.00),
(95, 84, 3, 10, 1.50, 15.00),
(96, 84, 4, 10, 4.00, 40.00),
(97, 85, 9, 10, 2.00, 20.00),
(98, 86, 10, 10, 7.00, 70.00);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `fk_cliente_usuario` (`id_usuario`);

--
-- Índices de tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id_produto`),
  ADD UNIQUE KEY `sku_produto` (`sku_produto`);

--
-- Índices de tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`),
  ADD UNIQUE KEY `UKh1mvtqgunowkllo2v0mlbw233` (`celular`),
  ADD UNIQUE KEY `UK6cmkkxhcdxp1tjprbvafj3upt` (`cpf_usuario`);

--
-- Índices de tabela `venda`
--
ALTER TABLE `venda`
  ADD PRIMARY KEY (`id_venda`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_produto` (`id_produto`),
  ADD KEY `fk_venda_usuario` (`id_usuario`);

--
-- Índices de tabela `vendas_itens`
--
ALTER TABLE `vendas_itens`
  ADD PRIMARY KEY (`id_venda_item`),
  ADD KEY `id_venda` (`id_venda`),
  ADD KEY `id_produto` (`id_produto`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=164;

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `venda`
--
ALTER TABLE `venda`
  MODIFY `id_venda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT de tabela `vendas_itens`
--
ALTER TABLE `vendas_itens`
  MODIFY `id_venda_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Restrições para tabelas `venda`
--
ALTER TABLE `venda`
  ADD CONSTRAINT `fk_cliente_venda` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_produto_venda` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_usuario_venda` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Restrições para tabelas `vendas_itens`
--
ALTER TABLE `vendas_itens`
  ADD CONSTRAINT `vendas_itens_ibfk_1` FOREIGN KEY (`id_venda`) REFERENCES `venda` (`id_venda`) ON DELETE CASCADE,
  ADD CONSTRAINT `vendas_itens_ibfk_2` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
