package com.leo.teste.api.domain;

import com.leo.teste.api.enums.TipoTransacaoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_transacao")
    private TipoTransacaoEnum tipoDeTransacao;

    private BigDecimal valor;

}
