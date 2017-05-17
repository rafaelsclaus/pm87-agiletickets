package br.com.caelum.agiletickets;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.caelum.agiletickets.models.Sessao;
import junit.framework.Assert;

public class FirstTest {

	@Test
	public void hello() {
		assertTrue(true);
	}
	
	@Test
	public void deveVender2IngressosSeHa2Vagas(){
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(2);
		
		Assert.assertTrue(sessao.podeReservar(2));
	}
	
}
