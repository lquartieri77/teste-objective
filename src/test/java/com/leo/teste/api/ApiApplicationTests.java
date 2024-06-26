package com.leo.teste.api;

import com.leo.teste.api.domain.Conta;
import com.leo.teste.api.domain.Transacao;
import com.leo.teste.api.dto.ContaDTO;
import com.leo.teste.api.dto.TransacaoDTO;
import com.leo.teste.api.exception.ContaNotFoundException;
import com.leo.teste.api.exception.TransacaoNaoRealizadaException;
import com.leo.teste.api.repository.ContaRepository;
import com.leo.teste.api.repository.TransacaoRepository;
import com.leo.teste.api.service.ContaServiceImpl;
import com.leo.teste.api.service.TransacaoServiceImpl;
import com.leo.teste.api.service.validator.ContaValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ApiApplicationTests {


	ContaRepository contaRepository = mock(ContaRepository.class);

	@InjectMocks
	private ContaServiceImpl contaService;

	@Mock
	private ContaValidator contaValidator;

	@Mock
	private TransacaoRepository transacaoRepository;

	private TransacaoDTO transacaoDTO;

	@Test
	public void testCriarTransacaoValorPermitido() {
		Conta contaSimulada = new Conta();
		contaSimulada.setId(1L);
		contaSimulada.setNumeroConta(12345);
		contaSimulada.setSaldo(BigDecimal.valueOf(500.0));

		transacaoDTO = new TransacaoDTO(
				null,
				12345,
				'D',
				null,
				null,
				BigDecimal.valueOf(100.0),
				null
		);

		ContaValidator contaValidatorMock = mock(ContaValidator.class);
		TransacaoServiceImpl transacaoService = new TransacaoServiceImpl(contaRepository, contaValidatorMock, transacaoRepository );

		when(contaRepository.findByNumeroConta(12345)).thenReturn(contaSimulada);
		when(contaRepository.findByNumeroConta(12345)).thenReturn(contaSimulada);
		when(transacaoRepository.save(any())).thenReturn(new Transacao());
		transacaoDTO.setNumeroConta(contaSimulada.getNumeroConta());
		TransacaoDTO resultado = transacaoService.criarTransacao(transacaoDTO);
		BigDecimal saldoEsperado = BigDecimal.valueOf(400.0);
		assertEquals(saldoEsperado, contaSimulada.getSaldo());
		assertEquals('D', resultado.getTipoTransacaoChar());
		verify(contaRepository, times(1)).findByNumeroConta(12345);

		verify(transacaoRepository, times(1)).save(any());
	}

	@Test
	public void testCriarTransacaoValorNaoPermitido() {
		ContaValidator contaValidatorMock = mock(ContaValidator.class);
		doThrow(new TransacaoNaoRealizadaException("Operacao não realizada - Valor superior ao disponivel no saldo da conta."))
				.when(contaValidatorMock).permiteOperacaoNoSaldo(any(), any());

		TransacaoServiceImpl transacaoService = new TransacaoServiceImpl(contaRepository, contaValidatorMock, transacaoRepository );
		ContaDTO c = new ContaDTO(
				1L,
				12345,
				BigDecimal.valueOf(500.0)
		);

		assertThrows(TransacaoNaoRealizadaException.class,
				() -> contaValidatorMock.permiteOperacaoNoSaldo(BigDecimal.valueOf(1000.0), c));
	}

	@Test(expected = ContaNotFoundException.class)
	public void testGetContaById_ContaNotFound() {
		when(contaRepository.findById(1L)).thenReturn(Optional.empty());

		contaService.getContaById(1L);
	}

	@Test
	public void testCreateConta_ValidInput() {
		ContaDTO contaDTO = new ContaDTO(1L, 12345, BigDecimal.valueOf(1000.0));

		// Simule o comportamento do método save
		Conta contaSalva = new Conta();
		contaSalva.setId(1L);
		contaSalva.setNumeroConta(12345);
		contaSalva.setSaldo(BigDecimal.valueOf(1000.0));
		when(contaRepository.save(any())).thenReturn(contaSalva);

		ContaDTO result = contaService.createConta(contaDTO);

		assertNotNull(result); // Verifica se o objeto não é nulo
		assertEquals(contaDTO.getId(), result.getId());
		assertEquals(contaDTO.getNumeroConta(), result.getNumeroConta());
		assertEquals(contaDTO.getSaldo(), result.getSaldo());
		verify(contaValidator, times(1)).validarSeContaJaEstaCadastrada(contaDTO);
		verify(contaValidator, times(1)).validarSaldoNaCriacaoDaConta(contaDTO);
	}


}
