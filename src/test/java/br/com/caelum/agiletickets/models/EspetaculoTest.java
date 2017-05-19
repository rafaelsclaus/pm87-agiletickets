package br.com.caelum.agiletickets.models;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void DeveCadastrarApenasUmaSessaoSeForDiario(){
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(new LocalDate(), new LocalDate(), new LocalTime(), Periodicidade.DIARIA);
		
		assertEquals(1,sessoes.size());
	}
	
	@Test
	public void DeveCadastrarUmaSessaoPorDiaSeForDiarioETiverUmIntervaloDeQuatroDiasEntreDuasDatas(){
		
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(3);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.DIARIA);
		
		assertEquals(4, sessoes.size());
		
	}
	
	@Test
	public void DeveCadastrarUmaSessaoSemanalSeIntervaloEntreDatasNaoForMaiorQueSeteDias() {
		Espetaculo espetaculo = new Espetaculo();

		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(5);

		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.SEMANAL);

		assertEquals(1, sessoes.size());

	}
	
	@Test
	public void DeveCadastrarUmaSessaoACadaSemanaSeForSemanalETiverIntervaloDeQuatroSemanasEntreDuasDatas(){
		Espetaculo espetaculo = new Espetaculo();

		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusWeeks(3);

		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.SEMANAL);

		assertEquals(4, sessoes.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void NaoDeveAdicionarUmaSessaoDiariaSeADataFinalForMenorQueADataInicial(){
		Espetaculo espetaculo = new Espetaculo();

		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().minusDays(3);

		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.DIARIA);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void NaoDeveAdicionarUmaSessaoSemanalSeADataFinalForMenorQueADataInicial(){
		Espetaculo espetaculo = new Espetaculo();

		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().minusWeeks(3);

		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, new LocalTime(), Periodicidade.DIARIA);

	}
}
