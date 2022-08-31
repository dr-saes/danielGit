package br.com.danielsaes.api_receitas_despesas.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.danielsaes.api_receitas_despesas.enums.TipoDespesa;
import br.com.danielsaes.api_receitas_despesas.modelo.Despesa;
import br.com.danielsaes.api_receitas_despesas.repository.DespesaRepository;

public class AtualizacaoDespesaForm {
	
	@NotBlank
	@NotBlank
	@NotNull
	private String descricao;

	@NotBlank
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
	@NotNull
	private String valorDespesa;

	@NotNull
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$")
	@NotBlank
	private String dataDespesa;
	
	private TipoDespesa tipoDespesa;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt-br"));
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValorDespesa() {
		return valorDespesa;
	}

	public void setValorReceita(String valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public String getDataDespesa() {
		return dataDespesa;
	}

	public void setDataReceita(String dataDespesa) {
		this.dataDespesa = dataDespesa;
	}
	
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public Optional<Despesa> atualizar(Long id, DespesaRepository despesaRepository) {
		Optional<Despesa> despesa = despesaRepository.findById(id);
		despesa.get().setDescricao(this.descricao);
		despesa.get().setValorDespesa(new BigDecimal(this.valorDespesa));
		despesa.get().setDataDespesa(LocalDate.parse(this.dataDespesa, formatter));
		despesa.get().setMesDespesa(LocalDate.parse(this.dataDespesa, formatter).getMonthValue());
		despesa.get().setTipoDespesa(this.tipoDespesa);
		despesa.get().setAnoDespesa(LocalDate.parse(this.dataDespesa, formatter).getYear());
		
		if(this.tipoDespesa == null) {
			despesa.get().setTipoDespesa(TipoDespesa.OUTRAS);
		}
		return despesa;
	}
	

}
