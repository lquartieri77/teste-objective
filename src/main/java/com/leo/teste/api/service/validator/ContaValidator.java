package com.leo.teste.api.service.validator;

import com.leo.teste.api.dto.ContaDTO;
import com.leo.teste.api.exception.ContaJaExisteException;
import com.leo.teste.api.exception.TransacaoNaoRealizadaException;
import com.leo.teste.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ContaValidator {

    @Autowired
    private ContaRepository contaRepository;

    /**
     * A ideia aqui eh validar se houve algum valor na entrada, caso nao tehna sido informado, atribuimos um valor inicial
     * @param conta
     */
    public void validarSaldoNaCriacaoDaConta(ContaDTO conta){
        if(contaExisteEfoiInformadoSaldo(conta)){
            conta.setSaldo(BigDecimal.ZERO);
        }

    }

    private boolean contaExisteEfoiInformadoSaldo(ContaDTO conta) {

        return conta != null && conta.getSaldo() == null;
    }

    /**
     * A ideia aqui eh verificar se o saldo permite a operacao, caso contrario levanta uma excessao e interrompe o fluxo
     * @param valorOperacao
     * @param conta
     * @throws TransacaoNaoRealizadaException
     */
    public void permiteOperacaoNoSaldo(BigDecimal valorOperacao, ContaDTO conta) throws TransacaoNaoRealizadaException {
        if(valorOperacao!=null && conta!=null && conta.getSaldo()!=null){
            int resultado = valorOperacao.compareTo(conta.getSaldo());
            if (resultado > 0) {
                throw new TransacaoNaoRealizadaException("Operacao não realizada - Valor superior ao disponivel no saldo da conta.");

            }

            return;
        }

        throw new TransacaoNaoRealizadaException("Operacao não realizada - valores nao informados");

    }

    public void validarSeContaJaEstaCadastrada(ContaDTO contaDTO) {
        if(contaRepository.findByNumeroConta(contaDTO.getNumeroConta())!=null){
            throw new ContaJaExisteException("Número de conta já cadastrado");
        }
    }

}
