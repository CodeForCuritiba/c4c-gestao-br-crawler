package org.openbrazil.crawler.plugin
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
  * Pronim plugin.
  */
case class PronimPlugin() extends Plugin {

  implicit val host = "portal.marmeleiro.pr.gov.br"
  implicit val uri = "/pronimtb/index.asp?acao=1&item=2"
  implicit val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  val toDate = LocalDateTime.now()
  val fromDate = toDate.minusDays(7)
  val year = toDate.getYear

  def urlEncodedForm = s"""hndAcao=1
    |&hndItem=2
    |&hndvisao=1
    |&hndAPDtIni=
    |&hndAPLotacao=
    |&hndAPCargo=
    |&hndAPNivel=
    |&hndTipoEsportacaoDados=
    |&cmbTipoEsportacaoDados=2
    |&cmbUnidadeLC=DW_LC131_AG_0
    |&txtNomePublicacoes=
    |&cmbTemaPublicacoes=
    |&hndAnoCargasPublicacoes=
    |&cmbAnoCargasPublicacoes=
    |&hndPeriodoPublicacao=
    |&txtReferenciaDePublicacoes=
    |&txtReferenciaAtePublicacoes=
    |&cmbReferenciaDePublicacoes=
    |&cmbReferenciaAtePublicacoes=
    |&hndEntidadePublicacoes=
    |&cmbEntidadePublicacoes=
    |&cmbUnidadeGestoraPublicacoes=
    |&hndOrdenacao=
    |&cmbOrdenacao=0
    |&hndCriterioOrdenacao=
    |&hndDataVigenciaLC=
    |&cmbDataVigenciaLC=$year
    |&hndAno=
    |&hndExercicio=
    |&hndTipoMovimento=
    |&cmbTipoMovimento=0
    |&hndUnidadeGestoraLC=CONSOLIDADA
    |&cmbUnidadeGestoraLC=-1
    |&hndUnidadeCP=SELECIONE
    |&cmbUnidadeCP=
    |&txtNumeroContrato=
    |&txtAnoContrato=
    |&hndSitProcessoLicit=
    |&cmbSitProcessoLicit=Aberta
    |&txtNumeroProcesso=
    |&txtAnoProcesso=
    |&hndDataFiltro=DATA+DE+EXPEDI%C7%C3O
    |&cmbDataFiltro=1
    |&txtDataInicial=${dateEncode(fromDate)}
    |&txtDataFinal=${dateEncode(toDate)}
    |&hndMesInicial=SELECIONE
    |&cmbMesInicial=
    |&hndMesFinal=SELECIONE
    |&cmbMesFinal=
    |&hndUnidadeGP=
    |&hndVinculoGP=SELECIONE
    |&cmbVinculoGP=
    |&cmbDataGP=
    |&hndUnidadeGestora=SELECIONE
    |&cmbUnidadeGestora=
    |&txtNomeFuncionario=
    |&txtCargoFuncionario=
    |&txtLotacaoFuncionario=
    |&hndApresentarPorCP=
    |&cmbApresentarPor=0
    |&cmbEstoqueUnidadeCM=
    |&hndEstoqueDataVigenciaLC=SELECIONE
    |&cmbEstoqueDataVigenciaLC=
    |&hndEstoqueUnidadeGestoraLC=%0D%0A++++++++++++++++++++++++++++++++++++SELECIONE%0D%0A++++++++++++++++++++++++++++++++
    |&cmbEstoqueUnidadeGestoraLC=
    |&hndEstoqueAlmoxarifado=%0D%0A++++++++++++++++++++++++++++++++++++SELECIONE%0D%0A++++++++++++++++++++++++++++++++
    |&cmbEstoqueAlmoxarifado=
    |&txtEstoqueLocalizacao=
    |&txtEstoqueMaterial=
    |&hndEstoqueMesInicial=SELECIONE
    |&cmbEstoqueMesInicial=
    |&hndEstoqueMesFinal=SELECIONE
    |&cmbEstoqueMesFinal=
    |&hndEstoqueTipoMovimento=SELECIONE
    |&cmbEstoqueTipoMovimento=-1
    |&txtEstoqueClassificacao=
    |&cmbPatrimonioUnidadePP=
    |&hndPatrimonioUnidadeGestoraLC=%0D%0A++++++++++++++++++++++++++++++++++++SELECIONE%0D%0A++++++++++++++++++++++++++++++++
    |&cmbPatrimonioUnidadeGestoraLC=
    |&txtPatrimonioDescricaoBem=
    |&txtPatrimonioDataInicial=
    |&txtPatrimonioDataFinal=
    |&txtPatrimonioClassificacao=
    |&txtPatrimonioLocalizacao=
    |&hndPatrimonioSituacaoBem=SELECIONE
    |&cmbPatrimonioSituacaoBem=-1
    |&hndPatrimonioStatus=SELECIONE
    |&cmbPatrimonioStatus=-1
    |&hndPatrimonioTipoIngresso=
    |&cmbPatrimonioTipoIngresso=
    |&cmbFrotasUnidadeAF=
    |&hndFrotasUnidadeGestora=%0D%0A++++++++++++++++++++++++++++++++++++SELECIONE%0D%0A++++++++++++++++++++++++++++++++
    |&cmbFrotasUnidadeGestora=
    |&hndFrotasTipoVeiculo=SELECIONE
    |&cmbFrotasTipoVeiculo=
    |&txtFrotasDescricao=
    |&txtFrotasDataInicial=
    |&txtFrotasDataFinal=
    |&txtFrotasLocalizacao=
    |&txtFrotasPlaca=
    |&txtFrotasAnoFabricacao=
    |&hndFrotasSituacaoVeiculo=SELECIONE
    |&cmbFrotasSituacaoVeiculo=-1
    |&hndApresentarPorGP=SELECIONE
    |&hndApresentar=
    |&ckTipoGestaoPessoas=-1
    |&txtLotacaoCargo=
    |&ckEmpenhoOrcamentario=1
    |&txtNomeFornecedor=
    |&txtCPFCNPJFornecedor=
    |&hndDiariasPassagens=SELECIONE
    |&cmbDiariasPassagens=
    |&txtCargoBenificiario=
    |&hndTipoTransferencia=SELECIONE
    |&cmbTipoTransferencia=
    |&hndOrigemRecurso=SELECIONE
    |&cmbOrigemRecurso=-1
    |&txtEmpenho=
    |&hndEstado=
    |&cmbEstado=-1
    |&hndMunicipio=
    |&cmbMunicipio=-1
    |&txtValorContratoInicial=
    |&txtValorContratoFinal=
    |&txtObjeto=
    |&ckTipoAlienacaoBens=512
    |&ckTipoModalidadeConvite=4
    |&ckTipoLicitacaoCompra=1
    |&ckTipoModalidadeConcorrencia=1
    |&ckTipoLicitacaoConcessao=2
    |&ckTipoModalidadeConcurso=2
    |&ckTipoLicitacaoTrabalhoArtistico=256
    |&ckTipoModalidadeDispensavel=8
    |&ckTipoLicitacaoTrabalhoCientifico=128
    |&ckTipoModalidadeInexigivel=16
    |&ckTipoLicitacaoTrabalhoTecnico=64
    |&ckTipoModalidadeLeilao=32
    |&ckTipoLicitacaoObra=8
    |&ckTipoModalidadePregaoPresencial=192
    |&ckTipoLicitacaoServico=16
    |&ckTipoModalidadeTomadaPreco=256
    |&ckTipoLicitacaoServicoEngenharia=32
    |&ckTipoLicitacaoPermissao=2048
    |&ckTipoLicitacaoCredenciamento=1024
    |&ckTipoLicitacaoLocacao=4096
    |&txtDescricaoProduto=
    |&hndCategoria=
    |&confirma=Geral
    |""".stripMargin

}
